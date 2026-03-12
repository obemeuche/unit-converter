#!/bin/sh
# Maven Wrapper script
# Adapted from https://github.com/apache/maven-wrapper

set -e

MAVEN_PROJECTBASEDIR="${MAVEN_BASEDIR:-$(cd "$(dirname "$0")" && pwd)}"
MAVEN_WRAPPER_JAR="$MAVEN_PROJECTBASEDIR/.mvn/wrapper/maven-wrapper.jar"
MAVEN_WRAPPER_PROPERTIES="$MAVEN_PROJECTBASEDIR/.mvn/wrapper/maven-wrapper.properties"

# If the wrapper jar doesn't exist, download Maven directly
if [ ! -f "$MAVEN_WRAPPER_JAR" ]; then
    # Check for Maven in PATH
    if command -v mvn > /dev/null 2>&1; then
        exec mvn "$@"
    fi

    # Download and use Maven
    MAVEN_VERSION="3.9.6"
    MAVEN_HOME="${HOME}/.m2/wrapper/dists/apache-maven-${MAVEN_VERSION}"

    if [ ! -d "$MAVEN_HOME" ]; then
        echo "Downloading Maven ${MAVEN_VERSION}..."
        mkdir -p "$MAVEN_HOME"
        MAVEN_URL="https://archive.apache.org/dist/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz"
        curl -fsSL "$MAVEN_URL" | tar -xzf - -C "$MAVEN_HOME" --strip-components=1
    fi

    exec "$MAVEN_HOME/bin/mvn" "$@"
fi

exec java -jar "$MAVEN_WRAPPER_JAR" "$@"
