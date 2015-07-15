/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npu.services;

import java.util.HashMap;
import npu.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.MapFactoryBean;

/**
 *
 * @author Arvin
 */
public class TaxServiceForSales implements TaxService {

    HashMap<String, Double> stateTaxMap;

    public void setStateTaxMap(HashMap<String, Double> stateTaxMap) {
        this.stateTaxMap = stateTaxMap;
    }

    public HashMap<String, Double> getStateTaxMap() {
        return stateTaxMap;
    }

    @Override
    public double computeTax(Order order) {
        return stateTaxMap.get(order.getCustomer().getState());

    }

}
