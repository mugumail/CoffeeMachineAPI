package com.mugu.coffee.representation;

import com.mugu.coffee.model.Item;
import java.util.List;

public interface CoffeeMachineService {

    public List<Item> getItemsWithPrice();

    public long selectItemAndPrice(Item item);

}
