package com.portfolio;

public class Tarefa {
    private  String descricao;
    private boolean concluida;

    public Tarefa(String descricao){
        this.descricao  =  descricao;
        this.concluida  =  false;
    }

    public String getDescrica() { return descricao; }
    public boolean isConcluida() { return concluida; }
    public void setConcluida(boolean concluida){ this.concluida= concluida;}

    @Override
    public String toString() {
        return (concluida ? "[X]" : "[ ]" )+ descricao;
    }

    

    
}

