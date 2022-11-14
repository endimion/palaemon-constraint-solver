package gr.aegean.palaemon.constraint.solver.domain.solver;

import gr.aegean.palaemon.constraint.solver.domain.model.AssignmentStatusEnum;
import gr.aegean.palaemon.constraint.solver.domain.model.PassengerIncident;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;
import org.optaplanner.core.api.score.stream.Joiners;

public class PassengerIncidentConstraintProvider implements ConstraintProvider {


    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[]{
                noOverlappingAssignments(constraintFactory),
                minimizeDistances(constraintFactory),
                speakingTheLanguage(constraintFactory),
                ableToHandleIncident(constraintFactory),
                crewNotAssigned(constraintFactory)
        };
    }

    // Hard Constraint the crew member cannot be assigned to two different incidents
    Constraint noOverlappingAssignments(ConstraintFactory constraintFactory) {
        return constraintFactory.forEachUniquePair(PassengerIncident.class, Joiners.equal(PassengerIncident::getCrewMember))
                .penalize("Crew Overlap", HardSoftScore.ONE_HARD, (passengerIncident, passengerIncident2) -> 100);
    }

    // soft constraint the crew member that is the closest should be assigned to the resolvent of the incident
    Constraint minimizeDistances(ConstraintFactory constraintFactory) {
        return constraintFactory.forEach(PassengerIncident.class)
                .penalize("Minimize the distance for each assigned crewMember",  HardSoftScore.ONE_SOFT,
                        PassengerIncident::getAssignedMemberDistance);
    }

    // soft constraint the crew member should be able to speak with the passenger
    // this has a much lower score than the distance
    Constraint speakingTheLanguage(ConstraintFactory constraintFactory) {
        return constraintFactory.forEach(PassengerIncident.class).filter(passengerIncident ->
                        !passengerIncident.getCrewMember().getSpokenLanguages().contains(passengerIncident.getPassengerLanguage()))
                .penalize("Passenger should be preferably speaking the language",  HardSoftScore.ONE_SOFT, (incident) -> 1);
    }


    // hard constraint. The Crew Member should be capable of handling the specifics of the incident
    Constraint ableToHandleIncident(ConstraintFactory constraintFactory) {
        return constraintFactory.forEach(PassengerIncident.class)
                .filter(passengerIncident -> !passengerIncident.getCrewMember().canAssist(passengerIncident.getHealthCondition()))
                .penalize("Crew Member must be trained to handle the specifics of the incident",  HardSoftScore.ONE_HARD,value -> 2);
    }

    // hard constraint. The Crew Member should not be already assigned to another incident
    Constraint crewNotAssigned(ConstraintFactory constraintFactory) {
        return constraintFactory.forEach(PassengerIncident.class)
                .filter(passengerIncident -> passengerIncident.getCrewMember().getAssignmentStatus().equals(AssignmentStatusEnum.ASSIGNED))
                .penalize("Crew Member must not be  already assigned to another incident",  HardSoftScore.ONE_HARD, value -> 20);
    }


    // hard constraint. Only one crew member should be assigned
    Constraint onlyOneCrewMember(ConstraintFactory constraintFactory) {
        return constraintFactory.forEach(PassengerIncident.class)
                .filter(passengerIncident -> passengerIncident.getCrewMember().getAssignmentStatus().equals(AssignmentStatusEnum.ASSIGNED))
                .penalize("Crew Member must not be  already assigned to another incident",  HardSoftScore.ONE_HARD, value -> 20);
    }


}
