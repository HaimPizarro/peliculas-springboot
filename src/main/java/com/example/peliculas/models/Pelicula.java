package com.example.peliculas.models;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "peliculas")
public class Pelicula {
    @Id
    private Long id;
    private String titulo;
    private int anio;
    private String director;
    private String genero;
    private String sinopsis;
}
