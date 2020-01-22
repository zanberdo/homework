package com.zanfardino.homework.http;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import javax.ws.rs.core.MediaType;

import static com.zanfardino.homework.TestConstant.CAMPAIGNS_JSON;
import static com.zanfardino.homework.TestConstant.CREATIVES_JSON;

public abstract class AbstractHttpTestServer {
    private static final Logger LOG = LogManager.getLogger(AbstractHttpTestServer.class);

    private static final Integer TEST_PORT = 1337;
    private static final String BASE_URL = "/api";
    private static final String BAD_REQUEST = "/bad_request";
    private static final String SERVER_ERROR = "/server_error";
    private static final String NOT_OK = "/not_ok";

    private static WireMockServer server = null;

    @AfterClass
    public static void cleanUp() {
        server.shutdownServer();
        server.shutdown();
    }

    @BeforeClass
    public static void setupClass() {
        if (server != null && server.isRunning()) {
            LOG.info("    Mock Server already running, returning");
            return;
        }
        server = new WireMockServer(WireMockConfiguration.options().port(TEST_PORT).notifier(new ConsoleNotifier(true)));

        // GET campaigns
        server.stubFor(
                WireMock.get(WireMock.urlEqualTo(BASE_URL + "/campaigns"))
                        .withHeader("Accept", WireMock.equalTo(MediaType.APPLICATION_JSON))
                        .willReturn(WireMock.aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", MediaType.APPLICATION_JSON)
                                .withBody(CAMPAIGNS_JSON))
        );

        server.stubFor(
                WireMock.get(WireMock.urlEqualTo(BASE_URL + NOT_OK + "/campaigns"))
                        .withHeader("Accept", WireMock.equalTo(MediaType.APPLICATION_JSON))
                        .willReturn(WireMock.aResponse()
                                .withStatus(300)
                        )
        );

        server.stubFor(
                WireMock.get(WireMock.urlEqualTo(BASE_URL + BAD_REQUEST + "/campaigns"))
                        .withHeader("Accept", WireMock.equalTo(MediaType.APPLICATION_JSON))
                        .willReturn(WireMock.aResponse()
                                .withStatus(400)
                        )
        );

        server.stubFor(
                WireMock.get(WireMock.urlEqualTo(BASE_URL + SERVER_ERROR + "/campaigns"))
                        .withHeader("Accept", WireMock.equalTo(MediaType.APPLICATION_JSON))
                        .willReturn(WireMock.aResponse()
                                .withStatus(500)
                        )
        );


        // GET creatives
        server.stubFor(
                WireMock.get(WireMock.urlEqualTo(BASE_URL + "/creatives"))
                        .withHeader("Accept", WireMock.equalTo(MediaType.APPLICATION_JSON))
                        .willReturn(WireMock.aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", MediaType.APPLICATION_JSON)
                                .withBody(CREATIVES_JSON))
        );

        server.stubFor(
                WireMock.get(WireMock.urlEqualTo(BASE_URL + NOT_OK + "/creatives"))
                        .withHeader("Accept", WireMock.equalTo(MediaType.APPLICATION_JSON))
                        .willReturn(WireMock.aResponse()
                                .withStatus(300)
                        )
        );

        server.stubFor(
                WireMock.get(WireMock.urlEqualTo(BASE_URL + BAD_REQUEST + "/creatives"))
                        .withHeader("Accept", WireMock.equalTo(MediaType.APPLICATION_JSON))
                        .willReturn(WireMock.aResponse()
                                .withStatus(400)
                        )
        );

        server.stubFor(
                WireMock.get(WireMock.urlEqualTo(BASE_URL + SERVER_ERROR + "/creatives"))
                        .withHeader("Accept", WireMock.equalTo(MediaType.APPLICATION_JSON))
                        .willReturn(WireMock.aResponse()
                                .withStatus(500)
                        )
        );

        server.start();
    }

}
