package com.zanfardino.homework.controllers;

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
    private static final JerseyRestController controller = new JerseyRestController();;
    private static final Utility utility = new Utility();;

    @Before
    public void setup() {
        System.setProperty("javax.xml.bind.context.factory", "org.eclipse.persistence.jaxb.JAXBContextFactory");

        controller.setUrl("http://localhost:1337/api/");
    }

    @Test
    public void testGetCampaigns() throws Exception {
        final List<CampaignDO> expected = utility.unmarshalJSON(CAMPAIGNS_JSON, CampaignDO.class);
        final List<CampaignDO> actual = controller.getCampaigns();

        assertEquals(expected, actual);
    }

    @Test
    public void testGetCreatives() throws Exception {
        final List<CreativeDO> expected = utility.unmarshalJSON(CREATIVES_JSON, CreativeDO.class);
        final List<CreativeDO> actual = controller.getCreatives();

        assertEquals(expected, actual);
    }

}
