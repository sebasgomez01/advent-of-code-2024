import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;

public class Day1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Listas para almacenar los valores
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        while (true) {
            // Leer la línea ingresada por el usuario
            String input = scanner.nextLine();

            // Verificar si el usuario quiere terminar
            if (input.equalsIgnoreCase("")) {
                break;
            }

            // Dividir la entrada en partes
            //String[] inputs = input.split("  ");
             // Dividir la entrada en partes (usando un solo espacio o más)
            String[] inputs = input.trim().split("\\s+");

            // Validar que tenga al menos dos valores
            if (inputs.length >= 2) {
                try {
                    // Convertir las entradas a enteros
                    int valor1 = Integer.parseInt(inputs[0]);
                    int valor2 = Integer.parseInt(inputs[1]);

                    // Agregar los valores a las listas
                    list1.add(valor1);
                    list2.add(valor2);
                } catch (NumberFormatException e) {
                    System.out.println("Error: Por favor, introduce dos números enteros válidos.");
                }
            } else {
                System.out.println("Entrada no válida. Por favor, introduce dos valores separados por un espacio.");
            }
        }

        //System.out.println("Primeros números: " + list1);
        //System.out.println("Segundos números: " + list2);
        Collections.sort(list1);
        Collections.sort(list2);
        //System.out.println("Primeros números: " + list1);
        //System.out.println("Segundos números: " + list2);

        Integer totalDistance = 0;
        for (int i = 0; i < list1.size(); i++) {
            Integer distance = list1.get(i) - list2.get(i);
            if(distance < 0) {
                distance = distance * (-1);
            }
            totalDistance = totalDistance + distance;
        }

        scanner.close();   

        Integer similarity = 0;
        for(Integer n : list1) {
            int apariciones = Collections.frequency(list2, n);
            similarity = similarity + n*apariciones;
        }

        System.out.println("total distance:");             
        System.out.println(totalDistance);       
        System.out.println("similarity:"); 
        System.out.println(similarity);     

    }
}
