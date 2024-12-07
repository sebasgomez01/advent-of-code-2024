import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;

public class Day2 {

    public static Integer distance(Integer i, Integer j) {
        Integer result = i - j;
        if(result < 0) {
            result = result * (-1);
        }
        return result;
    }

    public static Boolean descendent(List<Integer> l) {
        Boolean result = true;
        for(int i = 0; i < l.size() - 1; i++) {
            if(l.get(i) < l.get(i+1)) {
                return false;
            }
        }
        return result;
    }

    public static Boolean isSafeAfterRemovingOne(List<Integer> l) {
    // Intentar eliminar cada nivel y verificar si el resto es seguro
    for (int i = 0; i < l.size(); i++) {
        List<Integer> copy = new ArrayList<>(l);
        copy.remove(i); // Eliminar un nivel
        if ((ascendent(copy) || descendent(copy)) && correctDistance(copy)) {
            return true; // Si es seguro después de eliminar este nivel
        }
    }
        return false; // Ninguna eliminación hace el reporte seguro
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
            Integer distance = distance(l.get(i), l.get(i+1));
            if(distance < 1 || distance > 3) {
                return false;
            }
        }
        return result;
    }

    public static Integer badLevelsCount(List<Integer> l) {
        Integer count = 0;
        Integer badLevelIndex = -1;
        
        // niveles malos por tener mucha distancia
        for(int i = 0; i < l.size() - 1; i++) {
            Integer distance = distance(l.get(i), l.get(i+1));
            if(distance < 1 || distance > 3) {
                count++;
                System.out.println("contador bad levels:");
                System.out.println(count);
                
                if(count > 1) {
                    return -1;
                }
            badLevelIndex = i;
            }
        }

        List<Integer> copy = new ArrayList<>();
        for(int i = 0; i < l.size(); i++) {
            if(i != badLevelIndex) {
                copy.add(l.get(i));
            }
        }
        System.out.println("copy:");
        System.out.println(copy);
        badLevelIndex = -1;
        count = 0;
        // niveles malos porque rompen el ascendente o descendente
        for(int i = 1; i < l.size() - 1; i++) {
            if( (l.get(i) > l.get(i + 1) && l.get(i) > l.get(i - 1)) || 
            (l.get(i) < l.get(i + 1) && l.get(i) <  l.get(i - 1))   ) {
                count++;
                System.out.println("contador bad levels:");
                System.out.println(count);
                i++;
                if(count > 1) {
                    return -1;
                }
                badLevelIndex = i;
            }   
        }

        List<Integer> copy2 = new ArrayList<>();
        for(int i = 0; i < copy.size(); i++) {
            if(i != badLevelIndex) {
                copy2.add(copy.get(i));
            }
        }
        System.out.println("copy2:");
        System.out.println(copy2);

        // casos borde en los que el primero y el último rompen la cadena ascendente o descendente
        if(copy2.size() == l.size()) {

            if(copy2.get(0) > copy2.get(1) && copy2.get(1) < copy2.get(2)) {
                copy2.remove(0);
            } else if(copy2.get(copy2.size() - 1) > copy2.get(copy2.size() - 2) && 
                    copy2.get(copy2.size() - 2) < copy2.get(copy2.size() - 3)) {
                copy2.remove(copy2.size() - 1);
            }
        }

        if( (ascendent(copy2) || descendent(copy2)) && correctDistance(copy2) ) {
            return 1;
        }

        return -1;
    }

    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);

       
        Integer safeReports = 0;
        Integer safeReportsWithOneBadLevel = 0;
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

            if(isSafeAfterRemovingOne(report)) {
                safeReportsWithOneBadLevel++;
            }

            //if(badLevelsCount(report) == 1) {
            
            //    safeReportsWithOneBadLevel++;
            //}

            //isSafeAfterRemovingOne(List<Integer> l)

            
        }
        scanner.close();   
        System.out.println("safe reports");
        System.out.println(safeReports);

        System.out.println("safe reports with one bad level");
        System.out.println(safeReportsWithOneBadLevel);
    }
    

     
}

//(ascendent(report) || descendent(report)) && 
/* 
public static Boolean correctDistannceWihtOneBadLevel(List<Integer> l) {
        Boolean result = true;
        Integer badLevelCount = 0;
        Integer badLevelIndex;
        for(int i = 1; i < l.size() - 1; i++) {
            Integer distanceNext = distance(l.get(i), l.get(i+1));

            if( (distanceNext < 1 || distanceNext > 3) || (l.get(i) > l.get(i+1) && l.get(i) > l.get(i-1))
                || (l.get(i) < l.get(i+1) && l.get(i) < l.get(i-1) )) {
                badLevelCount = badLevelCount + 1;
                badLevelIndex = i;
                if(badLevelCount > 1) {
                    return false;
                }
            }
        }

        Integer distanceFirstElement = distance(l.get(0), l.get(1));
        if( (distanceFirstElement < 1 || distanceFirstElement > 3) || (l.get(0) > l.get(1) && l.get(1) < l.get(2)) || 
            (l.get(0) < l.get(1) && l.get(1) > l.get(2) )  ) 
        {
            badLevelCount++;
        }  

        if(badLevelCount > 1) {
            return false;
        }

        return result;
    }



*/