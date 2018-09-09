package com.mugu.coffee.service;

import com.mugu.coffee.config.TestContextConfiguration;
import com.mugu.coffee.model.BuyItem;
import com.mugu.coffee.model.Item;
import com.mugu.coffee.model.Status;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(loader=AnnotationConfigContextLoader.class, classes={TestContextConfiguration.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class CoffeeMachineServiceTest {
    @Autowired
    CoffeeMachineService coffeeMachineService;

    @Before
    public void load() throws Exception {
        //loadDDL();
    }

    @Test
    public void shouldShowItemsTest(){
        List<Item> itemList = coffeeMachineService.getItemsWithPrice();
        Assert.assertNotNull(itemList);
        Assert.assertEquals(itemList.get(0).getItemName(), "Capucinno");
    }

    @Test
    public void shouldBuyCoffeeTest(){
        BuyItem coffee = new BuyItem();
        coffee.setItemCode("STS-001");
        coffee.setAvailableQuantity(10);
        coffee.setRequiredQuantity(1);
        coffeeMachineService.buyCoffee(coffee);
        List<Item> itemList = coffeeMachineService.getItemsWithPrice();
        Assert.assertNotNull(itemList);
        Assert.assertEquals(itemList.get(0).getItemName(), "Capucinno");
        Assert.assertEquals(itemList.get(0).getInventory().getQuantity(), 9);
    }

    @Test
    public void shouldNotBuyCoffeeTest(){
        BuyItem coffee = new BuyItem();
        coffee.setItemCode("STM-001");
        coffee.setAvailableQuantity(10);
        coffee.setRequiredQuantity(1);
        coffeeMachineService.buyCoffee(coffee);
        List<Item> itemList = coffeeMachineService.getItemsWithPrice();
        Assert.assertNotNull(itemList);
        Assert.assertEquals(itemList.get(0).getItemName(), "Capucinno");
        Assert.assertEquals(itemList.get(0).getInventory().getQuantity(), 10);
    }
}
