package com.javaexpress.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.gateway.route.builder.UriSpec;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
@EnableDiscoveryClient
public class ApigatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApigatewayserverApplication.class, args);
	}

	@Bean
	RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p.path("javaexpress/accounts/**")
						.filters(f -> f.rewritePath("/javaexpress/accounts/(?<segment>.*)", "/${segment}")
								.addRequestHeader("X-RESPONSE-HEADER-TIME", LocalDateTime.now().toString()))
								.uri("lb://ACCOUNTS"))
				.route(p ->
						p.path("/javaexpress/cards/**")
								.filters(f -> f.rewritePath("/javaexpress/cards/(?<segment>.*)", "/${segment}"))
								.uri("lb://CARDS"))
				.route(p ->
						p.path("/javaexpress/loans/**")
								.filters(f -> f.rewritePath("/javaexpress/loans/(?<segment>.*)", "/${segment}"))
								.uri("lb://LOANS"))
				.build();
	}
}
