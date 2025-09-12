package org.france.airplanebackend.aircraft;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Aircraft {
    @Id
    private Long id;
    private String airframe;
    private String pilot;

    public Aircraft() {
        this( null,"","");
    }

    public Aircraft(Long id, String airframe, String pilot) {
        this.id = id;
        this.airframe = airframe;
        this.pilot = pilot;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAirframe() {
        return airframe;
    }

    public void setAirframe(String airframe) {
        this.airframe = airframe;
    }

    public String getPilot() {
        return pilot;
    }

    public void setPilot(String pilot) {
        this.pilot = pilot;
    }
}
