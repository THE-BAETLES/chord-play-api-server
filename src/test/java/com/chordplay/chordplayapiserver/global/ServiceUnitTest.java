package com.chordplay.chordplayapiserver.global;

import com.chordplay.chordplayapiserver.domain.entity.User;
import com.chordplay.chordplayapiserver.global.util.ContextUtil;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
public class ServiceUnitTest {

    private static MockedStatic<ContextUtil> contextUtilMockedStatic;

    @BeforeAll
    public static void staticInit(){
        User mockUser = createStaticMockUser();
        contextUtilMockedStatic = mockStatic(ContextUtil.class);
        contextUtilMockedStatic.when(()->ContextUtil.getPrincipalUserId()).thenReturn(mockUser.getId());
    }

    @AfterAll
    public static void after() {
        contextUtilMockedStatic.close();
    }

    public static User createStaticMockUser(){
        return User.builder()
                .id("6313b2381f8fa3bb122eaa78")
                .username("최현준")
                .email("test@gmail.com")
                .nickname("test")
                .roles("ROLE_USER")
                .build();
    }
    protected User createMockUser(){
        return User.builder()
                .id("6313b2381f8fa3bb122eaa78")
                .username("최현준")
                .email("test@gmail.com")
                .nickname("test")
                .roles("ROLE_USER")
                .build();
    }
}
