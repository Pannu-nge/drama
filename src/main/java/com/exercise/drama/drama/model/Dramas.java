package com.exercise.drama.drama.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Dramas {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long dramaId;
    private String title;
    private String description;
    @Temporal(TemporalType.DATE)
    private Date releasedDate;

    @OneToMany(cascade = CascadeType.REMOVE,
            fetch = FetchType.LAZY,
            mappedBy = "dramas")
    private Set<Seasons> seasons = new HashSet<Seasons>();

    @ManyToMany
    @JoinTable(
            name = "drama_genre",
            joinColumns = @JoinColumn(name = "drama_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres;

    @ManyToMany
    @JoinTable(
            name = "production_company",
            joinColumns = @JoinColumn(name = "drama_id"),
            inverseJoinColumns = @JoinColumn(name = "company_id")
    )
    private Set<Company> companies;

    @ManyToMany
    @JoinTable(
            name = "drama_crew",
            joinColumns = @JoinColumn(name = "drama_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private Set<Person> persons;

    public Long getDramaId() {
        return dramaId;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public Date getReleasedDate() {
        return releasedDate;
    }
    public void setDramaId(Long dramaId) {
        this.dramaId = dramaId;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setReleasedDate(Date releasedDate) {
        this.releasedDate = releasedDate;
    }

    public Set<Seasons> getSeasons() {
        return seasons;
    }

    public void setSeasons(Set<Seasons> seasons) {
        this.seasons = seasons;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Set<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<Company> companies) {
        this.companies = companies;
    }

    public Set<Person> getPersons() {
        return persons;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }
}
