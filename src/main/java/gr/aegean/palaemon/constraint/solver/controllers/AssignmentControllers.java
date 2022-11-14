package gr.aegean.palaemon.constraint.solver.controllers;

import gr.aegean.palaemon.constraint.solver.domain.TO.AssignmentRequestTO;
import gr.aegean.palaemon.constraint.solver.domain.TO.CrewMemberTO;
import gr.aegean.palaemon.constraint.solver.domain.TO.PassengerIncidentSolutionTO;
import gr.aegean.palaemon.constraint.solver.domain.TO.IncidentAssignmentTO;
import gr.aegean.palaemon.constraint.solver.domain.model.PassengerIncident;
import gr.aegean.palaemon.constraint.solver.domain.model.PassengerIncidentAssignment;
import gr.aegean.palaemon.constraint.solver.domain.model.PassengerSpecialConditions;
import gr.aegean.palaemon.constraint.solver.wrappers.WrapperUtils;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class AssignmentControllers {

    @PostMapping("/makeAssignment")
    public @ResponseBody
    PassengerIncidentSolutionTO makeAssignment(@RequestBody AssignmentRequestTO assignmentRequestTO
    ) {
        PassengerIncidentAssignment unsolvedPassengerIncidentAssignment = new PassengerIncidentAssignment();
        unsolvedPassengerIncidentAssignment.setPassengerIncidentList(
                assignmentRequestTO.getIncidents().stream().map(WrapperUtils::transformIncidentTO2PassengerIncident).collect(Collectors.toList()));
        unsolvedPassengerIncidentAssignment.setCrewMemberList(
                assignmentRequestTO.getCrewMembers().stream().map(WrapperUtils::tranformCrewMemberTOtoCrewMember).collect(Collectors.toList()));

        SolverFactory<PassengerIncidentAssignment> solverFactory = SolverFactory.createFromXmlResource("PassengerIncidentSolverConfiguration.xml");
        Solver<PassengerIncidentAssignment> solver = solverFactory.buildSolver();

        PassengerIncidentAssignment theAssignment = solver.solve(unsolvedPassengerIncidentAssignment);

        //get the CrewMembers that were assigned
        List<String> assignedCrewIds = theAssignment.getPassengerIncidentList().stream().map(passengerIncident ->
                passengerIncident.getCrewMember().getCrewMemberId()).collect(Collectors.toList());
        //get the CrewMembers that are free
        List<CrewMemberTO> freeCrewMembers = assignmentRequestTO.getCrewMembers().stream().filter(crewMemberTO -> {
            return !assignedCrewIds.contains(crewMemberTO.getCrewMemberId());
        }).collect(Collectors.toList());
        // generate the solution with only one Crew Member assigned
        PassengerIncidentSolutionTO response = new PassengerIncidentSolutionTO();
        response.setCrewMemberList(theAssignment.getCrewMemberList().
                stream().map(WrapperUtils::transformCrewMember2CrewMemberTO).collect(Collectors.toList()));
        // response.setPassengerIncidentList();
        response.setPassengerIncidentList(theAssignment.getPassengerIncidentList().
                stream().map(WrapperUtils::mapIncident2IncidentAssignmentTO).collect(Collectors.toList()));

        // if the provided Crew Members are more than the Incidents
        if (assignmentRequestTO.getCrewMembers().size() > assignmentRequestTO.getIncidents().size()) {
            // For each of the incident fetch the incident type
            response.getPassengerIncidentList().
//                    filter(incidentAssignmentTO -> {
//                        return incidentAssignmentTO.getHealthCondition().equals(PassengerSpecialConditions.STRETCHER)
//                                || incidentAssignmentTO.getHealthCondition().equals(PassengerSpecialConditions.UNABLE_TO_WALT)
//                                || incidentAssignmentTO.getHealthCondition().equals(PassengerSpecialConditions.MEDICAL_EQUIP_NEEDED)
//                                || incidentAssignmentTO.getHealthCondition().equals(PassengerSpecialConditions.SEVER_WALKING_DISABILITY);
//                    }).
                    forEach(incidentAssignmentTO -> {
                        // select the crew member that is the closest to the incident
                        String incidentId = incidentAssignmentTO.getIncidentId();
                        Optional<CrewMemberTO> memberToAdd = freeCrewMembers.stream().filter(crewMemberTO -> {
                            return crewMemberTO.getDistanceFromIncidents().get(incidentId) != null &&
                                    crewMemberTO.getDistanceFromIncidents().get(incidentId) < 1000; //TODO
                        }).findAny();
                        // add it to the assignment and remove it from the free
                        memberToAdd.ifPresent(crewMemberTO -> {
                            incidentAssignmentTO.getCrewMembers().add(crewMemberTO);
                            freeCrewMembers.remove(memberToAdd.get());
                        });
                    });


        }


        return response;
    }


}
