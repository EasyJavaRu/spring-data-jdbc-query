package ru.easyjava.spring.data.jdbc.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;
import ru.easyjava.spring.data.jdbc.entity.Customer;

import javax.inject.Inject;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * JDBC implementation of CustomerRepository.
 */
@Repository
public class CustomerRepositoryJdbc implements CustomerRepository {
    /**
     * Email retrieval query.
     */
    private static final String EMAIL_QUERY
            = "SELECT EMAIL FROM CUSTOMERS WHERE ID=?";

    /**
     * Non-parameterized email retrieval query.
     */
    private static final String ALL_EMAIL_QUERY
            = "SELECT EMAIL FROM CUSTOMERS";

    /**
     * All customers retrieval query.
     */
    private static final String ALL_QUERY
            = "SELECT * FROM CUSTOMERS ORDER BY ID DESC";

    /**
     * Customer creation query.
     */
    private static final String CREATE_QUERY
            = "INSERT INTO CUSTOMERS (EMAIL) VALUES(?)";

    /**
     * Database connection.
     */
    @Inject
    private JdbcTemplate jdbcTemplate;

    @Override
    public final void add(final String email) {
        jdbcTemplate.update(CREATE_QUERY, email);
    }

    /**
     * Example of PreparedStatementSetter.
     */
    private static final class CustomerSetter
            implements PreparedStatementSetter {

        /**
         * Customer object, used in callback.
         */
        private Customer customer;

        /**
         * Constructor.
         * @param c Custoer object to work on.
         */
        public CustomerSetter(final Customer c) {
            this.customer = c;
        }

        @Override
        public void setValues(final PreparedStatement ps) throws SQLException {
            ps.setString(1, customer.getEmail());
        }
    }

    @Override
    public final void add(final Customer c) {
        jdbcTemplate.update(CREATE_QUERY, new CustomerSetter(c));
    }

    @Override
    public final String getEmail(final Integer id) {
        return jdbcTemplate.queryForObject(EMAIL_QUERY, String.class, id);
    }

    @Override
    public final List<String> allEmails() {
        return jdbcTemplate.queryForList(ALL_EMAIL_QUERY, String.class);
    }

    @Override
    public final List<Map<String, Object>> all() {
        return jdbcTemplate.queryForList(ALL_QUERY);
    }
}
