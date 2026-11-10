package fr.simplon.domesdespuys.service;

import fr.simplon.domesdespuys.dto.ProductPostDTO;
import fr.simplon.domesdespuys.dto.ProductPutDTO;
import fr.simplon.domesdespuys.entity.Product;
import fr.simplon.domesdespuys.repository.ProductRepository;
import fr.simplon.domesdespuys.service.interfaces.iProduct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements iProduct {

    private ProductRepository productRepository;

    @Override
    public List<Product> getCollection() {
        return productRepository.findAll();
    }

    @Override
    public Product getById(String id) {
        Long productId = Long.parseLong(id);
        Optional<Product> product = productRepository.findById(productId);
        return product.orElse(null);
    }

    @Override
    public Product create(ProductPostDTO dto) {
        return null;
    }

    @Override
    public Product update(String id, ProductPutDTO dto) {
        return null;
    }
}