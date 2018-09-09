package com.mugu.coffee.service;

import com.mugu.coffee.common.ConvertSqlDialectToDerby;
import com.mugu.coffee.common.ItemsDataRowMapper;
import com.mugu.coffee.model.BuyItem;
import com.mugu.coffee.model.Item;
import com.mugu.coffee.model.Status;
import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

public class CoffeeMachineService {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private ItemsDataRowMapper itemsDataRowMapper;

    public CoffeeMachineService(JdbcTemplate jdbcTemplate, ItemsDataRowMapper itemsDataRowMapper) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        this.itemsDataRowMapper = new ItemsDataRowMapper();
    }

    public List<Item> getItemsWithPrice() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT it.itemCode, it.description, inv.price, inv.quantity");
        sql.append(" from item as it JOIN inventory as inv on inv.itemCode = it.itemCode ");
        sql.append("WHERE inv.quantity > 0");

        return jdbcTemplate.query(sql.toString(), itemsDataRowMapper);
    }

    public Status buyCoffee(BuyItem buyItem) {
        int quantity = buyItem.getAvailableQuantity()-buyItem.getRequiredQuantity();
        Status status = new Status();
        StringBuilder sql = new StringBuilder();
        sql.append("update inventory set quantity = :qty where itemCode = :itemCd");

        MapSqlParameterSource params =
            new MapSqlParameterSource()
                .addValue("qty", quantity)
                .addValue("itemCd", buyItem.getItemCode());
        int dbStatus = jdbcTemplate.update(sql.toString(), params);

        if(dbStatus == 1) {
            status.setStatusCode(200);
            status.setStatusDescription("Thank you");
        } else {
            status.setStatusCode(dbStatus);
            status.setStatusDescription("Not able to dispense");
        }
        return status;
    }
}
