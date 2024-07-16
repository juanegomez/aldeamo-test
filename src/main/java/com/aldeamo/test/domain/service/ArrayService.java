package com.aldeamo.test.domain.service;

import com.aldeamo.test.persistence.ArrayRepository; // Importación del repositorio de arrays
import com.aldeamo.test.persistence.entity.Array; // Importación de la entidad Array
import org.springframework.beans.factory.annotation.Autowired; // Importación de la anotación de inyección de dependencias
import org.springframework.http.HttpStatus; // Importación de clases relacionadas con HTTP
import org.springframework.stereotype.Service; // Importación de la anotación de servicio
import org.springframework.web.server.ResponseStatusException; // Importación de la excepción de estado de respuesta

import java.util.ArrayList; // Importación de clases para listas
import java.util.Arrays; // Importación de clases para manejo de arreglos
import java.util.List; // Importación de clases para listas
import java.util.Optional; // Importación de clases para manejo de valores opcionales
import java.util.stream.Collectors; // Importación de clases para operaciones de transmisión
import java.util.stream.IntStream; // Importación de clases para operaciones de transmisión

/**
 * Servicio que maneja operaciones sobre arreglos de vasos.
 */
@Service // Anotación que marca esta clase como un servicio de Spring
public class ArrayService {

    @Autowired // Anotación para inyección automática de dependencias
    private ArrayRepository arrayRepository; // Repositorio para acceder a datos de Array

    /**
     * Método principal que realiza la operación sobre el arreglo de vasos.
     *
     * @param arrayId    Identificador del arreglo de vasos en la base de datos.
     * @param iterations Número de iteraciones que se deben realizar con los números primos.
     * @return Lista de enteros que contiene el resultado de la operación.
     */
    public List<Integer> exercise(int arrayId, int iterations) {
        // Obtener el arreglo de vasos desde el repositorio basado en el ID proporcionado
        Optional<Array> glassesDb = arrayRepository.getArray(arrayId);

        // Verificar si el arreglo de vasos existe en la base de datos
        if (glassesDb.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El arreglo de vasos no existe, seleccione otro id.");
        }

        // Convertir la cadena de entrada del arreglo a un arreglo de enteros
        Integer[] glassesArray = convertStringToIntegerArray(glassesDb.get().getInputArray());

        // Inicializar listas para almacenar el resultado final y los arreglos intermedios
        List<Integer> result = new ArrayList<>();
        List<Integer> currentArray = new ArrayList<>(Arrays.asList(glassesArray)); // Convertir el arreglo a una lista mutable
        List<Integer> arrayB = new ArrayList<>(); // Lista para almacenar los vasos filtrados

        // Generar una lista de números primos para filtrar los vasos
        List<Integer> primes = generatePrimes(iterations);

        // Iterar sobre cada número primo
        for (int prime : primes) {
            List<Integer> toRemove = new ArrayList<>(); // Lista de índices a remover

            // Verificar si currentArray está vacío antes de continuar con el próximo primo
            if (currentArray.isEmpty()) {
                break;
            }

            // Iterar sobre el arreglo actual de vasos de atrás hacia adelante
            for (int i = currentArray.size() - 1; i >= 0; i--) {
                Integer glass = currentArray.get(i);
                // Verificar si el vaso es divisible por el número primo actual
                if (glass % prime == 0) {
                    arrayB.add(glass); // Agregar el vaso a la lista filtrada
                    toRemove.add(i); // Agregar el índice del vaso a la lista de índices a remover
                }
            }

            // Eliminar los vasos filtrados del arreglo actual
            for (Integer indexToRemove : toRemove) {
                currentArray.remove((int) indexToRemove);
            }
        }

        // Agregar los vasos filtrados y los restantes al resultado final
        result.addAll(arrayB);
        result.addAll(currentArray);

        return result; // Devolver el resultado final
    }


    /**
     * Convierte una cadena de entrada en un arreglo de enteros.
     *
     * @param input Cadena de entrada que contiene los números separados por coma.
     * @return Arreglo de enteros generado a partir de la cadena de entrada.
     */
    private Integer[] convertStringToIntegerArray(String input) {
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .toArray(Integer[]::new); // Convertir el flujo a un arreglo de enteros
    }

    /**
     * Genera una lista de números primos entre 2 y 100.
     *
     * @param count Número de números primos a generar.
     * @return Lista de números primos.
     */
    private List<Integer> generatePrimes(int count) {
        return IntStream.rangeClosed(2, Integer.MAX_VALUE) // Generar números del 2 al máximo valor de entero
                .filter(this::isPrime) // Filtrar los números que son primos
                .limit(count) // Limitar la cantidad de números primos a generar
                .boxed() // Convertir cada entero primitivo a su objeto correspondiente Integer
                .collect(Collectors.toList()); // Recolectar los números primos en una lista
    }

    /**
     * Verifica si un número dado es primo.
     *
     * @param num Número a verificar.
     * @return true si el número es primo, false de lo contrario.
     */
    private boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}