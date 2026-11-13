package fr.simplon.domesdespuys.service;

import fr.simplon.domesdespuys.dto.AddressDTO;
import fr.simplon.domesdespuys.entity.Address;
import fr.simplon.domesdespuys.entity.User;
import fr.simplon.domesdespuys.repository.AddressRepository;
import fr.simplon.domesdespuys.repository.OrderRepository;
import fr.simplon.domesdespuys.repository.UserRepository;
import fr.simplon.domesdespuys.service.interfaces.iAddress;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements iAddress {

    private AddressRepository addressRepository;
    private UserRepository userRepository;
    private OrderRepository orderRepository;

    @Override
    public Address create(AddressDTO dto) {
        Address address = new Address();
        dtoToObject(dto, address);
        address.setUser(getDefaultUser());
        address.setCreatedAt(LocalDateTime.now());
        return addressRepository.saveAndFlush(address);
    }

    @Override
    public Address update(String id, AddressDTO dto) {
        Address address = findById(id);
        if (address != null) {
            dtoToObject(dto, address);
            addressRepository.saveAndFlush(address);
        }
        return address;
    }

    @Override
    public boolean delete(String id) {
        Address address = findById(id);
        if (address == null) {
            return false;
        }
        if (orderRepository.existsByAddress(address)) {
            address.setDeleted(true);
            addressRepository.saveAndFlush(address);
        } else {
            addressRepository.delete(address);
        }
        return true;
    }

    private User getDefaultUser() {
        return userRepository.findById(1L).orElseThrow();
    }

    private Address findById(String id) {
        return addressRepository
                .findById(Long.parseLong(id))
                .filter(address -> !address.isDeleted())
                .orElse(null);
    }

    private void dtoToObject(AddressDTO dto, Address address) {
        address.setLine(dto.getLine());
        address.setExtraLine(dto.getExtraLine());
        address.setZipCode(dto.getZipCode());
        address.setCity(dto.getCity());
        address.setCountry(dto.getCountry());
        address.setPhone(dto.getPhone());
        address.setFirstname(dto.getFirstname());
        address.setLastname(dto.getLastname());
    }
}
