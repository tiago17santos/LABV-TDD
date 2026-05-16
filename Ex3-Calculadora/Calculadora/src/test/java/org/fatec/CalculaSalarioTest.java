package org.fatec;


import org.fatec.entity.Cargo;
import org.fatec.entity.Funcionario;
import org.fatec.service.CalculaSalario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("SalarioCalculator — calcularSalarioLiquido()")
public class CalculaSalarioTest {

    private static final double DELTA = 0.001;   // tolerância para double

    private CalculaSalario calculador;

    @BeforeEach
    void setUp() {
        calculador = new CalculaSalario();
    }

    @Nested
    @DisplayName("DESENVOLVEDOR | limite R$ 3.000,00 | abaixo=10% / acima=20%")
    class DesenvolvedorTests {

        @Test
        @DisplayName("1- salario 2.999,99 (abaixo do limite) → desconto 10%")
        void desenvolvedor_abaixoLimite_desconto10() {
            Funcionario f = new Funcionario("Ana Dev", "ana@dev.com", 2_999.99, Cargo.DESENVOLVEDOR);
            double esperado = 2_999.99 * 0.90;
            assertEquals(esperado, calculador.calcularSalarioLiquido(f), DELTA);
        }

        @Test
        @DisplayName("2- salario 3.000,00 (exato no limite) → desconto 20%")
        void desenvolvedor_noLimite_desconto20() {
            Funcionario f = new Funcionario("Bruno Dev", "bruno@dev.com", 3_000.00, Cargo.DESENVOLVEDOR);
            double esperado = 3_000.00 * 0.80;
            assertEquals(esperado, calculador.calcularSalarioLiquido(f), DELTA);
        }

        @Test
        @DisplayName("3- salario 5.000,00 (acima do limite) → desconto 20%")
        void desenvolvedor_acimaLimite_desconto20() {
            Funcionario f = new Funcionario("Carlos Dev", "carlos@dev.com", 5_000.00, Cargo.DESENVOLVEDOR);
            double esperado = 5_000.00 * 0.80;
            assertEquals(esperado, calculador.calcularSalarioLiquido(f), DELTA);
        }

        @Test
        @DisplayName("4- salario 1.500,00 (bem abaixo) → desconto 10%")
        void desenvolvedor_bemAbaixoLimite_desconto10() {
            Funcionario f = new Funcionario("Diana Dev", "diana@dev.com", 1_500.00, Cargo.DESENVOLVEDOR);
            double esperado = 1_500.00 * 0.90;
            assertEquals(esperado, calculador.calcularSalarioLiquido(f), DELTA);
        }
    }

    @Nested
    @DisplayName("DBA | limite R$ 2.000,00 | abaixo=15% / acima=25%")
    class DbaTests {

        @Test
        @DisplayName("5- salario 1.999,99 (abaixo do limite) → desconto 15%")
        void dba_abaixoLimite_desconto15() {
            Funcionario f = new Funcionario("Eduardo DBA", "edu@db.com", 1_999.99, Cargo.DBA);
            double esperado = 1_999.99 * 0.85;
            assertEquals(esperado, calculador.calcularSalarioLiquido(f), DELTA);
        }

        @Test
        @DisplayName("6- salario 2.000,00 (exato no limite) → desconto 25%")
        void dba_noLimite_desconto25() {
            Funcionario f = new Funcionario("Fernanda DBA", "fer@db.com", 2_000.00, Cargo.DBA);
            double esperado = 2_000.00 * 0.75;
            assertEquals(esperado, calculador.calcularSalarioLiquido(f), DELTA);
        }

        @Test
        @DisplayName("7- salario 4.000,00 (acima do limite) → desconto 25%")
        void dba_acimaLimite_desconto25() {
            Funcionario f = new Funcionario("Gabriel DBA", "gab@db.com", 4_000.00, Cargo.DBA);
            double esperado = 4_000.00 * 0.75;
            assertEquals(esperado, calculador.calcularSalarioLiquido(f), DELTA);
        }

        @Test
        @DisplayName("8- salario 800,00 (bem abaixo) → desconto 15%")
        void dba_bemAbaixoLimite_desconto15() {
            Funcionario f = new Funcionario("Helena DBA", "hel@db.com", 800.00, Cargo.DBA);
            double esperado = 800.00 * 0.85;
            assertEquals(esperado, calculador.calcularSalarioLiquido(f), DELTA);
        }
    }

    @Nested
    @DisplayName("TESTADOR | limite R$ 2.000,00 | abaixo=15% / acima=25%")
    class TestadorTests {

        @Test
        @DisplayName("9- salario 1.999,99 (abaixo do limite) → desconto 15%")
        void testador_abaixoLimite_desconto15() {
            Funcionario f = new Funcionario("Igor QA", "igor@qa.com", 1_999.99, Cargo.TESTADOR);
            double esperado = 1_999.99 * 0.85;
            assertEquals(esperado, calculador.calcularSalarioLiquido(f), DELTA);
        }

        @Test
        @DisplayName("10- salario 2.000,00 (exato no limite) → desconto 25%")
        void testador_noLimite_desconto25() {
            Funcionario f = new Funcionario("Julia QA", "julia@qa.com", 2_000.00, Cargo.TESTADOR);
            double esperado = 2_000.00 * 0.75;
            assertEquals(esperado, calculador.calcularSalarioLiquido(f), DELTA);
        }

