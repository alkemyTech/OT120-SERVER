package com.alkemy.ong.common;

import com.alkemy.ong.model.entity.Role;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.repository.IUserRepository;
import com.alkemy.ong.service.abstraction.IRoleService;
import java.sql.Timestamp;
import java.time.Instant;
import org.assertj.core.util.Lists;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;

public abstract class AbstractBaseIntegrationTest {

  protected static final long USER_ID = 1L;

  protected TestRestTemplate restTemplate = new TestRestTemplate();
  protected HttpHeaders headers = new HttpHeaders();

  @MockBean
  protected IUserRepository userRepository;

  @MockBean
  protected AuthenticationManager authenticationManager;

  @MockBean
  protected IRoleService roleService;

  @LocalServerPort
  private int port;

  protected String createURLWithPort(String uri) {
    return "http://localhost:" + port + uri;
  }

  protected void setAuthorizationHeaderBasedOn(String role) {
    String jwt = SecurityTestConfig.createToken("johnny@gmail.com", role);
    headers.set("Authorization", jwt);
  }

  protected Role stubRole(String name) {
    Role role = new Role();
    role.setName(name);
    return role;
  }

  protected User stubUser(String role) {
    return new User(USER_ID,
        "John",
        "Doe",
        "jonny@gmail.com",
        "123456789",
        "https://foo.jpg",
        Lists.list(stubRole(role)),
        Timestamp.from(Instant.now()),
        false);
  }
}
