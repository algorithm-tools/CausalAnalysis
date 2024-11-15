package org.algorithmtools.ca4j.comparator;

import org.algorithmtools.ca4j.pojo.result.IndicatorCalculateResult;

import java.util.Comparator;

public class ContributionResultComparator implements Comparator<IndicatorCalculateResult> {
    @Override
    public int compare(IndicatorCalculateResult o1, IndicatorCalculateResult o2) {
        return o1.getChangeValue() >= o2.getChangeValue() ? 1 : 0;
    }
}
