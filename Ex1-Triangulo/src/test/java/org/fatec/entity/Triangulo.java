package org.fatec.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.fatec.entity.Triangulo.TipoTriangulo.*;
import static org.fatec.entity.Triangulo.classificar;

@DisplayName("Classificação de Triângulos")
class TrianguloTest {

    // 1- triangulo escaleno valido
    @Nested
    @DisplayName("1 - Triângulo Escaleno Válido")
    class Triteste1 {
        @Test
        @DisplayName("Lados (3, 4, 5) formam um triângulo escaleno")
        void escalenoValido() {
            assertEquals(ESCALENO, classificar(3, 4, 5));
        }
    }

    // 2 triângulo isósceles valido
    @Nested
    @DisplayName("2 - Triângulo Isósceles Válido")
    class Triteste2 {
        @Test
        @DisplayName("Lados (5, 5, 3) formam um triângulo isósceles")
        void isoscelesValido() {
            assertEquals(ISOSCELES, classificar(5, 5, 3));
        }
    }

    // 3 - triângulo equilátero valido
    @Nested
    @DisplayName("3 - Triângulo Equilátero Válido")
    class Triteste3 {
        @Test
        @DisplayName("Lados (6, 6, 6) formam um triângulo equilátero")
        void equilateroValido() {
            assertEquals(EQUILATERO, classificar(6, 6, 6));
        }
    }

    // 4 - Isósceles — permutações dos mesmos valores
    @Nested
    @DisplayName("4 - Isósceles — permutações dos mesmos valores")
    class Triteste4 {
        @Test
        @DisplayName("CT-04a: Lados (5, 5, 3) — par igual no início")
        void permutacaoA() {
            assertEquals(ISOSCELES, classificar(5, 5, 3));
        }

        @Test
        @DisplayName("CT-04b: Lados (5, 3, 5) — par igual intercalado")
        void permutacaoB() {
            assertEquals(ISOSCELES, classificar(5, 3, 5));
        }

        @Test
        @DisplayName("CT-04c: Lados (3, 5, 5) — par igual no final")
        void permutacaoC() {
            assertEquals(ISOSCELES, classificar(3, 5, 5));
        }
    }

    // 5 - Um valor zero -> invalido
    @Nested
    @DisplayName("5 - Um valor zero")
    class Triteste5 {
        @Test
        @DisplayName("Lados (0, 4, 5) → inválido (lado zero)")
        void ladoZero() {
            assertEquals(INVALIDO, classificar(0, 4, 5));
        }
    }

    // 6 - Um valor negativo -> invalido
    @Nested
    @DisplayName("6 - Um valor negativo")
    class Triteste6 {
        @Test
        @DisplayName("Lados (-1, 4, 5) → inválido (lado negativo)")
        void ladoNegativo() {
            assertEquals(INVALIDO, classificar(-1, 4, 5));
        }
    }

    // 7 - Soma de 2 lados igual ao terceiro -> invalido
    @Nested
    @DisplayName("7 - Soma de 2 lados igual ao terceiro lado")
    class Triteste7 {
        @Test
        @DisplayName("Lados (1, 2, 3) → inválido (a + b = c)")
        void somaDoisIgualTerceiro() {
            assertEquals(INVALIDO, classificar(1, 2, 3));
        }
    }


    @Nested
    @DisplayName("8 - Permutações: soma de 2 lados igual ao terceiro")
    class Triteste8 {
        @Test
        @DisplayName("CT-08a: Lados (1, 2, 3) — a + b = c")
        void permutacaoA() {
            assertEquals(INVALIDO, classificar(1, 2, 3));
        }

        @Test
        @DisplayName("CT-08b: Lados (3, 1, 2) — b + c = a")
        void permutacaoB() {
            assertEquals(INVALIDO, classificar(3, 1, 2));
        }

        @Test
        @DisplayName("CT-08c: Lados (2, 3, 1) — a + c = b")
        void permutacaoC() {
            assertEquals(INVALIDO, classificar(2, 3, 1));
        }
    }

    // 9 - Soma de 2 lados menor que o terceiro -> invalido
    @Nested
    @DisplayName("9 - Soma de 2 lados menor que o terceiro lado")
    class Triteste9 {
        @Test
        @DisplayName("Lados (1, 2, 10) → inválido (a + b < c)")
        void somaDoisMenorTerceiro() {
            assertEquals(INVALIDO, classificar(1, 2, 10));
        }
    }


    @Nested
    @DisplayName("10 - Permutações: soma de 2 lados menor que o terceiro")
    class Triteste10 {
        @Test
        @DisplayName("10a: Lados (1, 2, 10) — a + b < c")
        void permutacaoA() {
            assertEquals(INVALIDO, classificar(1, 2, 10));
        }

        @Test
        @DisplayName("10b: Lados (10, 1, 2) — b + c < a")
        void permutacaoB() {
            assertEquals(INVALIDO, classificar(10, 1, 2));
        }

        @Test
        @DisplayName("10c: Lados (2, 10, 1) — a + c < b")
        void permutacaoC() {
            assertEquals(INVALIDO, classificar(2, 10, 1));
        }
    }

    // 11 - Três valores iguais a zero -> invalido
    @Nested
    @DisplayName("11 - Três valores iguais a zero")
    class Triteste11 {
        @Test
        @DisplayName("Lados (0, 0, 0) → inválido (todos zeros)")
        void tresZeros() {
            assertEquals(INVALIDO, classificar(0, 0, 0));
        }
    }
}