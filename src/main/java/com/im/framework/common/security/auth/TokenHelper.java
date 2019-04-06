package com.im.framework.common.security.auth;

import com.im.framework.common.security.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Component
public class TokenHelper {

    @Value("${app.name}")
    private String APP_NAME;

    @Value("${jwt.secret}")
    public String SECRET;

    @Value("${jwt.expires_in}")
    private int EXPIRES_IN;

    @Value("${jwt.header}")
    private String AUTH_HEADER;

    private static final String CLAIM_KEY_ID = "uid";
    private static final String CLAIM_KEY_ACC = "acc";
    private static final String CLAIM_KEY_MOBILE = "mob";
    private static final String CLAIM_KEY_CREATED = "created";

    private SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

    public String getMobileFromToken(String token) {
        String mobile;
        try {
            final Claims claims = getAllClaimsFromToken(token);
            mobile =  (String) claims.get(CLAIM_KEY_MOBILE);
        } catch (Exception e) {
            mobile = null;
        }
        return mobile;
    }

    public String getAccIdFromToken(String token) {
        String accId;
        try {
            final Claims claims = getAllClaimsFromToken(token);
            accId = (String) claims.get(CLAIM_KEY_ACC);
        } catch (Exception e) {
            accId = null;
        }
        return accId;
    }

    public String getUidFromToken(String token) {
        String accId;
        try {
            final Claims claims = getAllClaimsFromToken(token);
            accId = (String) claims.get(CLAIM_KEY_ID);
        } catch (Exception e) {
            accId = null;
        }
        return accId;
    }

    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getAllClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            claims.put(CLAIM_KEY_CREATED, new Date());
            claims.setIssuedAt(new Date());
            return Jwts.builder()
                    .setIssuer(APP_NAME)
                    .setSubject(this.getMobileFromToken(token))
                    .setClaims(claims)
                    .setIssuedAt(new Date())
                    .setExpiration(generateExpirationDate())
                    .signWith(SIGNATURE_ALGORITHM, SECRET)
                    .compact();
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    public String generateToken(JwtUser jwtUser) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_ID, jwtUser.getId());
        claims.put(CLAIM_KEY_ACC, jwtUser.getAccId());
        claims.put(CLAIM_KEY_MOBILE, jwtUser.getMobile());
        claims.put(CLAIM_KEY_CREATED, new Date());

        return Jwts.builder()
                .setIssuer(APP_NAME)
                .setSubject(jwtUser.getMobile())
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(generateExpirationDate())
                .signWith(SIGNATURE_ALGORITHM, SECRET)
                .compact();
    }

    private Claims getAllClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    private Date generateExpirationDate() {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = LocalDateTime.now().plusMonths(EXPIRES_IN).atZone(zone).toInstant();
        return Date.from(instant);
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        JwtUser user = (JwtUser) userDetails;
        final String username = getMobileFromToken(token);
        return (username.equals(user.getUsername()) && !isTokenExpired(token));
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String getToken(HttpServletRequest request) {
        /**
         *  Getting the token from Authentication header
         *  e.g Bearer your_token
         */
        return getAuthHeaderFromHeader(request);
    }

    public String getAuthHeaderFromHeader(HttpServletRequest request) {
        return request.getHeader(AUTH_HEADER);
    }

}