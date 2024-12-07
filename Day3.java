import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

public class Day3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int result = 0;
        while(true) {

    
            String input = scanner.nextLine();
            
            if (input.equalsIgnoreCase("")) {
                break;
            }

            String text = input;
            
            // El patrón para buscar "mul(xxx,xxx)"
            String regex = "mul\\((\\d+),(\\d+)\\)";
            
            
            Pattern pattern = Pattern.compile(regex);
            
            
            Matcher matcher = pattern.matcher(text);

            
            
           
            while (matcher.find()) {
               
                int num1 = Integer.parseInt(matcher.group(1)); // Primer número
                int num2 = Integer.parseInt(matcher.group(2)); // Segundo número
                
                System.out.println("producto entre");
                System.out.println(num1);
                System.out.println(num2);
                
                int product = num1 * num2;
                result = result + product;

            }
        }

        System.out.println(result);
    }
}
