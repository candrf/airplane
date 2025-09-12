package org.france.airplanebackend.aircraft;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AircraftService {
    private final AircraftRepository aircraftRepository;

    public AircraftService(AircraftRepository aircraftRepository){
        this.aircraftRepository = aircraftRepository;
    }


    public Aircraft saveAircraft(Aircraft ac){
        return aircraftRepository.save(ac);
    }

    public Aircraft findAircraft(Long id){
        return aircraftRepository.findById(id).orElse(null);
    }

    public List<Aircraft> findAllAircraft() {
        return aircraftRepository.findAll();
    }
}
