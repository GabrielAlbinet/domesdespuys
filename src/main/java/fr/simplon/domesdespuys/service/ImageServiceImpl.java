package fr.simplon.domesdespuys.service;

import fr.simplon.domesdespuys.dto.ImageDTO;
import fr.simplon.domesdespuys.entity.Image;
import fr.simplon.domesdespuys.entity.Product;
import fr.simplon.domesdespuys.repository.ImageRepository;
import fr.simplon.domesdespuys.service.interfaces.iImage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ImageServiceImpl implements iImage {

    private ImageRepository imageRepository;

    @Override
    public Image create(ImageDTO dto, Product product) {
        Image image = new Image();
        image.setProduct(product);
        image.setPath(dto.getPath());
        image.setExtension(dto.getExtension());
        image.setIsDefault(Boolean.TRUE.equals(dto.getIsDefault()));
        return imageRepository.save(image);
    }

    @Override
    public boolean delete(String id) {
        try {
            long imageId = Long.parseLong(id);
            if (!imageRepository.existsById(imageId)) {
                return false;
            }
            imageRepository.deleteById(imageId);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}