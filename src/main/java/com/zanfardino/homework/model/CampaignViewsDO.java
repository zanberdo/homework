package com.zanfardino.homework.model;

import java.util.Objects;

public class CampaignViewsDO {
    Long id;
    Long views;

    public CampaignViewsDO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(final Long views) {
        this.views = views;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final CampaignViewsDO that = (CampaignViewsDO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(views, that.views);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, views);
    }

    @Override
    public String toString() {
        return "CampaignViewsDO{" +
                "id=" + id +
                ", views=" + views +
                '}';
    }
}
