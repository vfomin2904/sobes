package ru.geekbrains.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.geekbrains.persist.User;
import ru.geekbrains.persist.UserRepository;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
@SpringBootTest
public class UserResourceTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindAllUsers() throws Exception {
        User user = new User(null,
                "test_firstname",
                "test_lastname",
                "test_login",
                "test_password");
        user = userRepository.save(user);

        mvc.perform(MockMvcRequestBuilders
                .get("/user/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].firstname", is(user.getFirstname())));
    }

    @Test
    public void testFindByIdUsers() throws Exception {
        User user = new User(null,
                "test_firstname",
                "test_lastname",
                "test_login",
                "test_password");
        user = userRepository.save(user);

        mvc.perform(MockMvcRequestBuilders
                        .get("/user/" + user.getId() +  "/id")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", is(user.getFirstname())));
    }
}
