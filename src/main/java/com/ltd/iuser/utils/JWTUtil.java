package com.ltd.iuser.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.itian.busker.common.Code;
import com.itian.busker.common.Constants;
import com.itian.busker.common.pojo.BuskerException;
import org.apache.commons.collections4.MapUtils;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtil {

    public static String createToken(String key, Map<String, String> payloadMap, int timeUnit) {
        Instant now = Instant.now();
        Instant expire = now.plusSeconds(timeUnit);
        Map<String, Object> map = new HashMap<>();
        map.put("alg", Constants.Algorithm.HS256);
        map.put("typ", "JWT");
        JWTCreator.Builder builder = JWT.create()
                .withHeader(map);
        if (!MapUtils.isEmpty(payloadMap)) {
            for (Map.Entry<String, String> entry : payloadMap.entrySet()) {
                builder = builder.withClaim(entry.getKey(), entry.getValue());
            }
        }
        try {
            String token = builder.withExpiresAt(new Date(expire.toEpochMilli()))
                    .withIssuedAt(new Date(now.toEpochMilli()))
                    .sign(Algorithm.HMAC256(key));
            return token;
        } catch (Exception e) {
            throw new BuskerException(Code.TOKEN_CREATE_ERROR, e);
        }
    }

    public static Map<String, Claim> verifyToken(String key, String token) {
        JWTVerifier verifier = null;
        try {
            verifier = JWT.require(Algorithm.HMAC256(key)).build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaims();
        } catch (InvalidClaimException e) {
            throw new BuskerException(Code.TOKEN_VERIFY_ERROR, e);
        } catch (Exception e) {
            throw new BuskerException(Code.TOKEN_VERIFY_ERROR, e);
        }

    }
}
