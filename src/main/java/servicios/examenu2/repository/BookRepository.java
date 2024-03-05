package servicios.examenu2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import servicios.examenu2.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByName(String name);
    Optional<Book> findById(Long id);
    List<Book> findByAuthor(String author);

    @Query("SELECT b FROM Book b ORDER BY b.author ASC")
    List<Book> findAllOrderByAuthorAsc();

    @Query("SELECT b FROM Book b ORDER BY b.publicationDate ASC")
    List<Book> findAllOrderByPublicationDateAsc();

}
