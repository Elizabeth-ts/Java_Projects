/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package angel_cs532_hw2;

import java.io.Serializable;

/**
 *
 * @author Arvin
 */
public class DataObject implements Serializable {

    private int data;

    public DataObject(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DataObject{" + "data=" + data + '}';
    }

}
