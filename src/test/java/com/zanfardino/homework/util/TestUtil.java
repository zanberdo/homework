package com.zanfardino.homework.util;

import com.zanfardino.homework.exceptions.JsonProcessingException;
import com.zanfardino.homework.model.CampaignClicksDO;
import com.zanfardino.homework.model.CampaignDO;
import com.zanfardino.homework.model.CampaignImpressionsDO;
import com.zanfardino.homework.model.CampaignViewsDO;
import com.zanfardino.homework.model.CreativeDO;
import com.zanfardino.homework.model.TotalDO;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.zanfardino.homework.TestConstant.CAMPAIGNS_JSON;
import static com.zanfardino.homework.TestConstant.CREATIVES_JSON;
import static org.junit.Assert.assertEquals;

public class TestUtil {
    private List<CampaignDO> campaignList;
    private List<CreativeDO> creativeList;

    private File campaignFile;
    private File creativeFile;

    private Utility utility;

    @Before
    public void setup() {
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

    @Test(expected = JsonProcessingException.class)
    public void testMarshalInvalidPOJO() throws Exception {
        utility.marshallPOJO(new Object(), CampaignDO.class);
    }

    @Test
    public void testMarshalPOJO() throws Exception {
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
    public void testMarshalPOJOList() throws Exception {
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

        final String actual = utility.marshallPOJO(campaignList, CampaignDO.class);

        assertEquals(expected, actual);
    }

    @Test
    public void testMarshalTotal() throws Exception {
        final String expected = "{\n" +
                "   \"total\" : 999\n" +
                "}";
        final TotalDO total = new TotalDO();
        total.setTotal(999);

        final String actual = utility.marshallPOJO(total, TotalDO.class);

        assertEquals(expected, actual);
    }

    @Test
    public void testMarshalCampaignClicksDO() throws Exception {
        final String expected = "{\n" +
                "   \"clicks\" : 7777,\n" +
                "   \"id\" : 999\n" +
                "}";
        final CampaignClicksDO campaignClicks = new CampaignClicksDO();
        campaignClicks.setId(999l);
        campaignClicks.setClicks(7777l);

        final String actual = utility.marshallPOJO(campaignClicks, CampaignClicksDO.class);
        assertEquals(expected, actual);
    }

    @Test
    public void testMarshalCampaignImpressions() throws Exception{
        final String expected = "{\n" +
                "   \"id\" : 888,\n" +
                "   \"impressions\" : 1234\n" +
                "}";
        final CampaignImpressionsDO campaignImpressions = new CampaignImpressionsDO();
        campaignImpressions.setId(888l);
        campaignImpressions.setImpressions(1234l);

        final String actual = utility.marshallPOJO(campaignImpressions, CampaignImpressionsDO.class);
        assertEquals(expected, actual);
    }

    @Test
    public void testMarshalCampaignClicks() throws Exception{
        final String expected = "{\n" +
                "   \"clicks\" : 5432,\n" +
                "   \"id\" : 123\n" +
                "}";
        final CampaignClicksDO campaignClicks = new CampaignClicksDO();
        campaignClicks.setId(123l);
        campaignClicks.setClicks(5432l);

        final String actual = utility.marshallPOJO(campaignClicks, CampaignClicksDO.class);
        assertEquals(expected, actual);
    }

    @Test
    public void testMarshalCampaignViews() throws Exception {
        final String expected = "{\n" +
                "   \"id\" : 867,\n" +
                "   \"views\" : 1000\n" +
                "}";
        final CampaignViewsDO campaignViews = new CampaignViewsDO();
        campaignViews.setId(867l);
        campaignViews.setViews(1000l);

        final String actual = utility.marshallPOJO(campaignViews, CampaignViewsDO.class);

        assertEquals(expected, actual);
    }

    @Test(expected = JsonProcessingException.class)
    public void testUnmarshallerInvalidJSON() {
        utility.unmarshalJSON("", CampaignDO.class);
    }

    @Test(expected = JsonProcessingException.class)
    public void testUnmarshallerInvalidFile() {
        utility.unmarshalFile(new File(""), CampaignDO.class);
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
