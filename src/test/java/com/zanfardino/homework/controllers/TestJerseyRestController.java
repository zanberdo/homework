package com.zanfardino.homework.controllers;

import com.zanfardino.homework.exceptions.BadRequestReturnedException;
import com.zanfardino.homework.exceptions.HttpClientBadRequestReturnedException;
import com.zanfardino.homework.exceptions.HttpClientServerErrorReturnedException;
import com.zanfardino.homework.http.AbstractHttpTestServer;
import com.zanfardino.homework.model.CampaignDO;
import com.zanfardino.homework.model.CreativeDO;
import com.zanfardino.homework.util.Utility;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.zanfardino.homework.TestConstant.CAMPAIGNS_JSON;
import static com.zanfardino.homework.TestConstant.CREATIVES_JSON;
import static org.junit.Assert.assertEquals;

public class TestJerseyRestController extends AbstractHttpTestServer {
    private static final JerseyRestController controller = new JerseyRestController();
    private static final Utility utility = new Utility();

    private static final String URL = "http://localhost:1337/api/";
    private static final String NOT_OK = "not_ok/";
    private static final String BAD_REQUEST = "bad_request/";
    private static final String SERVER_ERROR = "server_error/";

    @Before
    public void setup() {
        controller.setUrl(URL);
    }

    @Test(expected = BadRequestReturnedException.class)
    public void testGetCampaignsNoOK() throws Exception {
        controller.setUrl(URL + NOT_OK);
        controller.getCampaigns();
    }

    @Test(expected = HttpClientBadRequestReturnedException.class)
    public void testGetCampaignsBadRequest() throws Exception {
        controller.setUrl(URL + BAD_REQUEST);
        controller.getCampaigns();
    }

    @Test(expected = HttpClientServerErrorReturnedException.class)
    public void testGetCampaignsServerError() throws Exception {
        controller.setUrl(URL + SERVER_ERROR);
        controller.getCampaigns();
    }

    @Test
    public void testGetCampaigns() throws Exception {
        final List<CampaignDO> expected = utility.unmarshalJSON(CAMPAIGNS_JSON, CampaignDO.class);
        final List<CampaignDO> actual = controller.getCampaigns();

        assertEquals(expected, actual);
    }

    @Test(expected = BadRequestReturnedException.class)
    public void testGetCreativesNotOK() throws Exception {
        controller.setUrl(URL + NOT_OK);
        controller.getCampaigns();
    }

    @Test(expected = HttpClientBadRequestReturnedException.class)
    public void testGetCreativesBadRequest() throws Exception {
        controller.setUrl(URL + BAD_REQUEST);
        controller.getCreatives();
    }

    @Test(expected = HttpClientServerErrorReturnedException.class)
    public void testGetCreativesServerError() throws Exception {
        controller.setUrl(URL + SERVER_ERROR);
        controller.getCreatives();
    }

    @Test
    public void testGetCreatives() throws Exception {
        final List<CreativeDO> expected = utility.unmarshalJSON(CREATIVES_JSON, CreativeDO.class);
        final List<CreativeDO> actual = controller.getCreatives();

        assertEquals(expected, actual);
    }

}
