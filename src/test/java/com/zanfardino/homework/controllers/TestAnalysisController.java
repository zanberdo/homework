package com.zanfardino.homework.controllers;

import com.zanfardino.homework.model.CampaignDO;
import com.zanfardino.homework.model.CampaignSummaryDO;
import com.zanfardino.homework.model.CreativeDO;
import com.zanfardino.homework.util.Utility;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.InvalidObjectException;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created on 2019-04-25.
 */
public class TestAnalysisController {
    private final static Long CAMPAIGN_ID = 4976875L;

    private static AnalysisController controller;
    private static Utility utility;
    private static List<CampaignDO> campaigns;
    private static List<CreativeDO> creatives;

    @Before
    public void setup() throws Exception {
        System.setProperty("javax.xml.bind.context.factory", "org.eclipse.persistence.jaxb.JAXBContextFactory");

        utility = new Utility();
        controller = new AnalysisController();

        campaigns = utility.unmarshalFile(new File("src/test/resources/campaigns.json"), CampaignDO.class);
        creatives = utility.unmarshalFile(new File("src/test/resources/creatives.json"), CreativeDO.class);
    }

    @Test(expected = InvalidObjectException.class)
    public void testProcessNullCampaigns() throws Exception {
        controller.process(null, creatives);
    }

    @Test(expected = InvalidObjectException.class)
    public void testProcessNullCreatives() throws Exception {
        controller.process(campaigns, null);
    }

    @Test(expected = InvalidObjectException.class)
    public void testProcessNullCampaignsNullCreatives() throws Exception {
        controller.process(null, null);
    }

    @Test
    public void testProcess() throws Exception {
        controller.process(campaigns, creatives);
    }

    @Test
    public void testGetCampaignCount() throws Exception {
        controller.process(campaigns, creatives);

        final Integer actual = controller.getCampaignCount();
        final Integer expected = 199;
        assertEquals("Failed to return expected number of campaigns", expected, actual);
    }

    @Test
    public void testGetCreativesCount() throws Exception {
        controller.process(campaigns, creatives);

        final Integer actual = controller.getCreativesCount();
        final Integer expected = 1630;
        assertEquals("Failed to return expected number of creativess", expected, actual);
    }

    @Test
    public void testGetClicksPerCampaign() throws Exception {
        controller.process(campaigns, creatives);

        final Long expected = 24044L;
        final Long actual = controller.getClicksByCampaign(CAMPAIGN_ID);
        assertEquals("Failed to get expected total number of clicks per campaign", expected, actual);
    }

    @Test
    public void testGetImpressionsPerCampaign() throws Exception {
        controller.process(campaigns, creatives);

        final Long expected = 217592L;
        final Long actual = controller.getImpressionsByCampaign(CAMPAIGN_ID);
        assertEquals("Failed to get expected total impressions for campaign", expected, actual);
    }

    @Test
    public void testGetViewsPerCampaign() throws Exception {
        controller.process(campaigns, creatives);

        final Long expected = 32015L;
        final Long actual = controller.getViewsByCampaign(CAMPAIGN_ID);
        assertEquals("Failed to get expected total views for campaign", expected, actual);
    }

    @Test
    public void testGetCampaignSummaryByCampaign() throws Exception {
        controller.process(campaigns, creatives);

        final CampaignDO campaign = new CampaignDO();
        campaign.setId(CAMPAIGN_ID);
        campaign.setAdvertiser("Telemundo");
        campaign.setName("cow-pox winos");
        campaign.setCpm("$12.00");
        campaign.setStartDate("2018-06-28");
        campaign.setEndDate("2018-06-28");

        final CampaignSummaryDO expected = new CampaignSummaryDO(campaign);
        expected.setClicks(24044L);
        expected.setImpressions(217592L);
        expected.setViews(32015L);
        expected.setCreatives(new HashSet<>(utility.unmarshalFile(new File("src/test/resources/summary.json"), CreativeDO.class)));

        final CampaignSummaryDO actual = controller.getCampaignSummaryById(CAMPAIGN_ID);
        assertEquals("Failed to return expected campaign summary", expected, actual);
    }

    @Test
    public void testGetCampaignSummaryList() throws Exception {
        controller.process(campaigns, creatives);

        final int expectedSize = controller.getCampaignCount();
        final List<CampaignSummaryDO> actual = controller.getCampaignSummary();

        assertEquals("Failed to return expected campaign summary", expectedSize, actual.size());
        // TODO: assert actual list is equal to expected list
    }

}