package com.example.gatewayservice.mapper;

import com.example.gatewayservice.dto.BookRequest;
import com.example.gatewayservice.dto.BookResponse;
import com.example.grpc.BookGrpc;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookGrpc.BookRequest bookRequestToBookRequestGRPC(BookRequest bookRequest);

    BookResponse bookResponseGRPCToBookResponse(BookGrpc.BookResponse bookResponse);

    List<BookResponse> bookResponseGRPCListToBookResponseList(List<BookGrpc.BookResponse> bookResponseGRPCList);
}
