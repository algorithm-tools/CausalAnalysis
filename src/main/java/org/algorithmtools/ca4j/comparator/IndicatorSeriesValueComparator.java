package org.algorithmtools.ca4j.comparator;

import org.algorithmtools.ca4j.pojo.IndicatorSeries;

import java.util.Comparator;

public class IndicatorSeriesValueComparator implements Comparator<IndicatorSeries> {
    @Override
    public int compare(IndicatorSeries o1, IndicatorSeries o2) {
        if (o1.getValue() > o2.getValue()) {
            return 1;
        } else if (o1.getValue() < o2.getValue()) {
            return -1;
        } else {
            return Long.compare(o1.getTime(), o2.getTime());
        }
    }
}
