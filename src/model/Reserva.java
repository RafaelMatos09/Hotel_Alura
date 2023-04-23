package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reserva {
    private Integer id;
    private Date dataEntradaC;
    private Date dataSaidaC;
    private float valor;
    private String formaPagamento;
    private String dataEntrada;
    private String dataSaida;

    public Reserva(String dataEntrada, String dataSaida, String formaPagamento) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        this.dataEntradaC = sdf.parse(dataEntrada);
        this.dataSaidaC = sdf.parse(dataSaida);
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.formaPagamento = formaPagamento;
        this.valor = caclValorDiaria();
    }

    public Reserva(Integer id, String dataEntrada, String dataSaida, float valor, String formaPagamento){
        this.id = id;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.formaPagamento = formaPagamento;
        this.valor = valor;
    }

    public Reserva() {

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getDataEntrada() {
        return dataEntrada;
    }

    public String getDataSaida() {
        return dataSaida;
    }

    public float getValor() {
        return valor;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public float caclValorDiaria() {
        float diaria = 120;
        float calc = diferencaEmdias() * diaria;
        return calc;
    }

    public long diferencaEmdias() {
        long diferenca = dataSaidaC.getTime() - dataEntradaC.getTime();
        return diferenca / (24 * 60 * 60 * 1000);
    }

}
