package com.mycompany.tarea;

import java.util.HashMap;

public class Tarea {

    public static void main(String[] args) {

        System.out.println("======================================");
        System.out.println("LABORATORIO: PROCESAMIENTO MASIVO");
        System.out.println("======================================");

        System.out.println("Nombre: JONATHAN JUAN DAVID OSORIO PASCUAL");
        System.out.println("Carnet: 9941 24 14047");

        String archivo = "clientes.csv";
        int cantidadClientes = 2000000;

        System.out.println("\nGenerando archivo...");
        long inicioGeneracion = System.currentTimeMillis();

        // Corregido: Ahora llama a GeneradorCliente con 'G' mayúscula
        GeneradorCliente.generarArchivo(archivo, cantidadClientes);

        long finGeneracion = System.currentTimeMillis();
        System.out.println("Tiempo de generacion: "
                + (finGeneracion - inicioGeneracion) + " ms");

        mostrarMemoria();

        System.out.println("\nProcesando clientes con HashMap...");
        long inicioProceso = System.currentTimeMillis();

        HashMap<String, Integer> campanias =
                ProcesadorOptimizado.procesarClientes(archivo);

        long finProceso = System.currentTimeMillis();

        System.out.println("\n=== RESUMEN FINAL ===");
        System.out.println("Total campañas: " + campanias.size());

        for (String key : campanias.keySet()) {
            System.out.println(key + " : " + campanias.get(key));
        }

        System.out.println("\nTiempo procesamiento optimizado: "
                + (finProceso - inicioProceso) + " ms");

        mostrarMemoria();

        System.out.println("\nPrograma finalizado.");
    }

    public static void mostrarMemoria() {
        Runtime runtime = Runtime.getRuntime();
        long usada = runtime.totalMemory() - runtime.freeMemory();
        long total = runtime.totalMemory();
        long maxima = runtime.maxMemory();

        System.out.println("\n=== MEMORIA JVM ===");
        System.out.println("Usada: " + (usada / 1024 / 1024) + " MB");
        System.out.println("Total: " + (total / 1024 / 1024) + " MB");
        System.out.println("Maxima: " + (maxima / 1024 / 1024) + " MB");
    }
}