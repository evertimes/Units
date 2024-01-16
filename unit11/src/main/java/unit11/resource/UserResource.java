package unit11.resource;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unit11.data.dto.UserDtoRequest;
import unit11.data.dto.UserDtoResponse;
import unit11.service.UserService;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserResource {

    private UserService userService;
    @GetMapping("/{userId}")
    @Operation(summary = "getUser")
    public UserDtoResponse getUser(@PathVariable("userId") Integer id){
        return userService.getUser(id);
    }

    @PutMapping("/{userId}")
    @Operation(summary = "updateUser")
    public void updateUser(@PathVariable("userId") Integer id, @RequestBody UserDtoRequest usersDto){
        userService.updateUser(id, usersDto);
    }

    @PostMapping
    @Operation(summary = "addUser")
    public void addUser(@RequestBody UserDtoRequest usersDto){
        userService.addUser(usersDto);
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "deleteUser")
    public void deleteUser(@PathVariable("userId") Integer id){
        userService.deleteUser(id);
    }

}
