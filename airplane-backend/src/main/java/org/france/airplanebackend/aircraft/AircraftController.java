package org.france.airplanebackend.aircraft;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aircraft")
public class AircraftController {

    private final AircraftService aircraftService;

    public AircraftController(AircraftService aircraftService){
        this.aircraftService = aircraftService;
    }

    @PostMapping
    public Aircraft createAircraft(@RequestBody Aircraft ac){

        return aircraftService.saveAircraft(ac);
    }

    @GetMapping()
    public ResponseEntity<List<Aircraft>> getAircraft(){
        return ResponseEntity.ok(aircraftService.findAllAircraft());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aircraft> getAircraftById(@PathVariable Long id){

        return ResponseEntity.ok(aircraftService.findAircraft(id));
    }
}
