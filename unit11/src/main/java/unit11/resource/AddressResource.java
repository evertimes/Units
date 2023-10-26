package unit11.resource;

import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unit11.data.dto.AddressDto;
import unit11.service.AddressService;

@RestController
@RequestMapping("/address")
@AllArgsConstructor
public class AddressResource {

    private AddressService addressService;

    @GetMapping("/")
    @Operation(summary = "getAddressList")
    public List<AddressDto> getAddressList() {
        return addressService.getAddresses();
    }

    @PostMapping("/")
    @Operation(summary = "addAddress")
    public void addAddress(@RequestBody AddressDto addressDto) {
        addressService.addAddress(addressDto);
    }

    @DeleteMapping("/{address}")
    @Operation(summary = "deleteAddress")
    public void deleteAddress(@PathVariable String address) {
        addressService.deleteAddress(address);
    }
}
