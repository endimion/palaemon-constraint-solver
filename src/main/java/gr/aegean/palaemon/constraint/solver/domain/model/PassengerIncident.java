package gr.aegean.palaemon.constraint.solver.domain.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XStreamAlias("PassengerIncident")
@PlanningEntity
public class PassengerIncident {
    @PlanningId
    private String incidentId;

    private String name; //passenger name
    private String surname; //passenger surname
    private String passengerLanguage;
    private PassengerSpecialConditions healthCondition;
    private String deck;





    // Planning variables: changes during planning, between score calculations.
    @PlanningVariable(valueRangeProviderRefs = {
            "crewRange"})
    private CrewMember crewMember;

    //returns the distance of the assigned to the incident crew member
    // part of the planning is to minimize the sum of these values
    public int getAssignedMemberDistance(){
        return this.crewMember.getDistanceFromIncidents().get(this.incidentId);
    }
}
