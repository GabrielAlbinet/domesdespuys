package fr.simplon.domesdespuys.service.interfaces;

import fr.simplon.domesdespuys.dto.ProductPostDTO;
import fr.simplon.domesdespuys.dto.ProductPutDTO;
import fr.simplon.domesdespuys.entity.Product;

public interface iProduct extends
        iCreate<Product, ProductPostDTO>,
        iUpdate<Product, ProductPutDTO>,
        iGetItem<Product>,
        iCollection<Product>
{
}