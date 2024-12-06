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
public class LeaderboardTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testFilterByRegion() throws Exception {
        mockMvc.perform(get("/leaderboard")
                .with(user("okak").password("123").roles("ADMIN"))
                .param("region", "Europe"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].region", hasItems("Europe")));
    }
    @Test
    public void testFilterByPremiumStatus() throws Exception {
        mockMvc.perform(get("/leaderboard")
                .with(user("okak").password("123").roles("ADMIN"))
                .param("isPremium", "true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].isPremium").value(everyItem(equalTo(1))));
    }
    @Test
    public void testSortByScoreDescending() throws Exception {
        MvcResult result = mockMvc.perform(get("/leaderboard")
            .with(user("okak").password("123").roles("ADMIN"))
            .param("sortBy", "totalScore")
            .param("order", "desc"))
            .andReturn();
        //System.out.println("uou: " + result.getResponse().getContentAsString());
        JsonNode jsonResponse = new ObjectMapper().readTree(result.getResponse().getContentAsString());
        int firstScore = jsonResponse.get(0).get("totalScore").asInt();
        int secondScore = jsonResponse.get(1).get("totalScore").asInt();
        assertTrue(firstScore > secondScore);
    }
}   
