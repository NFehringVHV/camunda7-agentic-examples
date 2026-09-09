/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright the camunda7-agentic contributors.
 */
package io.github.camunda7agentic.examples.process;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Embedded Camunda 7 engine that deploys the {@code agentic-demo} process and exposes the REST API
 * (default {@code http://localhost:8080/engine-rest}). Run this together with {@code example-worker}
 * and start the {@code agentic-demo} process (e.g. via Tasklist or the REST API) with a
 * {@code userPrompt} variable such as "What is the weather in Berlin and email it to a@b.com".
 */
@SpringBootApplication
public class ExampleProcessApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExampleProcessApplication.class, args);
    }
}
