package com.main.Model;

import java.util.Random;

public class Processo {
    private int id;
    private int prioridade;
    private int framesLimit;
    private TempoUCP tempoUCP;
    private TipoProcesso tipoProcesso;
    
    private static final Random random = new Random();

    public Processo() {
        this.id = generateRandomId();
    }

    private int generateRandomId() {
        // Gera um ID aleatório no intervalo de 1000000 a 9999999
        return random.nextInt(9000000) + 1000000; // 9000000 + 1000000 -> 1000000 até 9999999
    }

    public int getId() {
        return id;
    }

    public void setPrioridade(int prioridade){
        if (prioridade < 1 || prioridade > 15) {
            throw new IllegalArgumentException();
        }
        this.prioridade = prioridade;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public int getFramesLimit(){
        return framesLimit;
    }

    public void setFramesLimit(int framesLimit) {
        if (framesLimit < 1 || framesLimit > 5){
            throw new IllegalArgumentException("Precisa ser de 1 a 5");
        }
        this.framesLimit = framesLimit;
    }

    public TipoProcesso getTipoProcesso() {
        return tipoProcesso;
    }

    public void setTipoProcesso(TipoProcesso tipoProcesso) {
        this.tipoProcesso = tipoProcesso;
    }

    public enum TipoProcesso{
        CpuBound,
        IoBound
    }

    public enum TempoUCP {
        Pronto,
        Execução
    }
}
