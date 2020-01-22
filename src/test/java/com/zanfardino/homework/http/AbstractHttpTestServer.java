package com.zanfardino.homework.http;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.BeforeClass;

import javax.ws.rs.core.MediaType;

import static com.zanfardino.homework.TestConstant.CAMPAIGNS_JSON;
import static com.zanfardino.homework.TestConstant.CREATIVES_JSON;

public abstract class AbstractHttpTestServer {
    private static final Logger LOG = LogManager.getLogger(AbstractHttpTestServer.class);

    static final Integer TEST_PORT = 1337;
    static final String BASE_URL = "/api";
    private static WireMockServer server = null;

    @After
    public void cleanUp() {
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

        // TODO: Add validation testing end-points for target failures

        // GET
        server.stubFor(
                WireMock.get(WireMock.urlEqualTo(BASE_URL + "/campaigns"))
                        .withHeader("Accept", WireMock.equalTo(MediaType.APPLICATION_JSON))
                        .willReturn(WireMock.aResponse().withStatus(200)
                                .withStatus(200)
                                .withHeader("Content-Type", MediaType.APPLICATION_JSON)
                                .withBody(CAMPAIGNS_JSON))
        );
        server.stubFor(
                WireMock.get(WireMock.urlEqualTo(BASE_URL + "/creatives"))
                        .withHeader("Accept", WireMock.equalTo(MediaType.APPLICATION_JSON))
                        .willReturn(WireMock.aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", MediaType.APPLICATION_JSON)
                                .withBody(CREATIVES_JSON))
        );

        server.start();
    }

}
