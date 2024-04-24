package nl.novi.finalAssignmentBackend.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.http.MediaType.APPLICATION_JSON;
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class GameControllerIntegrationTest {


    @Autowired
    MockMvc mockMvc;


    @Test
    void shouldCreateCorrectGameList() throws Exception{

        String requestJson = """
                {
                         "sellingPrice": 60.0,
                         "originalStock": 200,
                         "description": "Experience Middle-earth like never before in The Lord of the Rings: The Battle for Middle-Earth, the first The Lord of the Rings game that puts you in command of a real-time, open world. ",
                         "name": "Lord of the rings battle for middle earth",
                         "currentStock": 100,
                         "yearOfRelease": 2007,
                         "id": 1,
                         "platform": "PC",
                         "publisher": "EA Games",
                         "playDurationInMin": 300,
                         "amountSold": 10,
                         "currentStock": 100,
                         "purchasePrice": 10
          
                }
                """;

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/games")
                .contentType(APPLICATION_JSON)
                .content(requestJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());


    }
}
