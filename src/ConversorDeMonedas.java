public class ConversorDeMonedas {

    private final ConsultaApi consultaApi;

    public ConversorDeMonedas(ConsultaApi consultaApi) {
        this.consultaApi = consultaApi;
    }

    public double convertCurrency(double amount, String fromCurrency, String toCurrency) {
        Double exchangeRate = consultaApi.fetchExchangeRate(fromCurrency, toCurrency);

        if (exchangeRate != null && exchangeRate != -1) {
            return amount * exchangeRate;
        } else {
            System.out.println("No se pudo obtener la tasa de cambio para " + fromCurrency + " a " + toCurrency + ".");
            return -1;
        }
    }
}