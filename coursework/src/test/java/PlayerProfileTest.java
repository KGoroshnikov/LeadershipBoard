import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.Main;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;


@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class PlayerProfileTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetPlayerProfile() throws Exception {
        mockMvc.perform(get("/players/{id}", 15L)
            .with(user("okak").password("123").roles("ADMIN")))
            .andDo(result -> System.out.println("Response: " + result.getResponse().getContentAsString()))
            .andExpect(status().isOk());

    }
    
    @Test
    public void testPlayerNotFound() throws Exception {
        mockMvc.perform(get("/players/{id}", 999L)
            .with(user("okak").password("123").roles("ADMIN")))
            .andExpect(status().isNotFound());
    }
    
}   
