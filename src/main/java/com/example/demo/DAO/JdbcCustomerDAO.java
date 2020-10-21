package com.example.demo.DAO;

import com.example.demo.CustomersAndUsers.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
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
    public int count() {
       return jdbcTemplate.query("select * from PERMISSIONS", rowMap()).size();
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


    private RowMapper<Customer> rowMap() {
        return (rs, rowNum) -> new Customer(
                rs.getString("name"),
                rs.getInt("id"),
                rs.getString("bank"),
                rs.getString("permission")
        );
    }
}

