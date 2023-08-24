package com.example.gatewayservice.controller;

import com.example.gatewayservice.dto.BookRequest;
import com.example.gatewayservice.dto.BookResponse;
import com.example.gatewayservice.mapper.BookMapper;
import com.example.grpc.BookGrpc;
import com.example.grpc.BookServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping("gateway")
@RequiredArgsConstructor
public class MyController {

    private final BookMapper bookMapper;

    @Inject
    private BookServiceGrpc.BookServiceBlockingStub stub;

    @PostMapping
    public ResponseEntity<?> saveBook(@RequestBody BookRequest bookRequest) {

        BookGrpc.BookRequest bookRequest1 = bookMapper.bookRequestToBookRequestGRPC(bookRequest);

        BookGrpc.BookResponse bookResponse = stub.saveBook(bookRequest1);

        return ResponseEntity.status(HttpStatus.CREATED).body(bookMapper.bookResponseGRPCToBookResponse(bookResponse));
    }

    @GetMapping
    public ResponseEntity<?> getBooks() {
        BookGrpc.BookResponseList books = stub.getBooks(BookGrpc.Empty.newBuilder().build());

        return ResponseEntity.ok(bookMapper.bookResponseGRPCListToBookResponseList(books.getBookResponseList()));
    }

}
