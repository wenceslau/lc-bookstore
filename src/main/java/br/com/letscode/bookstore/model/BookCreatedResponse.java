package br.com.letscode.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookCreatedResponse {

    private Long id;
    private String title;
    private Integer pages;

    public static BookCreatedResponse of(Book book) {
        BookCreatedResponse response = new BookCreatedResponse();
        BeanUtils.copyProperties(book, response);
        return response;
    }
}
