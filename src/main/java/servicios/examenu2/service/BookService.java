package servicios.examenu2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import servicios.examenu2.model.Book;
import servicios.examenu2.model.BookDto;
import servicios.examenu2.repository.BookRepository;
import servicios.examenu2.utils.TypesResponse;
import servicios.examenu2.utils.Message;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookService {
    private final BookRepository repository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.repository = bookRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Object> findAll() {
        return new ResponseEntity<>(new Message(repository.findAll(), "Listado de libros", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Object> findById(Long id) {
        return new ResponseEntity<>(new Message(repository.findById(id), "Libro encontrado", TypesResponse.SUCCESS), HttpStatus.OK);

    }

    @Transactional(rollbackFor = SQLException.class)
    public ResponseEntity<Object> save(BookDto book) {
        Optional<Book> optionalBook = repository.findByName(book.getName());

        if (optionalBook.isPresent()) {
            return new ResponseEntity<>(new Message("El libro ya existe", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        Book bookToSave = new Book();
        bookToSave.setStatus(true);
        bookToSave.setName(book.getName());
        bookToSave.setAuthor(book.getAuthor());
        bookToSave.setPublicationDate(book.getPublicationDate());
        bookToSave.setCoverImage(book.getCoverImage());



        Book savedBook = repository.saveAndFlush(bookToSave);

        return new ResponseEntity<>(new Message(savedBook, "Libro registrado exitosamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(rollbackFor = SQLException.class)
    public ResponseEntity<Object> update(BookDto book) {
        Optional<Book> optionalBook = repository.findById(book.getId());

        if (!optionalBook.isPresent()) {
            return new ResponseEntity<>(new Message("No existe dicho libro.", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        Book bookToSave = optionalBook.get();
        bookToSave.setStatus(book.isStatus());
        bookToSave.setName(book.getName());
        bookToSave.setAuthor(book.getAuthor());
        bookToSave.setPublicationDate(book.getPublicationDate());
        bookToSave.setCoverImage(book.getCoverImage());
        bookToSave = repository.saveAndFlush(bookToSave);

        return new ResponseEntity<>(new Message(bookToSave, "Libro modificado exitosamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(rollbackFor = SQLException.class)
    public ResponseEntity<Object> changeStatus(BookDto book) {

        Optional<Book> optionalBook = repository.findById(book.getId());

        if (!optionalBook.isPresent()) {
            return new ResponseEntity<>(new Message("No existe dicho libro.", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        Book bookToSave = optionalBook.get();
        bookToSave.setStatus(!bookToSave.isStatus());
        bookToSave = repository.saveAndFlush(bookToSave);

        return new ResponseEntity<>(new Message(bookToSave, "Status modificado exitósamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Object> findByAuthor(Book book) {

        List<Book> books = repository.findByAuthor(book.getAuthor());

        return new ResponseEntity<>(new Message(books, "Libro encontrado por autor", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Object> findAllOrderByAuthor() {

        List<Book> books = repository.findAllOrderByAuthorAsc();

        return new ResponseEntity<>(new Message(books, "Libros encontrados ordenados por autor ascendentemente", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Object> findAllOrderByPublicationDate() {

        List<Book> books = repository.findAllOrderByPublicationDateAsc();

        return new ResponseEntity<>(new Message(books, "Libros encontrados ordenados por fecha de publicación ascendentemente", TypesResponse.SUCCESS), HttpStatus.OK);
    }

}
