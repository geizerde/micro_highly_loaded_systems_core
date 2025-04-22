//package ru.hpclab.hl.module1.service;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import ru.hpclab.hl.module1.api.repository.IProductRepository;
//import ru.hpclab.hl.module1.model.Product;
//
//import java.math.BigDecimal;
//import java.util.Arrays;
//import java.util.List;
//import java.util.NoSuchElementException;
//import java.util.UUID;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = {ProductServiceTest.ProductServiceTestConfiguration.class})
//public class ProductServiceTest {
//
//    @Autowired
//    private ProductService productService;
//
//    @Autowired
//    private IProductRepository productRepository;
//
//    @Test
//    public void testAddAndGetAllProducts() {
//        Product product = new Product(1L, UUID.randomUUID(), "Laptop", "Electronics", BigDecimal.valueOf(999.99), "HP");
//
//        Product savedProduct = productService.create(product);
//
//        Assertions.assertEquals(product.getName(), savedProduct.getName());
//        verify(productRepository, times(1)).save(product);
//
//        List<Product> products = productService.getAll();
//
//        Assertions.assertEquals(2, products.size());
//        Assertions.assertEquals("Smartphone", products.get(0).getName());
//        Assertions.assertEquals("Tablet", products.get(1).getName());
//        verify(productRepository, times(1)).findAll();
//    }
//
//    @Test
//    public void testGetProductById() {
//        Long id = 2L;
//        Product product = new Product(id, UUID.randomUUID(), "Headphones", "Audio", BigDecimal.valueOf(199.99), "Sony");
//
//        when(productRepository.findById(id)).thenReturn(product);
//
//        Product foundProduct = productService.getById(id);
//        Assertions.assertEquals("Headphones", foundProduct.getName());
//        verify(productRepository, times(1)).findById(id);
//    }
//
//    @Test
//    public void testGetProductById_NotFound() {
//        Long id = 3L;
//        when(productRepository.findById(id)).thenThrow(new NoSuchElementException("Product not found"));
//
//        Assertions.assertThrows(NoSuchElementException.class, () -> productService.getById(id));
//        verify(productRepository, times(1)).findById(id);
//    }
//
//    @Test
//    public void testDeleteProduct() {
//        Long id = 4L;
//
//        productService.delete(id);
//        verify(productRepository, times(1)).delete(id);
//    }
//
//    @Configuration
//    static class ProductServiceTestConfiguration {
//
//        @Bean
//        IProductRepository productRepository() {
//            IProductRepository productRepository = mock(IProductRepository.class);
//
//            when(productRepository.save(any())).thenReturn(new Product(1L, UUID.randomUUID(), "Laptop", "Electronics", BigDecimal.valueOf(999.99), "HP"));
//
//            when(productRepository.findAll()).thenReturn(Arrays.asList(
//                    new Product(2L, UUID.randomUUID(), "Smartphone", "Electronics", BigDecimal.valueOf(499.99), "Samsung"),
//                    new Product(3L, UUID.randomUUID(), "Tablet", "Electronics", BigDecimal.valueOf(299.99), "Apple")
//            ));
//
//            return productRepository;
//        }
//
//        @Bean
//        ProductService productService(IProductRepository productRepository) {
//            return new ProductService(productRepository);
//        }
//    }
//}
