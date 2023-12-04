package com.zahid.model;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.stream.Collectors;

import com.zahid.repository.FilmRepository;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/")
public class FilmResource {

    @Inject
    FilmRepository filmRepository;

    @GET
    @Path("/helloWorld")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloWord() {
        return "Hello World!";
    }

    @GET
    @Path("/helloWorld2")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloWord2() {
        return "Hello World 2!";
    }

    @GET
    @Path("/film/{filmId}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getFildm(Short filmId) {
        Optional<Film> film = filmRepository.getFilm(filmId);

        return film.isPresent() ? film.get().getTitle(): "No matching fildm found"; 
    }

    @GET
    @Path("/pagedFilms/{page}/{minLength}")
    @Produces(MediaType.TEXT_PLAIN)
    public String paged(Long page, Short minLength) {
        return filmRepository.paged(page, minLength)
            .map(f -> String.format("%s (%d min)", f.getTitle(), f.getLength()))
            .collect(Collectors.joining("\n"));
    }

    @GET
    @Path("/actors/{startsWith}/{minLength}")
    @Produces(MediaType.TEXT_PLAIN)
    public String actors(String startsWith, Short minLength) {
        return filmRepository.actors(startsWith, minLength)
            .map(f -> String.format("%s (%d min): %s",
                f.getTitle(),
                f.getLength(),
                f.getActors().stream()
                    .map(a -> String.format("%s %s", a.getFirstName(), a.getLastName()))
                    .collect(Collectors.joining(","))))
            .collect(Collectors.joining("\n"));
    }

    @GET
    @Path("/update/{minLength}/{rentalRate}")
    @Produces(MediaType.TEXT_PLAIN)
    public String update(Short minLength, Float rentalRate) {
        filmRepository.updateRentalRate(minLength, rentalRate);
        return filmRepository.getFilms(minLength)
            .map(f -> String.format("%s (%d min): $%f",
                f.getTitle(),
                f.getLength(),
                f.getRentalRate().floatValue())
            )
            .collect(Collectors.joining("\n"));
    }

}
