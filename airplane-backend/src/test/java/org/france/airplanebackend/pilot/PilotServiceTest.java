package org.france.airplanebackend.pilot;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PilotServiceTest {

    @Mock
    PilotRepository pilotRepository;

    @InjectMocks PilotService pilotService;

    Pilot testPilot = new Pilot(1L, "Bob", "Smith", 25);

    List<Pilot> testList = new ArrayList<>();

    @Test
    void shouldSavePilot(){
        // Arrange
        when(pilotRepository.save(testPilot)).thenReturn(testPilot);

        // Act
        Pilot acutal = pilotService.savePilot(testPilot);

        // Assert
        verify(pilotRepository).save(any(Pilot.class));
        assertThat(acutal).isEqualTo(testPilot);
    }

    @Test
    void shouldFindAllPilots(){
        // Arrange
        testList.add(testPilot);
        when(pilotRepository.findAll()).thenReturn(testList);

        // Act
        List<Pilot> actual = pilotService.findAllPilots();

        // Assert
        verify(pilotRepository).findAll();
        assertThat(actual).isEqualTo(testList);
    }

    @Test
    void shouldFindPilotById(){
        // Arrange
        when(pilotRepository.findById(testPilot.getId())).thenReturn(Optional.of(testPilot));

        // Act
        Pilot actual = pilotService.findPilotById(1L);

        // Assert
        verify(pilotRepository).findById(1L);
        assertThat(actual).isEqualTo(testPilot);
    }
}
