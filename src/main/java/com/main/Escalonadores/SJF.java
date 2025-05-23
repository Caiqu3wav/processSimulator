package com.main.Escalonadores;

import com.main.Model.Processo;

import java.util.Arrays;
import java.util.Comparator;

public class SJF {
    public static void sjf(Processo[] processos) {
        System.out.println("\n[ESCALONAMENTO: SJF]\n");

        Arrays.sort(processos, Comparator.comparingInt(Processo::getPrioridade));

        System.out.println("\nGerenciamento:");
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

            processos[index].setTempoProcessamento(tempoDeProcessamento);

            System.out.println(" (" + processo.getTempoUCP() + " de UCP)" + " (" + tempoDeProcessamento + " de tempo de processamento)");

            tempoTotalProcessamento += processo.getTempoUCP();
            tempoDeEspera += processo.getTempoUCP();
            index++;
        }

        double mediaTempoDeEspera = (double)
                Arrays.stream(temposDeEsperas).sum() / (double)processos.length;

        double mediaTempoProcessamento = Arrays.stream(processos)
                        .mapToInt(Processo::getTempoProcessamento).average().orElse(0);


        System.out.println("\nResumo dos Processos:");
        for (Processo p : processos) {
            System.out.println(p.getNome() +
                    " | UCP: " + p.getTempoUCP() +
                    " | Tempo total: " + p.getTempoProcessamento());
        }

        System.out.println("Tempo total de UCP: " + tempoTotalProcessamento);
        System.out.printf("Média do tempo de espera: %.2f\n", mediaTempoDeEspera);
        System.out.printf("Média do tempo de processamento: %.2f\n", mediaTempoProcessamento);
    }
}
