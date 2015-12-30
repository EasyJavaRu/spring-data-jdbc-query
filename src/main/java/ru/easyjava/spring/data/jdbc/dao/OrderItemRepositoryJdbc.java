package ru.easyjava.spring.data.jdbc.dao;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.easyjava.spring.data.jdbc.entity.Order;

import javax.inject.Inject;

/**
 * JDBC based implementation of OrderItemRepository.
 */
@Repository
public class OrderItemRepositoryJdbc implements OrderItemRepository {
    /**
     * Query for items calculation.
     */
    private static final String ITEMS_COUNT =
            "SELECT SUM(QUANTITY) FROM ORDER_ITEMS WHERE ORDER_ID=:id";

    /**
     * Database connection.
     */
    @Inject
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public final Number itemsInOrder(final Order o) {
        return jdbcTemplate.queryForObject(
                ITEMS_COUNT,
                new BeanPropertySqlParameterSource(o),
                Integer.class);
    }
}
