package org.fatec.dao;

import org.fatec.entity.Email;
import org.fatec.entity.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonDAO {

    public void save(Person p) {
        List<String> errors = isValidToInclude(p);
        if (!errors.isEmpty()) {
            throw new IllegalArgumentException("Person inválida: " + errors);
        }
    }


    public List<String> isValidToInclude(Person p) {
        List<String> errors = new ArrayList<>();

        // Regra 1 — nome: ao menos 2 partes compostas somente de letras
        validateName(p.getName(), errors);

        // Regra 2 — idade no intervalo [1, 200]
        validateAge(p.getAge(), errors);

        // Regra 3 — deve ter ao menos 1 e-mail
        if (p.getEmails() == null || p.getEmails().isEmpty()) {
            errors.add("Person deve ter ao menos um Email associado.");
        } else {
            // Regra 4 — cada e-mail deve respeitar o formato _____@_____._____
            for (Email email : p.getEmails()) {
                validateEmail(email.getName(), errors);
            }
        }

        return errors;
    }


    private void validateName(String name, List<String> errors) {
        if (name == null || name.isBlank()) {
            errors.add("O nome nao pode ser vazio.");
            return;
        }
        String[] parts = name.trim().split("\\s+");
        if (parts.length < 2) {
            errors.add("O nome deve ser composto por ao menos 2 partes.");
            return;
        }
        for (String part : parts) {
            if (!part.matches("[a-zA-Z]+")) {
                errors.add("O nome deve ser composto apenas por letras (sem numeros ou simbolos): \"" + part + "\"");
                return;
            }
        }
    }

    private void validateAge(int age, List<String> errors) {
        if (age < 1 || age > 200) {
            errors.add("A idade deve estar no intervalo [1, 200]. Valor recebido: " + age);
        }
    }

    private void validateEmail(String email, List<String> errors) {
        if (email == null || email.isBlank()) {
            errors.add("O endereco de email nao pode ser vazio.");
            return;
        }
        // Formato esperado: <local>@<dominio>.<tld>  — cada parte >= 1 caractere
        if (!email.matches("[^@.]+@[^@.]+\\.[^@.]+")) {
            errors.add("Email em formato invalido (esperado: usuario@dominio.tld): \"" + email + "\"");
        }
    }
}