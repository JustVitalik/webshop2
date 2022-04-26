package com.example.WebShop2.service;

import com.example.WebShop2.model.Category;
import com.example.WebShop2.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAllCategiry() {
        return categoryRepository.findAll();

    }

    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    public void removeCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    public Optional<Category> getCatById(Long id) {
        return categoryRepository.findById(id);
    }


}
