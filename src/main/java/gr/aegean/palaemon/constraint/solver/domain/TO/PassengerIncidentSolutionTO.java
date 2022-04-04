package gr.aegean.palaemon.constraint.solver.domain.TO;

import gr.aegean.palaemon.constraint.solver.domain.model.CrewMember;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class PassengerIncidentSolutionTO {

    private List<IncidentAssignmentTO> passengerIncidentList;
    private List<CrewMemberTO> crewMemberList;
}

