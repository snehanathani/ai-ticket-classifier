package com.example.ticketclassifier.controller;

import org.springframework.web.bind.annotation.*;

import com.example.ticketclassifier.service.*;
import com.example.ticketclassifier.model.*;

@RestController
@RequestMapping("/classify")
public class TicketController {

    private final TicketClassifier classifier;

    public TicketController(TicketClassifier classifier) {
        this.classifier = classifier;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public TicketResponse classify(@RequestBody TicketRequest request) {
        return classifier.classify(request.text());
    }
}



