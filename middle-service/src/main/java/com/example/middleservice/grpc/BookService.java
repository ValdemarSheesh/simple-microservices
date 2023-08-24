package com.example.middleservice.grpc;

import com.example.grpc.BookGrpc.BookRequest;
import com.example.grpc.BookGrpc.BookResponse;
import com.example.grpc.BookGrpc.BookResponseList;
import com.example.grpc.BookGrpc.Empty;
import com.example.grpc.BookServiceGrpc.BookServiceImplBase;
import com.example.middleservice.mapper.BookMapper;
import com.example.middleservice.model.Book;
import com.example.middleservice.repo.BookRepo;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@GrpcService
public class BookService extends BookServiceImplBase {

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private BookMapper bookMapper;

    @Override
    public void saveBook(BookRequest request, StreamObserver<BookResponse> responseObserver) {
        Book book = bookMapper.bookRequestToBook(request);

        bookRepo.save(book);

        responseObserver.onNext(bookMapper.bookToBookResponse(book));
        responseObserver.onCompleted();
    }

    @Override
    public void getBooks(Empty request, StreamObserver<BookResponseList> responseObserver) {
        List<BookResponse> bookResponses = bookMapper.booksToBookResponseList(bookRepo.findAll());

        BookResponseList bookResponseList = BookResponseList.newBuilder()
                .addAllBookResponse(bookResponses)
                .build();

        responseObserver.onNext(bookResponseList);
        responseObserver.onCompleted();
    }
}
