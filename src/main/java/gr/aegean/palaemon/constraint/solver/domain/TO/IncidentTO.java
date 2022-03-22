package gr.aegean.palaemon.constraint.solver.domain.TO;

import com.fasterxml.jackson.annotation.JsonProperty;
import gr.aegean.palaemon.constraint.solver.domain.model.PassengerSpecialConditions;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class IncidentTO {

    private String incidentId;
    private String name; //passenger name
    private String surname; //passenger surname
    private String passengerLanguage;
    private PassengerSpecialConditions healthCondition;
    private String deck;
    private String geofence;
    @JsonProperty("x_loc")
    private String xLoc;
    @JsonProperty("y_loc")
    private String yLoc;
}
