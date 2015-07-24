/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

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
    private Timestamp recordStartDate, recordEndDate;

    public StepRecord(int stepCount, Timestamp recordStartDate, Timestamp recordEndDate) {
        this.stepCount = stepCount;
        this.recordStartDate = recordStartDate;
        this.recordEndDate = recordEndDate;
    }

    public StepRecord(long stepRecordId, int stepCount, Timestamp recordStartDate, Timestamp recordEndDate) {
        this.stepRecordId = stepRecordId;
        this.stepCount = stepCount;
        this.recordStartDate = recordStartDate;
        this.recordEndDate = recordEndDate;
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

    public Timestamp getRecordStartDate() {
        return recordStartDate;
    }

    public void setRecordStartDate(Timestamp recordStartDate) {
        this.recordStartDate = recordStartDate;
    }

    public Timestamp getRecordEndDate() {
        return recordEndDate;
    }

    public void setRecordEndDate(Timestamp recordEndDate) {
        this.recordEndDate = recordEndDate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (int) (this.stepRecordId ^ (this.stepRecordId >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StepRecord other = (StepRecord) obj;
        return this.stepRecordId == other.stepRecordId;
    }

    @Override
    public String toString() {
        return "StepRecord{" + "stepRecordId=" + stepRecordId + ", stepCount=" + stepCount + ", recordStartDate=" + recordStartDate + ", recordEndDate=" + recordEndDate + '}';
    }

}
