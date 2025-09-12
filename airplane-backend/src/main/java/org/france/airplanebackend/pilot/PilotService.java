package org.france.airplanebackend.pilot;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PilotService {
    private final PilotRepository pilotRepository;

    public PilotService(PilotRepository pilotRepository) {
        this.pilotRepository = pilotRepository;
    }

    public Pilot savePilot(Pilot newPilot){
        return pilotRepository.save(newPilot);
    }

    public List<Pilot> findAllPilots(){
        return pilotRepository.findAll();
    }

    public Pilot findPilotById(Long id){
        return pilotRepository.findById(id).get();
    }
}
