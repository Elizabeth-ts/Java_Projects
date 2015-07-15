/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npu.services;

import npu.domain.Customer;
import npu.domain.Order;

/**
 *
 * @author Arvin
 */
public interface BillingService {

    public void billCustomer(Customer cus, Order order);
}
