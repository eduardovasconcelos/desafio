package br.com.framework.desafio.component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import br.com.framework.desafio.model.UserPrinciple;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtProvider {
 
    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);
 
    @Value("${desafio.app.jwtSecret}")
    private String jwtSecret;
 
    public String generateJwtToken(Authentication authentication) {
    	
    	Instant issuedAt = Instant.now().truncatedTo(ChronoUnit.SECONDS);
        Instant expiration = issuedAt.plus(15, ChronoUnit.MINUTES);
        
        UserPrinciple userPrincipal = (UserPrinciple) authentication.getPrincipal();
 
        return Jwts.builder()
                    .setSubject((userPrincipal.getUsername()))
                    .setIssuedAt(Date.from(issuedAt))
                    .setExpiration(Date.from(expiration))
                    .signWith(SignatureAlgorithm.HS512, jwtSecret)
                    .compact();
    }
    
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Assinatura inválida para o JWT -> Message: {} ", e);
        } catch (MalformedJwtException e) {
            logger.error("Token JWT inválido -> Message: {}", e);
        } catch (ExpiredJwtException e) {
            logger.error("Token JWT expirado -> Message: {}", e);
        } catch (UnsupportedJwtException e) {
            logger.error("Token JWT não suportado -> Message: {}", e);
        } catch (IllegalArgumentException e) {
            logger.error("JWT string de claims vazia -> Message: {}", e);
        }
        
        return false;
    }
    
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                      .setSigningKey(jwtSecret)
                      .parseClaimsJws(token)
                      .getBody().getSubject();
    }
}
