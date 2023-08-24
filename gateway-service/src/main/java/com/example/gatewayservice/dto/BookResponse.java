package com.example.gatewayservice.dto;

import lombok.Builder;

@Builder
public record BookResponse(
        String id,
        String title,
        String author
) {
}
