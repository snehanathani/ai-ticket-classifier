package com.example.ticketclassifier.service;

import com.example.ticketclassifier.model.TicketResponse;

public interface TicketClassifier {
    TicketResponse classify(String text);
}
