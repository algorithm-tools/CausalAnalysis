package org.algorithmtools.ca4j.enumtype;

/**
 * Type of indicator statistics
 */
public enum IndicatorStatType {

    /**
     * Cumulative: the values of the subcomponents of the indicator are cumulative
     */
    Add,
    /**
     * Non-Accumulative, Independent Values, Continuous Values
     */
    Unique_Continuity
    ,
    /**
     * Non-accumulative, independent values, discrete values
     */
    Unique_Discrete
    ;
}
