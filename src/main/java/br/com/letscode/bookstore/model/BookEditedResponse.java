package br.com.letscode.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookEditedResponse {

    private Long id;
    private String title;
    private Integer pages;

    public static BookEditedResponse of(Book book) {
        BookEditedResponse response = new BookEditedResponse();
        BeanUtils.copyProperties(book, response);
        return response;
    }
}
