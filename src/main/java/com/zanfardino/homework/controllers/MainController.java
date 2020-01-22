package com.zanfardino.homework.controllers;

import com.zanfardino.homework.exceptions.BadOptionsException;
import com.zanfardino.homework.exceptions.NoDataFoundException;
import com.zanfardino.homework.model.CampaignClicksDO;
import com.zanfardino.homework.model.CampaignDO;
import com.zanfardino.homework.model.CampaignImpressionsDO;
import com.zanfardino.homework.model.CampaignSummaryDO;
import com.zanfardino.homework.model.CampaignViewsDO;
import com.zanfardino.homework.model.CreativeDO;
import com.zanfardino.homework.model.ParametersDO;
import com.zanfardino.homework.model.TotalDO;
import com.zanfardino.homework.util.Utility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Objects;


public class MainController {
    private static final Logger LOG = LogManager.getLogger(MainController.class);
    private static final Utility utility = new Utility();

    private CommandLineController commandLineController;
    private AnalysisController analysisController;
    private JerseyRestController jerseyRestController;

    public MainController(
            final CommandLineController commandLineController,
            final AnalysisController analysisController,
            final JerseyRestController jerseyRestController) {
        this.commandLineController = commandLineController;
        this.analysisController = analysisController;
        this.jerseyRestController = jerseyRestController;
    }

    public ParametersDO getParameters(final String[] args) throws Exception {
        LOG.debug("Processing command line parameters...");

        ParametersDO parameters;
        try {
            parameters = commandLineController.process(args);
        } catch (BadOptionsException e) {
            LOG.error("ERROR: Failed to provide required options: {}", e.getMessage());
            throw e;
        }
        return parameters;
    }

    public void analyzeData(final String url) throws Exception {
        LOG.info("Fetching Ad-Juster Data...");
        final String targetUrl = url + "/api/";
        jerseyRestController.setUrl(targetUrl);

        try {
            final List<CampaignDO> campaignData = jerseyRestController.getCampaigns();
            final List<CreativeDO> creativeData = jerseyRestController.getCreatives();
            analysisController.process(campaignData, creativeData);
        } catch (Exception e) {
            LOG.info("Failed to fetch data from target url ({})", targetUrl);
            LOG.error("ERROR: Failed to communicate with target URL! ", e);
            throw e;
        } finally {
            jerseyRestController.close();
        }
    }

    public void processRequest(final ParametersDO parameters) throws Exception {
        if (parameters.hasCampaigns()) {
            final TotalDO campaignTotal = new TotalDO();
            campaignTotal.setTotal(analysisController.getCampaignCount());
            System.out.println(utility.marshallPOJO(campaignTotal, TotalDO.class));
        }
        if (parameters.hasCreatives()) {
            final TotalDO creativesTotal = new TotalDO();
            final Integer total = analysisController.getCreativesCount();
            creativesTotal.setTotal(total);
            System.out.println(utility.marshallPOJO(creativesTotal, TotalDO.class));
        }
        if (Objects.nonNull(parameters.getClicks())) {
            final Long campaignID = parameters.getClicks();
            final CampaignClicksDO campaignClicks = new CampaignClicksDO();
            final Long clicks = analysisController.getClicksByCampaign(campaignID);
            if (Objects.isNull(clicks)) {
                throw new NoDataFoundException("Cannot return clicks; no Campaign data found for campaign ID: " + campaignID);
            }
            campaignClicks.setId(campaignID);
            campaignClicks.setClicks(clicks);
            System.out.println(utility.marshallPOJO(campaignClicks, CampaignClicksDO.class));
        }
        if (Objects.nonNull(parameters.getImpressions())) {
            final Long campaignID = parameters.getImpressions();
            final CampaignImpressionsDO campaignImpressions = new CampaignImpressionsDO();
            final Long impressions = analysisController.getImpressionsByCampaign(campaignID);
            if (Objects.isNull(impressions)) {
                throw new NoDataFoundException("Cannot return impressions; no Campaign data found for campaign ID: " + campaignID);
            }
            campaignImpressions.setId(campaignID);
            campaignImpressions.setImpressions(impressions);
            System.out.println(utility.marshallPOJO(campaignImpressions, CampaignImpressionsDO.class));
        }
        if (Objects.nonNull(parameters.getViews())) {
            final Long campaignID = parameters.getViews();
            final CampaignViewsDO campaignViews = new CampaignViewsDO();
            final Long views = analysisController.getViewsByCampaign(campaignID);
            if (Objects.isNull(views)) {
                throw new NoDataFoundException("Cannot return vies; no Campaign data found for campaign ID: " + campaignID);
            }
            campaignViews.setId(campaignID);
            campaignViews.setViews(views);
            System.out.println(utility.marshallPOJO(campaignViews, CampaignViewsDO.class));
        }
        if (Objects.nonNull(parameters.getSummary())) {
            final Long campaignID = parameters.getSummary();
            final CampaignSummaryDO summary = analysisController.getCampaignSummaryById(campaignID);
            if (Objects.isNull(summary)) {
                throw new NoDataFoundException("Cannot return summary; no Campaign data found for campaign ID: " + campaignID);
            }
            System.out.println(utility.marshallPOJO(summary, CampaignSummaryDO.class));
        }
        if (parameters.hasReports()) {
            final List<CampaignSummaryDO> report = analysisController.getCampaignSummary();

            System.out.println(utility.marshallPOJO(report, CampaignSummaryDO.class));
        }
    }

}
