package unit11.service;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import unit11.data.domain.AddressEntity;
import unit11.data.dto.AddressDto;
import unit11.repository.AddressRepository;

@Service
@AllArgsConstructor
public class AddressService {

    private AddressRepository addressRepository;

    public List<AddressDto> getAddresses() {
        return addressRepository.findAll().stream().map(e -> new AddressDto(e.getAddress())).toList();
    }

    public void addAddress(AddressDto addressDto) {
        addressRepository.save(AddressEntity.builder().address(addressDto.getAddress()).build());
    }

    public void deleteAddress(String address) {
        addressRepository.deleteById(address);
    }
}
