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
//import ru.hpclab.hl.module1.service.CustomerService;
//
//import java.time.LocalDate;
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
//@Import(CustomerControllerTest.TestConfig.class)
//public class CustomerControllerTest {
//    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
//
//    @Autowired
//    private MockMvc mvc;
//
//    @Autowired
//    private CustomerService customerService;
//
//    private Customer sampleCustomer;
//
//    @BeforeEach
//    public void setUp() {
//        sampleCustomer = new Customer(1L, UUID.randomUUID(), "John Doe", "john@example.com", "123456789", LocalDate.now());
//    }
//
//    @Test
//    public void registerCustomer_shouldReturnSavedCustomer() throws Exception {
//        when(customerService.create(any())).thenReturn(sampleCustomer);
//
//        String jsonRequest = objectMapper.writeValueAsString(sampleCustomer);
//
//        mvc.perform(post("/customers")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonRequest))
//                .andExpect(status().isOk())
//                .andExpect(content().json(objectMapper.writeValueAsString(sampleCustomer)));
//
//        verify(customerService, times(1)).create(any());
//    }
//
//    @Test
//    public void getAllCustomers_shouldReturnListOfCustomers() throws Exception {
//        List<Customer> customers = Arrays.asList(
//                new Customer(2L, UUID.randomUUID(), "Alice Smith", "alice@example.com", "111222333", LocalDate.now()),
//                new Customer(3L, UUID.randomUUID(), "Bob Johnson", "bob@example.com", "444555666", LocalDate.now())
//        );
//
//        when(customerService.getAll()).thenReturn(customers);
//
//        mvc.perform(get("/customers").accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().json(objectMapper.writeValueAsString(customers)));
//
//        verify(customerService, times(1)).getAll();
//    }
//
//    @Test
//    public void getCustomerById_shouldReturnCustomer_whenExists() throws Exception {
//        when(customerService.getById(sampleCustomer.getId())).thenReturn(sampleCustomer);
//
//        mvc.perform(get("/customers/" + sampleCustomer.getId()).accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().json(objectMapper.writeValueAsString(sampleCustomer)));
//
//        verify(customerService, times(1)).getById(sampleCustomer.getId());
//    }
//
//    @Test
//    public void deleteCustomer_shouldReturnNoContent() throws Exception {
//        doNothing().when(customerService).delete(sampleCustomer.getId());
//
//        mvc.perform(delete("/customers/" + sampleCustomer.getId()))
//                .andExpect(status().isOk());
//
//        verify(customerService, times(1)).delete(sampleCustomer.getId());
//    }
//
//    @Configuration
//    static class TestConfig {
//        @Bean
//        public CustomerService customerService() {
//            return mock(CustomerService.class);
//        }
//    }
//}
