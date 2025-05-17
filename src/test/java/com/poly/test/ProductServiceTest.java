package com.poly.test;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.poly.model.Category;
import com.poly.model.Product;
import com.poly.repository.ProductRepository;
import com.poly.service.impl.ProductServiceImpl;

@SpringBootTest
public class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;

    @Test
    public void testGetProductById() {
        Product product = new Product();
        product.setId(1L);
        product.setName("iPhone 15");
        product.setPrice(1500);
        Category category = new Category();
        category.setId(1);
        category.setName("Smartphone");
        product.setCategory(category);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Optional<Product> result = productService.getProductById(1);
        System.out.println("Tên sản phẩm: " + result.get().getName());
        System.out.println("Giá sản phẩm: " + result.get().getPrice());
        System.out.println("Danh mục: " + result.get().getCategory().getName());
        assertEquals("iPhone 15", result.get().getName());
        assertEquals(1500, result.get().getPrice());
    }
}
