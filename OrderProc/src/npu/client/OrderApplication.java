/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npu.client;

/**
 *
 * @author Arvin
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import npu.domain.*;
import npu.services.AccountingService;
import npu.services.InventoryService;
import npu.services.OrderProcessor;
import npu.services.TaxServiceForSales;
import npu.services.TaxService;

import org.springframework.context.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class OrderApplication {

    public static void main(String args[]) {
        ApplicationContext container = new ClassPathXmlApplicationContext("npu/application.xml");
        Order order;
        AccountingService acctService = (AccountingService) container.getBean("acctServiceIntlRules");
        InventoryService invService = (InventoryService) container.getBean("invServiceImpl");
        TaxServiceForSales taxService = (TaxServiceForSales) container.getBean("salesTaxService");
        OrderProcessor orderProc = (OrderProcessor) container.getBean("orderProcessor");

        for (Map.Entry entry : taxService.getStateTaxMap().entrySet()) {
            System.out.println(entry.getKey() + ", " + entry.getValue());
        }

        invService.printCurrentInventory();
        List<OrderItem> orderList = new ArrayList<>();
        orderList.add(new OrderItem(new Product("CPU", 5000), 2));
        order = new Order(new Customer("Arvin", "CA"), orderList);
        acctService.recordNewOrder(order);

        orderProc.newOrder(order);

        System.out.println(order + "\nAfter: " + acctService.computeTax(order));
        invService.printCurrentInventory();

    }
}
