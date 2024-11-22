package org.example.service;

import org.example.model.Order;
import org.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // Получение заказа по ID
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id " + id));
    }

    // Создание нового заказа
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    // Обновление заказа
    public Order updateOrder(Long id, Order order) {
        Order existingOrder = getOrderById(id);
        existingOrder.setOrderDate(order.getOrderDate());
        existingOrder.setUser(order.getUser());
        existingOrder.setProducts(order.getProducts());
        return orderRepository.save(existingOrder);
    }

    // Удаление заказа
    public void deleteOrder(Long id) {
        Order existingOrder = getOrderById(id);
        orderRepository.delete(existingOrder);
    }
}
