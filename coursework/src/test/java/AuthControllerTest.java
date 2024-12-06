import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.Main;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testRegistrationFailurePasswordsDoNotMatch() throws Exception {
        mockMvc.perform(post("/register")
                .param("username", "testuser2")
                .param("password", "password123")
                .param("confirmPassword", "password321"))
                .andExpect(redirectedUrl("/register?error"));
    }

    @Test
    public void testRegistrationFailureUsernameAlreadyExists() throws Exception {
        mockMvc.perform(post("/register")
                .param("username", "okak")
                .param("password", "password")
                .param("confirmPassword", "password"))
                .andExpect(redirectedUrl("/register?error"));
    }

    @Test
    public void testRegistrationSuccess() throws Exception {
        mockMvc.perform(post("/register")
                .param("username", "testuser")
                .param("password", "password123")
                .param("confirmPassword", "password123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?success"));
    }

    @Test
    public void testLoginSuccess() throws Exception {
        mockMvc.perform(post("/login")
                .param("username", "okak")
                .param("password", "123"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/leaderboard-page"));
    }

    @Test
    public void testLoginFailure() throws Exception {
        mockMvc.perform(post("/login")
                .param("username", "invalidUser")
                .param("password", "wrongPassword"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/login?error"));
    }
    
}
