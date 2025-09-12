package org.france.airplanebackend.aircraft;

import org.france.airplanebackend.pilot.Pilot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class AircraftServiceTest {
    @Mock AircraftRepository aircraftRepository;

    @InjectMocks
            AircraftService aircraftService;

    Pilot pilot = new Pilot(1L, "Andrew", "France", 29);
    Aircraft testAc = new Aircraft(1L,"C-130",pilot);
    Aircraft testAc2 = new Aircraft(2L,"C-17",pilot);
    List<Aircraft> testList = new ArrayList<>();


    @Test
    void shouldSaveAircraft(){
        // Arrange - mocks the aircraft repo, return object mocked
        when(aircraftRepository.save(testAc)).thenReturn(testAc);
        // Act - call the save
        Aircraft actual = aircraftService.saveAircraft(testAc);
        // Assert
        verify(aircraftRepository, times(1)).save(any(Aircraft.class));
        assertThat(actual).isEqualTo(testAc);
    }

    @Test
    void shouldFindAll(){
        // Arrange
        testList.add(testAc);
        testList.add(testAc2);
        when(aircraftRepository.findAll()).thenReturn(testList);

        // Act
        List<Aircraft> actualList = aircraftService.findAllAircraft();

        // Assert
        verify(aircraftRepository, times(1)).findAll();
        assertThat(actualList).isEqualTo(testList);
    }

    @Test
    void shouldFindById(){
        // Arrange
        when(aircraftRepository.findById(1L)).thenReturn(Optional.of(testAc));

        //Act
        Aircraft actual = aircraftService.findAircraft(1L);

        // Assert
        verify(aircraftRepository, times(1)).findById(1L);
        assertThat(actual).isEqualTo(testAc);
    }
}