package org.algorithmtools.ca4j.comparator;

import org.algorithmtools.ca4j.pojo.IndicatorSeries;

import java.util.Comparator;

public class IndicatorSeriesLogicIndexComparator implements Comparator<IndicatorSeries> {
    @Override
    public int compare(IndicatorSeries o1, IndicatorSeries o2) {
        return o1.getLogicalIndex().compareTo(o2.getLogicalIndex());
    }
}
