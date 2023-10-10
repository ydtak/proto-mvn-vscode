# Proto Example with Maven and VSCode  

## Overview

This is an example Maven project to compile protos for a Java program on VSCode.

## Steps

Follow these steps to enable proto integration for the Maven project:

### 1. Add Protocol Buffers Compiler (`protoc`)

Install `protoc` to your PATH from the [official proto Github repository](https://github.com/protocolbuffers/protobuf/releases). The releases include pre-compiled binaries which can be uncompressed into the `/usr` directory.

### 2. Add `protobuf` dependency to Maven

Add `protobuf` dependency to the Maven project. Select the desired version from the [Maven repository](https://mvnrepository.com/artifact/com.google.protobuf/protobuf-java).

```xml
<dependency>
    <groupId>com.google.protobuf</groupId>
    <artifactId>protobuf-java</artifactId>
    <version>x.x.x</version> <!-- replace with the desired version -->
</dependency>
```

### 3. Add `protobuf-maven-plugin` plugin to Maven

To generate Java sources (enables autocomplete) from `.proto` files, add the `protobuf-maven-plugin` plugin to the Maven project. Get the desired verseion from [Xolstice](https://www.xolstice.org/protobuf-maven-plugin/examples/protoc-artifact.html).

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.xolstice.maven.plugins</groupId>
            <artifactId>protobuf-maven-plugin</artifactId>
            <version>x.x.x</version> <!-- replace with the desired version -->
            <configuration>
                <protocExecutable>/usr/bin/protoc</protocExecutable> <!-- Path to the protoc compiler -->
                <protoSourceRoot>proto</protoSourceRoot> <!-- Path to proto source files -->
            </configuration>
            <executions>
                <execution>
                    <goals>
                        <goal>compile</goal>
                        <goal>test-compile</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

## Extensions

Recommend using extension `zxh404.vscode-proto3` and `jeongukjae.vscode-protobuf` from the VSCode Marketplace for proto support. Install `clang-format`, `txtpbfmt`, and `api-linter` to enable `jeongukjae.vscode-protobuf` to lint and format `.proto` files.

### `zxh404.vscode-proto3`

For this extension to work fully, update the `settings.json` with the following. This configuration assumes the `pom.xml` is configured with `protoSourceRoot` set to `proto` and build `directory` set to `mvn-compile-target`.

```json
"protoc": {
    "compile_on_save": true,
    "options": [
        "--proto_path=${workspaceRoot}/proto",
        "--java_out=mvn-compile-target"
    ]
},
```

### `clang-format`

Install clang-format using `sudo apt install clang-format`. This is used to format `.proto` files.

### `txtpbfmt`

Install `txtpbfmt` following the instructions on [Github](https://github.com/protocolbuffers/txtpbfmt). This is used to format any `.textproto` files.

Note this requires using `go` ([official installation docs](https://go.dev/doc/install)) and moving the `txtpbfmt` binary into the `PATH` directory.

### `api-linter`

Install `api-linter` following the instructions on the [official docs](https://linter.aip.dev/). This is used to to lint `.proto` files.

The linter is quite heavy so one may consider [configuring](https://linter.aip.dev/configuration) the linter to disable certain rules. Add the following comment to a `.proto` file to disable linter rules. See the [Rule Documentation](https://linter.aip.dev/rules/) for all linter rules.

```proto
// (-- api-linter: <RULE_GROUP>::<AIP#>::<SCOPE>=disabled --)

// For example the following disables Core rule for AIP 191 (proto-package).
// See https://linter.aip.dev/191/proto-package.
// (-- api-linter: core::0191::proto-package=disabled --)

// Use the following to disable all Core rules for AIP 191.
// (-- api-linter: core::0191=disabled --)
```

Note this requires using `go` ([official installation docs](https://go.dev/doc/install)) and moving the `api-linter` binary into the `PATH` directory.
