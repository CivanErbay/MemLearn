/*
package memlearn.backend.controller;

import memlearn.backend.db.UserDb;
import memlearn.backend.model.LoginData;
import memlearn.backend.model.PlanningUser;
import memlearn.backend.security.JWTUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;


class LoginControllerTest {

    @LocalServerPort
    public int port;

    @Autowired
    public TestRestTemplate restTemplate;

    @Autowired
    public PasswordEncoder encoder;

    @Autowired
    public UserDb userDb;

    @Autowired
    public JWTUtils jwtUtils;

    @BeforeEach
    public void resetDb(){
        userDb.deleteAll();
    }

    @Test
    public void loginWithValidCredentials() {

        //GIVEN
        PlanningUser user = userDb.save(new PlanningUser("superUser", encoder.encode("savePassword"), "admin"));
        userDb.save(user);
    }
        //WHEN
        String url = "http://localhost:" + port + "auth/login";
        ResponseEntity<String> tokenResponse = restTemplate.postForEntity(url, new LoginData("superUser", "savePassword"), String.class);

        //THEN
        assertEquals(tokenResponse.getStatusCode(), HttpStatus.OK)
        assertTrue(jwtUtils.validateToken(tokenResponse.getBody(), "superUser"))
}*/
