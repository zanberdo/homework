package com.zanfardino.homework;

import com.zanfardino.homework.controllers.AnalysisController;
import com.zanfardino.homework.controllers.CommandLineController;
import com.zanfardino.homework.controllers.JerseyRestController;
import com.zanfardino.homework.controllers.MainController;
import com.zanfardino.homework.model.ParametersDO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger LOG = LogManager.getLogger(Main.class);

    public static void main(final String[] args) {
        LOG.debug("Starting main() ...");

        final AnalysisController analysisController1 = new AnalysisController();
        final JerseyRestController jerseyRestController = new JerseyRestController();
        final CommandLineController commandLineController = new CommandLineController();
        final MainController controller = new MainController(commandLineController, analysisController1, jerseyRestController);

        ParametersDO parameters = controller.getParameters(args);
        controller.analyzeData(parameters);
        controller.processRequest(parameters);
    }

}
