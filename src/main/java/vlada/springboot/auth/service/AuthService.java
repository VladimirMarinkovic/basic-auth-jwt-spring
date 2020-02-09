package vlada.springboot.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vlada.springboot.auth.dto.LoginZahtev;
import vlada.springboot.auth.dto.RegistracijaZahtev;
import vlada.springboot.auth.model.Korisnik;
import vlada.springboot.auth.repository.KorisnikRepository;
import vlada.springboot.auth.security.AuthenticationResponse;
import vlada.springboot.auth.security.JwtProvider;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private KorisnikRepository korisnikRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtProvider jwtProvider;


    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public void  registruj(RegistracijaZahtev registracijaZahtev) {
        Korisnik korisnik = new Korisnik();
        korisnik.setUserName(registracijaZahtev.getUsername());
        korisnik.setEmail(registracijaZahtev.getEmail());
        korisnik.setPassword(encodePassword(registracijaZahtev.getPassword()));

        korisnikRepository.save(korisnik);
    }

    public AuthenticationResponse login(LoginZahtev loginZahtev) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginZahtev.getUsername(),
                loginZahtev.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String authenticationToken = jwtProvider.generateToken(authenticate);
        return new AuthenticationResponse(authenticationToken, loginZahtev.getUsername());
    }

    public Optional<User> ulogovanKorisnik() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        return Optional.of(principal);
    }

}
