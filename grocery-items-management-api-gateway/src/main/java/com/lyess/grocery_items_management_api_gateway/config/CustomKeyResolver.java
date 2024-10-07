package com.lyess.grocery_items_management_api_gateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * @author : Brandon Ward
 * @email: brandon.ward@stackhawk.com
 * @location: Denver, Colorado, USA
 * @see <a href="https://www.baeldung.com/spring-cloud-gateway-rate-limit-by-client-ip">spring-cloud-gateway-rate-limit-by-client-ip</a>
 * @see <a href="https://andifalk.gitbook.io/spring-cloud-gateway-workshop/hands-on-labs/lab2">Configure a rate limiter</a>
 */
@Component("customKeyResolver")
public class CustomKeyResolver implements KeyResolver {

    private static final Logger LOG = LoggerFactory.getLogger(CustomKeyResolver.class);

    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        String hostname = Objects.requireNonNull(exchange.getRequest().getLocalAddress()).getHostName();
        LOG.info("Request from {}", hostname);
        return Mono.just(hostname);
    }
}
