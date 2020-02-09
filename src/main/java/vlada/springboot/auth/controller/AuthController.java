package vlada.springboot.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vlada.springboot.auth.dto.LoginZahtev;
import vlada.springboot.auth.dto.RegistracijaZahtev;
import vlada.springboot.auth.security.AuthenticationResponse;
import vlada.springboot.auth.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/registracija")
    public ResponseEntity registracija(@RequestBody RegistracijaZahtev registracijaZahtev) {
        authService.registruj(registracijaZahtev);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginZahtev loginZahtev) {
        return authService.login(loginZahtev);
    }
}
