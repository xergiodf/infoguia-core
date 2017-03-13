package com.minicubic.infoguiacore.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import java.util.Date;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author xergio
 */
public final class Util {
    
    public static boolean isEmpty(Object o) {
        return (o == null) || ("".equals(o));
    }

    /**
     * 
     * @param subject
     * @param name
     * @return 
     */
    public static String createToken(String subject, String name) {

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder()
                .setIssuedAt(now)
                .setSubject(subject)
                .setId(name)
                //.setExpiration(new Date(Constants.EXP_TOKEN))
                .signWith(SignatureAlgorithm.ES256.HS256, Constants.SECRET_KEY);

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    /**
     * Verifica el token
     *
     * @param token
     * @return
     * @throws SignatureException
     */
    public static boolean verifyToken(String token) {

        boolean valid = true;

        try {
            Util.getClaims(token);
        } catch (Exception e) {
            //e.printStackTrace();
            valid = false;
        }

        return valid;
    }

    /**
     * Obtiene los claims del token
     *
     * @param token
     * @return
     * @throws SignatureException
     */
    public static Claims getClaims(String token) throws SignatureException, ExpiredJwtException {

        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(Constants.SECRET_KEY))
                .parseClaimsJws(token).getBody();

        return claims;
    }
}
