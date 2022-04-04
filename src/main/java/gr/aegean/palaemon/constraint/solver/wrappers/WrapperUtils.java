package gr.aegean.palaemon.constraint.solver.wrappers;

import gr.aegean.palaemon.constraint.solver.domain.TO.CrewMemberTO;
import gr.aegean.palaemon.constraint.solver.domain.TO.IncidentAssignmentTO;
import gr.aegean.palaemon.constraint.solver.domain.TO.IncidentTO;
import gr.aegean.palaemon.constraint.solver.domain.model.CrewMember;
import gr.aegean.palaemon.constraint.solver.domain.model.PassengerIncident;

import java.util.ArrayList;

public class WrapperUtils {

    public static CrewMember tranformCrewMemberTOtoCrewMember(CrewMemberTO crewMemberTO) {
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

    public static CrewMemberTO transformCrewMember2CrewMemberTO(CrewMember  crewMember) {
        CrewMemberTO result = new CrewMemberTO();
        result.setCrewMemberId(crewMember.getCrewMemberId());
        result.setSpokenLanguages(crewMember.getSpokenLanguages());
        result.setDistanceFromIncidents(crewMember.getDistanceFromIncidents());
        result.setName(crewMember.getName());
        result.setSurname(crewMember.getSurname());
        result.setDeck(crewMember.getDeck());
        result.setEmergencyDuty(crewMember.getEmergencyDuty());
        result.setAssignmentStatus(crewMember.getAssignmentStatus());
        return result;
    }

    public static PassengerIncident transformIncidentTO2PassengerIncident(IncidentTO incident) {
        PassengerIncident result = new PassengerIncident();
        result.setIncidentId(incident.getIncidentId());
        result.setPassengerLanguage(incident.getPassengerLanguage());
        result.setHealthCondition(incident.getHealthCondition());
        result.setDeck(incident.getDeck());
        result.setName(incident.getName());
        result.setSurname(incident.getSurname());
        result.setGeofence(incident.getGeofence());
        result.setXLoc(incident.getXLoc());
        result.setYLoc(incident.getYLoc());
        return result;
    }

    public static IncidentAssignmentTO mapIncident2IncidentAssignmentTO(PassengerIncident incident) {
        IncidentAssignmentTO result = new IncidentAssignmentTO();
        result.setIncidentId(incident.getIncidentId());
        result.setPassengerLanguage(incident.getPassengerLanguage());
        result.setHealthCondition(incident.getHealthCondition());
        result.setDeck(incident.getDeck());
        result.setName(incident.getName());
        result.setSurname(incident.getSurname());
        result.setGeofence(incident.getGeofence());
        result.setXLoc(incident.getXLoc());
        result.setYLoc(incident.getYLoc());
        result.setCrewMembers(new ArrayList<>());
        result.getCrewMembers().add(transformCrewMember2CrewMemberTO(incident.getCrewMember()));
        return result;
    }


}
