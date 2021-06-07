package com.mercadolivre.desafiospring.service;

import com.mercadolivre.desafiospring.domain.entity.Category;
import com.mercadolivre.desafiospring.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAll(){
        return categoryRepository.findAll();
    }

    public Category create(Category category){
        return categoryRepository.save(category);
    }

}
