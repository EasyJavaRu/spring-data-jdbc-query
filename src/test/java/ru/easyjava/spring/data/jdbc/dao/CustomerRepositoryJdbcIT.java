package ru.easyjava.spring.data.jdbc.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.easyjava.spring.data.jdbc.entity.Customer;

import javax.inject.Inject;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@ContextConfiguration("/testContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerRepositoryJdbcIT {
    @Inject
    private CustomerRepositoryJdbc testedObject;

    @DirtiesContext
    @Test
    public void testGetEmail() {
        assertThat(testedObject.getEmail(100), is("TEST"));
    }

    @DirtiesContext
    @Test
    public void testAllEmails() {
        assertThat(testedObject.allEmails(), hasItem("TEST"));
    }

    @DirtiesContext
    @Test
    public void testAll() {
        assertTrue(testedObject.all().stream().filter(m -> "TEST".equals(m.get("EMAIL"))).findFirst().isPresent());
    }

    @DirtiesContext
    @Test
    public void testAdd() {
        testedObject.add("new@example.org");

        Customer c = new Customer();
        c.setEmail("nextone@example.org");
        testedObject.add(c);
    }
}