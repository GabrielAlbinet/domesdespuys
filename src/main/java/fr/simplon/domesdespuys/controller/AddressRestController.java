package fr.simplon.domesdespuys.controller;


import com.fasterxml.jackson.annotation.JsonView;
import fr.simplon.domesdespuys.dto.AddressDTO;
import fr.simplon.domesdespuys.entity.Address;
import fr.simplon.domesdespuys.service.interfaces.iAddress;
import fr.simplon.domesdespuys.view.addressView;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/address")
public class AddressRestController {

    private final iAddress addressService;

    @PostMapping
    @JsonView(addressView.AddressDetailView.class)
    public ResponseEntity<Address> createAddress(@Valid @RequestBody AddressDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(addressService.create(dto));
    }

    @PutMapping(path = "/{id}")
    @JsonView(addressView.AddressDetailView.class)
    public ResponseEntity<Address> updateAddress(@PathVariable String id, @Valid @RequestBody AddressDTO dto) {
        Address address = addressService.update(id, dto);
        return address == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(address);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable String id) {
        return addressService.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}