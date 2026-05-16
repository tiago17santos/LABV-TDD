package org.fatec.service;

import org.fatec.entity.Funcionario;

public class CalculaSalario {
    public double calcularSalarioLiquido(Funcionario funcionario) {
        double base     = funcionario.getSalarioBase();
        double desconto = resolverDesconto(funcionario);
        return base - (base * desconto);
    }


    private double resolverDesconto(Funcionario f) {
        return switch (f.getCargo()) {
            case DESENVOLVEDOR -> f.getSalarioBase() >= 3_000.00 ? 0.20 : 0.10;
            case DBA, TESTADOR -> f.getSalarioBase() >= 2_000.00 ? 0.25 : 0.15;
            case GERENTE       -> f.getSalarioBase() >= 5_000.00 ? 0.30 : 0.20;
        };
    }
}


