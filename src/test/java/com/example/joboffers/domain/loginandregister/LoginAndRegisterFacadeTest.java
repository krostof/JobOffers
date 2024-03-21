package com.example.joboffers.domain.loginandregister;

import com.example.joboffers.domain.loginandregister.dto.RegisterInfoDto;
import com.example.joboffers.domain.loginandregister.dto.UserDto;
import com.example.joboffers.domain.loginandregister.dto.UserRegisterDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoginAndRegisterFacadeTest {

    LoginAndRegisterFacade registerFacade = new LoginAndRegisterFacade(
            new LoginRepositoryImplTest()
    );

    @Test
    void should_throw_exception_when_user_not_found(){

        String username = "test";

        Exception exception = assertThrows(UserNotFoundException.class, () -> registerFacade.findByUsername(username));

        String expectedMessage = "TEST";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    void should_register_user(){
        UserRegisterDto dto = new UserRegisterDto("testName","testPass");

        RegisterInfoDto infoDto = registerFacade.register(dto);

        assertEquals(infoDto.name(), "testName");
        assertEquals(infoDto.message(), "User registered successfully");
    }

    @Test
    void should_find_user_by_username(){
        UserRegisterDto dto = new UserRegisterDto("testName","testPass");


        registerFacade.register(dto);
        UserDto userDto = registerFacade.findByUsername("testName");

        assertEquals(userDto.name(), "testName");
        assertEquals(userDto.password(), "testPass");
    }


}