package com.zanfardino.homework.model;

import java.util.Objects;

public class TotalDO {
    Integer total;

    public TotalDO() {
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(final Integer total) {
        this.total = total;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final TotalDO totalDO = (TotalDO) o;
        return Objects.equals(total, totalDO.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(total);
    }

    @Override
    public String toString() {
        return "TotalDO{" +
                "total=" + total +
                '}';
    }
}
