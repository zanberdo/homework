## Description

Write a program in java that:

* Consumes data from the following end points:
    * http://localhost/api/campaigns
    * http://localhost/api/creatives 

* Outputs the following data:
    * Total Impressions and Clicks per Campaign and any Campaign data

### Details

* Campaigns - a group of creatives make up a campaign
* Creative - is an ad on a digital publisher website

Command line parameters (returned when providing -h or --help):
```
usage: Supply appropriate option to process test data.
 [--campaigns] [--clicks <CAMPAIGN ID>] [--creatives] [-h] [--impressions
       <CAMPAIGN ID>] [--report] [--summary <CAMPAIGN ID>] [--url <TARGET
       URL>] [--views <CAMPAIGN ID>]
    --campaigns                   Return total number of campaigns
                                  processed
    --clicks <CAMPAIGN ID>        Return click count by campaign ID
    --creatives                   Return total number of creatives
                                  processed
 -h,--help                        This help file
    --impressions <CAMPAIGN ID>   Return impressions count by campaign ID
    --report                      Return full summary report for all
                                  campaigns
    --summary <CAMPAIGN ID>       Return summary of campaign by campaign
                                  ID
    --url <TARGET URL>            Target url from which to retrieve data.
                                  (required)
    --views <CAMPAIGN ID>         Return view count by campaign ID
```
