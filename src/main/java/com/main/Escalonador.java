package com.main;
import com.main.Escalonadores.*;
import com.main.Model.Processo;

public class Escalonador {
    public static void prioridades(Processo[] processos) {
        Prioridades.prioridades(processos);
    }

    public static void fifo(Processo[] processos) {
        FIFO.fifo(processos);
    }

    public static void sjf(Processo[] processos) {
        SJF.sjf(processos);
    }

    public static void roundrobin(Processo[] processos, int quantum) {
        RoundRobin.roundrobin(processos, quantum);
    }
}
