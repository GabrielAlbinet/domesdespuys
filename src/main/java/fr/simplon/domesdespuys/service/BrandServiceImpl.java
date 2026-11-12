package fr.simplon.domesdespuys.service;

import com.github.slugify.Slugify;
import fr.simplon.domesdespuys.dto.BrandDTO;
import fr.simplon.domesdespuys.entity.Brand;
import fr.simplon.domesdespuys.repository.BrandRepository;
import fr.simplon.domesdespuys.service.interfaces.iBrand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BrandServiceImpl implements iBrand {

    private BrandRepository brandRepository;
    private final Slugify slugify = Slugify.builder().build();

    @Override
    public List<Brand> getCollection() {
        return brandRepository.findAll();
    }

    @Override
    public Brand create(BrandDTO dto) {
        Brand brand = new Brand();
        dtoToObject(dto, brand);
        return brand;
    }

    @Override
    public Brand getById(String id) {
        try {
            return brandRepository
                    .findById(Long.parseLong(id))
                    .orElse(null);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public Brand update(String id, BrandDTO dto) {
        Brand brand = getById(id);
        if (brand != null) {
            dtoToObject(dto, brand);
        }
        return brand;
    }

    @Override
    public boolean delete(String id) {
        Brand brand = getById(id);
        if (brand == null) {
            return false;
        }
        brandRepository.delete(brand);
        return true;
    }

    private void dtoToObject(BrandDTO dto, Brand brand) {
        brand.setLabel(dto.getLabel());
        brand.setLogo(dto.getLogo());
        brand.setSlug(slugify.slugify(dto.getLabel()));
        brandRepository.saveAndFlush(brand);
    }

}