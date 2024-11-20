package com.wings.may24.service;

import com.wings.may24.model.PriceStockModel;
import com.wings.may24.model.Product;
import java.util.List;

public interface ProductService {
    void saveProduct(Product product);
    List<Product> getProducts();
    void updateProduct(int id, PriceStockModel product);
    void deleteProductById(int id);
}
