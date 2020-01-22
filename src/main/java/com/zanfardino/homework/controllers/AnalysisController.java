package com.zanfardino.homework.controllers;

import com.zanfardino.homework.model.CampaignDO;
import com.zanfardino.homework.model.CampaignSummaryDO;
import com.zanfardino.homework.model.CreativeDO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class AnalysisController {
    private static final Logger LOG = LogManager.getLogger(AnalysisController.class);
    private Map<CampaignDO, Set<CreativeDO>> dataMap;
    private Map<Long, CampaignDO> campaignIdMap;

    public AnalysisController() {
    }

    public void process(final List<CampaignDO> campaigns, final List<CreativeDO> creatives) throws Exception {
        LOG.info("Processing Campaign Data ...");
        dataMap = new HashMap<>();
        campaignIdMap = new HashMap<>();

        if (Objects.isNull(campaigns) || campaigns.isEmpty()) {
            LOG.error("Failed to provide a valid Campaign objects: {}", campaigns);
            throw new InvalidObjectException("Failed to provide a valid Campaign objects. ");
        }
        if (Objects.isNull(creatives) || creatives.isEmpty()) {
            LOG.error("Failed to provide a valid Creatives objects: {}", creatives);
            throw new InvalidObjectException("Failed to provide a valid Creatives objects. ");
        }

        for (CampaignDO campaign : campaigns) {
            campaignIdMap.put(campaign.getId(), campaign);
            final Set<CreativeDO> creativeSet = new HashSet<>();
            dataMap.put(campaign, creativeSet);
            for (CreativeDO creative : creatives) {
                if (creative.getParentId().equals(campaign.getId())) {
                    final Set<CreativeDO> campaignCreativeSet = dataMap.get(campaign);
                    campaignCreativeSet.add(creative);
                    dataMap.put(campaign, campaignCreativeSet);
                }
            }
        }
    }

    public Integer getCampaignCount() {
        LOG.info("Returning total number of campaigns...");
        return dataMap.size();
    }

    public Integer getCreativesCount() {
        LOG.info("Returning total number of creatives processed...");

        int total = 0;
        for (CampaignDO campaign : dataMap.keySet()) {
            total += dataMap.get(campaign).size();
        }
        return total;
    }

    public Long getClicksByCampaign(final Long campaignID) {
        LOG.info("Returning total number of clicks for campaign ID ({}) ...", campaignID);

        Long clicks = 0L;
        final CampaignDO dataMapKey = campaignIdMap.get(campaignID);
        final Set<CreativeDO> creatives = dataMap.get(dataMapKey);

        if (Objects.nonNull(creatives)) {
            for (CreativeDO creative : creatives) {
                clicks += creative.getClicks();
            }
        } else {
            clicks = null;
        }
        return clicks;
    }

    public Long getImpressionsByCampaign(final Long campaignID) {
        LOG.info("Returning total number of impressions for campaign ID ({}) ...", campaignID);

        Long impressions = 0L;
        final CampaignDO dataMapKey = campaignIdMap.get(campaignID);
        final Set<CreativeDO> creatives = dataMap.get(dataMapKey);

        if (Objects.nonNull(creatives)) {
            for (CreativeDO creative : creatives) {
                impressions += creative.getImpressions();
            }
        } else {
            impressions = null;
        }
        return impressions;
    }

    public Long getViewsByCampaign(final Long campaignID) {
        LOG.info("Returning total number of views for campaign ID ({}) ...", campaignID);

        Long views = 0L;
        final CampaignDO dataMapKey = campaignIdMap.get(campaignID);
        final Set<CreativeDO> creatives = dataMap.get(dataMapKey);

        if (Objects.nonNull(creatives)) {
            for (CreativeDO creative : creatives) {
                views += creative.getViews();
            }
        } else {
            views = null;
        }
        return views;
    }

    public Set<CreativeDO> getCreativesByCampaign(final Long campaignID) {
        LOG.info("Returning Creatives for campaign ID ({}) ...", campaignID);

        final CampaignDO dataMapKey = campaignIdMap.get(campaignID);
        return dataMap.get(dataMapKey);
    }

    public CampaignSummaryDO getCampaignSummaryById(final Long campaignID) {
        LOG.debug("Returning Campaign summary report for campaign id ({})", campaignID);

        final CampaignDO dataMapKey = campaignIdMap.get(campaignID);

        final CampaignDO campaign = new CampaignDO();
        campaign.setId(dataMapKey.getId());
        campaign.setAdvertiser(dataMapKey.getAdvertiser());
        campaign.setCpm(dataMapKey.getCpm());
        campaign.setName(dataMapKey.getName());
        campaign.setStartDate(dataMapKey.getStartDate());
        campaign.setEndDate(dataMapKey.getEndDate());

        final CampaignSummaryDO summary = new CampaignSummaryDO(campaign);
        summary.setClicks(getClicksByCampaign(dataMapKey.getId()));
        summary.setImpressions(getImpressionsByCampaign(dataMapKey.getId()));
        summary.setViews(getViewsByCampaign(dataMapKey.getId()));
        summary.setCreatives(getCreativesByCampaign(dataMapKey.getId()));

        return summary;
    }

    public List<CampaignSummaryDO> getCampaignSummary() {
        LOG.debug("Returning full summary Campaign summaries ...");

        final List<CampaignSummaryDO> campaignSummary = new ArrayList<>();
        for (Long campaignID : campaignIdMap.keySet()) {
            campaignSummary.add(getCampaignSummaryById(campaignID));
        }
        return campaignSummary;
    }
}

