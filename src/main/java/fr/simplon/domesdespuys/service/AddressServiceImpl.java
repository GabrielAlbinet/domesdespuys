package fr.simplon.domesdespuys.service;

import fr.simplon.domesdespuys.dto.AddressDTO;
import fr.simplon.domesdespuys.entity.Address;
import fr.simplon.domesdespuys.service.interfaces.iAddress;

public class AddressServiceImpl implements iAddress {
    @Override
    public Address create(AddressDTO dto) {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public Address update(String id, AddressDTO dto) {
        return null;
    }
}
