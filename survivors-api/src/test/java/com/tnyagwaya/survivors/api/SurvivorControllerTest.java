package com.tnyagwaya.survivors.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tnyagwaya.survivors.data.FlagInfectionRequest;
import com.tnyagwaya.survivors.data.SurvivorInfo;
import com.tnyagwaya.survivors.survivor.Location;
import com.tnyagwaya.survivors.survivor.SurvivorService;
import common.AbstractTestUtilities;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(controllers = {SurvivorController.class})
class SurvivorControllerTest extends AbstractTestUtilities {

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SurvivorService survivorService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldAddSurvivorIfRequestIsValid() throws Exception {
        final SurvivorInfo info = getSurvivorInfo();
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/survivors")
                        .content(objectMapper.writeValueAsString(info))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }


    @Test
    public void shouldUpdateSurvivorLocation() throws Exception {
        final SurvivorInfo info = getSurvivorInfo();

      mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/survivors")
                        .content(objectMapper.writeValueAsString(info))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        final Location location = new Location(new BigDecimal(-17.82300), new BigDecimal(31.01000));

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/survivors/{survivorId}/location",info.getSurvivorId())
                        .content(objectMapper.writeValueAsString(location))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(jsonPath("$.latitude").value(new BigDecimal(-17.82300)));
    }

    @Test
    public void shouldReportInfectedSurvivor() throws Exception {
        final SurvivorInfo info = getSurvivorInfo();
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/survivors")
                        .content(objectMapper.writeValueAsString(info))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        final FlagInfectionRequest flagInfectionRequest = new FlagInfectionRequest(info.getSurvivorId(),"bug-reporter-1");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/survivors/flag")
                        .content(objectMapper.writeValueAsString(flagInfectionRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(jsonPath("$.survivorId").value(info.getSurvivorId()));

    }




}