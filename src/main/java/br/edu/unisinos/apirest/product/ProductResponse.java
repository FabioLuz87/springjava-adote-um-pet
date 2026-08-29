package br.edu.unisinos.apirest.product;

import java.math.BigDecimal;

public record ProductResponse(Long id, String sku, String name, BigDecimal price) {

    public static ProductResponse from(Product product) {
        return new ProductResponse(product.getId(), product.getSku(), product.getName(), product.getPrice());
    }
}
