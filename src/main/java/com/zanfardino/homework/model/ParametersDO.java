package com.zanfardino.homework.model;

import java.util.Objects;

public class ParametersDO {
    private String url;
    private Long clicks;
    private Long impressions;
    private Long views;
    private Long summary;
    private boolean campaigns;
    private boolean creatives;
    private boolean reports;

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
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

    public Long getSummary() {
        return summary;
    }

    public void setSummary(final Long summary) {
        this.summary = summary;
    }

    public boolean hasCampaigns() {
        return campaigns;
    }

    public void setCampaigns(final boolean campaigns) {
        this.campaigns = campaigns;
    }

    public boolean hasCreatives() {
        return creatives;
    }

    public void setCreatives(final boolean creatives) {
        this.creatives = creatives;
    }

    public boolean hasReports() {
        return reports;
    }

    public void setReports(final boolean reports) {
        this.reports = reports;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final ParametersDO that = (ParametersDO) o;
        return campaigns == that.campaigns &&
                creatives == that.creatives &&
                reports == that.reports &&
                Objects.equals(url, that.url) &&
                Objects.equals(clicks, that.clicks) &&
                Objects.equals(impressions, that.impressions) &&
                Objects.equals(views, that.views) &&
                Objects.equals(summary, that.summary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, clicks, impressions, views, summary, campaigns, creatives, reports);
    }

    @Override
    public String toString() {
        return "ParametersDO{" +
                "url='" + url + '\'' +
                ", clicks=" + clicks +
                ", impressions=" + impressions +
                ", views=" + views +
                ", summary=" + summary +
                ", campaigns=" + campaigns +
                ", creatives=" + creatives +
                ", reports=" + reports +
                '}';
    }
}
