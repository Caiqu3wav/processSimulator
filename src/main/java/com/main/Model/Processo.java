package com.main.Model;


public class Processo {
    int prioridade;
    TipoProcesso tipoProcesso;

    public enum TipoProcesso{
        CpuBound,
        IoBound
    }



}
