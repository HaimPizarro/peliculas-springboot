package com.example.peliculas.repository;

//JPA repository
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.peliculas.models.Pelicula;

public interface PeliculasRepository extends JpaRepository<Pelicula, Long> {
    
}
