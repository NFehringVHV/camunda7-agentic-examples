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

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Demo tool for the agentic showcase: returns a deterministic "weather" for a location provided by
 * the LLM.
 *
 * <p>Reads {@code location} from the scope and writes {@code toolCallResult} as a {@link Map}. The
 * format is intentionally stable because it is fed back to the LLM as JSON in the history.
 */
@Component("getWeatherDelegate")
public class GetWeatherDelegate implements JavaDelegate {

    private static final Logger LOG = LoggerFactory.getLogger(GetWeatherDelegate.class);

    /**
     * Deliberate delay simulating a slow downstream system. It also side-steps a known
     * correlation-timing limitation of the simple sequential demo topology: the tool must not throw
     * its {@code LLM-Result} message before the main flow is parked at the catch. See the
     * "Known limitation — correlation timing" note in the starter's Tool convention.
     */
    static final long SIMULATED_SYSTEM_CALL_MS = 10_000L;

    @Override
    public void execute(DelegateExecution execution) {
        simulateSystemCall();

        Object locationVar = execution.getVariable("location");
        String location = locationVar == null ? "unknown" : locationVar.toString();

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("location", location);
        result.put("temperatureCelsius", deterministicTemperature(location));
        result.put("condition", deterministicCondition(location));
        result.put("source", "agentic-demo (fake weather)");

        LOG.info("GetWeatherDelegate: location={} result={}", location, result);
        execution.setVariable("toolCallResult", result);
    }

    /**
     * Simulates the call of a slow external system (e.g. a weather API) so the agentic demo has
     * realistic runtimes. Package-private so tests can override / shorten the wait.
     */
    void simulateSystemCall() {
        try {
            Thread.sleep(SIMULATED_SYSTEM_CALL_MS);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    private int deterministicTemperature(String location) {
        return 5 + Math.floorMod(location.toLowerCase().hashCode(), 26);
    }

    private String deterministicCondition(String location) {
        String[] conditions = {"sunny", "cloudy", "rainy", "windy", "foggy"};
        return conditions[Math.floorMod(location.toLowerCase().hashCode(), conditions.length)];
    }
}
