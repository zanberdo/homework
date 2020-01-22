package com.zanfardino.homework.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Objects;

@XmlRootElement()
@XmlAccessorType(XmlAccessType.PROPERTY)
public class CampaignDO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String advertiser;
    private String name;
    private String cpm;
    private String startDate;
    private String endDate;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getAdvertiser() {
        return advertiser;
    }

    public void setAdvertiser(final String advertiser) {
        this.advertiser = advertiser;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getCpm() {
        return cpm;
    }

    public void setCpm(final String cpm) {
        this.cpm = cpm;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(final String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(final String endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final CampaignDO that = (CampaignDO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(advertiser, that.advertiser) &&
                Objects.equals(name, that.name) &&
                Objects.equals(cpm, that.cpm) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, advertiser, name, cpm, startDate, endDate);
    }

    @Override
    public String toString() {
        return "CampaignDO{" +
                "id=" + id +
                ", advertiser='" + advertiser + '\'' +
                ", name='" + name + '\'' +
                ", cpm='" + cpm + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
