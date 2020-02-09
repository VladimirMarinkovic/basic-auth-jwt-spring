package vlada.springboot.auth.security;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationResponse {

    private String authenticationToken;
    private String username;

}
