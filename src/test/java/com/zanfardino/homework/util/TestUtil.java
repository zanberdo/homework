package com.zanfardino.homework.util;

import com.zanfardino.homework.model.CampaignDO;
import com.zanfardino.homework.model.CreativeDO;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.zanfardino.homework.TestConstant.CAMPAIGNS_JSON;
import static com.zanfardino.homework.TestConstant.CREATIVES_JSON;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestUtil {
    private static List<CampaignDO> campaignList;
    private static List<CreativeDO> creativeList;

    private static File campaignFile;
    private static File creativeFile;

    private static Utility utility;

    @Before
    public void setup() {
        System.setProperty("javax.xml.bind.context.factory", "org.eclipse.persistence.jaxb.JAXBContextFactory");

        final CampaignDO campaign1 = new CampaignDO();
        campaign1.setAdvertiser("Telemundo");
        campaign1.setCpm("$12.00");
        campaign1.setEndDate("2018-06-28");
        campaign1.setId(4976875l);
        campaign1.setName("cow-pox winos");
        campaign1.setStartDate("2018-06-28");

        final CampaignDO campaign2 = new CampaignDO();
        campaign2.setAdvertiser("Telemundo");
        campaign2.setCpm("$44.00");
        campaign2.setEndDate("2018-06-28");
        campaign2.setId(12126023l);
        campaign2.setName("planity Bearnaise");
        campaign2.setStartDate("2018-06-28");

        campaignList = new ArrayList<>();
        campaignList.add(campaign1);
        campaignList.add(campaign2);

        final CreativeDO creative1 = new CreativeDO();
        creative1.setClicks(1930);
        creative1.setConversions(187);
        creative1.setId(32360l);
        creative1.setImpressions(13296);
        creative1.setName("cow-pox winos esterase");
        creative1.setParentId(4976875l);
        creative1.setViews(2209);

        final CreativeDO creative2 = new CreativeDO();
        creative2.setClicks(1227);
        creative2.setConversions(253);
        creative2.setId(12616l);
        creative2.setImpressions(37587);
        creative2.setName("cow-pox winos,cryophile");
        creative2.setParentId(4976875l);
        creative2.setViews(3263);

        creativeList = new ArrayList<>();
        creativeList.add(creative1);
        creativeList.add(creative2);

        campaignFile = new File("src/test/resources/campaigns_list_test.json");
        creativeFile = new File("src/test/resources/creatives_list_test.json");

        utility = new Utility();
    }

    @Test
    public void testMarshalInvalidPOJO() {
        final String actual = utility.marshallPOJO(new Object(), CampaignDO.class);
        assertNull(actual);
    }

    @Test
    public void testMarshalPOJO() {
        final CampaignDO campaign = new CampaignDO();
        campaign.setAdvertiser("Telemundo");
        campaign.setCpm("$12.00");
        campaign.setEndDate("2018-06-28");
        campaign.setId(4976875l);
        campaign.setName("cow-pox winos");
        campaign.setStartDate("2018-06-28");

        final String actual = utility.marshallPOJO(campaign, CampaignDO.class);
        final String expected = "{\n" +
                "   \"advertiser\" : \"Telemundo\",\n" +
                "   \"cpm\" : \"$12.00\",\n" +
                "   \"endDate\" : \"2018-06-28\",\n" +
                "   \"id\" : 4976875,\n" +
                "   \"name\" : \"cow-pox winos\",\n" +
                "   \"startDate\" : \"2018-06-28\"\n" +
                "}";

        assertEquals(expected, actual);
    }

    @Test
    public void testMarshalPOJOList() {
        final String actual = utility.marshallPOJO(campaignList, CampaignDO.class);
        final String expected = "[ {\n" +
                "   \"advertiser\" : \"Telemundo\",\n" +
                "   \"cpm\" : \"$12.00\",\n" +
                "   \"endDate\" : \"2018-06-28\",\n" +
                "   \"id\" : 4976875,\n" +
                "   \"name\" : \"cow-pox winos\",\n" +
                "   \"startDate\" : \"2018-06-28\"\n" +
                "}, {\n" +
                "   \"advertiser\" : \"Telemundo\",\n" +
                "   \"cpm\" : \"$44.00\",\n" +
                "   \"endDate\" : \"2018-06-28\",\n" +
                "   \"id\" : 12126023,\n" +
                "   \"name\" : \"planity Bearnaise\",\n" +
                "   \"startDate\" : \"2018-06-28\"\n" +
                "} ]";

        assertEquals(expected, actual);
    }

    @Test
    public void testUnmarshallerInvalidJSON() {
        final List<Object> actual = utility.unmarshalJSON("", CampaignDO.class);
        assertNull( actual);
    }

    @Test
    public void testUnmarshallerInvalidFile() {
        final List<Object> actual = utility.unmarshalFile(new File(""), CampaignDO.class);
        assertNull( actual);
    }

    @Test
    public void testUnmarshallerCampaignJSON() {
        final List<CampaignDO> actual = utility.unmarshalJSON(CAMPAIGNS_JSON, CampaignDO.class);
        assertEquals("Failed to evaluate expected Java POJO from supplied JSON objects", campaignList, actual);
    }

    @Test
    public void testUnmarshallerCreativeJSON() {
        final List<CreativeDO> actual = utility.unmarshalJSON(CREATIVES_JSON, CreativeDO.class);
        assertEquals("Failed to evaluate expected Java POJO from supplied JSON objects", creativeList, actual);
    }

    @Test
    public void testUnmarshallerCampaignFile() {
        final List<CampaignDO> actual = utility.unmarshalFile(campaignFile, CampaignDO.class);
        assertEquals("Failed to evaluate expected Java POJO from supplied JSON file", campaignList, actual);
    }

    @Test
    public void testUnmarshallerCreativeFile() {
        final List<CreativeDO> actual = utility.unmarshalFile(creativeFile, CreativeDO.class);
        assertEquals("Failed to evaluate expected Java POJO from supplied JSON file", creativeList, actual);


    }
}
