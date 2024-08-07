package br.com.guikun.srvcatcafe.adapter.exception.handler.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ApiErroResponse {
    @JsonProperty("timestamp")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private final LocalDateTime timestamp;

    @JsonProperty("status")
    private HttpStatus status;

    @JsonProperty("codigoErro")
    private Integer codigoErro;

    @JsonProperty("mensagem")
    private String mensagem;

    @JsonProperty("mensagemDetalhada")
    private String mensagemDetalhada;

    private ApiErroResponse(){timestamp = LocalDateTime.now();}

    public ApiErroResponse(HttpStatus status){
        this();
        this.status = status;
        this.codigoErro = status.value();
    }

    public ApiErroResponse(HttpStatus status, Throwable ex){
        this();
        this.status = status;
        this.codigoErro = status.value();
        this.mensagem = ex.getMessage();
        this.mensagemDetalhada = ex.getLocalizedMessage();
    }

    public ApiErroResponse(HttpStatus status, String msg, Throwable ex){
        this();
        this.status = status;
        this.codigoErro = status.value();
        this.mensagem = msg;
        this.mensagemDetalhada = ex.getLocalizedMessage();
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public Integer getCodigoErro() {
        return codigoErro;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getMensagemDetalhada() {
        return mensagemDetalhada;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public void setMensagemDetalhada(String mensagemDetalhada) {
        this.mensagemDetalhada = mensagemDetalhada;
    }
}
