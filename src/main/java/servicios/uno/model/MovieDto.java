package servicios.uno.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MovieDto {
    @NotNull(groups = {Modify.class, ChangeStatus.class})
    private Long id;

    @NotBlank(groups = {Register.class, Modify.class, FindName.class})
    private String name;

    @NotBlank(groups = {Register.class, Modify.class})
    private String duration;

    @NotNull(groups = {Register.class, Modify.class, FindCategory.class})
    private Category category;

    @NotBlank(groups = {Register.class, Modify.class, FindDirector.class})
    private String director;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotBlank(groups = {Register.class, Modify.class, FindByOrderByPublicationDateDesc.class})
    private String publicationDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotBlank(groups = {FindPublicationDateBetweenDates.class})
    private String startDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotBlank(groups = {FindPublicationDateBetweenDates.class})
    private String endDate;

    private boolean status;

    public MovieDto() {
    }

    public MovieDto(Long id, String name, String duration, Category category, String director, String publicationDate, boolean status) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.category = category;
        this.director = director;
        this.publicationDate = publicationDate;
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

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Date getPublicationDate() {
        try {
            return new SimpleDateFormat("yyyy/MM/dd").parse(String.valueOf(publicationDate));
        } catch (Exception e) {
            return null;
        }
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getStartDate() {
        try {
            return new SimpleDateFormat("yyyy/MM/dd").parse(String.valueOf(startDate));
        } catch (Exception e) {
            return null;
        }
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        try {
            return new SimpleDateFormat("yyyy/MM/dd").parse(String.valueOf(endDate));
        } catch (Exception e) {
            return null;
        }
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }


    public interface Register {
    }

    public interface Modify {
    }

    public interface ChangeStatus {
    }

    public interface FindName {
    }

    public interface FindDirector {
    }

    public interface FindCategory {
    }

    public interface FindPublicationDateBetweenDates {
    }

    public interface FindByOrderByPublicationDateDesc {
    }
}
