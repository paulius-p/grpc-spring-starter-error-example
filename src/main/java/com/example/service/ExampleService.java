package com.example.service;

import io.grpc.stub.StreamObserver;
import io.grpc.testing.protobuf.SimpleRequest;
import io.grpc.testing.protobuf.SimpleResponse;
import io.grpc.testing.protobuf.SimpleServiceGrpc;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class ExampleService extends SimpleServiceGrpc.SimpleServiceImplBase {

  @Override
  public void unaryRpc(SimpleRequest request, StreamObserver<SimpleResponse> responseObserver) {
    var response = SimpleResponse.newBuilder().setResponseMessage("message-2").build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }
}
