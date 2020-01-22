package com.zanfardino.homework.model;

import java.util.Objects;

public class CampaignImpressionsDO {
    Long id;
    Long impressions;

    public CampaignImpressionsDO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getImpressions() {
        return impressions;
    }

    public void setImpressions(final Long impressions) {
        this.impressions = impressions;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final CampaignImpressionsDO that = (CampaignImpressionsDO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(impressions, that.impressions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, impressions);
    }

    @Override
    public String toString() {
        return "CampaignImpressionsDO{" +
                "id=" + id +
                ", impressions=" + impressions +
                '}';
    }
}
