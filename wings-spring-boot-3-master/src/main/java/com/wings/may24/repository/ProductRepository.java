package com.wings.may24.repository;

import com.wings.may24.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findById(int id);
    List<Product> findAllByVendor(String vendor);
}
