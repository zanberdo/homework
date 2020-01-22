package com.zanfardino.homework.controllers;

import com.zanfardino.homework.exceptions.HttpClientBadRequestReturnedException;
import com.zanfardino.homework.exceptions.HttpClientServerErrorReturnedException;
import com.zanfardino.homework.model.CampaignDO;
import com.zanfardino.homework.model.CreativeDO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.HttpMethod.GET;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

public class JerseyRestController {
    private static final Logger LOG = LogManager.getLogger(JerseyRestController.class);
    private final Client client = ClientBuilder.newClient();

    private String url;

    public void setUrl(final String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void close() {
        LOG.debug("Shutting down rest client...");
        if (client != null) client.close();
    }

    public List<CampaignDO> getCampaigns() throws HttpClientBadRequestReturnedException {
        LOG.debug("Getting Campaign data...");
        final WebTarget target = client.target(url + "campaigns");
        logRequest(target, GET);

        final Invocation.Builder request = target.request(APPLICATION_JSON);
        final Response response = request.get();
        logResponse(response);

        // TODO: write endpoint definition and tests for the following error conditions
        if (response.getStatus() >= 500) {
            throw new HttpClientServerErrorReturnedException(response.getStatus(), response.readEntity(String.class), response.getHeaders().toString());
        } else if (response.getStatus() >= 400) {
            throw new HttpClientBadRequestReturnedException(response.getStatus(), response.readEntity(String.class), response.getHeaders().toString());
        }
        return response.readEntity(new GenericType<List<CampaignDO>>() {
        });
    }

    public List<CreativeDO> getCreatives() throws HttpClientBadRequestReturnedException {
        LOG.debug("Getting Creatives data...");
        final WebTarget target = client.target(url + "creatives");
        logRequest(target, GET);

        final Invocation.Builder request = target.request(APPLICATION_JSON);
        final Response response = request.get();
        logResponse(response);

        // TODO: write endpoint definition and tests for the following error conditions
        if (response.getStatus() >= 500) {
            throw new HttpClientServerErrorReturnedException(response.getStatus(), response.readEntity(String.class), response.getHeaders().toString());
        } else if (response.getStatus() >= 400) {
            throw new HttpClientBadRequestReturnedException(response.getStatus(), response.readEntity(String.class), response.getHeaders().toString());
        }
        return response.readEntity(new GenericType<List<CreativeDO>>() {
        });
    }

    private void logRequest(final WebTarget target, final String method) {
        LOG.debug("    Outgoing Request: {} {}", method, target.getUri());
    }

    private void logResponse(final Response response) {
        LOG.debug("    Received Response: {} {}", response.getStatusInfo().getStatusCode(), response.getStatusInfo().getReasonPhrase());
    }

}
