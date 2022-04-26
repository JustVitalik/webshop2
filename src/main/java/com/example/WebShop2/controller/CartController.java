package com.example.WebShop2.controller;

import com.example.WebShop2.configuration.global.GlobalData;
import com.example.WebShop2.model.Product;
import com.example.WebShop2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CartController {
    @Autowired
    ProductService productService;


    @GetMapping("/addCart/{id}")
    public String adddCart(@PathVariable("id") Long id) {
        GlobalData.cart.add(productService.getProductById(id).get());
        return "redirect:/shop";
    }

//    @GetMapping("/cart")
//    public String cartGet(Model model) {
//        model.addAttribute("cartCount", GlobalData.cart.size());
//        model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
//        model.addAttribute("cart", GlobalData.cart);
//        return "cart";
//    }

}
