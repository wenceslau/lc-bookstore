package br.com.letscode.bookstore.service;

import br.com.letscode.bookstore.exception.BookException;
import br.com.letscode.bookstore.model.Book;
import br.com.letscode.bookstore.model.BookCreatedResponse;
import br.com.letscode.bookstore.model.BookDTO;
import br.com.letscode.bookstore.model.CreateBookRequest;
import br.com.letscode.bookstore.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.description.annotation.AnnotationValue;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;

    public BookCreatedResponse create(CreateBookRequest request) {
        log.debug("Received book to create {}", request);
        Book newBook = Book.of(request);
        Book savedBook = repository.save(newBook);
        log.debug("Book create in service {}", savedBook);
        return BookCreatedResponse.of(savedBook);
    }

    public BookCreatedResponse edit(Long id, CreateBookRequest request) {

        log.debug("Received book to create {}", request);

        Book receivedBook = Book.of(request);

        Book editBook = findBookById(id);

        BeanUtils.copyProperties(receivedBook, editBook, "id");

        Book editedBook = repository.save(editBook);

        log.debug("Book edited in service {}", editedBook);

        return BookCreatedResponse.of(editedBook);
    }

    public void remove(Long id) {

        log.debug("Received book id {} to remove", id);

        Book editBook = findBookById(id);

        repository.deleteById(editBook.getId());

        log.debug("Book removed.");

    }

    public List<Book> getAllBooks() {
        List<Book> result = new ArrayList<>();
        repository.findAll().forEach(result::add);
        return result;
    }

    public Page<Book> getAllBooks(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Book findBookById(Long id) {
        Optional<Book> optionalBook = repository.findById(id);
        if (optionalBook.isEmpty()){
            throw new BookException("Nenhum livro encontrado com o Id : "+ id);
        }
        return optionalBook.get();
    }
}
