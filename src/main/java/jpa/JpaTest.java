package jpa;

import jpa.api.repositories.UserRepository;
import jpa.model.Adress;
import jpa.model.Utilisateur;
import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.List;

public class JpaTest {

    private EntityManager manager;


    public JpaTest() {
    }


    /**
     * @param args
     */
    public static void main(String[] args) {
        JpaTest test = new JpaTest();
        try {
            test.createUser();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private void createUser() {
//        Personne p1 = new Personne("KOKO", "Prenom", "0761842926", "devops.integrale@gmail");
//        p1.setAdress(new Adress("14 Jorge Semprum", "Rennes", 35000, "France"));
//        PersonneRepository personneRepository = new PersonneRepository();
//        personneRepository.save(p1);
//        System.out.println(" done .. .. .. ");


        Utilisateur utilisateur1 = new Utilisateur("Lecompte"," Michelle", "0505238974","michelle@lecompte.fr");
        utilisateur1.setAdress(new Adress("8 rue timbre", "Rennes",35000, "France"));
        utilisateur1.setLogin("michu");
        utilisateur1.setPassword("Kokoinfo");

        UserRepository userRepository = new UserRepository();
        userRepository.save(utilisateur1);

        String originalPassword = "Kokoinfo";
        String generatedSecuredPasswordHash = utilisateur1.getPassword();
        System.out.println(generatedSecuredPasswordHash);
        boolean matched = BCrypt.checkpw(originalPassword, generatedSecuredPasswordHash);
        System.out.println(matched);
        matched = BCrypt.checkpw("kneoinv", generatedSecuredPasswordHash);
        System.out.println(matched);

        Collection<Utilisateur> utilisateurs = userRepository.findByLogin("michu");
        for (Utilisateur us : utilisateurs){
            System.out.println(us);
            System.out.println("is equals " + us.getPassword().equals(generatedSecuredPasswordHash));
            System.out.println(" check pass :" + BCrypt.checkpw(originalPassword, us.getPassword()));
        }



    }

}
