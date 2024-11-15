package org.algorithmtools.ca4j.enumtype;

/**
 * 影响类型
 */
public enum InfluenceType {

    /**
     * Positive
     */
    UP(1)
    /**
     * Negative
     */
    , DOWN(-1)
    ;

    private int code;

    InfluenceType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
