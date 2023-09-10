package com.example;

import com.example.Example;
import com.example.ExampleType;
import com.google.protobuf.TextFormat;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {

  public static void main(String[] args) {
    Example exampleProto =
        Example.newBuilder()
            .setId(12345L)
            .setName("Example Proto")
            .setProtoType(ExampleType.PROTO_TYPE_A)
            .addInnerMessages(
                Example.InnerMessage.newBuilder()
                    .setNumberField(1L)
                    .setStringField("string 1"))
            .addInnerMessages(
                Example.InnerMessage.newBuilder()
                    .setNumberField(2L)
                    .setStringField("string 2"))
            .build();

    System.out.println(exampleProto.toString());
    System.out.println(readExampleProtoConfig());
  }

  private static Example readExampleProtoConfig() {
    try (InputStream inputStream =
            new Main()
                .getClass()
                .getClassLoader()
                .getResourceAsStream("config/example_config.textproto");
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream)) {
      Example.Builder builder = Example.newBuilder();
      TextFormat.merge(inputStreamReader, builder);
      return builder.build();
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }
}
