package com.example.peliculas;

import com.example.peliculas.controllers.PeliculaController;
import com.example.peliculas.hateoas.PeliculaModelAssembler;
import com.example.peliculas.models.Pelicula;
import com.example.peliculas.services.PeliculaService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PeliculaController.class)
class PeliculaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("removal")
    @MockBean
    private PeliculaService peliculaService;

    @SuppressWarnings("removal")
    @MockBean
    private PeliculaModelAssembler peliculaAssembler;

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testObtenerPorId() throws Exception {

        // ---- Arrange ------------------------------------------------------
        Pelicula pelicula = new Pelicula(
                1L, "Matrix", 1999, "Wachowski", "Acción", "Ciencia ficción");

        EntityModel<Pelicula> peliculaModel = EntityModel.of(
                pelicula,
                linkTo(methodOn(PeliculaController.class).obtenerPorId(1L)).withSelfRel(),
                linkTo(methodOn(PeliculaController.class).actualizar(1L, null)).withRel("update"),
                linkTo(methodOn(PeliculaController.class).eliminar(1L)).withRel("delete"),
                linkTo(methodOn(PeliculaController.class).obtenerTodas()).withRel("all")
        );

        when(peliculaService.obtenerPorId(1L)).thenReturn(pelicula);
        when(peliculaAssembler.toModel(pelicula)).thenReturn(peliculaModel);

        // ---- Act & Assert -------------------------------------------------
        mockMvc.perform(get("/peliculas/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.titulo").value("Matrix"))
                .andExpect(jsonPath("$.anio").value(1999))
                .andExpect(jsonPath("$.genero").value("Acción"))
                .andExpect(jsonPath("$._links.self.href").exists())
                .andExpect(jsonPath("$._links.update.href").exists())
                .andExpect(jsonPath("$._links.delete.href").exists())
                .andExpect(jsonPath("$._links.all.href").exists());   // ← **punto-coma imprescindible**
    }
}
