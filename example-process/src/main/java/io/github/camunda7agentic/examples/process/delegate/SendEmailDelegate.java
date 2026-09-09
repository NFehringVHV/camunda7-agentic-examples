/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright the camunda7-agentic contributors.
 */
package io.github.camunda7agentic.examples.process.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Demo tool for the agentic showcase: simulates sending an email.
 *
 * <p>Reads {@code to}, {@code subject}, {@code body} from the scope and writes {@code toolCallResult}
 * as a {@link Map}. Nothing is actually sent - the demo only logs and returns a "sent" result to the
 * LLM.
 */
@Component("sendEmailDelegate")
public class SendEmailDelegate implements JavaDelegate {

    private static final Logger LOG = LoggerFactory.getLogger(SendEmailDelegate.class);

    static final long SIMULATED_SYSTEM_CALL_MS = 10_000L;

    @Override
    public void execute(DelegateExecution execution) {
        simulateSystemCall();

        String to = asString(execution.getVariable("to"));
        String subject = asString(execution.getVariable("subject"));
        String body = asString(execution.getVariable("body"));

        LOG.info("SendEmailDelegate (DEMO, nothing is really sent): to={} subject={} bodyChars={}",
                to, subject, body == null ? 0 : body.length());

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("status", "sent");
        result.put("to", to);
        result.put("subject", subject);
        result.put("timestamp", OffsetDateTime.now().toString());
        result.put("source", "agentic-demo (fake email)");

        execution.setVariable("toolCallResult", result);
    }

    /**
     * Simulates the call of a slow external system (e.g. a mail server) so the agentic demo has
     * realistic runtimes. Package-private so tests can override / shorten the wait.
     */
    void simulateSystemCall() {
        try {
            Thread.sleep(SIMULATED_SYSTEM_CALL_MS);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    private String asString(Object value) {
        return value == null ? null : value.toString();
    }
}
