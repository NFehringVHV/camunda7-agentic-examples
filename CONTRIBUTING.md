# Contributing to camunda7-agentic-examples

Thanks for your interest in contributing! This project is licensed under the
[Apache License 2.0](LICENSE).

## Ground rules

- By submitting a contribution you agree that it is licensed under the Apache-2.0 license and that
  you have the right to submit it (see [Developer Certificate of Origin](https://developercertificate.org/)).
- Every source file must carry an SPDX header: `SPDX-License-Identifier: Apache-2.0`.
- Write code, comments, commit messages, and documentation in **English**.
- Never commit credentials (AWS keys, tokens, passwords). Use environment variables or the standard
  provider credential chains.

## Development

- Java 21+, Maven.
- Build with `mvn verify`. `example-worker` depends on `camunda7-agentic-starter`; build/install the
  starter first if it is not published to a repository you can resolve.

## Pull requests

- Branch from `main` and open a pull request against `main`.
- The `main` branch is protected: changes land via reviewed pull requests, not direct pushes.
- Keep PRs focused; describe the motivation and the change. Reference any related issue.

## Reporting bugs / requesting features

Open a GitHub issue with a clear description and, for bugs, a minimal reproduction.
