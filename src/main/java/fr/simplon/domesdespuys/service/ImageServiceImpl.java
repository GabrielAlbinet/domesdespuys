package fr.simplon.domesdespuys.service;

import fr.simplon.domesdespuys.repository.ImageRepository;
import fr.simplon.domesdespuys.service.interfaces.iImage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ImageServiceImpl implements iImage {
    @Override
    public boolean delete(String id) {
        return false;
    }
}