package rest;

import jpa.api.repositories.RepositoryFactory;
import jpa.api.repositories.UserRepository;
import jpa.model.Utilisateur;
import org.codehaus.jettison.json.JSONObject;
import utils.security.PasswordUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collection;

@Path("/auth")
public class AuthWebService {

    private static final UserRepository repository;

    static {
        repository = (UserRepository)
                RepositoryFactory.getRepository(UserRepository.class);
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response authLogin(JSONObject resource) {

        if (resource == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }
        String login = null;
        String password = null;
        Collection<Utilisateur> utilisateurs = new ArrayList<>();
        try {
            password = resource.getString("password");
            login = resource.getString("login");
            utilisateurs = repository.findByLogin(login);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        for (Utilisateur utilisateur : utilisateurs) {
            if (isRegisted(utilisateur, password)) {
                return Response.status(Response.Status.OK)
                        .entity(utilisateur).build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND)
                .build();
    }


    private boolean isRegisted(Utilisateur utilisateur, String passwordprovide) {
        boolean isRegisted = false;
        isRegisted = PasswordUtils.verifyUserPassword(
                passwordprovide, utilisateur.getPassword(), utilisateur.getSalt());
        return isRegisted;
    }

    private boolean isValid(Utilisateur utilisateur) {
        boolean isDone = true;

        if (utilisateur.getLogin().isEmpty() || utilisateur.getPassword().isEmpty())
            isDone = false;
        return isDone;
    }


}
