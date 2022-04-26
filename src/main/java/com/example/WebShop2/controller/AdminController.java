package com.example.WebShop2.controller;

import com.example.WebShop2.model.Category;
import com.example.WebShop2.model.Product;
import com.example.WebShop2.service.CategoryService;
import com.example.WebShop2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    @GetMapping("/admin")
    public String adminHome() {
        return "adminHome";
    }

    @GetMapping("/admin/categories")
    public String getCategories(Model model) {
        List<Category> categories = categoryService.getAllCategiry();
        model.addAttribute("categories", categories);
        return "categories";
    }

    @GetMapping("/admin/categories/add")
    public String addCategories(Model model) {
        model.addAttribute("category", new Category());
        return "categoriesAdd";
    }

    @PostMapping("/admin/categories/add")
    public String postCategories(@ModelAttribute("category") Category category) {
        categoryService.addCategory(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/delete/{id}")
    public String deleteCat(@PathVariable("id") Long id) {
        categoryService.removeCategory(id);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/update/{id}")
    public String updateCat(@PathVariable("id") Long id, Model model) {
        Optional<Category> category = categoryService.getCatById(id);
        model.addAttribute("category", category.get());
        return "categoriesAdd";
    }

    //    Product section
    @GetMapping("/admin/products")
    public String getCatAll(Model model) {
        model.addAttribute("products", productService.getAllProduct());
        return "products";
    }

    @GetMapping("/admin/product/add")
    public String productAddGet(Model model) {
        model.addAttribute("productNew", new Product());
        model.addAttribute("categories", categoryService.getAllCategiry());
        return "productsAdd";
    }

    @PostMapping("/admin/products/add")
    public String productAddPost(@ModelAttribute("productNew") Product product, @RequestParam("image") MultipartFile file) throws IOException {
        Product product1 = new Product();
        product1.setDescription(product.getDescription());
        product1.setPrice(product.getPrice());
        product1.setImages(file.getBytes());
        product1.setName(product.getName());
        product1.setCategory(product.getCategory());
        productService.addProduct(product1);

        return "redirect:/admin/products";
    }

    @GetMapping("/admin/productImage/{id}")
    void showImage(@PathVariable("id") Long id, HttpServletResponse response, Model model) throws IOException {
        Product product = productService.getProductByIdd(id);
        response.setContentType(("image/jpeg, image/jpg, image/png, image/gif"));
        response.getOutputStream().write(product.getImages());
        response.getOutputStream().close();

    }

    @GetMapping("/admin/product/delete/{id}")
    public String deleteProd(@PathVariable("id") Long id) {
        productService.removeProductById(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/update/{id}")
    public String updateProduct(@PathVariable("id") Long id, Model model) {
        model.addAttribute("productNew", productService.getProductByIdd(id));
        model.addAttribute("categories", categoryService.getCatById(id));
        return "productsAdd";
    }


}
