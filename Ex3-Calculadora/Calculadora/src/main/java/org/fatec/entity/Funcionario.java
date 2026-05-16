package org.fatec.entity;


public class Funcionario {

    private String nome;
    private String email;
    private double salarioBase;
    private Cargo cargo;

    public Funcionario(String nome, String email, double salarioBase, Cargo cargo) {
        this.nome        = nome;
        this.email       = email;
        this.salarioBase = salarioBase;
        this.cargo       = cargo;
    }

    public String getNome()         { return nome; }
    public String getEmail()        { return email; }
    public double getSalarioBase()  { return salarioBase; }
    public Cargo  getCargo()        { return cargo; }

    public void setNome(String nome)               { this.nome = nome; }
    public void setEmail(String email)             { this.email = email; }
    public void setSalarioBase(double salarioBase) { this.salarioBase = salarioBase; }
    public void setCargo(Cargo cargo)              { this.cargo = cargo; }
}