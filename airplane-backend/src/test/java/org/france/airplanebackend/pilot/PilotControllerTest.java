package org.france.airplanebackend.pilot;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PilotController.class)
@AutoConfigureMockMvc
public class PilotControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    PilotService pilotService;

    Pilot pilot1 = new Pilot(1L, "Jim", "Joe", 35);
    Pilot pilot2 = new Pilot(2L, "Bobby", "Bob", 28);
    ArrayList<Pilot> pilotList = new ArrayList<>();


    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreatePilot() throws Exception {
        // Arrange
        String testJson = objectMapper.writeValueAsString(pilot1);
        when(pilotService.savePilot(any(Pilot.class))).thenReturn(pilot1);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/pilot")
                .contentType(MediaType.APPLICATION_JSON)
                .content(testJson))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.firstName").value("Jim"));
        verify(pilotService).savePilot(any(Pilot.class));
    }

    @Test
    void shouldFindAllPilots() throws Exception {
        when(pilotService.findAllPilots()).thenReturn(pilotList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/pilot"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());

        verify(pilotService).findAllPilots();
    }

    @Test
    public void shouldFindPilotById() throws Exception{
        Long pilotId = 1L;
        Mockito.when(pilotService.findPilotById(pilotId)).thenReturn(pilot1);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/pilot/{id}", pilotId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));

        Mockito.verify(pilotService).findPilotById(pilotId);
    }
}
