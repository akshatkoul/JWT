package com.wings.may24.service.impl;

import com.wings.may24.model.PriceStockModel;
import com.wings.may24.model.Product;
import com.wings.may24.repository.ProductRepository;
import com.wings.may24.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<Product> getProducts() {
        List<Product> products = productRepository.findAll();
        if(products.isEmpty()) throw new RuntimeException("No products found.");
        return products;
    }

    @Override
    public void updateProduct(int id, PriceStockModel product) {
        Product existingProduct = productRepository.findById(id);
        if(existingProduct == null) throw new RuntimeException("No product found with id: "+id);
        existingProduct.setPrice(product.getPrice());
        existingProduct.setStock(product.getStock());
        productRepository.save(existingProduct);
    }

    @Override
    public void deleteProductById(int id) {
        Product existingProduct = productRepository.findById(id);
        if(existingProduct == null) throw new RuntimeException("No product found with id: "+id);
        productRepository.deleteById(id);
    }
}
