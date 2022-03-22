package gr.aegean.palaemon.constraint.solver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gr.aegean.palaemon.constraint.solver.domain.TO.AssignmentRequestTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MarshallingTests {


    @Test
    public void testMarshallingAssigmentRequest() throws JsonProcessingException {

        String input = "{\n" +
                "\t\n" +
                "\t\"crewMembers\": [{\n" +
                "\t\t\"crewMemberId\": \"id1\",\n" +
                "\t\t\"name\":\"name1\",\n" +
                "\t\t\"surname\":\"surname1\",\n" +
                "\t\t\"emergencyDuty\":\"medical_unit\",\n" +
                "\t\t\"spokenLanguages\": [\"en\"],\n" +
                "\t\t\"assignmentStatus\":\"UNASSIGNED\",\n" +
                "\t\t\"distanceFromIncidents\":{\"incident1\":100, \"incident2\":90},\n" +
                "\t\t\"deck\":\"deck1\"\n" +
                "\t}],\n" +
                "\t\"incidents\": [{\n" +
                "\t\t\"incidentId\":\"incident1\",\n" +
                "\t\t\"name\":\"passName1\",\n" +
                "\t\t\"surname\":\"passSurname1\",\n" +
                "\t\t\"passengerLanguage\":\"en\",\n" +
                "\t\t\"healthCondition\":\"severe_walking_disability\",\n" +
                "\t\t\"deck\":\"deck1\"\n" +
                "\t\t\n" +
                "\t\t\n" +
                "\t}]\n" +
                "\t\n" +
                "}";

        ObjectMapper mapper = new ObjectMapper();
        AssignmentRequestTO result = mapper.readValue(input, AssignmentRequestTO.class);

        assertEquals(result.getCrewMembers().size(),1);

    }
}
