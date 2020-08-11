package com.albert.management.web.rest;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.albert.management.domain.Book;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookResource {

    private Map<Long, Book> books = new HashMap<>();

    public BookResource() {
        this.books.put(1L, new Book(1L, "book A", 10));
        this.books.put(2L, new Book(2L, "book B", 20));
        this.books.put(3L, new Book(3L, "book C", 30));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        Book _book = this.books.get(id);
        if (_book == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(_book);
        }
    }

    @GetMapping("/list1")
    public ResponseEntity<ArrayList<Book>> getBook() {
        return ResponseEntity.status(HttpStatus.OK).body(new ArrayList<Book>(this.books.values()));
    }

    @GetMapping("/list2")
    public void getBook(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.print(new ArrayList<Book>(this.books.values()));
            out.flush();
            out.close();
        } catch (IOException e) {
            response.sendError(500, e.getMessage());
        }
    }

    @GetMapping("/create")
    public ResponseEntity<Book> createBook(Long id, String name, Integer remain) {
        Book _book = new Book(id, name, remain);
        if (this.books.containsKey(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(_book);
        } else {
            this.books.putIfAbsent(id, _book);
            return ResponseEntity.status(HttpStatus.OK).body(_book);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        if (this.books.containsKey(book.getId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(book);
        } else {
            this.books.putIfAbsent(book.getId(), book);
            return ResponseEntity.status(HttpStatus.OK).body(book);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Book> updateBook(@RequestBody Book book) {
        if (!this.books.containsKey(book.getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(book);
        } else {
            Book _book = this.books.get(book.getId());
            _book.setName(book.getName());
            _book.setRemain(book.getRemain());
            return ResponseEntity.status(HttpStatus.OK).body(book);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        this.books.remove(id);
        return ResponseEntity.ok().build();
    }
}
