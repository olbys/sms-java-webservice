package rest;

import jpa.api.repositories.PersonneRepository;
import jpa.api.repositories.RepositoryFactory;
import jpa.model.Personne;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

@Path("/personne")
public class PersonneWebService {

    private static final PersonneRepository repository;

    static {
        repository = (PersonneRepository)
                RepositoryFactory.getRepository(PersonneRepository.class);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonnes() {

        Collection<Personne> personnes = repository.findAll();
        return Response.status(200)
                .entity(personnes).build();

    }


    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonne_(@PathParam("id") int id) {
        Personne personne = repository.findById(id);
        if (personne == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }
        return Response.status(Response.Status.OK)
                .entity(personne).build();

    }


    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPersonne(Personne personne) {

        if (!checkPersonne(personne)) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .build();
        }
        repository.save(personne);
        return Response.status(Response.Status.CREATED)
                .entity(personne).build();
    }


    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePersonne(Personne personne) {

        System.out.println(personne);
        if (personne == null || !checkPersonne(personne)) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .build();
        }
        repository.update(personne);
        return Response.status(Response.Status.OK)
                .entity(personne).build();

    }

    @DELETE
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePersonne(Personne personne) {
        if (personne == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .build();
        }
        repository.remove(personne);
        return Response.status(Response.Status.ACCEPTED)
                .build();
    }


    private boolean checkPersonne(Personne personne) {
        boolean isDone = true;
        if (personne.getName().isEmpty() || personne.getLastName().isEmpty()
                || personne.getCellPhone().isEmpty())
            isDone = false;

        return isDone;
    }
}
