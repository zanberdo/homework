package com.zanfardino.homework.controllers;

import com.zanfardino.homework.exceptions.BadOptionsException;
import com.zanfardino.homework.model.ParametersDO;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandLineController {
    private static final Logger LOG = LogManager.getLogger(CommandLineController.class);
    private static final String CAMPAIGN_ID = "CAMPAIGN ID";
    private static final String CLICKS = "clicks";
    private static final String IMPRESSIONS = "impressions";
    private static final String VIEWS = "views";
    private static final String SUMMARY = "summary";

    public CommandLineController() {
    }

    public ParametersDO process(final String[] args) {
        LOG.debug("Processing args: {}", args);
        final Options options = buildOptions();
        final ParametersDO parameters = new ParametersDO();

        final String usage = "Supply appropriate option to process Ad-Juster mock data.\n";

        final CommandLine commandLine;
        final CommandLineParser parser = new DefaultParser();
        final HelpFormatter formatter = new HelpFormatter();

        try {
            commandLine = parser.parse(options, args);
            if (commandLine.hasOption("help")) {
                formatter.printHelp(usage, null, options, null, true);
                System.exit(0);
            }
            if (commandLine.getOptions().length == 0) {
                LOG.error("Failed to supply target URL and one or more required option.");
                formatter.printHelp(usage, null, options, null, true);
                throw new BadOptionsException("Failed to supply target URL and one or more required option.");
            }

            if (commandLine.hasOption("url") && commandLine.getOptions().length == 1 ) {
                LOG.error("Failed to supply one or more required reporting options.");
                formatter.printHelp(usage, null, options, null, true);
                throw new BadOptionsException("Failed to supply one or more required options.");
            }

            if (commandLine.hasOption("url")) {
                if (commandLine.getOptionValue("url").isEmpty()) {
                    LOG.error("Failed to supply required target URL.");
                    formatter.printHelp(usage, null, options, null, true);
                    throw new BadOptionsException("Failed to supply required target URL.");
                }
                parameters.setUrl(commandLine.getOptionValue("url"));
            }

            parameters.setCampaigns(commandLine.hasOption("campaigns"));
            parameters.setCreatives(commandLine.hasOption("creatives"));
            parameters.setReports(commandLine.hasOption("report"));

            if (commandLine.hasOption(CLICKS)) {
                parameters.setClicks((Long) commandLine.getParsedOptionValue(CLICKS));
            }
            if (commandLine.hasOption(IMPRESSIONS)) {
                parameters.setImpressions((Long) commandLine.getParsedOptionValue(IMPRESSIONS));
            }
            if (commandLine.hasOption(VIEWS)) {
                parameters.setViews((Long) commandLine.getParsedOptionValue(VIEWS));
            }
            if (commandLine.hasOption(SUMMARY)) {
                parameters.setSummary((Long) commandLine.getParsedOptionValue(SUMMARY));
            }
        } catch (ParseException e) {
            LOG.error("Parse error: {}", e.getMessage());
            formatter.printHelp(usage, null, options, null, true);
            throw new BadOptionsException(e.getMessage());
        }

        return parameters;
    }

    private Options buildOptions() {
        LOG.debug("Building command line options...");
        final Options options = new Options();

        options.addOption(Option.builder("h")
                .longOpt("help")
                .desc("This help file")
                .build());
        options.addOption(Option.builder()
                .longOpt("url")
                .desc("Target url from which to retrieve data. (required)")
                .hasArg()
                .argName("TARGET URL")
//                .required()
                .build());
        options.addOption(Option.builder()
                .longOpt("campaigns")
                .desc("Return total number of campaigns processed")
                .build());
        options.addOption(Option.builder()
                .longOpt("creatives")
                .desc("Return total number of creatives processed")
                .build());
        options.addOption(Option.builder()
                .longOpt(CLICKS)
                .desc("Return click count by campaign ID")
                .hasArg()
                .argName(CAMPAIGN_ID)
                .type(Number.class)
                .build());
        options.addOption(Option.builder()
                .longOpt(IMPRESSIONS)
                .desc("Return impressions count by campaign ID")
                .hasArg()
                .argName(CAMPAIGN_ID)
                .type(Number.class)
                .build());
        options.addOption(Option.builder()
                .longOpt(VIEWS)
                .desc("Return view count by campaign ID")
                .hasArg()
                .argName(CAMPAIGN_ID)
                .type(Number.class)
                .build());
        options.addOption(Option.builder()
                .longOpt(SUMMARY)
                .desc("Return summary of campaign by campaign ID")
                .hasArg()
                .argName(CAMPAIGN_ID)
                .type(Number.class)
                .build());
        options.addOption(Option.builder()
                .longOpt("report")
                .desc("Return full summary report for all campaigns")
                .build());

        return options;
    }

}
