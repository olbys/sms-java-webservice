package rest;

import jpa.api.repositories.RepositoryFactory;
import jpa.api.repositories.UserRepository;
import jpa.model.Utilisateur;
import org.codehaus.jettison.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
            password = resource.getString("password").trim();
            login = resource.getString("login");
            utilisateurs = repository.findByLogin(login);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        for (Utilisateur utilisateur : utilisateurs) {
            if (isRegisted(password, utilisateur)) {
                return Response.status(Response.Status.OK)
                        .entity(utilisateur).build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND)
                .build();
    }


    private boolean isRegisted(String passwordsecure, Utilisateur utilisateur) {
        return BCrypt.checkpw(passwordsecure, utilisateur.getPassword());
    }

    private boolean isValid(Utilisateur utilisateur) {
        boolean isDone = true;

        if (utilisateur.getLogin().isEmpty() || utilisateur.getPassword().isEmpty())
            isDone = false;
        return isDone;
    }


}
