package org.algorithmtools.ca4j.pojo;

import java.io.Serializable;


public class IndicatorSeries implements Serializable {
    /** indicator time, you can set other number for Timing Significance Values */
    private final long time;
    /** indicator value */
    private final double value;
    /** business logical index,facilitate business tracking. for example: ID、dimensionValue、dimensionValue-Group */
    private final String logicalIndex;

    public IndicatorSeries(long time, double value, String logicalIndex) {
        this.time = time;
        this.value = value;
        this.logicalIndex = logicalIndex;
    }

    public long getTime() {
        return time;
    }

    public double getValue() {
        return value;
    }

    public String getLogicalIndex() {
        return logicalIndex;
    }

    @Override
    public String toString() {
        return "["+time+", "+value+", "+logicalIndex+"]";
    }
}
