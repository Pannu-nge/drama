package com.exercise.drama.drama.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Seasons {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seasonId;
    private String seasonName;
    @Column(name = "drama_id", nullable = false)
    private Long dramaId;

    @OneToMany(
            mappedBy = "seasonId",
            fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE
    )
    private java.util.List<Episodes> episodes;

    public Long getSeasonId() {
        return seasonId;
    }
    public void setSeasonId(Long seasonId) {
        this.seasonId = seasonId;
    }
    public String getSeasonName() {
        return seasonName;
    }
    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
    }
    public List<Episodes> getEpisodes() {
        return episodes;
    }
    public void setEpisodes(List<Episodes> episodes) {
        this.episodes = episodes;
    }
    public Long getDramaId() {
        return dramaId;
    }
    public void setDramaId(Long dramaId) {
        this.dramaId = dramaId;
    }
}
