package com.zanfardino.homework.controllers;

import com.zanfardino.homework.model.ParametersDO;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class TestMainController {
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
    public void testGetParameters() {
        doReturn(parameters).when(mockCommandLineController).process(any());

        final String[] args = {"-h"};

        final ParametersDO expected = new ParametersDO();
        final ParametersDO actual = controller.getParameters(args);

        assertEquals(expected, actual);
    }
}
