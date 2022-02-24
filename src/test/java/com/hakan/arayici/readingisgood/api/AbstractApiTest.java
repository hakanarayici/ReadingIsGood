package com.hakan.arayici.readingisgood.api;

import com.hakan.arayici.readingisgood.config.JwtAuthenticationEntryPoint;
import com.hakan.arayici.readingisgood.service.security.JwtUserDetailService;
import com.hakan.arayici.readingisgood.util.JWTTokenUtil;
import org.junit.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.web.servlet.MockMvc;


public abstract class AbstractApiTest {

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

    @Autowired
    public MockMvc mockMvc;

    @MockBean
    private JwtUserDetailService jwtUserDetailService;

    @MockBean
    private JWTTokenUtil jwtTokenUtil;

    @MockBean
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;


}
