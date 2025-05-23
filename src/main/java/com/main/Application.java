package com.main;
import com.main.Model.Processo;
import com.main.Escalonador;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Application {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        Scanner scannerNumbers = new Scanner(System.in);

        System.out.println("Bem vindo simulador de processos!");
        System.out.println("Escolha o algoritmo de escalonamento:");
        System.out.println("1 - FIFO (First In First Out)");
        System.out.println("2 - SJF (Shortest Job First)");
        System.out.println("3 - Prioridades (Não-Preemptivo)");
        System.out.println("4 - Round-Robin");

        int escolhaAlgoritmo = scannerNumbers.nextInt();

        System.out.print("Quantos processos você deseja adicionar? ");
        int numProcessos = scannerNumbers.nextInt();
        scannerNumbers.nextLine();

        Processo[] processos = new Processo[numProcessos];

        for (int i = 0; i < numProcessos; i++) {
            System.out.println("Adicionando processo "+ (i + 1) + ":");
            processos[i] = new Processo();

            System.out.print("Nome: ");
            String nome = scanner.next();
            processos[i].setNome(nome);

            System.out.print("Prioridade (1-15): ");
            processos[i].setPrioridade(scannerNumbers.nextInt());

            System.out.print("Frames Limit (1-5): ");
            processos[i].setFramesLimit(scanner.nextInt());

            System.out.print("Tempo de UCP: ");
            processos[i].setTempoUCP(scannerNumbers.nextInt());

            System.out.print("Tipo (0 = CPU Bound, 1 = IO Bound): ");
            int tipoNum = scannerNumbers.nextInt();
            processos[i].setTipoProcesso(
                    tipoNum == 0 ? Processo.TipoProcesso.CpuBound : Processo.TipoProcesso.IoBound
            );
        }

        switch (escolhaAlgoritmo) {
            case 1:
                Escalonador.fifo(processos);
                break;
            case 2:
                Escalonador.sjf(processos);
                break;
            case 3:
                Escalonador.prioridades(processos);
                break;
            case 4:
                System.out.print("Digite o quantum (fatia de tempo): ");
                int quantum = scannerNumbers.nextInt();
                Escalonador.roundrobin(processos, quantum);
                break;
            default:
                System.out.println("Opção inválida.");
        }


        scanner.close();
    }
}
