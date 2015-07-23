/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FinalDomain;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author Arvin
 */
public class StepRecord implements Serializable {

    public static final long serialVersionUID = 2L;
    private long stepRecordId;
    private int stepCount;
    private Timestamp recordStartTime, recordEndTime;

    public StepRecord(int stepCount, Timestamp recordStartTime, Timestamp recordEndTime) {
        this.stepCount = stepCount;
        this.recordStartTime = recordStartTime;
        this.recordEndTime = recordEndTime;
    }

    public StepRecord(long stepRecordId, int stepCount, Timestamp recordStartTime, Timestamp recordEndTime) {
        this.stepRecordId = stepRecordId;
        this.stepCount = stepCount;
        this.recordStartTime = recordStartTime;
        this.recordEndTime = recordEndTime;
    }

    public long getStepRecordId() {
        return stepRecordId;
    }

    public void setStepRecordId(long stepRecordId) {
        this.stepRecordId = stepRecordId;
    }

    public int getStepCount() {
        return stepCount;
    }

    public void setStepCount(int stepCount) {
        this.stepCount = stepCount;
    }

    public Timestamp getRecordStartTime() {
        return recordStartTime;
    }

    public void setRecordStartTime(Timestamp recordStartTime) {
        this.recordStartTime = recordStartTime;
    }

    public Timestamp getRecordEndTime() {
        return recordEndTime;
    }

    public void setRecordEndTime(Timestamp recordEndTime) {
        this.recordEndTime = recordEndTime;
    }

    @Override
    public String toString() {
        return "StepRecord{" + "stepRecordId=" + stepRecordId + ", stepCount=" + stepCount + ", recordStartTime=" + recordStartTime + ", recordEndTime=" + recordEndTime + '}';
    }

}
