package unit11;

import java.util.Set;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import unit11.data.domain.AddressEntity;
import unit11.data.dto.AddressDto;
import unit11.repository.AddressRepository;
import unit11.service.AddressService;

@SpringBootTest
public class AddressServiceTest {

    @Autowired
    private AddressService addressService;
    @Autowired
    private AddressRepository addressRepository;

    @AfterEach
    void afterEach() {
        addressRepository.deleteAll();
    }

    @Test
    void getAddresses_whenExists_thenGetAddressedFromDb() {
        addressRepository.save(AddressEntity.builder().address("address").build());
        addressRepository.save(AddressEntity.builder().address("address2").build());

        var addresses = addressService.getAddresses();
        var size = addresses
            .stream().distinct()
            .filter(e -> e.getAddress().equals("address") || e.getAddress().equals("address2"))
            .toList()
            .size();

        Assertions.assertEquals(2, size);
    }

    @Test
    void addAddress_thenNewAddressExits() {

        addressService.addAddress(new AddressDto("my_address"));
        var address = addressRepository.findAll().stream().findFirst().get().getAddress();

        Assertions.assertEquals("my_address", address);
    }

    @Test
    void deleteAddress_thenNoAddressExits() {
        addressRepository.save(new AddressEntity("address", false, 1, Set.of()));
        addressService.deleteAddress("address");

        var address = addressRepository.findAll();

        Assertions.assertTrue(address.isEmpty());
    }


}
