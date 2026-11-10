package fr.simplon.domesdespuys.service.interfaces;

import fr.simplon.domesdespuys.dto.BrandDTO;
import fr.simplon.domesdespuys.entity.Brand;

public interface iBrand extends
        iCreate<Brand, BrandDTO>,
        iUpdate<Brand, BrandDTO>,
        iCollection<Brand>,
        iGetItem<Brand>,
        iDelete
    {
    }