        @Test
        @DisplayName("11- salario 3.500,00 (acima do limite) → desconto 25%")
        void testador_acimaLimite_desconto25() {
            Funcionario f = new Funcionario("Klaus QA", "klaus@qa.com", 3_500.00, Cargo.TESTADOR);
            double esperado = 3_500.00 * 0.75;
            assertEquals(esperado, calculador.calcularSalarioLiquido(f), DELTA);
        }

        @Test
        @DisplayName("12- salario 1.000,00 (bem abaixo) → desconto 15%")
        void testador_bemAbaixoLimite_desconto15() {
            Funcionario f = new Funcionario("Laura QA", "laura@qa.com", 1_000.00, Cargo.TESTADOR);
            double esperado = 1_000.00 * 0.85;
            assertEquals(esperado, calculador.calcularSalarioLiquido(f), DELTA);
        }
    }

    @Nested
    @DisplayName("GERENTE | limite R$ 5.000,00 | abaixo=20% / acima=30%")
    class GerenteTests {

        @Test
        @DisplayName("13- salario 4.999,99 (abaixo do limite) → desconto 20%")
        void gerente_abaixoLimite_desconto20() {
            Funcionario f = new Funcionario("Marcos Ger", "marcos@corp.com", 4_999.99, Cargo.GERENTE);
            double esperado = 4_999.99 * 0.80;
            assertEquals(esperado, calculador.calcularSalarioLiquido(f), DELTA);
        }

        @Test
        @DisplayName("14- salario 5.000,00 (exato no limite) → desconto 30%")
        void gerente_noLimite_desconto30() {
            Funcionario f = new Funcionario("Natalia Ger", "nat@corp.com", 5_000.00, Cargo.GERENTE);
            double esperado = 5_000.00 * 0.70;
            assertEquals(esperado, calculador.calcularSalarioLiquido(f), DELTA);
        }

        @Test
        @DisplayName("15- salario 8.000,00 (acima do limite) → desconto 30%")
        void gerente_acimaLimite_desconto30() {
            Funcionario f = new Funcionario("Otavio Ger", "ota@corp.com", 8_000.00, Cargo.GERENTE);
            double esperado = 8_000.00 * 0.70;
            assertEquals(esperado, calculador.calcularSalarioLiquido(f), DELTA);
        }

        @Test
        @DisplayName("16- salario 2.000,00 (bem abaixo) → desconto 20%")
        void gerente_bemAbaixoLimite_desconto20() {
            Funcionario f = new Funcionario("Paula Ger", "paula@corp.com", 2_000.00, Cargo.GERENTE);
            double esperado = 2_000.00 * 0.80;
            assertEquals(esperado, calculador.calcularSalarioLiquido(f), DELTA);
        }
    }


    @Nested
    @DisplayName("17- Verificacao dos valores absolutos")
    class ValoresAbsolutos {

        @Test
        @DisplayName("17a- DESENVOLVEDOR 3.000 → liquido R$ 2.400,00")
        void desenvolvedor3000_liquido2400() {
            Funcionario f = new Funcionario("Dev", "dev@x.com", 3_000.00, Cargo.DESENVOLVEDOR);
            assertEquals(2_400.00, calculador.calcularSalarioLiquido(f), DELTA);
        }

        @Test
        @DisplayName("17b- DBA 2.000 → liquido R$ 1.500,00")
        void dba2000_liquido1500() {
            Funcionario f = new Funcionario("DBA", "dba@x.com", 2_000.00, Cargo.DBA);
            assertEquals(1_500.00, calculador.calcularSalarioLiquido(f), DELTA);
        }

        @Test
        @DisplayName("17c- TESTADOR 2.000 → liquido R$ 1.500,00")
        void testador2000_liquido1500() {
            Funcionario f = new Funcionario("QA", "qa@x.com", 2_000.00, Cargo.TESTADOR);
            assertEquals(1_500.00, calculador.calcularSalarioLiquido(f), DELTA);
        }

        @Test
        @DisplayName("17d- GERENTE 5.000 → liquido R$ 3.500,00")
        void gerente5000_liquido3500() {
            Funcionario f = new Funcionario("Ger", "ger@x.com", 5_000.00, Cargo.GERENTE);
            assertEquals(3_500.00, calculador.calcularSalarioLiquido(f), DELTA);
        }

        @Test
        @DisplayName("17e- DESENVOLVEDOR 1.000 → liquido R$ 900,00")
        void desenvolvedor1000_liquido900() {
            Funcionario f = new Funcionario("Dev Jr", "jr@x.com", 1_000.00, Cargo.DESENVOLVEDOR);
            assertEquals(900.00, calculador.calcularSalarioLiquido(f), DELTA);
        }

        @Test
        @DisplayName("17f- GERENTE 10.000 → liquido R$ 7.000,00")
        void gerente10000_liquido7000() {
            Funcionario f = new Funcionario("CEO", "ceo@x.com", 10_000.00, Cargo.GERENTE);
            assertEquals(7_000.00, calculador.calcularSalarioLiquido(f), DELTA);
        }
    }
}
