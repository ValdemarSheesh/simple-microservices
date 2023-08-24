package com.example.middleservice.mapper;

import com.example.grpc.BookGrpc.BookRequest;
import com.example.grpc.BookGrpc.BookResponse;
import com.example.middleservice.model.Book;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    Book bookRequestToBook(BookRequest bookRequest);

    BookResponse bookToBookResponse(Book book);

    List<BookResponse> booksToBookResponseList(List<Book> books);
}
