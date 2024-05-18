package com.example.cookie.service;

import com.example.cookie.db.UserRepository;
import com.example.cookie.model.LoginRequest;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public void login(HttpServletResponse response, LoginRequest loginRequest){
        var id = loginRequest.getId();
        var password = loginRequest.getPassword();

        var optionalUser = userRepository.findByName(id);

        if(optionalUser.isPresent()){
            var userDto = optionalUser.get();
            if(userDto.getPassword().equals(password)){
                var cookie = new Cookie("authorization-cookie",userDto.getId());
                cookie.setDomain("localhost");
                cookie.setPath("/");
                cookie.setHttpOnly(true); // http에서 쿠키 변조를 할 수 없게 방지.
//                cookie.setSecure(true); // https에서만 사용되도록 설정
                cookie.setMaxAge(-1); // 쿠키 유효기간
                response.addCookie(cookie);
            }else{
                throw new RuntimeException("Password does not match");
            }

        }else{
            throw new RuntimeException("User Not Found");
        }
    }
}
