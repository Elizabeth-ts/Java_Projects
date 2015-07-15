/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npu.services;

import npu.domain.Order;

/**
 *
 * @author Arvin
 */
public class OrderProcessor {

    private AccountingService acctService;
    private InventoryService invService;

    public void setAcctService(AccountingService acctService) {
        this.acctService = acctService;
    }

    public void setInvService(InventoryService invService) {
        this.invService = invService;
    }

    public OrderProcessor() {
    }

    public void newOrder(Order order) {
        acctService.recordNewOrder(order);
        order.setTax(acctService.computeTax(order));
        order.setTotal(order.getSubTotal() * (order.getTax() + 1));
        invService.adjustInventory(order);
    }
}
