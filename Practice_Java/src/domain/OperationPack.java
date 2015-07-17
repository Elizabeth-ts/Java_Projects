/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;

/**
 *
 * @author Arvin
 */
public class OperationPack implements Serializable {

    public static final long serialVersionUID = 1L;
    private StepRecord stepRecord;
    private int operationCode;

    public OperationPack(StepRecord step, int operationCode) {
        this.stepRecord = step;
        this.operationCode = operationCode;
    }

    public StepRecord getStepRecord() {
        return stepRecord;
    }

    public void setStepRecord(StepRecord stepRecord) {
        this.stepRecord = stepRecord;
    }

    public int getOperationCode() {
        return operationCode;
    }

    public void setOperationCode(int operationCode) {
        this.operationCode = operationCode;
    }

    @Override
    public String toString() {
        return "OperationPack{" + "step=" + stepRecord + ", operationCode=" + operationCode + '}';
    }

}
