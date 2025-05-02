package com.example.peliculas.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.example.peliculas.controllers.PeliculaController;
import com.example.peliculas.models.Pelicula;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class PeliculaModelAssembler
        implements RepresentationModelAssembler<Pelicula, EntityModel<Pelicula>> {

    @Override
    public @NonNull EntityModel<Pelicula> toModel(@NonNull Pelicula pelicula) {

        return EntityModel.of(
                pelicula,

                // /self
                linkTo(methodOn(PeliculaController.class)
                        .obtenerPorId(pelicula.getId()))
                        .withSelfRel(),

                // /update
                linkTo(methodOn(PeliculaController.class)
                        .actualizar(pelicula.getId(), null))
                        .withRel("update"),

                // /delete
                linkTo(methodOn(PeliculaController.class)
                        .eliminar(pelicula.getId()))
                        .withRel("delete"),

                // /all
                linkTo(methodOn(PeliculaController.class)
                        .obtenerTodas())
                        .withRel("all")
        );
    }
}
