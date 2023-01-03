ARG VARIANT=bullseye
FROM mcr.microsoft.com/vscode/devcontainers/base:0-${VARIANT}
RUN curl -fsSL https://aka.ms/install-azd.sh | bash
RUN wget -q https://raw.githubusercontent.com/dapr/cli/master/install/install.sh -O - | bash

