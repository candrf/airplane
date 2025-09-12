package org.france.airplanebackend.aircraft;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class AircraftRepositoryTest {

    @Autowired
    private AircraftRepository aircraftRepository;


    @Test
    void shouldSaveAndFindAircraft() {
        Aircraft ac = new Aircraft(1L, "C-130", "John");
        Aircraft saved = aircraftRepository.save(ac);

        Optional<Aircraft> found = aircraftRepository.findById(saved.getId());

        assertThat(found).isPresent();
        assertThat(found.get().getAirframe()).isEqualTo("C-130");
    }

}
