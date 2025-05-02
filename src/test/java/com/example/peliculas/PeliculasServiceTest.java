package com.example.peliculas;

import com.example.peliculas.exceptions.PeliculasNotFound;
import com.example.peliculas.models.Pelicula;
import com.example.peliculas.repository.PeliculasRepository;
import com.example.peliculas.services.PeliculaService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PeliculasServiceTest {

    @Mock
    private PeliculasRepository peliculasRepository;

    @InjectMocks
    private PeliculaService peliculaService;

    @Test
    void obtenerTodas_devuelveTodasOrdenadas() {
        Pelicula p1 = new Pelicula(1L, "Peli 1", 2019, "Dir 1", "Gen 1", "Sinopsis 1");
        Pelicula p2 = new Pelicula(2L, "Peli 2", 2020, "Dir 2", "Gen 2", "Sinopsis 2");

        when(peliculasRepository.findAll()).thenReturn(Arrays.asList(p1, p2));

        List<Pelicula> resultado = peliculaService.obtenerTodas();

        assertEquals(2, resultado.size());
        assertEquals("Peli 1", resultado.get(0).getTitulo());
        verify(peliculasRepository).findAll();        
        verifyNoMoreInteractions(peliculasRepository);
    }

    @Test
    void obtenerPorId_cuandoExiste_retornaEntidad() {
        Pelicula peli = new Pelicula(1L, "Peli 1", 2019, "Dir 1", "Gen 1", "Sinopsis 1");
        when(peliculasRepository.findById(1L)).thenReturn(Optional.of(peli));

        Pelicula res = peliculaService.obtenerPorId(1L);

        assertNotNull(res);
        assertEquals(2019, res.getAnio());
        verify(peliculasRepository).findById(1L);
    }

    @Test
    void obtenerPorId_cuandoNoExiste_lanzaExcepcion() {
        when(peliculasRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(PeliculasNotFound.class, () -> peliculaService.obtenerPorId(99L));
        verify(peliculasRepository).findById(99L);
    }


}
