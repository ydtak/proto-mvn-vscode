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

Recommend using extension `zxh404.vscode-proto3` from the VSCode Marketplace for proto support. Install `clang-format` to enable this extension to format `.proto` files.
