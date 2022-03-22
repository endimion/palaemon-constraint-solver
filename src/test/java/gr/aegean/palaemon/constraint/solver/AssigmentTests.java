package gr.aegean.palaemon.constraint.solver;

import gr.aegean.palaemon.constraint.solver.domain.model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AssigmentTests {

    static PassengerIncidentAssignment unsolvedPassengerIncidentAssignment;

    @BeforeAll
    public static void setUp() {
        unsolvedPassengerIncidentAssignment = new PassengerIncidentAssignment();

        // First Crew Member
        CrewMember crewMember1 = new CrewMember();
        crewMember1.setCrewMemberId("1");
        crewMember1.setDeck("Deck_1");
        crewMember1.setAssignmentStatus(AssignmentStatusEnum.FREE);
        crewMember1.setName("name1");
        crewMember1.setSurname("surname1");
        crewMember1.setEmergencyDuty(CrewEmergencyRolesEnum.BOAT_PREPARATION_UNIT);
        List<String> crw1Languages = new ArrayList<>();
        crw1Languages.add("en");
        crewMember1.setSpokenLanguages(crw1Languages);

        Map<String,Integer> crw1Distance = new HashMap<>();
        crw1Distance.put("incident1", 10);
        crw1Distance.put("incident2", 30);
        crewMember1.setDistanceFromIncidents(crw1Distance);

        // Second Crew Member
        CrewMember crewMember2 = new CrewMember();
        crewMember2.setCrewMemberId("2");
        crewMember2.setDeck("Deck_1");
        crewMember2.setAssignmentStatus(AssignmentStatusEnum.FREE);
        crewMember2.setName("name2");
        crewMember2.setSurname("surname2");
        crewMember2.setEmergencyDuty(CrewEmergencyRolesEnum.FIRST_AID_UNIT);
        List<String> crw2Languages = new ArrayList<>();
        crw2Languages.add("en");
        crw2Languages.add("gr");
        crewMember2.setSpokenLanguages(crw2Languages);

        Map<String,Integer> crw2Distance = new HashMap<>();
        crw2Distance.put("incident1", 9);
        crw2Distance.put("incident2", 35);
        crewMember2.setDistanceFromIncidents(crw2Distance);

        // Third Crew Member
        CrewMember crewMember3 = new CrewMember();
        crewMember3.setCrewMemberId("3");
        crewMember3.setDeck("Deck_1");
        crewMember3.setAssignmentStatus(AssignmentStatusEnum.FREE);
        crewMember3.setName("name3");
        crewMember3.setSurname("surname3");
        crewMember3.setEmergencyDuty(CrewEmergencyRolesEnum.MEDICAL_UNIT);
        List<String> crw3Languages = new ArrayList<>();
        crw3Languages.add("en");
        crewMember3.setSpokenLanguages(crw3Languages);

        Map<String,Integer> crw3Distance = new HashMap<>();
        crw3Distance.put("incident1", 11);
        crw3Distance.put("incident2", 36);
        crewMember3.setDistanceFromIncidents(crw3Distance);


        //Incidents

        PassengerIncident incident1 = new PassengerIncident();
        incident1.setIncidentId("incident1");
        incident1.setName("passengerName1");
        incident1.setSurname("passengerSurname1");
        incident1.setHealthCondition(PassengerSpecialConditions.NORMAL_PREGNANCY);
        incident1.setPassengerLanguage("en");

        PassengerIncident incident2 = new PassengerIncident();
        incident2.setIncidentId("incident2");
        incident2.setName("passengerName2");
        incident2.setSurname("passengerSurname2");
        incident2.setHealthCondition(PassengerSpecialConditions.MEDICAL_EQUIP_NEEDED);
        incident2.setPassengerLanguage("gr");


        List<CrewMember> crewMemberList = new ArrayList<>();
        crewMemberList.add(crewMember1);
        crewMemberList.add(crewMember2);
        crewMemberList.add(crewMember3);

        List<PassengerIncident> incidentList = new ArrayList<>();
        incidentList.add(incident1);
        incidentList.add(incident2);

        unsolvedPassengerIncidentAssignment.setCrewMemberList(crewMemberList);
        unsolvedPassengerIncidentAssignment.setPassengerIncidentList(incidentList);

    }



    @Test
    public void test_whenCustomJavaSolver() {

        SolverFactory<PassengerIncidentAssignment> solverFactory = SolverFactory.createFromXmlResource("PassengerIncidentSolverConfiguration.xml");
        Solver<PassengerIncidentAssignment> solver = solverFactory.buildSolver();
        PassengerIncidentAssignment solvedCourseSchedule = solver.solve(unsolvedPassengerIncidentAssignment);


        assertNotNull(solvedCourseSchedule.getScore());

    }

}
