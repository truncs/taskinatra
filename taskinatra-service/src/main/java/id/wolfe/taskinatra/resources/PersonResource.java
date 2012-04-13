package id.wolfe.taskinatra.resources;

import com.yammer.dropwizard.jersey.params.LongParam;
import id.wolfe.taskinatra.api.Person;
import id.wolfe.taskinatra.db.PeopleDAO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/person/{personId}")
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {

    private final PeopleDAO peopleDAO;

    public PersonResource(PeopleDAO peopleDAO) {
        this.peopleDAO = peopleDAO;
    }

    @GET
    public Person getPerson(@PathParam("personId") LongParam personId) {
        return peopleDAO.findById(personId.get());
    }

}