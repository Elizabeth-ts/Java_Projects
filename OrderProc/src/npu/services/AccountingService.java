/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npu.services;

/**
 *
 * @author Arvin
 */
import npu.domain.*;

public interface AccountingService {

    public void recordNewOrder(Order order);

    public double computeTax(Order order);
}
