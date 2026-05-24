package com.mycompany.tarea;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class ProcesadorOptimizado {

    public static HashMap<String, Integer> procesarClientes(String archivo) {
        HashMap<String, Integer> campanias = new HashMap<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea;
            br.readLine(); // Saltar encabezado

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";", 8); 

                if (datos.length < 8) continue; 

                // Corregido: Ahora instancia 'cliente' en minúscula para coincidir con el tipo
                cliente cl = new cliente(
                        Integer.parseInt(datos[0]),
                        datos[1],
                        Double.parseDouble(datos[2]),
                        datos[3],
                        datos[4],
                        Integer.parseInt(datos[5]),
                        Double.parseDouble(datos[6]),
                        datos[7]
                );

                String campania = determinarCampania(cl);
                campanias.merge(campania, 1, Integer::sum);
            }
            br.close();

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error procesando archivo: " + e.getMessage());
        }

        return campanias;
    }

    // Corregido: Recibe el tipo de dato 'cliente' con minúscula
    public static String determinarCampania(cliente cliente) {
        String nivelIngreso = (cliente.getIngreso() >= 25000) ? "INGRESO_ALTO" :
                              (cliente.getIngreso() >= 15000) ? "INGRESO_MEDIO" :
                              (cliente.getIngreso() >= 10000) ? "INGRESO_BAJO" : "NO_APLICA";

        String nivelScore = (cliente.getScore() >= 800) ? "SCORE_EXCELENTE" :
                            (cliente.getScore() >= 600) ? "SCORE_BUENO" :
                            (cliente.getScore() >= 400) ? "SCORE_REGULAR" : "SCORE_RIESGO";

        String nivelDeuda = (cliente.getDeuda() >= 7000) ? "DEUDA_ALTA" :
                            (cliente.getDeuda() >= 3000) ? "DEUDA_MEDIA" : "DEUDA_BAJA";

        return cliente.getSegmento() + "_" + cliente.getRegion() + "_" + nivelIngreso + "_" + nivelScore + "_" + nivelDeuda;
    }
}