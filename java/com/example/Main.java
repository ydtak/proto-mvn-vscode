package com.example;

import com.example.ExampleProto;
import com.example.ExampleProtoType;

public class Main {

  public static void main(String[] args) {
    ExampleProto exampleProto =
        ExampleProto.newBuilder()
            .setId(12345L)
            .setName("Example Proto")
            .setProtoType(ExampleProtoType.PROTO_TYPE_A)
            .addInnerMessages(
                ExampleProto.InnerMessage.newBuilder()
                    .setNumberField(1L)
                    .setStringField("string 1"))
            .addInnerMessages(
                ExampleProto.InnerMessage.newBuilder()
                    .setNumberField(2L)
                    .setStringField("string 2"))
            .build();
    System.out.println(exampleProto.toString());
  }
}
