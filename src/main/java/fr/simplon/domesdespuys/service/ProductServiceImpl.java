package fr.simplon.domesdespuys.service;

import com.github.slugify.Slugify;
import fr.simplon.domesdespuys.dto.ImageDTO;
import fr.simplon.domesdespuys.dto.ProductPostDTO;
import fr.simplon.domesdespuys.dto.ProductPutDTO;
import fr.simplon.domesdespuys.entity.Brand;
import fr.simplon.domesdespuys.entity.Product;
import fr.simplon.domesdespuys.repository.BrandRepository;
import fr.simplon.domesdespuys.repository.ProductRepository;
import fr.simplon.domesdespuys.service.interfaces.iImage;
import fr.simplon.domesdespuys.service.interfaces.iProduct;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements iProduct {

    private ProductRepository productRepository;
    private BrandRepository brandRepository;
    private iImage imageService;
    private final Slugify slugify = Slugify.builder().build();

    @Override
    public List<Product> getCollection() {
        return productRepository.findAll();
    }

    @Override
    public Product getById(String id) {
        try {
            return productRepository
                    .findById(Long.parseLong(id))
                    .orElse(null);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    @Transactional
    public Product create(ProductPostDTO dto) {
        Brand brand = findBrand(dto.getIdBrand());
        if (brand == null) {
            return null;
        }
        Product product = new Product();
        product.setCreatedAt(LocalDateTime.now());
        fill(product, brand, dto.getLabel(), dto.getDescription(), dto.getPrice());
        productRepository.saveAndFlush(product);
        addImages(product, dto.getImages());
        return product;
    }

    @Override
    @Transactional
    public Product update(String id, ProductPutDTO dto) {
        Product product = getById(id);
        Brand brand = findBrand(dto.getIdBrand());
        if (product == null || brand == null) {
            return null;
        }
        fill(product, brand, dto.getLabel(), dto.getDescription(), dto.getPrice());
        if (dto.getImages() != null) {
            product.getImages().clear();
            productRepository.saveAndFlush(product);
            addImages(product, dto.getImages());
        }
        return productRepository.saveAndFlush(product);
    }

    private Brand findBrand(Long idBrand) {
        if (idBrand == null) {
            return null;
        }
        return brandRepository.findById(idBrand).orElse(null);
    }

    private void fill(Product product, Brand brand, String label, String description, int price) {
        product.setLabel(label);
        product.setDescription(description);
        product.setPrice(price);
        product.setSlug(slugify.slugify(label));
        product.setBrand(brand);
    }

    private void addImages(Product product, List<ImageDTO> images) {
        if (images == null) {
            return;
        }
        for (ImageDTO imageDTO : images) {
            product.getImages().add(imageService.create(imageDTO, product));
        }
    }
}