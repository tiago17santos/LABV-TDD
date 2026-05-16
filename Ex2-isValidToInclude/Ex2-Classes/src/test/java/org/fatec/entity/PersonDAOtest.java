package org.fatec.entity;

import org.fatec.dao.PersonDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("PersonDAO — isValidToInclude()")
class PersonDAOTest {

    private PersonDAO dao;

    /** Cria uma pessoa valida em todos os aspectos para ser modificada por cada teste */
    private Person validPerson() {
        Person p = new Person(1, "Joao Silva", 30);
        p.addEmail(new Email(1, "joao@email.com"));
        return p;
    }

    @BeforeEach
    void setUp() {
        dao = new PersonDAO();
    }

    @Nested
    @DisplayName("Person completamente valida")
    class CT00 {
        @Test
        @DisplayName("Nenhum erro para uma Person valida")
        void personValidaRetornaListaVazia() {
            List<String> errors = dao.isValidToInclude(validPerson());
            assertTrue(errors.isEmpty(),
                    "Esperava lista vazia, mas obteve: " + errors);
        }
    }


    @Nested
    @DisplayName("Regra 1 | Validacao do Nome")
    class RegraName {

        @Test
        @DisplayName("1a- nome com 2 partes validas nao gera erro")
        void nomeDuasPartesValido() {
            Person p = validPerson();
            p.setName("Ana Lima");
            assertTrue(dao.isValidToInclude(p).isEmpty());
        }

        @Test
        @DisplayName("1b- nome com 3 partes validas nao gera erro")
        void nomeTresPartesValido() {
            Person p = validPerson();
            p.setName("Maria Das Gracas");
            assertTrue(dao.isValidToInclude(p).isEmpty());
        }

        @Test
        @DisplayName("1c- nome com apenas 1 parte gera erro")
        void nomeUmaParteInvalido() {
            Person p = validPerson();
            p.setName("Joao");
            List<String> errors = dao.isValidToInclude(p);
            assertFalse(errors.isEmpty(), "Esperava erro para nome com 1 parte.");
        }

        @Test
        @DisplayName("1d- nome vazio gera erro")
        void nomeVazioInvalido() {
            Person p = validPerson();
            p.setName("");
            List<String> errors = dao.isValidToInclude(p);
            assertFalse(errors.isEmpty(), "Esperava erro para nome vazio.");
        }

        @Test
        @DisplayName("1e- nome nulo gera erro")
        void nomeNuloInvalido() {
            Person p = validPerson();
            p.setName(null);
            List<String> errors = dao.isValidToInclude(p);
            assertFalse(errors.isEmpty(), "Esperava erro para nome nulo.");
        }

        @Test
        @DisplayName("1f- nome com numero gera erro")
        void nomeComNumeroInvalido() {
            Person p = validPerson();
            p.setName("Joao 2");
            List<String> errors = dao.isValidToInclude(p);
            assertFalse(errors.isEmpty(), "Esperava erro para nome com numero.");
        }

        @Test
        @DisplayName("1g- nome com simbolo gera erro")
        void nomeComSimboloInvalido() {
            Person p = validPerson();
            p.setName("Joao Silva!");
            List<String> errors = dao.isValidToInclude(p);
            assertFalse(errors.isEmpty(), "Esperava erro para nome com simbolo.");
        }

        @Test
        @DisplayName("1h- nome so com espacos gera erro")
        void nomeSoEspacosInvalido() {
            Person p = validPerson();
            p.setName("   ");
            List<String> errors = dao.isValidToInclude(p);
            assertFalse(errors.isEmpty(), "Esperava erro para nome so com espacos.");
        }
    }


    @Nested
    @DisplayName("Regra 2 | Validacao da Idade [1, 200]")
    class RegraIdade {

        @Test
        @DisplayName("2a- idade 1 (limite inferior) nao gera erro")
        void idadeLimiteInferiorValida() {
            Person p = validPerson();
            p.setAge(1);
            assertTrue(dao.isValidToInclude(p).isEmpty());
        }

        @Test
        @DisplayName("2b - idade 200 (limite superior) nao gera erro")
        void idadeLimiteSuperiorValida() {
            Person p = validPerson();
            p.setAge(200);
            assertTrue(dao.isValidToInclude(p).isEmpty());
        }

        @Test
        @DisplayName("2c- idade 100 (meio do intervalo) nao gera erro")
        void idadeIntervaloValida() {
            Person p = validPerson();
            p.setAge(100);
            assertTrue(dao.isValidToInclude(p).isEmpty());
        }

        @Test
        @DisplayName("2d- idade 0 (abaixo do limite) gera erro")
        void idadeZeroInvalida() {
            Person p = validPerson();
            p.setAge(0);
            List<String> errors = dao.isValidToInclude(p);
            assertFalse(errors.isEmpty(), "Esperava erro para idade 0.");
        }

        @Test
        @DisplayName("2e- idade -1 (negativa) gera erro")
        void idadeNegativaInvalida() {
            Person p = validPerson();
            p.setAge(-1);
            List<String> errors = dao.isValidToInclude(p);
            assertFalse(errors.isEmpty(), "Esperava erro para idade negativa.");
        }

