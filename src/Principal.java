import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.println("\n¡Bienvenido al Conversor de Monedas!");
        System.out.println("Porfavor ingrese su APIKEY: ");
        String apikey = scanner.nextLine();

        ConsultaApi consultaApi = new ConsultaApi(apikey);
        ConversorDeMonedas conversor = new ConversorDeMonedas(consultaApi);
        boolean running = true;

        while (running) {
            System.out.println("\n--- Conversor de Moneda ---");
            System.out.println("Seleccione una opción:");
            System.out.println("1. Convertir de Dólar (USD) a Peso Colombiano (COP)");
            System.out.println("2. Convertir de Peso Colombiano (COP) a Dólar (USD)");
            System.out.println("3. Convertir de Peso Colombiano (COP) a Euro (EUR)");
            System.out.println("4. Convertir de Euro (EUR) a Peso Colombiano (COP)");
            System.out.println("5. Convertir de Peso Colombiano (COP) a Libra Esterlina (GBP)");
            System.out.println("6. Convertir de Libra Esterlina (GBP) a Peso Colombiano (COP)");
            System.out.println("7. Convertir de Peso Colombiano (COP) a Yen Japonés (JPY)");
            System.out.println("8. Convertir de Yen Japonés (JPY) a Peso Colombiano (COP)");
            System.out.println("9. Convertir de Dólar (USD) a Euro (EUR)");
            System.out.println("10. Convertir de Euro (EUR) a Dólar (USD)");
            System.out.println("0. Salir");
            System.out.print("Ingrese el número de la opción: ");


            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine();
                String fromCurrency = "";
                String toCurrency = "";

                switch (choice) {
                    case 1:
                        fromCurrency = "USD";
                        toCurrency = "COP";
                        break;
                    case 2:
                        fromCurrency = "COP";
                        toCurrency = "USD";
                        break;
                    case 3:
                        fromCurrency = "COP";
                        toCurrency = "EUR";
                        break;
                    case 4:
                        fromCurrency = "EUR";
                        toCurrency = "COP";
                        break;
                    case 5:
                        fromCurrency = "COP";
                        toCurrency = "GBP";
                        break;
                    case 6:
                        fromCurrency = "GBP";
                        toCurrency = "COP";
                        break;
                    case 7:
                        fromCurrency = "COP";
                        toCurrency = "JPY";
                        break;
                    case 8:
                        fromCurrency = "JPY";
                        toCurrency = "COP";
                        break;
                    case 9:
                        fromCurrency = "USD";
                        toCurrency = "EUR";
                        break;
                    case 10:
                        fromCurrency = "EUR";
                        toCurrency = "USD";
                        break;
                    case 0:
                        running = false;
                        System.out.println("Saliendo del programa.");
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, ingrese un número del 0 al 10.");
                        continue;
                }

                if (!fromCurrency.isEmpty() && !toCurrency.isEmpty()) {
                    convertAndDisplay(scanner, conversor, fromCurrency, toCurrency);
                }
            } else {
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
                scanner.nextLine(); // Consume invalid input
            }
        }
        scanner.close();
    }

    private static void convertAndDisplay(Scanner scanner, ConversorDeMonedas conversor, String fromCurrency, String toCurrency) {
        System.out.print("Ingrese la cantidad a convertir de " + fromCurrency + ": ");
        if (scanner.hasNextDouble()) {
            double amount = scanner.nextDouble();
            scanner.nextLine();
            double convertedAmount = conversor.convertCurrency(amount, fromCurrency, toCurrency);
            if (convertedAmount != -1) {
                System.out.println(amount + " " + fromCurrency + " son equivalentes a " + String.format("%.2f", convertedAmount) + " " + toCurrency);
            }
        } else {
            System.out.println("Cantidad no válida. Por favor, ingrese un número.");
            scanner.nextLine();
        }
    }
}