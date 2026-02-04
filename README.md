# AI Ticket Classifier – Spring Boot + Spring AI

## Overview

This project demonstrates a **deterministic, LLM-powered support ticket classification service** built using **Spring Boot 4** and **Spring AI**.  
It leverages **Spring AI ChatClient with tool/function calling** to ensure **structured, reliable, and non-hallucinated output**.

---

## Features

- REST API for support ticket classification
- Structured LLM output via tool / function calling
- Deterministic responses (temperature = 0)
- Enum-based category enforcement
- Mock profile for testing without OpenAI credits

---

## Categories

The classifier assigns each ticket to exactly one of the following categories:

- **BILLING**
- **TECHNICAL**
- **GENERAL**

---

## Tech Stack

- **Java 17**
- **Spring Boot 4**
- **Spring AI**
- **OpenAI Chat Model**

---

## How to Run

### Run with OpenAI (default profile)

```powershell
$env:OPENAI_API_KEY="your_key_here"
$env:SPRING_PROFILES_ACTIVE="default"
.\mvnw.cmd spring-boot:run
Run without OpenAI (mock profile)

This mode allows testing without OpenAI credits.

$env:SPRING_PROFILES_ACTIVE="mock"
.\mvnw.cmd spring-boot:run

API
Classify Ticket
POST /classify

Request Body
{
  "text": "Customer charged twice on credit card"
}

Response
{
  "category": "BILLING",
  "confidence": 0.94,
  "reason": "Duplicate charge indicates a billing issue."
}

Design Patterns

Controller–Service Pattern
Separates HTTP concerns from business logic.

Function / Tool-as-a-Contract Pattern
Enforces structured LLM output through tool invocation.

Enum Validation
Prevents invalid or hallucinated classification categories.

LLM Flow

1.User input is received via the REST API

2.Input text is sent to Spring AI ChatClient

3.Prompt constrains valid categories and behavior

4.LLM is forced to invoke the classification tool

5.Tool output is mapped to a domain model

6.Structured JSON response is returned to the client

7.Testing Without OpenAI Credits

For environments without OpenAI credits, the application supports a mock profile that simulates deterministic classification behavior.
This enables local testing and API validation without external dependencies.