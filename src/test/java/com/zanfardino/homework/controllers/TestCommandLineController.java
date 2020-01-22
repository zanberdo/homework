package com.zanfardino.homework.controllers;

import com.zanfardino.homework.exceptions.BadOptionsException;
import com.zanfardino.homework.model.ParametersDO;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestCommandLineController {
    private static final String URL = "homework.ad-juster.com/api";
    private static final String CAMPAIGN_ID = "99999";

    private CommandLineController controller;
    private ParametersDO expected;

    @Before
    public void setup() {
        controller = new CommandLineController();
        expected = new ParametersDO();
        expected.setUrl(URL);
    }

    @Test(expected = BadOptionsException.class)
    public void testUrlOptionMissingOptions() {
        final String[] options = {};
        controller.process(options);
    }

    @Test
    public void testHelpOption() {
        final String[] options = {"-h"};
        controller.process(options);
    }

    @Test(expected = BadOptionsException.class)
    public void testUrlOptionMissingTarget() {
        final String[] options = {"--url"};
        controller.process(options);
    }

    @Test(expected = BadOptionsException.class)
    public void testUrlOptionWithTargetNoAdditionalOptions() {
        final String[] options = {"--url", URL};
        controller.process(options);
    }

    @Test
    public void testCampaignsOption() {
        final String[] options = {"--url", URL, "--campaigns"};
        expected.setCampaigns(true);

        final ParametersDO actual = controller.process(options);
        assertTrue("Failed to return --campaigns option true", actual.hasCampaigns());
        assertEquals("Failed to return expected parameters", expected, actual);
    }

    @Test
    public void testCreativesOption() {
        final String[] options = {"--url", URL, "--creatives"};
        expected.setCreatives(true);

        final ParametersDO actual = controller.process(options);
        assertTrue("Failed to return --creatives option true", actual.hasCreatives());
        assertEquals("Failed to return expected parameters", expected, actual);
    }

    @Test
    public void testReportOption() {
        final String[] options = {"--url", URL, "--report"};
        expected.setReports(true);

        final ParametersDO actual = controller.process(options);
        assertTrue("Failed to return --report option true", actual.hasReports());
        assertEquals("Failed to return expected parameters", expected, actual);
    }

    @Test(expected = BadOptionsException.class)
    public void testClicksOptionMissingCampaignID() {
        final String[] options = {"--url", URL, "--clicks"};
        controller.process(options);
    }

    @Test
    public void testClicksOption() {
        final String[] options = {"--url", URL, "--clicks", CAMPAIGN_ID};
        expected.setClicks(Long.parseLong(CAMPAIGN_ID));

        final ParametersDO actual = controller.process(options);
        assertEquals("Failed to return campaign ID from --clicks option", expected, actual);
    }

    @Test(expected = BadOptionsException.class)
    public void testImpressionsOptionMissingCampaignID() {
        final String[] options = {"--url", URL, "--impressions"};
        controller.process(options);
    }

    @Test
    public void testImpressionsOption() {
        final String[] options = {"--url", URL, "--impressions", CAMPAIGN_ID};
        expected.setImpressions(Long.parseLong(CAMPAIGN_ID));

        final ParametersDO actual = controller.process(options);
        assertEquals("Failed to return campaign ID from --impressions option", expected, actual);
    }

    @Test(expected = BadOptionsException.class)
    public void testViewsOptionMissingCampaignID() {
        final String[] options = {"--url", URL, "--views"};
        controller.process(options);
    }

    @Test
    public void testViewsOption() {
        final String[] options = {"--url", URL, "--views", CAMPAIGN_ID};
        expected.setViews(Long.parseLong(CAMPAIGN_ID));

        final ParametersDO actual = controller.process(options);
        assertEquals("Failed to return campaign ID from --views option", expected, actual);
    }

    @Test(expected = BadOptionsException.class)
    public void testSummaryOptionMissingCampaignID() {
        final String[] options = {"--url", URL, "--summary"};
        controller.process(options);
    }

    @Test
    public void testSummaryOption() {
        final String[] options = {"--url", URL, "--summary", CAMPAIGN_ID};
        expected.setSummary(Long.parseLong(CAMPAIGN_ID));

        final ParametersDO actual = controller.process(options);
        assertEquals("Failed to return campaign ID from --views option", expected, actual);
    }
}
