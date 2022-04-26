package com.example.WebShop2.controller;


import com.example.WebShop2.configuration.global.GlobalData;
import com.example.WebShop2.service.CategoryService;
import com.example.WebShop2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

//    @GetMapping({"/", "/home"})
//    public String home(Model model){
//        return "index";
//    }
    @GetMapping("/shop")
    public String shop (Model model){
        model.addAttribute("categories", categoryService.getAllCategiry());
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("products", productService.getAllProduct());
        return "shop";
    }
}
