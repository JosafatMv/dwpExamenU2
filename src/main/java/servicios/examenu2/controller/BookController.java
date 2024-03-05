package servicios.examenu2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import servicios.examenu2.model.BookDto;
import servicios.examenu2.service.BookService;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = {"*"})
public class BookController {

    private final BookService service;

    @Autowired
    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<Object> findAll() {
        return service.findAll();
    }

    @PostMapping("/id")
    public ResponseEntity<Object> findById(@Validated(BookDto.FindById.class) @RequestBody BookDto dto) {
        return service.findById(dto.getId());
    }

    @PostMapping("")
    public ResponseEntity<Object> save(@Validated(BookDto.Register.class) @RequestBody BookDto dto) {
        return service.save(dto);
    }

    @PutMapping("")
    public ResponseEntity<Object> update(@Validated(BookDto.Update.class) @RequestBody BookDto dto) {
        return service.update(dto);
    }

    @PutMapping("/status")
    public ResponseEntity<Object> changeStatus(@Validated(BookDto.ChangeStatus.class) @RequestBody BookDto dto) {
        return service.changeStatus(dto);
    }

    @GetMapping("/order/author")
    public ResponseEntity<Object> findByAuthor() {
        return service.findAllOrderByAuthor();
    }

    @GetMapping("/order/publicationDate")
    public ResponseEntity<Object> findByPublicationDate() {
        return service.findAllOrderByPublicationDate();
    }


}
