package com.lyess.grocery_items_management_api_gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Optional;

/**
 * @author : Brandon Ward
 * @email: brandon.ward@stackhawk.com
 * @location: Denver, Colorado, USA
 * @see <a href="https://www.baeldung.com/spring-cloud-gateway-rate-limit-by-client-ip">spring-cloud-gateway-rate-limit-by-client-ip</a>
 */
@Component
public class ClientAddressResolver implements KeyResolver {

    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        return Optional.ofNullable(exchange.getRequest().getRemoteAddress())
                .map(InetSocketAddress::getAddress)
                .map(InetAddress::getHostAddress)
                .map(Mono::just)
                .orElse(Mono.empty());
    }
}
