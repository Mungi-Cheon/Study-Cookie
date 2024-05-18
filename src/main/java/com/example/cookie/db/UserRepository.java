package com.example.cookie.db;

import com.example.cookie.model.UserDto;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class UserRepository {

    private final List<UserDto> userList = new ArrayList<>();

    public Optional<UserDto> findById(String id) {
        return userList.stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    public Optional<UserDto> findByName(String name) {
        return userList.stream().filter(user -> user.getName().equals(name)).findFirst();
    }

    @PostConstruct
    public void start(){
        userList.add(
            new UserDto(
                UUID.randomUUID().toString(),
                "홍길동",
                "1234"
            )
        );
        userList.add(
            new UserDto(
                UUID.randomUUID().toString(),
                "유관순",
                "1234"
            )
        );
        userList.add(
            new UserDto(
                UUID.randomUUID().toString(),
                "강감찬",
                "1234"
            )
        );
    }
}