        @Test
        @DisplayName("2f- idade 201 (acima do limite) gera erro")
        void idadeAcimaLimiteInvalida() {
            Person p = validPerson();
            p.setAge(201);
            List<String> errors = dao.isValidToInclude(p);
            assertFalse(errors.isEmpty(), "Esperava erro para idade 201.");
        }
    }

    @Nested
    @DisplayName("Regra 3 | Person deve ter ao menos 1 Email")
    class RegraEmails {

        @Test
        @DisplayName("3a- lista de emails vazia gera erro")
        void semEmailsInvalido() {
            Person p = new Person(1, "Joao Silva", 30);
            // nenhum email adicionado
            List<String> errors = dao.isValidToInclude(p);
            assertFalse(errors.isEmpty(), "Esperava erro para lista de emails vazia.");
        }

        @Test
        @DisplayName("3b- lista de emails nula gera erro")
        void emailsNulosInvalido() {
            Person p = validPerson();
            p.setEmails(null);
            List<String> errors = dao.isValidToInclude(p);
            assertFalse(errors.isEmpty(), "Esperava erro para emails nulos.");
        }

        @Test
        @DisplayName("3c- person com 2 emails validos nao gera erro")
        void doisEmailsValidos() {
            Person p = validPerson();
            p.addEmail(new Email(2, "silva@corp.br"));
            assertTrue(dao.isValidToInclude(p).isEmpty());
        }
    }

    @Nested
    @DisplayName("Regra 4 | Formato do Email: local@dominio.tld")
    class RegraFormatoEmail {

        @Test
        @DisplayName("4a- formato valido simples nao gera erro")
        void emailFormatoValido() {
            Person p = validPerson();
            p.getEmails().getFirst().setName("usuario@dominio.com");
            assertTrue(dao.isValidToInclude(p).isEmpty());
        }

        @Test
        @DisplayName("4b- formato valido com subdominio nao gera erro")
        void emailSubdominioValido() {
            Person p = validPerson();
            p.getEmails().getFirst().setName("x@y.z");   // 1 char em cada parte
            assertTrue(dao.isValidToInclude(p).isEmpty());
        }

        @Test
        @DisplayName("4c- sem @ gera erro")
        void emailSemArrobaInvalido() {
            Person p = validPerson();
            p.getEmails().getFirst().setName("usuariodominio.com");
            List<String> errors = dao.isValidToInclude(p);
            assertFalse(errors.isEmpty(), "Esperava erro para email sem @.");
        }

        @Test
        @DisplayName("4d-sem ponto gera erro")
        void emailSemPontoInvalido() {
            Person p = validPerson();
            p.getEmails().getFirst().setName("usuario@dominiocom");
            List<String> errors = dao.isValidToInclude(p);
            assertFalse(errors.isEmpty(), "Esperava erro para email sem ponto.");
        }

        @Test
        @DisplayName("4e- parte local vazia gera erro")
        void emailLocalVazioInvalido() {
            Person p = validPerson();
            p.getEmails().getFirst().setName("@dominio.com");
            List<String> errors = dao.isValidToInclude(p);
            assertFalse(errors.isEmpty(), "Esperava erro para email sem parte local.");
        }

        @Test
        @DisplayName("4f- dominio vazio gera erro")
        void emailDominioVazioInvalido() {
            Person p = validPerson();
            p.getEmails().getFirst().setName("usuario@.com");
            List<String> errors = dao.isValidToInclude(p);
            assertFalse(errors.isEmpty(), "Esperava erro para email sem dominio.");
        }

        @Test
        @DisplayName("4g- TLD vazio gera erro")
        void emailTldVazioInvalido() {
            Person p = validPerson();
            p.getEmails().getFirst().setName("usuario@dominio.");
            List<String> errors = dao.isValidToInclude(p);
            assertFalse(errors.isEmpty(), "Esperava erro para email sem TLD.");
        }

        @Test
        @DisplayName("4h- nome de email vazio gera erro")
        void emailNomeVazioInvalido() {
            Person p = validPerson();
            p.getEmails().getFirst().setName("");
            List<String> errors = dao.isValidToInclude(p);
            assertFalse(errors.isEmpty(), "Esperava erro para email vazio.");
        }

        @Test
        @DisplayName("4i- email nulo gera erro")
        void emailNomeNuloInvalido() {
            Person p = validPerson();
            p.getEmails().getFirst().setName(null);
            List<String> errors = dao.isValidToInclude(p);
            assertFalse(errors.isEmpty(), "Esperava erro para email nulo.");
        }
    }

    @Nested
    @DisplayName("Regra 5- Multiplos erros simultaneos")
    class MultiErros {

        @Test
        @DisplayName("5a- nome invalido + idade invalida retornam 2 erros")
        void nomeEIdadeInvalidos() {
            Person p = new Person(1, "Joao", -5);
            p.addEmail(new Email(1, "joao@email.com"));
            List<String> errors = dao.isValidToInclude(p);
            assertEquals(2, errors.size(),
                    "Esperava exatamente 2 erros, mas obteve: " + errors);
        }

        @Test
        @DisplayName("5b- person sem nenhuma informacao valida retorna multiplos erros")
        void personCompletamenteInvalida() {
            Person p = new Person(0, "X", -1);
            // sem emails
            List<String> errors = dao.isValidToInclude(p);
            assertTrue(errors.size() >= 3,
                    "Esperava ao menos 3 erros, mas obteve: " + errors);
        }
    }
}
