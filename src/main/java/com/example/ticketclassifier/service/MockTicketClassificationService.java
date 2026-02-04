package com.example.ticketclassifier.service;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import com.example.ticketclassifier.model.*;

@Service
@Profile("mock")
public class MockTicketClassificationService implements TicketClassifier {

    @Override
    public TicketResponse classify(String text) {
        if (text.toLowerCase().contains("charge")) {
            return new TicketResponse(
                TicketCategory.BILLING,
                1.0,
                "Mocked billing classification"
            );
        }
        return new TicketResponse(
            TicketCategory.GENERAL,
            1.0,
            "Mocked general classification"
        );
    }
}
