package servicios.examenu2.model;

import jakarta.validation.constraints.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BookDto {

    @NotNull(groups = {Update.class, ChangeStatus.class, FindById.class})
    private Long id;

    @NotNull(groups = {Register.class, Update.class})
    private String name;

    @NotNull(groups = {Register.class, Update.class})
    private String author;

    @NotNull(groups = {Register.class, Update.class})
    private String publicationDate;

    private String coverImage;

    private boolean status;

    public BookDto() {
    }

    public BookDto(Long id, String name, String author, String publicationDate, String coverImage, boolean status) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.publicationDate = publicationDate;
        this.coverImage = coverImage;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public Date getPublicationDate() {
        try {
            return new SimpleDateFormat("yyyy/MM/dd").parse(String.valueOf(publicationDate));
        } catch (Exception e) {
            return null;
        }
    }

    public String getCoverImage() {
        return coverImage;
    }

    public boolean isStatus() {
        return status;
    }

    public interface Register{

    }

    public interface Update{

    }

    public interface ChangeStatus{

    }

    public interface FindById{

    }
}
