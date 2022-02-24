package br.com.letscode.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

    private Long id;
    private String title;
    private Integer pages;

    public static BookDTO of(Book book) {
        BookDTO bookDTO = BookDTO.builder()
                .id(book.getId())
                .pages(book.getPages())
                .title(book.getTitle())
                .build();
        return bookDTO;
    }
}
