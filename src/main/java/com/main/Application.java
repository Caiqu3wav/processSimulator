package com.main;
import com.main.Model.Processo;
import java.util.Scanner;

public class Application {
    public static void main(String[] args){
        System.out.println("Bem vindo simulador de processos!");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Quantos processos você deseja adicionar? ");
        int numProcessos = scanner.nextInt();
        scanner.nextLine();

        Processo[] processos = new Processo[numProcessos];

        for (int i = 0; i < numProcessos; i++) {
            System.out.println("Adicionando processo "+ (i + 1) + ":");
            processos[i] = new Processo();

            System.out.println("Escolha o nome do processo: ");
            String nome = scanner.nextLine();
            processos[i].setNome(nome);
            scanner.nextLine();

            System.out.println("Digite a prioridade do processo (de 1 a 15) ");
            int prioridade = scanner.nextInt();
            processos[i].setPrioridade(prioridade);
            scanner.nextLine();

            System.out.println("Digite limite de frames do processo (de 1 a 5)");
            int framesLimit = scanner.nextInt();
            processos[i].setFramesLimit(framesLimit);
            scanner.nextLine();

            System.out.println("Digite o tempo de ucp do processo");
            int tempoUcp = scanner.nextInt();
            processos[i].setTempoUCP(tempoUcp);
            scanner.nextLine();

            System.out.println("Escolha o tipo do processo: 0 pra CPU Bound e 1 pra IO Bound");
            int tipoNum = scanner.nextInt();
            if (tipoNum == 0) {
                processos[i].setTipoProcesso(Processo.TipoProcesso.CpuBound);
            } else if (tipoNum == 1) {
                processos[i].setTipoProcesso(Processo.TipoProcesso.IoBound);
            } else{
                throw new IllegalArgumentException("Tipo de processo inválido. Escolha 0 ou 1.");
            }
        }

        System.out.println("\nGerenciamento:");
        int tempoTotalProcessamento = 0;

        for (Processo processo : processos) {
            System.out.print(processo.getNome() + ": ");
            for (int j = 0; j < processo.getTempoUCP(); j++) {
                System.out.print("⬜");
            }
            System.out.println(" (" + processo.getTempoUCP() + " unidades de tempo)");

            tempoTotalProcessamento += processo.getTempoUCP();
        }

        System.out.println("Tempo total de processamento: " + tempoTotalProcessamento + " unidades de tempo");
        scanner.close();
    }
}
