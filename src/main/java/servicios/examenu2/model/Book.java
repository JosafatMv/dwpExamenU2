package servicios.examenu2.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", columnDefinition = "VARCHAR(100) NOT NULL")
    private String name;
    @Column(name = "author", columnDefinition = "VARCHAR(100) NOT NULL")
    private String author;
    @Column(name = "publication_date", columnDefinition = "DATE NOT NULL")
    private Date publicationDate;
    @Column(name = "cover_image", columnDefinition = "TEXT NOT NULL")
    private String coverImage;
    @Column(name = "status", columnDefinition = "BOOL DEFAULT TRUE")
    private boolean status;

    public Book() {
    }

    public Book(String name, String author, Date publicationDate, String coverImage, boolean status) {
        this.name = name;
        this.author = author;
        this.publicationDate = publicationDate;
        this.coverImage = coverImage;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
