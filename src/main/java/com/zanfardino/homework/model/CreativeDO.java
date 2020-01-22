package com.zanfardino.homework.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Objects;

@XmlRootElement()
@XmlAccessorType(XmlAccessType.PROPERTY)
public class CreativeDO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private Long id;
    private Long parentId;
    private Integer views;
    private Integer clicks;
    private Integer impressions;
    private Integer conversions;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(final Long parentId) {
        this.parentId = parentId;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(final Integer views) {
        this.views = views;
    }

    public Integer getClicks() {
        return clicks;
    }

    public void setClicks(final Integer clicks) {
        this.clicks = clicks;
    }

    public Integer getImpressions() {
        return impressions;
    }

    public void setImpressions(final Integer impressions) {
        this.impressions = impressions;
    }

    public Integer getConversions() {
        return conversions;
    }

    public void setConversions(final Integer conversions) {
        this.conversions = conversions;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final CreativeDO that = (CreativeDO) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(id, that.id) &&
                Objects.equals(parentId, that.parentId) &&
                Objects.equals(views, that.views) &&
                Objects.equals(clicks, that.clicks) &&
                Objects.equals(impressions, that.impressions) &&
                Objects.equals(conversions, that.conversions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, parentId, views, clicks, impressions, conversions);
    }

    @Override
    public String toString() {
        return "CreativeDO{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", parentId=" + parentId +
                ", views=" + views +
                ", clicks=" + clicks +
                ", impressions=" + impressions +
                ", conversions=" + conversions +
                '}';
    }
}
