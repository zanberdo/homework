package com.zanfardino.homework.http;

import com.zanfardino.homework.model.CampaignDO;
import com.zanfardino.homework.model.CreativeDO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.eclipse.persistence.jaxb.UnmarshallerProperties;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.helpers.DefaultValidationEventHandler;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

@Deprecated
public class TestJaxb {
    private static final Logger LOG = LogManager.getLogger(TestJaxb.class);

    private static final String campaignJSON = "[ {\n" +
            "   \"advertiser\" : \"Campaign Advertiser\",\n" +
            "   \"cpm\" : \"$100.00\",\n" +
            "   \"endDate\" : \"2020-01-15\",\n" +
            "   \"id\" : 1337,\n" +
            "   \"name\" : \"Campaign Test DO\",\n" +
            "   \"startDate\" : \"2020-01-01\"\n" +
            "} ]";

    private static final String creativeJSON = "[ {\n" +
            "   \"clicks\" : 111,\n" +
            "   \"conversions\" : 222,\n" +
            "   \"id\" : 9999,\n" +
            "   \"impressions\" : 333,\n" +
            "   \"name\" : \"Createive Test DO\",\n" +
            "   \"parentId\" : 0,\n" +
            "   \"views\" : 444\n" +
            "} ]";

    private static List<CampaignDO> campaigns = new ArrayList<>();
    private static List<CreativeDO> creatives = new ArrayList<>();
    private static File campaignFile;
    private static File creativeFile;


    private CampaignDO getCampaign(final String name) {
        final CampaignDO campaignDO = new CampaignDO();
        campaignDO.setId(1337L);
        campaignDO.setName(name);
        campaignDO.setAdvertiser("Campaign Advertiser");
        campaignDO.setCpm("$100.00");
        campaignDO.setStartDate("2020-01-01");
        campaignDO.setEndDate("2020-01-15");
        return campaignDO;
    }

    private CreativeDO getCreative(final String name) {
        final CreativeDO creativeDO = new CreativeDO();
        creativeDO.setId(9999L);
        creativeDO.setName(name);
        creativeDO.setParentId(0L);
        creativeDO.setClicks(111);
        creativeDO.setConversions(222);
        creativeDO.setImpressions(333);
        creativeDO.setViews(444);
        return creativeDO;
    }

    @Before
    public void setup() {
        // TODO: Replace this with a property setting...
        System.setProperty("javax.xml.bind.context.factory", "org.eclipse.persistence.jaxb.JAXBContextFactory");

//        campaignFile = new File("src/test/resources/campaign_test.json");
//        creativeFile = new File("src/test/resources/creative_test.json");

        campaigns.add(getCampaign("Campaign Test DO"));
        campaigns.add(getCampaign("Another Campaign Test DO"));

        creatives.add(getCreative("Createive Test DO"));
        creatives.add(getCreative("Another Creative Test DO"));
    }


    @Test
    public void testJAXBMarshalPOJOtoJSON() throws Exception {
        LOG.info("Testing JAXB marshalling from list of POJO to JSON list ...");

        final JAXBContext context = JAXBContext.newInstance(new Class[]{CampaignDO.class, CreativeDO.class});

        final Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(MarshallerProperties.MEDIA_TYPE, MediaType.APPLICATION_JSON);
        marshaller.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, false);

        final StringWriter actualCampaigns = new StringWriter();
        marshaller.marshal(campaigns, actualCampaigns);

        LOG.info("JSON results from converting list of Campaigns: \n{}", actualCampaigns.toString());

        final StringWriter actualCreatives = new StringWriter();
        marshaller.marshal(creatives, actualCreatives);

        LOG.info("JSON results from converting list of Creatives: \n{}", actualCreatives.toString());
    }

    @Test
    public void testJAXBUnmarshallerJSONListToListOfPOJO() throws Exception {
        LOG.info("Testing JAXB unmarshalling from JSON list to list of POJO ...");

        final JAXBContext context = JAXBContext.newInstance(new Class[]{CampaignDO.class, CreativeDO.class});

        final Unmarshaller unmarshaller = context.createUnmarshaller();
        unmarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE, MediaType.APPLICATION_JSON);
        unmarshaller.setProperty(UnmarshallerProperties.JSON_INCLUDE_ROOT, false);

        unmarshaller.setEventHandler(new DefaultValidationEventHandler());

        final StreamSource campaignSource = new StreamSource("src/test/resources/campaigns_list_test.json");
        final List<CampaignDO> campaigns = (List<CampaignDO>) unmarshaller.unmarshal(campaignSource, CampaignDO.class).getValue();

        LOG.info("Resulting list of Campaign POJOs from originating JSON list: {}", campaigns);

        final StreamSource creativeSource = new StreamSource("src/test/resources/creatives_list_test.json");
        final List<CreativeDO> creatives = (List<CreativeDO>) unmarshaller.unmarshal(creativeSource, CreativeDO.class).getValue();

        LOG.info("Resulting list of Creative POJOs from originating JSON list: {}", creatives);

    }

}
