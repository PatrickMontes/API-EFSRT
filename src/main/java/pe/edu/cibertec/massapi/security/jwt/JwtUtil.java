package pe.edu.cibertec.massapi.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.massapi.persistence.model.Usuario;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

@Slf4j
@Service
public class JwtUtil {

    private SecretKey secretKey;
    private static final Long TIEMPO_EXPIRACION_MILISEG = 1000L * 60L * 24L * 30L * 6L;

    @Value("${claveJwtString}")
    private String claveJwtString;


    @PostConstruct
    public void init() {
        byte[] keyBytes = claveJwtString.getBytes(StandardCharsets.UTF_8);
        secretKey = new SecretKeySpec(keyBytes, "HmacSHA256");
    }


    public String generarToken(Usuario usuario) {
        String username = usuario.getEmail();
        return generarToken(username);
    }


    public String generarToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + TIEMPO_EXPIRACION_MILISEG))
                .signWith(secretKey)
                .compact();
    }


    public String getUsernameFromToken(String token) {
        return extractClaims(token, Claims::getSubject);
    }


    private <T> T extractClaims(String token, Function<Claims, T> claimsTFunction){
        return claimsTFunction.apply(Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload());
    }


    public boolean esTokenValido(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !esTokenExpirado(token));
    }


    private boolean esTokenExpirado(String token) {
        return extractClaims(token, Claims::getExpiration).before(new Date());
    }

}
