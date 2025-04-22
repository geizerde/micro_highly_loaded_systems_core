package ru.hpclab.hl.module1.api.repository;

import org.springframework.stereotype.Repository;
import ru.hpclab.hl.module1.model.Customer;

@Repository
public interface ICustomerRepository extends IRestFullRepository<Customer> {}