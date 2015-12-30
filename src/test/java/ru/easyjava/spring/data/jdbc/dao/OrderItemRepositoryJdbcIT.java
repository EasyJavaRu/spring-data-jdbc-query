package ru.easyjava.spring.data.jdbc.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.easyjava.spring.data.jdbc.entity.Customer;
import ru.easyjava.spring.data.jdbc.entity.Order;

import javax.inject.Inject;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@ContextConfiguration("/testContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class OrderItemRepositoryJdbcIT {
    @Inject
    private OrderRepository orderRepository;

    @Inject
    private OrderItemRepositoryJdbc testedObject;

    @DirtiesContext
    @Test
    public void testItemsCount() {
        Order o = orderRepository.get(2);
        assertThat(testedObject.itemsInOrder(o), is(6));
    }
}