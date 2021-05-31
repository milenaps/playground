package com.mps.pocs;

public class Juros {

    /**
     * Entrada esperada no formato: <valor inicial> <total parcelas> <taxa juros>, exemplo: 100 3 0.1
     * @param args
     */
    public static void main(String[] args) {
        if (args != null && args.length >= 3) {
            try {
                double valorInicial = Double.parseDouble(args[0]);
                int totalParcelas = Integer.parseInt(args[1]);
                double taxaJuros = Double.parseDouble(args[2]);
                double valorFinal = valorInicial;

                System.out.println("Valor inicial: " + valorInicial);
                System.out.println("Total de parcelas: " + totalParcelas);
                System.out.println("Taxa de juros: " + taxaJuros);
                System.out.println("--------------------------");

                for (int i = 0; i < totalParcelas; i++) {
                    valorFinal += valorFinal * taxaJuros;
                }
                System.out.println("Valor final: " + valorFinal);

                System.out.println("Valor final otimizado: " + ((Math.pow(1 + taxaJuros, totalParcelas) * valorInicial)));
                return;
            } catch (Exception e) {
                //
            }
        }
        System.out.println("Parametros invalidos ou faltando");
    }
}
