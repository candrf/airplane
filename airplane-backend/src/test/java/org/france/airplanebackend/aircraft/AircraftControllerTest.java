package org.france.airplanebackend.aircraft;

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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureMockMvc
public class AircraftControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    AircraftService aircraftService;

    Aircraft ac1 = new Aircraft(1L, "C-130", "John");
    Aircraft ac2 = new Aircraft(2L, "C-17", "Jim");
    ArrayList<Aircraft> acList = new ArrayList<>();


    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldCreateAircraft() throws Exception{
        Mockito.when(aircraftService.saveAircraft(any(Aircraft.class)))
                .thenReturn(ac1);

        String aircraftJson = objectMapper.writeValueAsString(ac1);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/aircraft")
                .contentType(MediaType.APPLICATION_JSON)
                .content(aircraftJson))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.airframe").value("C-130"))
                .andExpect(jsonPath("$.pilot").value("John"));
        Mockito.verify(aircraftService).saveAircraft(any(Aircraft.class));

    }

    @Test
    public void shouldFindAllAircraft() throws Exception{
        acList.add(ac1);
        acList.add(ac2);
        Mockito.when(aircraftService.findAllAircraft()).thenReturn(acList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/aircraft"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());

        Mockito.verify(aircraftService).findAllAircraft();
    }

    @Test
    public void shouldFindAircraftById() throws Exception{
        Long acId = 1L;
        Mockito.when(aircraftService.findAircraft(acId)).thenReturn(ac1);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/aircraft/{id}", acId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));

        Mockito.verify(aircraftService).findAircraft(acId);
    }

}
