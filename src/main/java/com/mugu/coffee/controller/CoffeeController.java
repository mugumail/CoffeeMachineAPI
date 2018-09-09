package com.mugu.coffee.controller;

import com.mugu.coffee.model.BuyItem;
import com.mugu.coffee.model.Status;
import com.mugu.coffee.service.CoffeeMachineService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoffeeController {

    @Autowired
    CoffeeMachineService coffeeMachineService;

    @ApiOperation(value = "Show Available Items with Price in Coffee Machine")
    @PostMapping(value = "/availableMenu")
    public ResponseEntity getAvailableMenu() {
        return ResponseEntity.ok(coffeeMachineService.getItemsWithPrice());
    }

    @PostMapping(value = "/buyCoffee/{itemCode}")
    public Status buyCoffee(@RequestBody BuyItem buyItem) {
        return coffeeMachineService.buyCoffee(buyItem);
    }
}
