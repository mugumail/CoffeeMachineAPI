package com.mugu.coffee.common;

import com.mugu.coffee.model.Inventory;
import com.mugu.coffee.model.Item;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class ItemsDataRowMapper implements RowMapper<Item> {

    @Override
    public Item mapRow(ResultSet resultSet, int i) throws SQLException {
        Item item = new Item();
        item.setItemName(resultSet.getString("description"));
        Inventory inventory = new Inventory();
        inventory.setItemId(resultSet.getString("itemCode"));
        inventory.setPrice(resultSet.getFloat("price"));
        inventory.setQuantity(resultSet.getInt("quantity"));
        item.setInventory(inventory);
        return item;
    }
}
