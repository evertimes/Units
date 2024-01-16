package unit11.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import unit11.data.domain.UsersEntity;
import unit11.data.dto.UserDtoRequest;
import unit11.data.dto.UserDtoResponse;
import unit11.repository.AddressRepository;
import unit11.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private AddressRepository addressRepository;

    @Transactional
    public UserDtoResponse getUser(int id) {
        var user = userRepository.getReferenceById(id);
        return UserDtoResponse.builder().address(user.getAddress().getAddress()).name(user.getName()).id(id).build();
    }

    @Transactional
    public void updateUser(int id, UserDtoRequest usersDto) {
        var user = userRepository.getReferenceById(id);
        user.setName(usersDto.getName());
        if (usersDto.getAddress() != null) {
            user.setAddress(addressRepository.getReferenceById(usersDto.getAddress()));
        }
        userRepository.save(user);
    }

    @Transactional
    public void addUser(UserDtoRequest usersDto) {

        var user = UsersEntity.builder()
            .address(addressRepository.getReferenceById(usersDto.getAddress()))
            .name(usersDto.getName()).build();
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
