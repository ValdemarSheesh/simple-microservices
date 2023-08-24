package com.example.gatewayservice.config;

import com.example.grpc.BookServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class GrpcConfig {

    @Bean
    @Lookup
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public BookServiceGrpc.BookServiceBlockingStub getStub() {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:5000")
                .usePlaintext().build();

        return BookServiceGrpc.newBlockingStub(channel);
    }

}
