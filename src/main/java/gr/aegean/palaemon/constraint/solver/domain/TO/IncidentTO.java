package gr.aegean.palaemon.constraint.solver.domain.TO;

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
}
