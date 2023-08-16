package br.com.prova.model;

public class Funcionario {
    
    private int idFuncionario;
    private String nomeFuncionario;

    public Funcionario() {
        this.idFuncionario = 0;
        this.nomeFuncionario = "";
    }

    public Funcionario(int idFuncionario, String nomeFuncionario) {
        this.idFuncionario = idFuncionario;
        this.nomeFuncionario = nomeFuncionario;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }
    
    
}
