package gr.aegean.palaemon.constraint.solver.domain.TO;

import gr.aegean.palaemon.constraint.solver.domain.model.CrewMember;
import gr.aegean.palaemon.constraint.solver.domain.model.PassengerSpecialConditions;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IncidentAssignmentTO {
    private String incidentId;

    private String name; //passenger name
    private String surname; //passenger surname
    private String passengerLanguage;
    private PassengerSpecialConditions healthCondition;
    private String deck;
    private String geofence;
    private String xLoc;
    private String yLoc;
    private List<CrewMemberTO> crewMembers;
}
