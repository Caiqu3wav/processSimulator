package com.main;
import com.main.Model.Processo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Application {
    public static void main(String[] args){
        System.out.println("Bem vindo simulador de processos!");
        Scanner scanner = new Scanner(System.in);

        Scanner scannerNumbers = new Scanner(System.in);
        System.out.print("Quantos processos você deseja adicionar? ");
        int numProcessos = scannerNumbers.nextInt();
        scannerNumbers.nextLine();

        Processo[] processos = new Processo[numProcessos];

        for (int i = 0; i < numProcessos; i++) {
            System.out.println("Adicionando processo "+ (i + 1) + ":");
            processos[i] = new Processo();

            System.out.println("Escolha o nome do processo: ");
            String nome = scanner.next();
            processos[i].setNome(nome);

            System.out.println("Digite a prioridade do processo (de 1 a 15) ");
            int prioridade = scannerNumbers.nextInt();
            processos[i].setPrioridade(prioridade);

            System.out.println("Digite limite de frames do processo (de 1 a 5)");
            int framesLimit = scanner.nextInt();
            processos[i].setFramesLimit(framesLimit);

            System.out.println("Digite o tempo de ucp do processo");
            int tempoUcp = scannerNumbers.nextInt();
            processos[i].setTempoUCP(tempoUcp);

            scanner.nextLine();

            System.out.println("Escolha o tipo do processo: 0 pra CPU Bound e 1 pra IO Bound");
            int tipoNum = scannerNumbers.nextInt();
            if (tipoNum == 0) {
                processos[i].setTipoProcesso(Processo.TipoProcesso.CpuBound);
            } else if (tipoNum == 1) {
                processos[i].setTipoProcesso(Processo.TipoProcesso.IoBound);
            } else{
                throw new IllegalArgumentException("Tipo de processo inválido. Escolha 0 ou 1.");
            }
        }

        Arrays.sort(processos, Comparator.comparingInt(Processo::getPrioridade));

        System.out.println("\nGerenciamento:");
        int tempoTotalProcessamento = 0;
        int tempoDeEspera = 0;
        int[] temposDeEsperas = new int[numProcessos];

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
        Arrays.stream(temposDeEsperas).sum() / numProcessos;

        int somaTempoProcessamento = 0;
        for (Processo p : processos) {
            somaTempoProcessamento += p.getTempoProcessamento();
        }

        double mediaTempoProcessamento = (double)
        somaTempoProcessamento / numProcessos;

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
        scanner.close();
    }
}
