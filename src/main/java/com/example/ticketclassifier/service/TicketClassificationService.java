package com.example.ticketclassifier.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.example.ticketclassifier.llm.TicketClassificationTool;
import com.example.ticketclassifier.model.TicketResponse;

@Service
@Profile("!mock") //  real LLM only when NOT in mock mode
public class TicketClassificationService implements TicketClassifier {

    private final ChatClient chatClient;
    private final TicketClassificationTool tool;

    public TicketClassificationService(ChatClient.Builder builder, TicketClassificationTool tool) {
        this.chatClient = builder.build();
        this.tool = tool;
    }

    @Override
    public TicketResponse classify(String text) {

        String prompt = """
            You are a deterministic support ticket classifier.

            Categories:
            - BILLING: payments, invoices, refunds, charges
            - TECHNICAL: bugs, errors, system failures
            - GENERAL: everything else

            You MUST call the provided tool.
            Do NOT return free text.

            Ticket:
            "%s"
            """.formatted(text);

        return chatClient.prompt()
                .system("Respond only via tool invocation.")
                .user(prompt)
                .tools(tool)
                .options(ChatOptions.builder()
                        .temperature(0.0)
                        .build())
                .call()
                .entity(TicketResponse.class);
    }
}