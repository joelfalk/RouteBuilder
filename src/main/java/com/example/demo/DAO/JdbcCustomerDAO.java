package com.example.demo.DAO;

import com.example.demo.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JdbcCustomerDAO implements CustomerDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int insert(Customer customer) {
        return jdbcTemplate.update(
                "insert into PERMISSIONS (name, id, bank, permission) values (?,?,?,?)",
                customer.getName(), customer.getId(), customer.getBank(), customer.getPermission());
    }

    @Override
    public Optional<Customer> findById(int id) {
        return jdbcTemplate.queryForObject(
                "select * from PERMISSIONS where id = ?",
                new Object[]{id},
                (rs, rowNum) ->
                        Optional.of(new Customer(
                                rs.getString("name"),
                                rs.getInt("id"),
                                rs.getString("bank"),
                                rs.getString("permission")
                        ))
        );
    }

    @Override
    public int update(Customer customer) {
        return jdbcTemplate.update("update PERMISSIONS set (name,bank,permission) = ? where id = ?",
                customer.getName(), customer.getBank(), customer.getPermission());
    }

    @Override
    public int deleteById(int id) {
        return jdbcTemplate.update(
                "delete PERMISSIONS where id = ?",
                id);
    }
}
