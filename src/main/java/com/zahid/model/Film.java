package com.zahid.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "film", schema = "sakila")
public class Film {

    public Film(Short filmId, String title, Short length) {
        this.filmId = filmId;
        this.title = title;
        this.length = length;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Short filmId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "release_year")
    private Integer releaseYear;

    @Column(name = "language_id", nullable = false)
    private Short languageId;

    @Column(name = "original_language_id")
    private Short originalLanguageId;

    @Column(name = "rental_duration", nullable = false, columnDefinition = "smallint(5) unsigned default 3")
    private Short rentalDuration;

    @Column(name = "rental_rate", nullable = false, columnDefinition = "decimal(4,2) default 4.99")
    private BigDecimal rentalRate;

    @Column(name = "length")
    private Short length;

    @Column(name = "replacement_cost", nullable = false, columnDefinition = "decimal(5,2) default 19.99")
    private BigDecimal replacementCost;

    @Column(name = "rating", columnDefinition = "enum('G','PG','PG-13','R','NC-17') default 'G'")
    private String rating;

    @Column(name = "special_features", columnDefinition = "set('Trailers','Commentaries','Deleted Scenes','Behind the Scenes')")
    private String specialFeatures;

    @Column(name = "last_update", nullable = false, columnDefinition = "timestamp default current_timestamp on update current_timestamp")
    private Timestamp lastUpdate;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(name = "film_actor", joinColumns = { @JoinColumn(name = "film_id") }, inverseJoinColumns = {
            @JoinColumn(name = "actor_id") }

    )
    private Set<Actor> actors = new HashSet<>();
}
