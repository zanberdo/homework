package com.zanfardino.homework.model;

import java.util.Objects;
import java.util.Set;

/**
 * Created on 2019-04-25.
 */
public class CampaignSummaryDO extends CampaignDO {
    private Long clicks;
    private Long impressions;
    private Long views;
    private Set<CreativeDO> creatives;

    public CampaignSummaryDO() {
    }

    public CampaignSummaryDO(final CampaignDO campaign) {
        setId(campaign.getId());
        setName(campaign.getName());
        setAdvertiser(campaign.getAdvertiser());
        setCpm(campaign.getCpm());
        setStartDate(campaign.getStartDate());
        setEndDate(campaign.getEndDate());
    }

    public Long getClicks() {
        return clicks;
    }

    public void setClicks(final Long clicks) {
        this.clicks = clicks;
    }

    public Long getImpressions() {
        return impressions;
    }

    public void setImpressions(final Long impressions) {
        this.impressions = impressions;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(final Long views) {
        this.views = views;
    }

    public Set<CreativeDO> getCreatives() {
        return creatives;
    }

    public void setCreatives(final Set<CreativeDO> creatives) {
        this.creatives = creatives;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        final CampaignSummaryDO that = (CampaignSummaryDO) o;
        return Objects.equals(clicks, that.clicks) &&
                Objects.equals(impressions, that.impressions) &&
                Objects.equals(views, that.views) &&
                Objects.equals(creatives, that.creatives);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), clicks, impressions, views, creatives);
    }

    @Override
    public String toString() {
        return "CampaignSummaryDO{" +
                "clicks=" + clicks +
                ", impressions=" + impressions +
                ", views=" + views +
                ", creatives=" + creatives +
                "} " + super.toString();
    }
}
