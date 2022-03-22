package gr.aegean.palaemon.constraint.solver.controllers;

import gr.aegean.palaemon.constraint.solver.domain.TO.AssignmentRequestTO;
import gr.aegean.palaemon.constraint.solver.domain.model.PassengerIncidentAssignment;
import gr.aegean.palaemon.constraint.solver.wrappers.WrapperUtils;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.stream.Collectors;

@Controller
public class AssignementControllers {

    @PostMapping("/makeAssignment")
    public @ResponseBody
    PassengerIncidentAssignment makeAssignement(@RequestBody AssignmentRequestTO assignmentRequestTO
    ) {
        PassengerIncidentAssignment unsolvedPassengerIncidentAssignment = new PassengerIncidentAssignment();
        unsolvedPassengerIncidentAssignment.setPassengerIncidentList(
                assignmentRequestTO.getIncidents().stream().map(WrapperUtils::transformIncidentTO2PassengerIncident).collect(Collectors.toList()));
        unsolvedPassengerIncidentAssignment.setCrewMemberList(
                assignmentRequestTO.getCrewMembers().stream().map(WrapperUtils::tranformCrewMemberTOtoCrewMember).collect(Collectors.toList()));

        SolverFactory<PassengerIncidentAssignment> solverFactory = SolverFactory.createFromXmlResource("PassengerIncidentSolverConfiguration.xml");
        Solver<PassengerIncidentAssignment> solver = solverFactory.buildSolver();

        return solver.solve(unsolvedPassengerIncidentAssignment);

    }
}
