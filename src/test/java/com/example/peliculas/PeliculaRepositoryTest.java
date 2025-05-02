package com.example.peliculas;

import com.example.peliculas.models.Pelicula;
import com.example.peliculas.repository.PeliculasRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest          // sin properties extra
class PeliculaRepositoryTest {

    @Autowired
    PeliculasRepository repo;

    @Test
    void guardarYBuscarPelicula() {

        Pelicula p = new Pelicula(1L, "Peli 1", 2019,
                                  "Dir 1", "Gen 1", "Sinopsis 1");

        repo.save(p);

        assertThat(repo.findById(1L))
                .isPresent()
                .get()
                .extracting(Pelicula::getTitulo)
                .isEqualTo("Peli 1");
    }
}
