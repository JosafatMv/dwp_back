package servicios.uno.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", columnDefinition = "VARCHAR(100) NOT NULL")
    private String name;

    @Column(name = "duration", columnDefinition = "VARCHAR(15) NOT NULL")
    private String duration;

    @ManyToOne
    private Category category;

    @Column(name = "director", columnDefinition = "VARCHAR(100) NOT NULL")
    private String director;


    @Column(name = "publication_date", columnDefinition = "DATE NOT NULL")
    private Date publicationDate;

    @Column(name = "status", columnDefinition = "BOOL DEFAULT TRUE")
    private boolean status;

    public Movie() {
    }

    public Movie(String name, String duration, Category category, boolean status, String director, Date publicationDate) {
        this.name = name;
        this.duration = duration;
        this.category = category;
        this.status = status;
        this.director = director;
        this.publicationDate = publicationDate;
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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

}
