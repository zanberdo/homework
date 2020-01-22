package com.zanfardino.homework.model;

import java.util.Objects;

public class CampaignClicksDO {
    Long id;
    Long clicks;

    public CampaignClicksDO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getClicks() {
        return clicks;
    }

    public void setClicks(final Long clicks) {
        this.clicks = clicks;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final CampaignClicksDO that = (CampaignClicksDO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(clicks, that.clicks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clicks);
    }

    @Override
    public String toString() {
        return "CampaignClicksDO{" +
                "id=" + id +
                ", clicks=" + clicks +
                '}';
    }
}
