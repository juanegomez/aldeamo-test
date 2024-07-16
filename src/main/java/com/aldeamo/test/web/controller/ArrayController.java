package com.aldeamo.test.web.controller;

import com.aldeamo.test.domain.service.ArrayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bartender")
public class ArrayController {

    @Autowired
    private ArrayService arrayService;

    /**
     * Endpoint para procesar la respuesta de la prueba del bartender.
     *
     * @param arrayId     El ID del arreglo de vasos del bartender.
     * @param iterations  El número de iteraciones a realizar.
     * @return Una lista de enteros que contiene el resultado de la prueba.
     */
    @GetMapping("/exercise")
    public ResponseEntity<List<Integer>> getArray(@RequestParam("arrayId") int arrayId,
                                                  @RequestParam("iterations") int iterations) {
        try {
            List<Integer> result = arrayService.exercise(arrayId, iterations);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            // Manejo de errores si el arrayId no existe o hay otro problema con los parámetros
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            // Manejo de errores generales
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
