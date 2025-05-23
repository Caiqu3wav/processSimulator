package com.main.Escalonadores;

import com.main.Model.Processo;

import java.util.*;

public class RoundRobin {
    public static void roundrobin(Processo[] processos, int quantum) {
        System.out.println("\n[ESCALONAMENTO: Round Robin | Quantum = " + quantum + "]\n");
        Queue<Processo> fila = new LinkedList<>();
        Map<String, Integer> temposRestantes = new HashMap<>();
        Map<String, Integer> temposDeEspera = new HashMap<>();
        Map<String, Integer> temposDeFinalizacao = new HashMap<>();

        int tempoAtual = 0;

        for (Processo p : processos) {
            fila.add(p);
            temposRestantes.put(p.getNome(), p.getTempoUCP());
            temposDeEspera.put(p.getNome(), 0);
        }

        List<String> ordemExecucao = new ArrayList<>();

        while (!fila.isEmpty()) {
            Processo processo = fila.poll();
            String nome = processo.getNome();
            int tempoRestante = temposRestantes.get(nome);

            ordemExecucao.add(nome);
            System.out.println(nome + ": ");
            int tempoExecutado = Math.min(quantum, tempoRestante);
            for (int i = 0; i < tempoExecutado; i++) {
                System.out.print("⬜");
            }

            tempoAtual += tempoExecutado;
            tempoRestante -= tempoExecutado;
            System.out.println(" (" + tempoExecutado + " UCP) | Tempo atual: " + tempoAtual);

            if (tempoRestante > 0) {
                fila.add(processo);
                temposRestantes.put(nome, tempoRestante);
            } else {
                temposDeFinalizacao.put(nome, tempoAtual);
            }

            // Atualiza tempos de espera dos outros processos na fila
            for (Processo p : fila) {
                if (!p.getNome().equals(nome)) {
                    temposDeEspera.put(p.getNome(), temposDeEspera.get(p.getNome()) + tempoExecutado);
                }
            }
        }

        // Resumo final
        System.out.println("\nResumo:");
        int somaEspera = 0;
        int somaProcessamento = 0;

        for (Processo p : processos) {
            int espera = temposDeEspera.get(p.getNome());
            int fim = temposDeFinalizacao.get(p.getNome());
            p.setTempoProcessamento(fim);
            somaEspera += espera;
            somaProcessamento += fim;

            System.out.println(p.getNome() +
                    " | UCP: " + p.getTempoUCP() +
                    " | Espera: " + espera +
                    " | Finalização: " + fim);
        }

        double mediaEspera = (double) somaEspera / processos.length;
        double mediaFinal = (double) somaProcessamento / processos.length;

        System.out.println("Tempo total: " + tempoAtual);
        System.out.printf("Média de espera: %.2f\n", mediaEspera);
        System.out.printf("Média de finalização: %.2f\n", mediaFinal);
    }
}
