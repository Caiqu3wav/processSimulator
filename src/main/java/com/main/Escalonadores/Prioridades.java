package com.main.Escalonadores;

import com.main.Model.Processo;

import java.util.Arrays;
import java.util.Comparator;

public class Prioridades {
    public static void prioridades(Processo[] processos) {
        System.out.println("\n[ESCALONAMENTO: PRIORIDADES]\n");
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

            int tempoDeProcessamento = index > 0 ? processo.getTempoUCP() + processos[index - 1].getTempoProcessamento()
                    : processo.getTempoUCP();
            processos[index].setTempoProcessamento(tempoDeProcessamento);

            System.out.println(" (" + processo.getTempoUCP() + " de UCP)" + " (" + tempoDeProcessamento + " de tempo de processamento)");

            tempoTotalProcessamento += processo.getTempoUCP();
            tempoDeEspera += processo.getTempoUCP();

            index++;
        }

        double mediaTempoDeEspera = (double)
                Arrays.stream(temposDeEsperas).sum() / processos.length;

        int somaTempoProcessamento = 0;
        for (Processo p : processos) {
            somaTempoProcessamento += p.getTempoProcessamento();
        }

        double mediaTempoProcessamento = (double)
                somaTempoProcessamento / processos.length;

        System.out.println("\nResumo dos Processos:");
        for (Processo p : processos) {
            System.out.println(p.getNome() +
                    " | Prioridade: " + p.getPrioridade() +
                    " | UCP: " + p.getTempoUCP() +
                    " | Tempo de Processamento: " + p.getTempoProcessamento());
        }

        System.out.println("Tempo total de UCP: " + tempoTotalProcessamento + " unidades de tempo");
        System.out.printf("Média do tempo de espera: %.2f unidades de tempo%n", mediaTempoDeEspera);
        System.out.printf("Média do tempo de processamento: %.2f unidades de tempo%n", mediaTempoProcessamento);
    }
}
