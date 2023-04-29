package model;

import model.Reserva;

public class Hospedes {
    private Integer id;
    private String nome;
    private String sobrenome;
    private String dataNascimento;
    private String nacionalidade;
    private String telefone;
    private Integer reserva;


    public Hospedes(String nome, String sobrenome, String dataNascimento, String nacionalidade, String telefone, Integer reserva) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.nacionalidade = nacionalidade;
        this.telefone = telefone;
        this.reserva = reserva;

    }

    public Hospedes(Integer id, String nome, String sobrenome, String dataNascimento, String nacionalidade, String telefone, Integer reserva) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.nacionalidade = nacionalidade;
        this.telefone = telefone;
        this.reserva = reserva;

    }

    public Hospedes() {

    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }


    public String getDataNascimento() {
        return dataNascimento;
    }


    public String getNacionalidade() {
        return nacionalidade;
    }


    public String getTelefone() {
        return telefone;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReserva() {
        return reserva;
    }


}
