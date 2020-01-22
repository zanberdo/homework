package com.zanfardino.homework.controllers;

import com.zanfardino.homework.exceptions.NoDataFoundException;
import com.zanfardino.homework.model.CampaignDO;
import com.zanfardino.homework.model.CampaignSummaryDO;
import com.zanfardino.homework.model.ParametersDO;
import org.junit.Before;
import org.junit.Test;

import java.io.InvalidObjectException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

public class TestMainController {
    private static final String URL = "http://test.url.com";

    private AnalysisController mockAnalysisController;
    private JerseyRestController mockJerseyRestController;
    private CommandLineController mockCommandLineController;
    private MainController controller;
    private ParametersDO parameters;

    @Before
    public void setup() {
        mockAnalysisController = mock(AnalysisController.class);
        mockJerseyRestController = mock(JerseyRestController.class);
        mockCommandLineController = mock(CommandLineController.class);

        controller = new MainController(mockCommandLineController, mockAnalysisController, mockJerseyRestController);

        parameters = new ParametersDO();
        parameters.setUrl(URL);
    }

    @Test
    public void testGetParameters() throws Exception {
        doReturn(parameters).when(mockCommandLineController).process(any());

        final String[] args = {"-u," + URL};

        final ParametersDO expected = new ParametersDO();
        expected.setUrl(URL);
        final ParametersDO actual = controller.getParameters(args);

        assertEquals(expected, actual);
    }

    @Test
    public void testAnalyzeDataCampaignDataNull() throws Exception {
        doThrow(InvalidObjectException.class).when(mockAnalysisController).process(any(ArrayList.class), any(ArrayList.class));

        controller.analyzeData(URL);
    }

    @Test
    public void testProcessRequestCampaignTotal() throws Exception {
        doReturn(0).when(mockAnalysisController).getCampaignCount();
        parameters.setCampaigns(true);

        controller.processRequest(parameters);
    }

    @Test
    public void testProcessRequestCreativeTotal() throws Exception {
        doReturn(0).when(mockAnalysisController).getCreativesCount();
        parameters.setCreatives(true);

        controller.processRequest(parameters);
    }

    @Test(expected = NoDataFoundException.class)
    public void testProcessRequestGetClicksInvalidCampaignID() throws Exception {
        doReturn(null).when(mockAnalysisController).getClicksByCampaign(anyLong());

        parameters.setClicks(123l);
        controller.processRequest(parameters);
    }

    @Test
    public void testProcessRequestGetClicks() throws Exception {
        doReturn(9999l).when(mockAnalysisController).getClicksByCampaign(anyLong());

        parameters.setClicks(123l);
        controller.processRequest(parameters);

    }

    @Test(expected = NoDataFoundException.class)
    public void testProcessRequestGetImpressionsInvalidCampaignID() throws Exception {
        doReturn(null).when(mockAnalysisController).getImpressionsByCampaign(anyLong());

        parameters.setImpressions(123l);
        controller.processRequest(parameters);
    }

    @Test
    public void testProcessRequestGetImpressions() throws Exception {
        doReturn(9999l).when(mockAnalysisController).getImpressionsByCampaign(anyLong());

        parameters.setImpressions(123l);
        controller.processRequest(parameters);
    }

    @Test(expected = NoDataFoundException.class)
    public void testProcessRequestGetViewsInvalidCampaignID() throws Exception {
        doReturn(null).when(mockAnalysisController).getViewsByCampaign(anyLong());

        parameters.setViews(123l);
        controller.processRequest(parameters);
    }

    @Test
    public void testProcessRequestGetViews() throws Exception {
        doReturn(9999l).when(mockAnalysisController).getViewsByCampaign(anyLong());

        parameters.setViews(123l);
        controller.processRequest(parameters);
    }

    @Test(expected = NoDataFoundException.class)
    public void testProcessRequestSummaryReportInvalidCampaignID() throws Exception {
        doReturn(null).when(mockAnalysisController).getCampaignSummaryById(anyLong());

        parameters.setSummary(123l);
        controller.processRequest(parameters);
    }

    @Test
    public void testProcessRequestSummaryReport() throws Exception {
        final CampaignSummaryDO summary = new CampaignSummaryDO();
        summary.setName("Name");

        doReturn(summary).when(mockAnalysisController).getCampaignSummaryById(anyLong());

        parameters.setSummary(1l);
        controller.processRequest(parameters);
    }

}
