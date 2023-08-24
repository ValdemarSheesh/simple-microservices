package com.example.gatewayservice.dto;

import lombok.Builder;

@Builder
public record BookRequest(
        String title,
        String author
) {
}
