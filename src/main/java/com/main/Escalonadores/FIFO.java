package com.main.Escalonadores;

import com.main.Model.Processo;
import java.util.Arrays;
public class FIFO {
    public static void fifo(Processo[] processos) {
        System.out.println("\n[ESCALONAMENTO: FIFO]\n");

        int tempoTotalProcessamento = 0;
        int tempoDeEspera = 0;
        int[] temposDeEsperas = new int[processos.length];

        int index = 0;

        for (Processo processo : processos) {
            temposDeEsperas[index] = tempoDeEspera;

            System.out.print(processo.getNome() + ": ");
            for (int j = 0; j < processo.getTempoUCP(); j++) {
                System.out.print("⬜");
            }

            int tempoDeProcessamento = index > 0 ?
                    processo.getTempoUCP() + processos[index - 1].getTempoProcessamento()
                    : processo.getTempoUCP();

            processo.setTempoProcessamento(tempoDeProcessamento);

            System.out.println(" (" + processo.getTempoUCP() + " de UCP)" +
                    " (" + tempoDeProcessamento + " total)");

            tempoTotalProcessamento += processo.getTempoUCP();
            tempoDeEspera += processo.getTempoUCP();
            index++;
        }

        double mediaTempoDeEspera = Arrays.stream(temposDeEsperas).sum() / (double) processos.length;
        double mediaTempoProcessamento = Arrays.stream(processos)
                .mapToInt(Processo::getTempoProcessamento).average().orElse(0);

        System.out.println("\nResumo:");
        for (Processo p : processos) {
            System.out.println(p.getNome() +
                    " | UCP: " + p.getTempoUCP() +
                    " | Tempo Total: " + p.getTempoProcessamento());
        }

        System.out.println("Tempo total: " + tempoTotalProcessamento);
        System.out.printf("Média espera: %.2f\n", mediaTempoDeEspera);
        System.out.printf("Média processamento: %.2f\n", mediaTempoProcessamento);
    }
}
