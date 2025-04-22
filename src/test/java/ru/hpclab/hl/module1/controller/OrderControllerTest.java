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
//import ru.hpclab.hl.module1.model.Customer;
//import ru.hpclab.hl.module1.model.Product;
//import ru.hpclab.hl.module1.model.order.Order;
//import ru.hpclab.hl.module1.model.order.OrderItem;
//import ru.hpclab.hl.module1.model.order.PaymentStatus;
//import ru.hpclab.hl.module1.service.OrderService;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.Arrays;
//import java.util.List;
//import java.util.UUID;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(classes = Application.class)
//@AutoConfigureMockMvc
//@Import(OrderControllerTest.TestConfig.class)
//public class OrderControllerTest {
//    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
//
//    @Autowired
//    private MockMvc mvc;
//
//    @Autowired
//    private OrderService orderService;
//
//    private Order sampleOrder;
//    private Long orderId;
//
//    @BeforeEach
//    public void setUp() {
//        orderId = 1L;
//        sampleOrder = createSampleOrder(orderId);
//    }
//
//    @Test
//    public void placeOrder_shouldReturnSavedOrder() throws Exception {
//        when(orderService.create(any())).thenReturn(sampleOrder);
//
//        String jsonRequest = objectMapper.writeValueAsString(sampleOrder);
//
//        mvc.perform(post("/orders")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonRequest))
//                .andExpect(status().isOk())
//                .andExpect(content().json(objectMapper.writeValueAsString(sampleOrder)));
//
//        verify(orderService, times(1)).create(any());
//    }
//
//    @Test
//    public void getAllOrders_shouldReturnListOfOrders() throws Exception {
//        List<Order> orders = Arrays.asList(
//                createSampleOrder(2L),
//                createSampleOrder(3L)
//        );
//
//        when(orderService.getAll()).thenReturn(orders);
//
//        mvc.perform(get("/orders").accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().json(objectMapper.writeValueAsString(orders)));
//
//        verify(orderService, times(1)).getAll();
//    }
//
//    @Test
//    public void getOrderById_shouldReturnOrder_whenExists() throws Exception {
//        when(orderService.getById(orderId)).thenReturn(sampleOrder);
//
//        mvc.perform(get("/orders/" + orderId).accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().json(objectMapper.writeValueAsString(sampleOrder)));
//
//        verify(orderService, times(1)).getById(orderId);
//    }
//
//    @Test
//    public void deleteOrder_shouldReturnNoContent() throws Exception {
//        doNothing().when(orderService).delete(orderId);
//
//        mvc.perform(delete("/orders/" + orderId))
//                .andExpect(status().isOk());
//
//        verify(orderService, times(1)).delete(orderId);
//    }
//
//    @Test
//    public void calculateTotalPrice_shouldReturnTotalPrice() throws Exception {
//        BigDecimal totalPrice = BigDecimal.valueOf(2399.97);
//        when(orderService.calculateTotalPrice(orderId)).thenReturn(totalPrice);
//
//        mvc.perform(get("/orders/" + orderId + "/total-price"))
//                .andExpect(status().isOk())
//                .andExpect(content().string(totalPrice.toString()));
//
//        verify(orderService, times(1)).calculateTotalPrice(orderId);
//    }
//
//    private Order createSampleOrder(Long id) {
//        Product product1 = new Product(1L, UUID.randomUUID(), "Laptop", "Electronics", BigDecimal.valueOf(999.99), "HP");
//        Product product2 = new Product(2L, UUID.randomUUID(), "Smartphone", "Electronics", BigDecimal.valueOf(499.99), "Samsung");
//
//        OrderItem item1 = new OrderItem(1L, UUID.randomUUID(), product1, 1);
//        OrderItem item2 = new OrderItem(2L, UUID.randomUUID(), product2, 2);
//
//        Customer customer = new Customer(1L, UUID.randomUUID(), "John Doe", "john@example.com", "123456789", LocalDateTime.now().toLocalDate());
//
//        return new Order(id, UUID.randomUUID(), Arrays.asList(item1, item2), customer, LocalDateTime.now(), PaymentStatus.PAID);
//    }
//
//    @Configuration
//    static class TestConfig {
//        @Bean
//        public OrderService orderService() {
//            return mock(OrderService.class);
//        }
//    }
//}
