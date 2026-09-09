/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright the camunda7-agentic contributors.
 */
package io.github.camunda7agentic.examples.worker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Runnable agentic worker.
 *
 * <p>This application does not contain any agentic logic itself. It simply:
 * <ol>
 *   <li>puts {@code camunda7-agentic-starter} on the classpath (workers + auto-configuration), and</li>
 *   <li>puts the Spring AI AWS Bedrock Converse starter on the classpath, which auto-configures the
 *       {@code ChatModel} bean the starter consumes.</li>
 * </ol>
 *
 * <p>Point it at a running Camunda 7 engine via {@code agentic.c7.client.base-url} and provide AWS
 * credentials / a Bedrock model id via {@code spring.ai.bedrock.*} (see {@code application.yml}).
 */
@SpringBootApplication
public class ExampleWorkerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExampleWorkerApplication.class, args);
    }
}
