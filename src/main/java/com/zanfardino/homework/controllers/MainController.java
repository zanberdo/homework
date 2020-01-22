package com.zanfardino.homework.controllers;

import com.zanfardino.homework.exceptions.BadOptionsException;
import com.zanfardino.homework.model.CampaignDO;
import com.zanfardino.homework.model.CampaignSummaryDO;
import com.zanfardino.homework.model.CreativeDO;
import com.zanfardino.homework.model.ParametersDO;
import com.zanfardino.homework.util.Utility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


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

        ParametersDO parameters = new ParametersDO();
        try {
            parameters = commandLineController.process(args);
        } catch (BadOptionsException e) {
            LOG.error("ERROR: Failed to provide required options: {}", e.getMessage());
//            System.exit(1);
            throw e;
        }
        return parameters;
    }

    public void analyzeData(final ParametersDO parameters) throws Exception {
        LOG.info("Fetching Ad-Juster Data...");

        jerseyRestController.setUrl(parameters.getUrl() + "/api/");

        try {
            final List<CampaignDO> campaignData = jerseyRestController.getCampaigns();
            final List<CreativeDO> creativeData = jerseyRestController.getCreatives();
            analysisController.process(campaignData, creativeData);
        } catch (Exception e) {
            LOG.debug("Failed to fetch data from target url ({})", jerseyRestController.getUrl());
            LOG.error("ERROR: Failed to communicate with target URL ({})! ", e.getMessage());
//            System.exit(2);
            throw e;
        } finally {
            jerseyRestController.close();
        }
    }

    public void processRequest(final ParametersDO parameters) {
        if (parameters.hasCampaigns()) {
            final Total campaignTotal = new Total(analysisController.getCampaignCount());
            System.out.println(utility.marshallPOJO(campaignTotal, Total.class));
        }
        if (parameters.hasCreatives()) {
            final Total creativesTotal = new Total(analysisController.getCreativesCount());
            System.out.println(utility.marshallPOJO(creativesTotal, Total.class));
        }
        if (parameters.getClicks() != null && parameters.getClicks() > 0) {
            final Long campaignID = parameters.getClicks();
            final CampaignClicks campaignClicks = new CampaignClicks(campaignID, analysisController.getClicksByCampaign(campaignID));
            System.out.println(utility.marshallPOJO(campaignClicks, CampaignClicks.class));
        }
        if (parameters.getImpressions() != null && parameters.getImpressions() > 0) {
            final Long campaignID = parameters.getImpressions();
            final CampaignImpressions campaignImpressions = new CampaignImpressions(campaignID, analysisController.getImpressionsByCampaign(campaignID));
            System.out.println(utility.marshallPOJO(campaignImpressions, CampaignImpressions.class));
        }
        if (parameters.getViews() != null && parameters.getViews() > 0) {
            final Long campaignID = parameters.getViews();
            final CampaignViews campaignViews = new CampaignViews(campaignID, analysisController.getViewsByCampaign(campaignID));
            System.out.println(utility.marshallPOJO(campaignViews, CampaignViews.class));
        }
        if (parameters.getSummary() != null && parameters.getSummary() > 0) {
            final Long campaignID = parameters.getSummary();
            final CampaignSummaryDO summary = analysisController.getCampaignSummaryById(campaignID);
            System.out.println(utility.marshallPOJO(summary, CampaignSummaryDO.class));
        }
        if (parameters.hasReports()) {
            final List<CampaignSummaryDO> report = analysisController.getCampaignSummary();
            System.out.println(utility.marshallPOJO(report, CampaignSummaryDO.class));
        }
    }

    private static class Total {
        Integer total;

        public Total(final Integer total) {
            this.total = total;
        }
    }

    private static class CampaignClicks {
        Long id;
        Long clicks;

        public CampaignClicks(final Long id, final Long clicks) {
            this.id = id;
            this.clicks = clicks;
        }
    }

    private static class CampaignImpressions {
        Long id;
        Long impressions;

        public CampaignImpressions(final Long id, final Long impressions) {
            this.id = id;
            this.impressions = impressions;
        }
    }

    private static class CampaignViews {
        Long id;
        Long views;

        public CampaignViews(final Long id, final Long views) {
            this.id = id;
            this.views = views;
        }
    }

}
