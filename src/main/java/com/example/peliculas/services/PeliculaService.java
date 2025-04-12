package com.example.peliculas.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.peliculas.models.Pelicula;
import com.example.peliculas.repository.PeliculasRepository;
import com.example.peliculas.exceptions.PeliculasNotFound;

@Service
public class PeliculaService {

    private final PeliculasRepository peliculasRepository;

    public PeliculaService(PeliculasRepository peliculasRepository) {
        this.peliculasRepository = peliculasRepository;
    }

    public List<Pelicula> obtenerTodas() {
        return peliculasRepository.findAll();
    }

    public Pelicula obtenerPorId(Long id) {
        return peliculasRepository.findById(id)
                .orElseThrow(() -> new PeliculasNotFound(id));
    }
} 