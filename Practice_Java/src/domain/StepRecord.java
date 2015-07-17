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

    public static final long serialVersionUID = 1L;
    private int stepCount;
    private User user;
    private Timestamp recordStartTime, recordEndTime;

    public StepRecord(int stepCount, User user, Timestamp recordStartTime, Timestamp recordEndTime) {
        this.stepCount = stepCount;
        this.user = user;
        this.recordStartTime = recordStartTime;
        this.recordEndTime = recordEndTime;
    }

    public StepRecord(int stepCount, User user, Timestamp recordStartTime) {
        this.stepCount = stepCount;
        this.user = user;
        this.recordStartTime = recordStartTime;
    }

    public StepRecord(User user, Timestamp recordStartTime, Timestamp recordEndTime) {
        this.stepCount = stepCount;
        this.user = user;
        this.recordStartTime = recordStartTime;
        this.recordEndTime = recordEndTime;
    }

    public int getStepCount() {
        return stepCount;
    }

    public void setStepCount(int stepCount) {
        this.stepCount = stepCount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        return "StepRecord{" + "stepCount=" + stepCount + ", user=" + user + ", recordStartTime=" + recordStartTime + ", recordEndTime=" + recordEndTime + '}';
    }

}
