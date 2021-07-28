package com.laioffer.onlineOrder.controller;

import com.laioffer.onlineOrder.entity.Cart;
import com.laioffer.onlineOrder.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    @ResponseBody
    public Cart getCart(){
        return cartService.getCart();
    }

    @DeleteMapping(value = "/cart/{menuId}")
    @ResponseStatus(value = HttpStatus.OK)
    public void removeMenuItemFromCart(@PathVariable int menuId) {
        cartService.removeItem(menuId);
    }
}
