package com.dmi.cloud2;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@Slf4j
public class AuthorizationHeaderFilter extends
        AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {

    private final Environment env;

    @Autowired
    public AuthorizationHeaderFilter(Environment env) {
        super(Config.class);
        this.env = env;
    }

    public static class Config {
        //put configuration properties here
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            List<String> auths = request.getHeaders().get(HttpHeaders.AUTHORIZATION);
            if (auths == null || auths.size() == 0) {
                return onError(exchange, "No authorization header", HttpStatus.UNAUTHORIZED);
            }
            String authHead = auths.get(0);
            String[] jwts = authHead.split(" ");

            if (jwts.length != 2 || !"Bearer".equals(jwts[0]) || !isJwtValid(jwts[1])) {
                return onError(exchange, "Bad token", HttpStatus.UNAUTHORIZED);
            }
            return chain.filter(exchange);
        };
    }

    private boolean isJwtValid(String jwt) {
        String subject;
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(env.getProperty("token.secret"))
                    .parseClaimsJws(jwt)
                    .getBody();
            log.info("Expiration time is {}", claims.getExpiration());
            subject = claims
                    .getSubject();
        } catch (Exception e) {
            log.error("An error happened by parse token");
            return false;
        }
        return subject != null && !subject.isEmpty();
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }
}

