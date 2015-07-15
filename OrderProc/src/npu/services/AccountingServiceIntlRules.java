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
public class AccountingServiceIntlRules implements AccountingService {

    private TaxService taxService;

    public TaxService getTaxService() {
        return taxService;
    }

    public void setTaxService(TaxService taxService) {
        this.taxService = taxService;
    }

    @Override
    public void recordNewOrder(Order order) {
        System.out.println("Applying International Accounting Rules");
    }

    @Override
    public double computeTax(Order order) {
        return taxService.computeTax(order);
    }

}
