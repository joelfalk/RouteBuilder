package com.example.demo.DAO;

import com.example.demo.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import java.util.Optional;


@Repository
public class NamedParameterJdbcCustomerDAO extends JdbcCustomerDAO{

    @Autowired
        private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public int update(Customer customer) {
        return namedParameterJdbcTemplate.update(
                "update PERMISSIONS set name = :name, bank = :bank, permission = :permission where id = :id",
                new BeanPropertySqlParameterSource(customer));
    }

    @Override
    public Optional<Customer> findById(int id) {
        return namedParameterJdbcTemplate.queryForObject(
                "select * from permissions where id = :id",
                new MapSqlParameterSource("id", id),
                (rs, rowNum) ->
                        Optional.of(new Customer(
                                rs.getString("name"),
                                rs.getInt("id"),
                                rs.getString("bank"),
                                rs.getString("permission")
                        ))
        );
    }


}
