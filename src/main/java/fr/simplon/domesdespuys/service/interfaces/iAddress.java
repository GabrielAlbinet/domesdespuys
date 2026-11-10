package fr.simplon.domesdespuys.service.interfaces;

import fr.simplon.domesdespuys.dto.AddressDTO;
import fr.simplon.domesdespuys.entity.Address;

public interface iAddress extends
        iCreate<Address, AddressDTO>,
        iUpdate<Address, AddressDTO>,
        iDelete
{
}