package br.edu.unisinos.apirest.product;

import br.edu.unisinos.apirest.shared.ConflictException;
import br.edu.unisinos.apirest.shared.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Page<ProductResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(ProductResponse::from);
    }

    @Transactional(readOnly = true)
    public ProductResponse findById(Long id) {
        return ProductResponse.from(getProduct(id));
    }

    @Transactional
    public ProductResponse create(ProductRequest request) {
        if (repository.existsBySku(request.sku())) {
            throw new ConflictException("Já existe um produto com o SKU informado.");
        }

        Product product = new Product(request.sku(), request.name(), request.price());
        return ProductResponse.from(repository.save(product));
    }

    @Transactional
    public ProductResponse update(Long id, ProductRequest request) {
        Product product = getProduct(id);
        if (repository.existsBySkuAndIdNot(request.sku(), id)) {
            throw new ConflictException("Já existe um produto com o SKU informado.");
        }

        product.update(request.sku(), request.name(), request.price());
        return ProductResponse.from(product);
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(getProduct(id));
    }

    private Product getProduct(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado."));
    }
}
