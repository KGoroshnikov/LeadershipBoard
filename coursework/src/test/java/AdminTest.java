import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;


@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class AdminTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAddPlayer() throws Exception {
        mockMvc.perform(post("/players")
        .with(user("okak").password("123").roles("ADMIN"))
        .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"NewPlayer\", \"region\": \"Europe\", \"totalScore\": 100, \"isPremium\": 1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("NewPlayer"))
                .andExpect(jsonPath("$.region").value("Europe"));
    }


    @Test
    public void testDeletePlayer() throws Exception {
        MvcResult result = mockMvc.perform(post("/players")
            .with(user("okak").password("123").roles("ADMIN"))
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\": \"PlayerToDelete\", \"region\": \"Asia\", \"totalScore\": 50, \"isPremium\": 0}"))
            .andExpect(status().isOk())
            .andReturn();
        
        String response = result.getResponse().getContentAsString();
        Integer playerId = new ObjectMapper().readTree(response).get("id").asInt();

        mockMvc.perform(delete("/admin/players/{id}", playerId)
        .with(user("okak").password("123").roles("ADMIN")))
                .andExpect(status().isOk());

        mockMvc.perform(get("/players/{id}", playerId)
        .with(user("okak").password("123").roles("ADMIN")))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testEditPlayer() throws Exception {
        MvcResult result = mockMvc.perform(post("/players")
            .with(user("okak").password("123").roles("ADMIN"))
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\": \"PlayerToEdit\", \"region\": \"Asia\", \"totalScore\": 50, \"isPremium\": 0}"))
            .andExpect(status().isOk())
            .andReturn();
        
        String response = result.getResponse().getContentAsString();
        Integer playerId = new ObjectMapper().readTree(response).get("id").asInt();

        mockMvc.perform(put("/admin/players/{id}", playerId)
                .with(user("okak").password("123").roles("ADMIN"))
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"EditedPlayer\", \"region\": \"Europe\", \"totalScore\": 300, \"isPremium\": 1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("EditedPlayer"))
                .andExpect(jsonPath("$.region").value("Europe"))
                .andExpect(jsonPath("$.totalScore").value(300))
                .andExpect(jsonPath("$.isPremium").value(1));
    }

}   
