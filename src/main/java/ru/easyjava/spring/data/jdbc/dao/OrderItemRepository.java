package ru.easyjava.spring.data.jdbc.dao;

import ru.easyjava.spring.data.jdbc.entity.Order;

/**
 * OrderItem database layer.
 */
public interface OrderItemRepository {
    /**
     * Counts total quantity of items in specific order.
     * @param o Order to query.
     * @return Number of items.
     */
    Number itemsInOrder(Order o);
}
