import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;

public class Day2 {

    public static Boolean descendent(List<Integer> l) {
        Boolean result = true;
        for(int i = 0; i < l.size() - 1; i++) {
            if(l.get(i) < l.get(i+1)) {
                return false;
            }
        }
        return result;
    }

    public static Boolean ascendent(List<Integer> l) {
        Boolean result = true;
        for(int i = 0; i < l.size() - 1; i++) {
            if(l.get(i) > l.get(i+1)) {
                return false;
            }
        }
        return result;
    }

    public static Boolean correctDistance(List<Integer> l) {
        Boolean result = true;
        for(int i = 0; i < l.size() - 1; i++) {
            Integer distance = l.get(i) - l.get(i+1);
            if(distance < 0) {
                distance = distance * (-1);
            }

            if(distance < 1 || distance > 3) {
                return false;
            }
        }
        return result;
    }


    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);

       
        Integer safeReports = 0;

        while (true) {
            List<Integer> report = new ArrayList<>();
            // Leer la línea ingresada por el usuario
            String input = scanner.nextLine();
            // Verificar si el usuario quiere terminar
            if (input.equalsIgnoreCase("")) {
                break;
            }
            // Dividir la entrada en partes
            String[] inputs = input.trim().split("\\s+");
            // Validar que tenga al menos dos valores
            if (inputs.length >= 2) {
                try {
                    // Convertir las entradas a enteros
                    for(int i = 0; i < inputs.length; i++) {
                        int valor = Integer.parseInt(inputs[i]);    
                        report.add(valor);
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Error: Por favor, introduce dos números enteros válidos.");
                }

            } else {
                System.out.println("Entrada no válida. Por favor, introduce dos valores separados por un espacio.");
            }

            System.out.println("report");
            System.out.println(report);

            if( (ascendent(report) || descendent(report)) && correctDistance(report) ) {
                System.out.println("Safe report");
                safeReports = safeReports + 1;
            }
            
        }
        scanner.close();   

        System.out.println(safeReports);
            
    }
    

     
}
