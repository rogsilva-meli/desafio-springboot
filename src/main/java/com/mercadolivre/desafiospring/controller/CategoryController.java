package com.mercadolivre.desafiospring.controller;

import com.mercadolivre.desafiospring.domain.entity.Category;
import com.mercadolivre.desafiospring.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/categories")
    @ApiOperation(value= "Cria uma categoria de produto")
    public ResponseEntity<Category> createCategory(@Valid  @RequestBody Category category){
        Category c = categoryService.create(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(c);
    }

    @GetMapping("/categories")
    @ApiOperation(value= "Retorna uma lista de categorias de produto")
    ResponseEntity<List<Category>> getAllCategories(){
        List<Category> list = categoryService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
}
