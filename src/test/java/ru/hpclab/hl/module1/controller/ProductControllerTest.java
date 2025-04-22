//package ru.hpclab.hl.module1.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import ru.hpclab.hl.module1.Application;
//import ru.hpclab.hl.module1.model.Product;
//import ru.hpclab.hl.module1.service.ProductService;
//
//import java.math.BigDecimal;
//import java.util.Arrays;
//import java.util.List;
//import java.util.UUID;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(classes = Application.class)
//@AutoConfigureMockMvc
//@Import(ProductControllerTest.TestConfig.class)
//public class ProductControllerTest {
//    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
//
//    @Autowired
//    private MockMvc mvc;
//
//    @Autowired
//    private ProductService productService;
//
//    private Product sampleProduct;
//    private Long productId;
//
//    @BeforeEach
//    public void setUp() {
//        productId = 1L;
//        sampleProduct = new Product(productId, UUID.randomUUID(), "Laptop", "Electronics", BigDecimal.valueOf(999.99), "HP");
//    }
//
//    @Test
//    public void addProduct_shouldReturnSavedProduct() throws Exception {
//        when(productService.create(any())).thenReturn(sampleProduct);
//
//        String jsonRequest = objectMapper.writeValueAsString(sampleProduct);
//
//        mvc.perform(post("/products")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonRequest))
//                .andExpect(status().isOk())
//                .andExpect(content().json(objectMapper.writeValueAsString(sampleProduct)));
//
//        verify(productService, times(1)).create(any());
//    }
//
//    @Test
//    public void getAllProducts_shouldReturnListOfProducts() throws Exception {
//        List<Product> products = Arrays.asList(
//                new Product(2L, UUID.randomUUID(), "Smartphone", "Electronics", BigDecimal.valueOf(499.99), "Samsung"),
//                new Product(3L, UUID.randomUUID(), "Tablet", "Electronics", BigDecimal.valueOf(299.99), "Apple")
//        );
//
//        when(productService.getAll()).thenReturn(products);
//
//        mvc.perform(get("/products").accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().json(objectMapper.writeValueAsString(products)));
//
//        verify(productService, times(1)).getAll();
//    }
//
//    @Test
//    public void getProductById_shouldReturnProduct_whenExists() throws Exception {
//        when(productService.getById(productId)).thenReturn(sampleProduct);
//
//        mvc.perform(get("/products/" + productId).accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().json(objectMapper.writeValueAsString(sampleProduct)));
//
//        verify(productService, times(1)).getById(productId);
//    }
//
//    @Test
//    public void deleteProduct_shouldReturnNoContent() throws Exception {
//        doNothing().when(productService).delete(productId);
//
//        mvc.perform(delete("/products/" + productId))
//                .andExpect(status().isOk());
//
//        verify(productService, times(1)).delete(productId);
//    }
//
//    @Configuration
//    static class TestConfig {
//        @Bean
//        public ProductService productService() {
//            return mock(ProductService.class);
//        }
//    }
//}
