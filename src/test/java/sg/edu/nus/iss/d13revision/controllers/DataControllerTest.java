package sg.edu.nus.iss.d13revision.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DataController.class)
public class DataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void healthCheck_shouldReturnOk() throws Exception {
        mockMvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(content().string("HEALTH CHECK OK!"));
    }

    @Test
    void version_shouldReturnVersionString() throws Exception {
        mockMvc.perform(get("/version"))
            .andExpect(status().isOk())
            .andExpect(content().string("The actual version is 1.0.0"));
    }

    @Test
    void nations_shouldReturnArrayOf10WithExpectedFields() throws Exception {
        mockMvc.perform(get("/nations"))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$.length()").value(10))
            .andExpect(jsonPath("$[0].nationality").exists())
            .andExpect(jsonPath("$[0].capitalCity").exists())
            .andExpect(jsonPath("$[0].flag").exists())
            .andExpect(jsonPath("$[0].language").exists());
    }

    @Test
    void currencies_shouldReturnArrayOf20WithExpectedFields() throws Exception {
        mockMvc.perform(get("/currencies"))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$.length()").value(20))
            .andExpect(jsonPath("$[0].name").exists())
            .andExpect(jsonPath("$[0].code").exists());
    }
}
