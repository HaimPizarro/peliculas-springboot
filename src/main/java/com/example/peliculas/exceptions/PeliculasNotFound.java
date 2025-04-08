package com.example.peliculas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus; 

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PeliculasNotFound extends RuntimeException {
    public PeliculasNotFound(Long id) {

        super("No se ha encontrado el pelicula con id: " + id);
    }
}
