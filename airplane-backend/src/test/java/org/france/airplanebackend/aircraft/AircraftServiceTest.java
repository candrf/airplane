package org.france.airplanebackend.aircraft;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AircraftServiceTest {
    @Mock AircraftRepository aircraftRepository;

    @InjectMocks
            AircraftService aircraftService;

    Aircraft testAc = new Aircraft(1L,"C-130","John");

    @Test
    void shouldSaveAircraft(){
        when(aircraftRepository.save(testAc)).thenReturn(testAc);
    }

    @Test
    void saveAircraft() {
    }

    @Test
    void findAircraft() {
    }

    @Test
    void findAllAircraft() {
    }
}