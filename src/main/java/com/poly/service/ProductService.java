package com.poly.service;

import org.springframework.stereotype.Service;

import com.poly.model.Product;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {

	List<Product> getAllProductByCategoryId(int id);

	Optional<Product> getProductById(long id);

	void removeProductById(long id);

	void updateProduct(Product product);

	List<Product> getAllProduct();
    
	List<Product> searchProducts(String keyword);
}
