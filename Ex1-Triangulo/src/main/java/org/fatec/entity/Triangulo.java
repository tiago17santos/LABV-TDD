package org.fatec.entity;

public class Triangulo {

    public enum TipoTriangulo {
        EQUILATERO,
        ISOSCELES,
        ESCALENO,
        INVALIDO
    }


    public static TipoTriangulo classificar(int a, int b, int c) {
        // Todos os lados devem ser positivos
        if (a <= 0 || b <= 0 || c <= 0) {
            return TipoTriangulo.INVALIDO;
        }

        // Condição de existência: soma de dois lados deve ser ESTRITAMENTE maior que o terceiro
        if (a + b <= c || a + c <= b || b + c <= a) {
            return TipoTriangulo.INVALIDO;
        }

        // Classificação
        if (a == b && b == c) {
            return TipoTriangulo.EQUILATERO;
        }

        if (a == b || b == c || a == c) {
            return TipoTriangulo.ISOSCELES;
        }

        return TipoTriangulo.ESCALENO;
    }
}