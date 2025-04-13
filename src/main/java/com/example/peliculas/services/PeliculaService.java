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

    public Pelicula guardar(Pelicula pelicula) {
        if (peliculasRepository.existsById(pelicula.getId())) {
            throw new IllegalArgumentException("Ya existe una pelicula con ese id: "+pelicula.getId());
        }
        return peliculasRepository.save(pelicula);
    }

    public  Pelicula actualizar(Long id, Pelicula peliculaActualizada) {
        Pelicula peliculaActualizadaRes = peliculasRepository.findById(id)
                .orElseThrow(() -> new PeliculasNotFound(id));
        peliculaActualizadaRes.setTitulo(peliculaActualizada.getTitulo());
        peliculaActualizadaRes.setAnio(peliculaActualizada.getAnio());
        peliculaActualizadaRes.setDirector(peliculaActualizada.getDirector());
        peliculaActualizadaRes.setGenero(peliculaActualizada.getGenero());
        peliculaActualizadaRes.setSinopsis(peliculaActualizada.getSinopsis());
        return peliculasRepository.save(peliculaActualizadaRes);
    }

    public void eliminar(Long id) {
        Pelicula peliculaEliminadaRes = peliculasRepository.findById(id).orElseThrow(() -> new PeliculasNotFound(id));
        peliculasRepository.delete(peliculaEliminadaRes);
    }
} 