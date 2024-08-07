package br.com.guikun.srvcatcafe.domain.model;

import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serial;
import java.io.Serializable;

public class Endereco implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Field("rua")
    private String rua;
    @Field("numero")
    private String numero;
    @Field("cidade")
    private String cidade;
    @Field("estado")
    private String estado;
    @Field("cep")
    private String cep;

    public Endereco() {
    }

    public Endereco(String rua, String numero, String cidade, String estado, String cep) {
        this.rua = rua;
        this.numero = numero;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "rua='" + rua + '\'' +
                ", numero='" + numero + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                ", cep='" + cep + '\'' +
                '}';
    }
}
