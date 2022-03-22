package gr.aegean.palaemon.constraint.solver.wrappers;

import gr.aegean.palaemon.constraint.solver.domain.TO.CrewMemberTO;
import gr.aegean.palaemon.constraint.solver.domain.TO.IncidentTO;
import gr.aegean.palaemon.constraint.solver.domain.model.CrewMember;
import gr.aegean.palaemon.constraint.solver.domain.model.PassengerIncident;

public class WrapperUtils {

    public static CrewMember tranformCrewMemberTOtoCrewMember(CrewMemberTO crewMemberTO){
        CrewMember result = new CrewMember();
        result.setCrewMemberId(crewMemberTO.getCrewMemberId());
        result.setSpokenLanguages(crewMemberTO.getSpokenLanguages());
        result.setDistanceFromIncidents(crewMemberTO.getDistanceFromIncidents());
        result.setName(crewMemberTO.getName());
        result.setSurname(crewMemberTO.getSurname());
        result.setDeck(crewMemberTO.getDeck());
        result.setEmergencyDuty(crewMemberTO.getEmergencyDuty());
        result.setAssignmentStatus(crewMemberTO.getAssignmentStatus());
        return result;
    }

    public static PassengerIncident transformIncidentTO2PassengerIncident(IncidentTO incident){
        PassengerIncident result = new PassengerIncident();
        result.setIncidentId(incident.getIncidentId());
        result.setPassengerLanguage(incident.getPassengerLanguage());
        result.setHealthCondition(incident.getHealthCondition());
        result.setDeck(incident.getDeck());
        result.setName(incident.getName());
        result.setSurname(incident.getSurname());
        return result;
    }
}
