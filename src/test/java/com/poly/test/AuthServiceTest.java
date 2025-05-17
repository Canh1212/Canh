package com.poly.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.poly.model.User;
import com.poly.repository.UserRepository;
import com.poly.service.impl.UserServiceImpl;

@SpringBootTest
public class AuthServiceTest {

    @MockBean
    private UserRepository userRepository;  

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void testLoginSuccessful() {
        String email = "test@example.com";    
        String password = "password123";

        User mockUser = new User();
        mockUser.setEmail(email);
        mockUser.setPassword(password); 

        when(userRepository.findUserByEmail(email)).thenReturn(Optional.of(mockUser));

        boolean loginSuccess = userService.login(email, password);
        
        assertEquals(true, loginSuccess, "Đăng nhập thành công");
    }

    @Test
    public void testLoginInvalidPassword() {
        String email = "test@example.com";
        String wrongPassword = "wrongPassword";

        User mockUser = new User();
        mockUser.setEmail(email);
        mockUser.setPassword("password123"); 
        when(userRepository.findUserByEmail(email)).thenReturn(Optional.of(mockUser));

        boolean loginSuccess = userService.login(email, wrongPassword);
        
        assertEquals(false, loginSuccess, "Đăng nhập không thành công với mật khẩu sai");
    }

    @Test
    public void testLoginNonExistentUser() {
        String email = "nonexistent@example.com";
        String password = "password123";

        when(userRepository.findUserByEmail(email)).thenReturn(Optional.empty());

        boolean loginSuccess = userService.login(email, password);
        
        assertEquals(false, loginSuccess, "Đăng nhập không thành công với email không tồn tại");
    }
}
