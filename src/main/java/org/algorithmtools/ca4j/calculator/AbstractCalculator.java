package org.algorithmtools.ca4j.calculator;

import org.algorithmtools.ca4j.enumtype.CalculatorType;
import org.algorithmtools.ca4j.pojo.CausalAnalysisContext;
import org.algorithmtools.ca4j.pojo.CausalAnalysisLog;

/**
 * Causal Analysis Calculator abstract interface
 * @author mym
 */
public abstract class AbstractCalculator<D, R> {

    /**
     * calculator name
     */
    protected String calculatorName;

    /**
     * calculator type
     */
    protected CalculatorType calculatorType;

    public AbstractCalculator(String calculatorName, CalculatorType calculatorType) {
        this.calculatorName = calculatorName;
        this.calculatorType = calculatorType;
    }

    /**
     * init model
     * @param context context env
     */
    public abstract void init(CausalAnalysisContext context);

    /**
     * calculate
     * @param calculateData calculate data
     * @param log log
     */
    public abstract R calculate(D calculateData, CausalAnalysisLog log);

    /**
     * check compatibility
     * @param calculateData calculate data
     * @param log log
     * @return boolean true/false
     */
    public abstract boolean checkCompatibility(D calculateData, CausalAnalysisLog log);


    public String getCalculatorName() {
        return calculatorName;
    }

    public CalculatorType getCalculatorType() {
        return calculatorType;
    }
}
