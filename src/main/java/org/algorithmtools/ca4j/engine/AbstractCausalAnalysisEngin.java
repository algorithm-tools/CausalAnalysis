package org.algorithmtools.ca4j.engine;

import org.algorithmtools.ca4j.pojo.CausalAnalysisContext;

/**
 * @param <D> Data
 * @param <R> Result
 * @author mym
 */
public abstract class AbstractCausalAnalysisEngin<D, R> {

    protected CausalAnalysisContext context;

    public AbstractCausalAnalysisEngin(CausalAnalysisContext context) {
        this.context = context;
    }

    /**
     * analyse indicator
     * @param d indicator data.
     * @return R. analyse result.
     */
    public abstract R analyse(D d);

}
