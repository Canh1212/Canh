package com.poly.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.poly.model.Product;
import com.poly.repository.ProductRepository;
import com.poly.service.ProductService;

@Component
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductRepository productRepository;

    @Override
	public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
	public void updateProduct(Product product) {
        productRepository.save(product);
    }

    @Override
	public void removeProductById(long id) {
        productRepository.deleteById(id);
    }

    @Override
	public Optional<Product> getProductById(long id) {
        return productRepository.findById(id);
    }

    @Override
	public List<Product> getAllProductByCategoryId(int id) {
        return productRepository.findAllByCategory_Id(id);
    }
    @Override
    public List<Product> searchProducts(String keyword) {
        return productRepository.findByNameContainingIgnoreCase(keyword);
    }
}
