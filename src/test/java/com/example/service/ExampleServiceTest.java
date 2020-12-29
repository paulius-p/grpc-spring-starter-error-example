package com.example.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.IntegrationTestBase;
import io.grpc.testing.protobuf.SimpleRequest;
import io.grpc.testing.protobuf.SimpleResponse;
import io.grpc.testing.protobuf.SimpleServiceGrpc;
import org.junit.jupiter.api.Test;

class ExampleServiceTest extends IntegrationTestBase {
  
  @Test
  void should_complete_request() {
    var request = SimpleRequest.newBuilder().setRequestMessage("request-message").build();
    var expected = SimpleResponse.newBuilder().setResponseMessage("message-2").build();

    var stub = SimpleServiceGrpc.newBlockingStub(serverChannel);

    var response = stub.unaryRpc(request);

    assertThat(response).isEqualTo(expected);
  }
}