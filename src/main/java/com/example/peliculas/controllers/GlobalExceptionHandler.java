package com.example.peliculas.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.peliculas.exceptions.PeliculasNotFound;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;   


@ControllerAdvice
public class GlobalExceptionHandler {

   @ExceptionHandler(PeliculasNotFound.class)
   public ResponseEntity<Object> handlePeliculasNotFound(PeliculasNotFound ex) {
       Map<String, Object> body = new HashMap<>();

       
       body.put("timestamp", LocalDateTime.now());
       body.put("status", HttpStatus.NOT_FOUND.value());
       body.put("Error", "Not Found");
       body.put("message", ex.getMessage());

       return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
   }
}

