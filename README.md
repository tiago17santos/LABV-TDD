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

---
---
---

# Exercício TDD — PersonDAO.isValidToInclude()

Implementação em **Java 21 + JUnit 5** do Exercício 2 de TDD.  
O método `isValidToInclude(Person p)` retorna uma `List<String>` de erros com base nas regras de negócio abaixo.

---

## Build & Testes

```bash
# Clone o repositório
git clone <URL_DO_REPOSITORIO>
cd Ex2-Classes

# Compilar, rodar testes e gerar relatório JaCoCo
mvn test
```

---

## Evidências de Cobertura (JaCoCo)

```
-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running dao.PersonDAOTest

PersonDAO — isValidToInclude()
  0 - Person completamente valida
    ✓ Nenhum erro para uma Person valida

  Regra 1 | Validacao do Nome
    ✓ 1a- nome com 2 partes validas nao gera erro
    ✓ 1b- nome com 3 partes validas nao gera erro
    ✓ 1c- nome com apenas 1 parte gera erro
    ✓ 1d- nome vazio gera erro
    ✓ 1e- nome nulo gera erro
    ✓ 1f- nome com numero gera erro
    ✓ 1g- nome com simbolo gera erro
    ✓ 1h- nome so com espacos gera erro

  Regra 2 | Validacao da Idade [1, 200]
    ✓ 2a- idade 1 (limite inferior) nao gera erro
    ✓ 2b- idade 200 (limite superior) nao gera erro
    ✓ 2c- idade 100 (meio do intervalo) nao gera erro
    ✓ 2d- idade 0 (abaixo do limite) gera erro
    ✓ 2e- idade -1 (negativa) gera erro
    ✓ 2f- idade 201 (acima do limite) gera erro

  Regra 3 | Person deve ter ao menos 1 Email
    ✓ 3a- lista de emails vazia gera erro
    ✓ 3b- lista de emails nula gera erro
    ✓ 3c- person com 2 emails validos nao gera erro

  Regra 4 | Formato do Email: local@dominio.tld
    ✓ 4a- formato valido simples nao gera erro
    ✓ 4b- formato valido com 1 char em cada parte nao gera erro
    ✓ 4c- sem @ gera erro
    ✓ 4d- sem ponto gera erro
    ✓ 4e- parte local vazia gera erro
    ✓ 4f- dominio vazio gera erro
    ✓ 4g- TLD vazio gera erro
    ✓ 4h- nome de email vazio gera erro
    ✓ 4i- email nulo gera erro

  5 | Multiplos erros simultaneos
    ✓ 5a- nome invalido + idade invalida retornam 2 erros
    ✓ 5b- person sem nenhuma informacao valida retorna multiplos erros

Tests run: 29, Failures: 0, Errors: 0, Skipped- 0

[INFO] BUILD SUCCESS
```

### Cobertura JaCoCo — `PersonDAO.java`

| Elemento              | Instruções | Branches | Linhas |
|-----------------------|-----------|----------|--------|
| `isValidToInclude()`  | 100%      | 100%     | 100%   |
| `validateName()`      | 100%      | 100%     | 100%   |
| `validateAge()`       | 100%      | 100%     | 100%   |
| `validateEmail()`     | 100%      | 100%     | 100%   |

---

## Regras de Validação

| Regra | Campo  | Condição                                                                 | Erro gerado quando...                          |
|-------|--------|--------------------------------------------------------------------------|------------------------------------------------|
| 1     | nome   | ao menos 2 partes separadas por espaço, cada parte só com letras         | 1 parte, vazio, nulo ou contém dígito/símbolo  |
| 2     | age    | inteiro no intervalo fechado [1, 200]                                    | age ≤ 0 ou age ≥ 201                           |
| 3     | emails | lista não nula e com ao menos 1 item                                     | lista vazia ou nula                            |
| 4     | email  | formato `local@dominio.tld` com ≥ 1 caractere em cada parte             | qualquer parte ausente ou string vazia/nula    |

### Regex utilizada para e-mail

```java
email.matches("[^@.]+@[^@.]+\\.[^@.]+")
```

Garante que:
- Parte local (`[^@.]+`): ao menos 1 caractere, sem `@` nem `.`
- `@` literal
- Domínio (`[^@.]+`): ao menos 1 caractere
- `.` literal
- TLD (`[^@.]+`): ao menos 1 caractere
