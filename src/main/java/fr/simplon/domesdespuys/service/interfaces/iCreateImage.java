package fr.simplon.domesdespuys.service.interfaces;

import fr.simplon.domesdespuys.dto.ImageDTO;
import fr.simplon.domesdespuys.entity.Image;
import fr.simplon.domesdespuys.entity.Product;

public interface iCreateImage {

    Image create(ImageDTO dto, Product product);

}