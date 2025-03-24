package com.example.peliculas.services;

import com.example.peliculas.models.Pelicula;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PeliculaService {
 
    private final List<Pelicula> peliculas = new ArrayList<>();

    public PeliculaService() {
        peliculas.add(new Pelicula(1L, "The Matrix", 1999, "Lana Wachowski, Lilly Wachowski", "Ciencia Ficcion", "Un hacker descubre la verdadera naturaleza de su realidad."));
        peliculas.add(new Pelicula(2L, "The Shawshank Redemption", 1994, "Frank Darabont", "Drama", "Un hombre injustamente encarcelado encuentra esperanza y redención."));
        peliculas.add(new Pelicula(3L, "The Godfather", 1972, "Francis Ford Coppola", "Crimen", "La historia de una poderosa familia mafiosa en Estados Unidos."));
        peliculas.add(new Pelicula(4L, "Pulp Fiction", 1994, "Quentin Tarantino", "Crimen", "Historias entrelazadas de crimen, redención y caos en Los Ángeles."));
        peliculas.add(new Pelicula(5L, "The Dark Knight", 2008, "Christopher Nolan", "Accion", "Batman enfrenta al Joker, un criminal caótico que pone a prueba su moral."));

    }
    
    public List<Pelicula> obtenerTodas() {
        return peliculas;
    }

    public Optional<Pelicula> obtenerPorId(Long id) {
        return peliculas.stream()
                        .filter(p -> p.getId().equals(id))
                        .findFirst();
    }
}
