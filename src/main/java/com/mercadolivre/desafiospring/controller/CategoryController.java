package com.mercadolivre.desafiospring.controller;

import com.mercadolivre.desafiospring.entity.Category;
import com.mercadolivre.desafiospring.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/categories")
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        Category c = categoryService.create(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(c);
    }

    @GetMapping("/categories")
    ResponseEntity<List<Category>> getAllCategories(){
        List<Category> list = categoryService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
}
