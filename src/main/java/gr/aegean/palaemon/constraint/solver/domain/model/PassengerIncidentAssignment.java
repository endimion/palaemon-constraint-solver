package gr.aegean.palaemon.constraint.solver.domain.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import lombok.ToString;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.persistence.xstream.api.score.buildin.hardsoft.HardSoftScoreXStreamConverter;

import java.util.List;

@PlanningSolution
@XStreamAlias("PassengerIncidentAssignment")
@ToString
public class PassengerIncidentAssignment {

    private List<PassengerIncident> passengerIncidentList;
    private List<CrewMember> crewMemberList;
    @XStreamConverter(HardSoftScoreXStreamConverter.class)
    private HardSoftScore score;

    public  PassengerIncidentAssignment(){}
    public  PassengerIncidentAssignment(List<PassengerIncident> passengerIncidentList, List<CrewMember> crewMemberList){
        this.passengerIncidentList=passengerIncidentList;
        this.crewMemberList=crewMemberList;
    }

    @ValueRangeProvider(id = "crewRange") //this which does not change in the problem (i.e. the constants)
    @ProblemFactCollectionProperty
    public List<CrewMember> getCrewMemberList() {
        return crewMemberList;
    }
    public void setCrewMemberList(List<CrewMember> crewMemberList){
        this.crewMemberList=crewMemberList;
    }

    @PlanningEntityCollectionProperty //what is being assigned
    public List<PassengerIncident> getPassengerIncidentList() {
        return this.passengerIncidentList;
    }
    public void setPassengerIncidentList(List<PassengerIncident> processList) {
        this.passengerIncidentList = processList;
    }

    @PlanningScore
    public HardSoftScore getScore() {
        return score;
    }

    public void setScore(HardSoftScore score) {
        this.score = score;
    }


}

