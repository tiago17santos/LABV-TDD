# Exercício TDD — Classificação de Triângulos

Implementação em **Java 21 + JUnit 5** do Exercício 1 de TDD, com relatório de cobertura via **JaCoCo**.

---

## Build & Execução dos Testes

```bash
# Clone o repositório
git clone <URL_DO_REPOSITORIO>
cd Ex1-Triangulo

# Compilar, rodar testes e gerar relatório JaCoCo
mvn test
```

---

## Evidências de Cobertura (JaCoCo)

Saída do terminal após `mvn test`:

```
[INFO] --- jacoco-maven-plugin:0.8.11:report ---
[INFO] Loading execution data file target/jacoco.exec
[INFO] Analyzed bundle 'triangle-tdd' with 1 classes

-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running triangle.TriangleTest

Classificação de Triângulos
  1- Triângulo Escaleno Válido
    ✓ Lados (3, 4, 5) formam um triângulo escaleno
  2- Triângulo Isósceles Válido
    ✓ Lados (5, 5, 3) formam um triângulo isósceles
  3- Triângulo Equilátero Válido
    ✓ Lados (6, 6, 6) formam um triângulo equilátero
  4- Isósceles — permutações dos mesmos valores
    ✓ CT-04a: Lados (5, 5, 3) — par igual no início
    ✓ CT-04b: Lados (5, 3, 5) — par igual intercalado
    ✓ CT-04c: Lados (3, 5, 5) — par igual no final
  5- Um valor zero
    ✓ Lados (0, 4, 5) → inválido (lado zero)
  6- Um valor negativo
    ✓ Lados (-1, 4, 5) → inválido (lado negativo)
  7- Soma de 2 lados igual ao terceiro lado
    ✓ Lados (1, 2, 3) → inválido (a + b = c)
  8- Permutações: soma de 2 lados igual ao terceiro
    ✓ CT-08a: Lados (1, 2, 3) — a + b = c
    ✓ CT-08b: Lados (3, 1, 2) — b + c = a
    ✓ CT-08c: Lados (2, 3, 1) — a + c = b
  9- Soma de 2 lados menor que o terceiro lado
    ✓ Lados (1, 2, 10) → inválido (a + b < c)
  10- Permutações: soma de 2 lados menor que o terceiro
    ✓ 10a: Lados (1, 2, 10) — a + b < c
    ✓ 10b: Lados (10, 1, 2) — b + c < a
    ✓ 10c: Lados (2, 10, 1) — a + c < b
  11- Três valores iguais a zero
    ✓ Lados (0, 0, 0) → inválido (todos zeros)

Tests run: 17, Failures: 0, Errors: 0, Skipped: 0

Results:
Tests run: 17, Failures: 0, Errors: 0, Skipped: 0

[INFO] BUILD SUCCESS
```

### Cobertura JaCoCo — `Triangle.java`

| Elemento          | Instruções | Branches | Linhas |
|-------------------|-----------|----------|--------|
| `Triangle`        | 100%      | 100%     | 100%   |
| `classificar()`   | 100%      | 100%     | 100%   |


---

## Casos de Teste

| CT     | Situação                                              | Entrada       | Esperado    |
|--------|-------------------------------------------------------|---------------|-------------|
| CT-01  | Triângulo escaleno válido                             | (3, 4, 5)     | ESCALENO    |
| CT-02  | Triângulo isósceles válido                            | (5, 5, 3)     | ISOSCELES   |
| CT-03  | Triângulo equilátero válido                           | (6, 6, 6)     | EQUILATERO  |
| CT-04a | Isósceles — par igual no início                       | (5, 5, 3)     | ISOSCELES   |
| CT-04b | Isósceles — par igual intercalado                     | (5, 3, 5)     | ISOSCELES   |
| CT-04c | Isósceles — par igual no final                        | (3, 5, 5)     | ISOSCELES   |
| CT-05  | Um valor zero                                         | (0, 4, 5)     | INVALIDO    |
| CT-06  | Um valor negativo                                     | (-1, 4, 5)    | INVALIDO    |
| CT-07  | Soma de 2 lados igual ao terceiro                     | (1, 2, 3)     | INVALIDO    |
| CT-08a | Permutação: a + b = c                                 | (1, 2, 3)     | INVALIDO    |
| CT-08b | Permutação: b + c = a                                 | (3, 1, 2)     | INVALIDO    |
| CT-08c | Permutação: a + c = b                                 | (2, 3, 1)     | INVALIDO    |
| CT-09  | Soma de 2 lados menor que o terceiro                  | (1, 2, 10)    | INVALIDO    |
| CT-10a | Permutação: a + b < c                                 | (1, 2, 10)    | INVALIDO    |
| CT-10b | Permutação: b + c < a                                 | (10, 1, 2)    | INVALIDO    |
| CT-10c | Permutação: a + c < b                                 | (2, 10, 1)    | INVALIDO    |
| CT-11  | Três valores iguais a zero                            | (0, 0, 0)     | INVALIDO    |

---

## Lógica de Classificação

```
1. Se qualquer lado ≤ 0        →  INVALIDO
2. Se a+b ≤ c, a+c ≤ b ou
   b+c ≤ a                     →  INVALIDO
3. Se a = b = c                →  EQUILATERO
4. Se a=b ou b=c ou a=c        →  ISOSCELES
5. Caso contrário              →  ESCALENO
```
