package org.france.airplanebackend.pilot;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pilot")
public class PilotController {

    private final PilotService pilotService;

    PilotController(PilotService pilotService){
        this.pilotService = pilotService;
    }

    @PostMapping
    public Pilot createPilot(@RequestBody Pilot pilot){
        return pilotService.savePilot(pilot);
    }

    @GetMapping
    public ResponseEntity<List<Pilot>> findAllPilots(){
        return ResponseEntity.ok(pilotService.findAllPilots());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pilot> findPilotById(@PathVariable Long id){
        return ResponseEntity.ok(pilotService.findPilotById(id));
    }
}
