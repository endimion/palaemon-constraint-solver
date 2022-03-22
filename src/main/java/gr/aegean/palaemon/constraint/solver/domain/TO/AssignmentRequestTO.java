package gr.aegean.palaemon.constraint.solver.domain.TO;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentRequestTO {

    private List<CrewMemberTO> crewMembers;
    private List<IncidentTO> incidents;

}
