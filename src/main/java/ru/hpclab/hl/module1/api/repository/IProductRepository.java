package ru.hpclab.hl.module1.api.repository;

import org.springframework.stereotype.Repository;
import ru.hpclab.hl.module1.model.Product;

@Repository
public interface IProductRepository extends IRestFullRepository<Product> {}