package com.example.peliculas.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PELICULA", schema = "SPRING_BOOT_E1")
public class Pelicula {
    
    @Id
    @NotNull(message = "El campo id no puede ser nulo")
    @Positive(message = "El campo id debe ser un número positivo")
    private Long id;
    
    @NotBlank(message = "El campo titulo no puede estar en blanco")
    @Size(min = 1, max = 100, message = "El campo titulo debe tener entre 1 y 100 caracteres")
    private String titulo;

    @Min(value = 1800, message = "El campo anio debe ser mayor o igual a 1800")
    @Max(value = 2100, message = "El campo anio debe ser menor o igual a 2100")
    private int anio;

    @NotBlank(message = "El campo director no puede estar en blanco")
    @Size(min = 1, max = 100, message = "El campo director debe tener entre 1 y 100 caracteres")
    private String director;

    @NotBlank(message = "El campo genero no puede estar en blanco")
    @Size(min = 1, max = 100, message = "El campo genero debe tener entre 1 y 100 caracteres")
    private String genero;

    @Size(max = 255, message = "El campo sinopsis no debe superar los 255 caracteres")
    private String sinopsis;
}