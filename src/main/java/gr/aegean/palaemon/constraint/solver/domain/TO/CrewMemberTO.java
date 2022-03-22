package gr.aegean.palaemon.constraint.solver.domain.TO;

import gr.aegean.palaemon.constraint.solver.domain.model.AssignmentStatusEnum;
import gr.aegean.palaemon.constraint.solver.domain.model.CrewEmergencyRolesEnum;
import lombok.*;
import org.kie.api.definition.rule.All;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CrewMemberTO {
    private String crewMemberId;
    private String name;
    private String surname;
    private CrewEmergencyRolesEnum emergencyDuty;
    private List<String> spokenLanguages;
    private AssignmentStatusEnum assignmentStatus;
    private Map<String, Integer> distanceFromIncidents; // incidentID,distance
    private String deck;
}
