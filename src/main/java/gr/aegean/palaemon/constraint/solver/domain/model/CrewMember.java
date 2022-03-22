package gr.aegean.palaemon.constraint.solver.domain.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.*;
import org.optaplanner.core.api.domain.lookup.PlanningId;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static gr.aegean.palaemon.constraint.solver.domain.model.PassengerSpecialConditions.*;
import static gr.aegean.palaemon.constraint.solver.domain.model.PassengerSpecialConditions.HEARING_IMPAIRED;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@XStreamAlias("CrewMember")
public class CrewMember {


    @PlanningId
    private String crewMemberId;

    private String name;
    private String surname;
    private CrewEmergencyRolesEnum emergencyDuty;
    private List<String> spokenLanguages;

    private AssignmentStatusEnum assignmentStatus;

    private String deck;

    private Map<String, Integer> distanceFromIncidents; // incidentID,distance
    //in meters from each incident: with the distance from each incident. The first param is the incidentID


    //returns true if the person is capable of assisting for the given passenger condition
    public boolean canAssist(PassengerSpecialConditions condition) {
        switch (this.emergencyDuty) {
            case BOAT_PREPARATION_UNIT:
                return Arrays.stream((new PassengerSpecialConditions[]{GAIT, NORMAL_PREGNANCY, VISUALLY_IMPAIRED})).collect(Collectors.toList()).contains(condition);
            case FIRST_RESPONSE_UNIT:
            case MEDICAL_UNIT:
            case FIRST_AID_UNIT:
                return Arrays.stream((new PassengerSpecialConditions[]{GAIT, NORMAL_PREGNANCY, VISUALLY_IMPAIRED,
                        HEARING_IMPAIRED, COGNITIVE_IMPAIRED, COMPLICATED_PREGNANCY, HEAVE_DOSES, MEDICAL_EQUIP_NEEDED, STRETCHER,
                        SEVER_WALKING_DISABILITY})).collect(Collectors.toList()).contains(condition);
            case PASSENGER_ASSISTANCE_UNIT:
            case CABIN_SEARCH_UNIT:
                return Arrays.stream((new PassengerSpecialConditions[]{GAIT, NORMAL_PREGNANCY, VISUALLY_IMPAIRED,
                        HEARING_IMPAIRED, COGNITIVE_IMPAIRED, COMPLICATED_PREGNANCY, HEAVE_DOSES,
                        SEVER_WALKING_DISABILITY})).collect(Collectors.toList()).contains(condition);
            case PASSENGER_MUSTERING_UNIT:
            default:
                return false;
        }
    }


}
