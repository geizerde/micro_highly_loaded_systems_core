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
//import ru.hpclab.hl.module1.api.repository.IOrderRepository;
//import ru.hpclab.hl.module1.model.*;
//import ru.hpclab.hl.module1.model.order.Order;
//import ru.hpclab.hl.module1.model.order.OrderItem;
//import ru.hpclab.hl.module1.model.order.PaymentStatus;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.Arrays;
//import java.util.List;
//import java.util.NoSuchElementException;
//import java.util.UUID;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = {OrderServiceTest.OrderServiceTestConfiguration.class})
//public class OrderServiceTest {
//
//    @Autowired
//    private OrderService orderService;
//
//    @Autowired
//    private IOrderRepository orderRepository;
//
//    @Test
//    public void testPlaceAndGetAllOrders() {
//        Order order = createSampleOrder(1L);
//
//        Order savedOrder = orderService.create(order);
//
//        Assertions.assertEquals(order.getCustomer().getFullName(), savedOrder.getCustomer().getFullName());
//        verify(orderRepository, times(1)).save(order);
//
//        List<Order> orders = orderService.getAll();
//
//        Assertions.assertEquals(2, orders.size());
//        Assertions.assertEquals("Alice Smith", orders.get(0).getCustomer().getFullName());
//        Assertions.assertEquals("Bob Johnson", orders.get(1).getCustomer().getFullName());
//        verify(orderRepository, times(1)).findAll();
//    }
//
//    @Test
//    public void testGetOrderById() {
//        Long id = 1L;
//        Order order = createSampleOrder(id);
//        when(orderRepository.findById(id)).thenReturn(order);
//
//        Order foundOrder = orderService.getById(id);
//        Assertions.assertEquals(order.getCustomer().getFullName(), foundOrder.getCustomer().getFullName());
//        verify(orderRepository, times(1)).findById(id);
//    }
//
//    @Test
//    public void testGetOrderById_NotFound() {
//        Long id = 2L;
//        when(orderRepository.findById(id)).thenThrow(new NoSuchElementException("Order not found"));
//
//        Assertions.assertThrows(NoSuchElementException.class, () -> orderService.getById(id));
//        verify(orderRepository, times(1)).findById(id);
//    }
//
//    @Test
//    public void testDeleteOrder() {
//        Long id = 3L;
//        orderService.delete(id);
//        verify(orderRepository, times(1)).delete(id);
//    }
//
//    @Test
//    public void testCalculateTotalPrice() {
//        Long id = 4L;
//        Order order = createSampleOrder(id);
//        when(orderRepository.findById(id)).thenReturn(order);
//
//        BigDecimal totalPrice = orderService.calculateTotalPrice(id);
//
//        Assertions.assertEquals(BigDecimal.valueOf(1999.97), totalPrice);
//        verify(orderRepository, times(1)).findById(id);
//    }
//
//    private static Order createSampleOrder(Long id) {
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
//    static class OrderServiceTestConfiguration {
//
//        @Bean
//        IOrderRepository orderRepository() {
//            IOrderRepository orderRepository = mock(IOrderRepository.class);
//
//            when(orderRepository.save(any())).thenReturn(createSampleOrder(1L));
//
//            when(orderRepository.findAll()).thenReturn(Arrays.asList(
//                    new Order(2L, UUID.randomUUID(), List.of(), new Customer(2L, UUID.randomUUID(), "Alice Smith", "alice@example.com", "111222333", LocalDateTime.now().toLocalDate()), LocalDateTime.now(), PaymentStatus.PAID),
//                    new Order(3L, UUID.randomUUID(), List.of(), new Customer(3L, UUID.randomUUID(), "Bob Johnson", "bob@example.com", "444555666", LocalDateTime.now().toLocalDate()), LocalDateTime.now(), PaymentStatus.PAID)
//            ));
//
//            return orderRepository;
//        }
//
//        @Bean
//        OrderService orderService(IOrderRepository orderRepository) {
//            return new OrderService(orderRepository);
//        }
//    }
//}
