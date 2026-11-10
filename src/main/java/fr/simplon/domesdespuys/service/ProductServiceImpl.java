package fr.simplon.domesdespuys.service;

import fr.simplon.domesdespuys.dto.ProductPostDTO;
import fr.simplon.domesdespuys.dto.ProductPutDTO;
import fr.simplon.domesdespuys.entity.Product;
import fr.simplon.domesdespuys.service.interfaces.iProduct;

import java.util.List;

public class ProductServiceImpl implements iProduct {
    @Override
    public List<Product> getCollection() {
        return List.of();
    }

    @Override
    public Product create(ProductPostDTO dto) {
        return null;
    }

    @Override
    public Product getById(String id) {
        return null;
    }

    @Override
    public Product update(String id, ProductPutDTO dto) {
        return null;
    }
}
