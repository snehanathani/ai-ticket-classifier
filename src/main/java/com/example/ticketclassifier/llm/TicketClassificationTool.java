package com.example.ticketclassifier.llm;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;
import com.example.ticketclassifier.model.*;

@Component
public class TicketClassificationTool {

    public record Input(
            String category,
            Double confidence,
            String reason
    ) {}

    @Tool(description = "Return the classified support ticket")
    public TicketResponse classify(Input input) {
        return new TicketResponse(
                TicketCategory.valueOf(input.category()),
                input.confidence(),
                input.reason()
        );
    }
}
