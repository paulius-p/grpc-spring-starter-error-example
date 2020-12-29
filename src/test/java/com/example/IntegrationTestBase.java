package com.example;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.lognet.springboot.grpc.context.LocalRunningGrpcPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestApplication.class)
@ActiveProfiles("test")
public abstract class IntegrationTestBase {

  @LocalRunningGrpcPort
  private int grpcPort;

  protected ManagedChannel serverChannel;

  @BeforeEach
  public final void setupChannels() {
    serverChannel = ManagedChannelBuilder.forAddress("localhost", grpcPort)
        .usePlaintext()
        .build();
  }

  @AfterEach
  public final void shutdownChannels() {
    Optional.ofNullable(serverChannel).ifPresent(ManagedChannel::shutdownNow);
  }
}
