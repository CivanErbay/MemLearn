package memlearn.backend.security;

import memlearn.backend.db.UserDb;
import memlearn.backend.model.PlanningUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MongoDbUserDetailsService implements UserDetailsService {

    //Userdb wird eingebunden und damit die Verbindung zur Datenbank hergestellt!
    private final UserDb userDb;

    //Konstruktor
    @Autowired
    MongoDbUserDetailsService(UserDb userDb) {
        this.userDb = userDb;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //Aus der Datenbank (userDb.findById) wird mithilfe des inputs (String username) beim Aufruf der Methode loadUserByUsername(..)
        //der User mit dem username aus dem input-Feld aufgerufen und im Optional<PlanningUser> optionalUser gespeichert.
        Optional<PlanningUser> optionalUser = userDb.findById(username);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("user with username: \"" + username + "\" not found");
        }

        //aus dem optionalUser (Typ Optional<PlanningUser> s. Zeile 30) wird mit .get() einer user vom Type PlanningUser (nicht mehr Optional)
        PlanningUser testUser = optionalUser.get();

        //neuer User wird erstellt und returned in dem Format Userdetails (s. Zeile 28)
        return new User(username, testUser.getPassword(), List.of(new SimpleGrantedAuthority(testUser.getRole())));
    }
}
