package com.banking.bank.config;

import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.ConsumptionProbe;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Component
public class RateLimit extends OncePerRequestFilter {

    private final Map<String, Integer> urlRateLimits = new HashMap<>();

    private final Map<String, Bucket> userBuckets = new HashMap<>();

    // Rate limit değerlerinin belirlendiği constructor
    public RateLimit() {
        urlRateLimits.put("/api/v1/auth/login", 5); // Login endpoint'ine 15 dakikada 5 istek
        urlRateLimits.put("/api/v1/auth/register", 5); // Register endpoint'ine 15 dakikada 5 istek
        urlRateLimits.put("/api/v1/*", 100); // Diğer tüm API'ler için 15 dakikada 100 istek
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String requestUrl = request.getRequestURI();

        int limit = urlRateLimits.getOrDefault(requestUrl, 100);

        Bucket bucket = userBuckets.computeIfAbsent(requestUrl, key ->
                Bucket4j.builder()
                        .addLimit(io.github.bucket4j.Bandwidth.simple(limit, Duration.ofMinutes(15))) 
                        .build()
        );

        ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(1);

        if (probe.isConsumed()) {
            filterChain.doFilter(request, response);
        } else {
            response.setStatus(429);
            response.getWriter().write("Too many requests, please try again later.");
        }
    }
}
