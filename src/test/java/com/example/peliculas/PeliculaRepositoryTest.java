package com.example.peliculas;

import com.example.peliculas.models.Pelicula;
import com.example.peliculas.repository.PeliculasRepository;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;



@DataJpaTest
public class PeliculaRepositoryTest {
    
    @Autowired
    private PeliculasRepository peliculasRepository;

    @Test
    public void testGuardarYBuscarPelicula() {

        Pelicula pelicula = new Pelicula(1L, "Pelicula 1", 2019, "Director 1", "Género 1", "Sinopsis 1");
        peliculasRepository.save(pelicula);
        Optional<Pelicula> peliculaActualizada = peliculasRepository.findById(1L);
        assertTrue(peliculaActualizada.isPresent());
        assertEquals("Pelicula 1", peliculaActualizada.get().getTitulo());
    }
}
