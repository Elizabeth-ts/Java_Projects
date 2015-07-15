/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npu.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import npu.domain.Order;
import npu.domain.OrderItem;
import npu.domain.Product;

/**
 *
 * @author Arvin
 */
public class InventoryServiceImpl implements InventoryService {

    Map<Product, Long> map = new HashMap<>();

    public InventoryServiceImpl() {
        init();
    }

    public void init() {
        map.put(new Product("CPU", 5000), new Long(5));
        map.put(new Product("Memory", 3000), new Long(7));
        map.put(new Product("Mouse", 30), new Long(100));
    }

    @Override
    public void adjustInventory(Order order) {
        List<OrderItem> list = order.getOrderList();
        for (OrderItem o : list) {
            Product prob = o.getProduct();
            if (map.containsKey(prob)) {
                Long current = map.get(prob) - o.getQuantity();
                if (current > 0) {
                    map.replace(prob, current);
                }
            }
        }
    }

    @Override
    public void printCurrentInventory() {
        for (Map.Entry entry : map.entrySet()) {
            System.out.println(entry.getKey() + "," + entry.getValue());
        }
    }

    private void trace(String s) {
        System.out.println(s);
    }
}
