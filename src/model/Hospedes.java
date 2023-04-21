package model;

public class Hospedes {
    private Integer id;
    private String nome;
    private String sobrenome;
    private String dataNascimento;
    private String nacionalidade;
    private String telefone;
    private String reserva;

    public Hospedes(String nome, String sobrenome, String dataNascimento, String nacionalidade, String telefone, String reserva){
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.nacionalidade = nacionalidade;
        this.telefone = telefone;
        this.reserva = reserva;
    }

    public Hospedes(Integer id,String nome, String sobrenome, String dataNascimento, String nacionalidade, String telefone, String reserva){
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.nacionalidade = nacionalidade;
        this.telefone = telefone;
        this.reserva = reserva;
    }

    public String getNome() {
        return nome;
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

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getId() {
        return id;
    }

    public String getReserva() {
        return reserva;
    }
}
