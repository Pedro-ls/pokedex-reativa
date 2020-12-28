package com.pedrohsilva.pokedex.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Objects;

@Document
public class Pokemon {

    @Id
    private String id;

    private String nome;
    private String categoria;
    private String habilidade;
    private Double peso;
    private String foto;

    /**
     *@param id
     *@param nome
     * @param categoria
     * @param habilidade
     * @param peso
     * @param foto
     * **/
    public Pokemon(String id, String nome, String categoria, String habilidade, Double peso, String foto) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.habilidade = habilidade;
        this.peso = peso;
        this.foto = foto;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getHabilidade() {
        return habilidade;
    }

    public void setHabilidade(String habilidade) {
        this.habilidade = habilidade;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", categoria='" + categoria + '\'' +
                ", habilidade='" + habilidade + '\'' +
                ", peso=" + peso +
                ", foto='" + foto + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pokemon pokemon = (Pokemon) o;
        return Objects.equals(getId(), pokemon.getId()) && Objects.equals(getNome(), pokemon.getNome()) && Objects.equals(getCategoria(), pokemon.getCategoria()) && Objects.equals(getHabilidade(), pokemon.getHabilidade()) && Objects.equals(getPeso(), pokemon.getPeso()) && Objects.equals(getFoto(), pokemon.getFoto());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getCategoria(), getHabilidade(), getPeso(), getFoto());
    }
}
