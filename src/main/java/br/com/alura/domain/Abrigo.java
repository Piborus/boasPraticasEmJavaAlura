package br.com.alura.domain;

import java.util.Arrays;

public class Abrigo {


    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private Pet[] pets;

    public Abrigo(String nome, String telefone, String email){
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;

    }

    public Abrigo() {
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }

    public Pet[] getPets() {
        return pets;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return """
                "id":%s, "nome":%s, "email":%s, "telefone":%s"
                """.formatted(this.id, this.nome, this.email, this.telefone);
    }
}
