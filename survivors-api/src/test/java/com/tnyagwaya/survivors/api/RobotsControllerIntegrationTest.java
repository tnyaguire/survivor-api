package com.tnyagwaya.survivors.api;

import com.tnyagwaya.survivors.robot.RobotService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@WebMvcTest(controllers = {RobotsController.class})
class RobotsControllerIntegrationTest {

    @MockBean
    RobotService robotService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldGetListOfRobots() throws Exception{
            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/robots"))
                    .andExpect(MockMvcResultMatchers.status().isOk());
    }
}