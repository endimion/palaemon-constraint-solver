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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final CrewMemberTO other = (CrewMemberTO) obj;

        return this.getCrewMemberId().equals(other.getCrewMemberId());
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.crewMemberId == null ? 0 : this.crewMemberId.toUpperCase().hashCode());
        return hash;
    }



}
