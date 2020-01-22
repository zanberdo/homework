package com.zanfardino.homework.controllers;

import com.github.jknack.handlebars.internal.Param;
import com.zanfardino.homework.model.ParametersDO;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class TestMainController {
    private static final String URL = "http://test.url.com";

    private static AnalysisController mockAnalysisController;
    private static JerseyRestController mockJerseyRestController;
    private static CommandLineController mockCommandLineController;
    private static MainController controller;
    private static ParametersDO parameters;

    @Before
    public void setup() {
        mockAnalysisController = mock(AnalysisController.class);
        mockJerseyRestController = mock(JerseyRestController.class);
        mockCommandLineController = mock(CommandLineController.class);

        controller = new MainController(mockCommandLineController, mockAnalysisController, mockJerseyRestController);

        parameters = new ParametersDO();
    }

    @Test
    public void testGetParameters() throws Exception {
        parameters.setUrl(URL);
        doReturn(parameters).when(mockCommandLineController).process(any());

        final String[] args = {"-u," + URL};

        final ParametersDO expected = new ParametersDO();
        expected.setUrl(URL);
        final ParametersDO actual = controller.getParameters(args);

        assertEquals(expected, actual);
    }
}
