package com.books_manager.mapper;

import com.books_manager.dto.BookDTO;
import com.books_manager.entities.Book;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface BookMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "author", target = "author")
    @Mapping(source = "id", target = "id")
    BookDTO convertFromEntityToDto(Book book);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "author", target = "author")
    @Mapping(target = "id", ignore = true)
    Book convertFromDtoToEntity(BookDTO bookDto);
}
