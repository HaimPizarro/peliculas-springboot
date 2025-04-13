package com.example.peliculas.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.peliculas.models.Pelicula;
import com.example.peliculas.services.PeliculaService;
import com.example.peliculas.models.ResponseWrapper;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@RestController
@RequestMapping("/peliculas/")
public class PeliculaController {
    
    private final PeliculaService peliculaService;

    public PeliculaController(PeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }

    @GetMapping
    public List<Pelicula> obtenerTodas() {
        return peliculaService.obtenerTodas();
    }
    
    @GetMapping("/{id}")
    public Pelicula obtenerPorId(@PathVariable Long id) {
        return peliculaService.obtenerPorId(id);
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<Pelicula>> crear(@Valid @RequestBody Pelicula pelicula) {
        Pelicula nuevaPelicula = peliculaService.guardar(pelicula);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper<>("Pelicula creada con exito", 1, List.of(nuevaPelicula)));
    }


    @PutMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Pelicula>> actualizar(@PathVariable Long id, @Valid @RequestBody Pelicula actualizadaPelicula ) {
        Pelicula actualizadaPeliculaRes = peliculaService.actualizar(id, actualizadaPelicula);

        return ResponseEntity.ok(
                new ResponseWrapper<>("Pelicula actualizada con exito", 1, List.of(actualizadaPeliculaRes))
        );
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Pelicula>> eliminar(@PathVariable Long id) {
        peliculaService.eliminar(id);
        ResponseWrapper<Pelicula> wrapper = new ResponseWrapper<>("Pelicula eliminada con exito", 1, List.of());
        return ResponseEntity.ok(wrapper);

    }
}






