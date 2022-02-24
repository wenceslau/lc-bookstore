package br.com.letscode.bookstore.controller;

import br.com.letscode.bookstore.model.Book;
import br.com.letscode.bookstore.model.BookCreatedResponse;
import br.com.letscode.bookstore.model.BookDTO;
import br.com.letscode.bookstore.model.CreateBookRequest;
import br.com.letscode.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/books")
@RequiredArgsConstructor
@Slf4j
public class BookRestController {

    private final BookService bookService;

    @GetMapping(value = "init")
    @ResponseStatus(HttpStatus.CREATED)
    public String init() {
        log.info("Creating many book");

        for(int i = 1; i <= 10; i++){
            CreateBookRequest request = new CreateBookRequest();
            request.setTitle("Book " + i);
            request.setPages(200 + i);
            bookService.create(request);
        }
        log.info("Many Books created");
        return "Books criados";
    }

    @GetMapping
    public List<Book> getAll() {
        return bookService.getAllBooks();
    }

    @GetMapping(value = "/{id}")
    public BookDTO findBookById(@PathVariable("id") Long id) {
        return BookDTO.of(bookService.findBookById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookCreatedResponse createBook(@RequestBody CreateBookRequest request) {
        log.info("Creating book: {}", request);
        BookCreatedResponse book = bookService.create(request);
        log.info("Book created: {}", book);
        return book;
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookCreatedResponse editBook(@PathVariable("id") Long id, @RequestBody CreateBookRequest request) {
        log.info("Editing book: {}", request);
        BookCreatedResponse book = bookService.edit(id, request);
        log.info("Book edited: {}", book);
        return book;
    }

    @PutMapping(value = "error/{id}")
    public ResponseEntity<?> editBookError(@PathVariable("id") Long id, @RequestBody CreateBookRequest request) {
        log.info("Editing book: {}", request);
        try {
            BookCreatedResponse book = bookService.edit(id, request);
            log.info("Book edited: {}", book);
            return ResponseEntity.status(HttpStatus.OK).body(book);
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex);
        }
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeBook(@PathVariable("id") Long id) {
        log.info("removing book id: {}", id);
        bookService.remove(id);
        log.info("Book id {} removed", id);
    }

    /*

    "last": false,              = é a ultima pagina da lista
    "totalElements": 10,        = total de elementos da requisiçao
    "totalPages": 5,            = total de paginas calculado pelo total de elemtos com o numero de registros da pagina definido
    "first": true,              = é a primeira pagina da lista
    "numberOfElements": 2,      = Numero de elemetos nessa pagina
    "number": 0,                = numero da pagina atual, começa no zero
    "size": 2,                  = numero de elemetos por pagina que o usuario solicitou

     */
    @GetMapping(value = "/pageable")
    public Page<Book> getPageBook(Pageable pageable) {
        return bookService.getAllBooks(pageable);
    }
}
