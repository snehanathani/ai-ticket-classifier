package com.example.ticketclassifier.model;

public record TicketResponse(
        TicketCategory category,
        double confidence,
        String reason
) {}
