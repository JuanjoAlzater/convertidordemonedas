import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConsultaApi {

    private static final String API_BASE_URL = "https://v6.exchangerate-api.com/v6/";
    private static final String API_KEY = "675424c6e492ffadc9acb2e5";

    private final Gson gson = new Gson();

    public Double fetchExchangeRate(String fromCurrency, String toCurrency) {
        try {
            // Construct the URL for the specific currency pair
            String apiUrl = API_BASE_URL+API_KEY+"/pair/"+ fromCurrency+"/"+toCurrency;
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Parse the JSON response using Gson
                ExchangeRateResponse responseData = gson.fromJson(response.toString(), ExchangeRateResponse.class);
                if (responseData.getResult().equals("success")) {
                    return responseData.getConversionRate();
                } else {
                    System.out.println("Error from API: " + responseData.getErrorType());
                    return -1.0;
                }

            } else {
                System.out.println("Error al conectar con la API. Código de respuesta: " + responseCode);
                return -1.0;
            }

        } catch (IOException e) {
            System.out.println("Error al realizar la petición a la API: " + e.getMessage());
            return -1.0;
        }
    }

    // Helper class to represent the structure of the JSON response for a pair
    private static class ExchangeRateResponse {
        private String result;
        private String error_type;
        private double conversion_rate;

        public String getResult() {
            return result;
        }

        public String getErrorType() {
            return error_type;
        }

        public double getConversionRate() {
            return conversion_rate;
        }
    }
}