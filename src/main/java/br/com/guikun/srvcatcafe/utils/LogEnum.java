package br.com.guikun.srvcatcafe.utils;

public enum LogEnum {
    ENTRADA("Metodo: {} - Entrada: {}"),
    SAIDA("Metodo: {} - Saida: {}"),
    ERRO("Metodo: {} - Mensagem de erro: %s");

    private final String msg;
    LogEnum(String msg){this.msg = msg;}

    public String getMsg(){return msg;}

    public String getErrorMsg(String metodo, String msgErro){
        if(this.equals(ERRO))
            return String.format(this.msg, metodo, msgErro);
        throw new IllegalArgumentException("Método exclusivo para mensagens de erro.");
    }
}
