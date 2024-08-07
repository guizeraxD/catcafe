package br.com.guikun.srvcatcafe.domain.model;

import br.com.guikun.srvcatcafe.domain.enums.TipoUsuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
@Document(collection = "Usuarios")
public class Usuario implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    @Field("nome")
    private String nome;
    @Field("email")
    private String email;
    @Field("senha")
    private String senha;
    @Field("telefone")
    private String telefone;
    @Field("dataCadastro")
    private LocalDateTime dataCadastro;
    @Field("enderecos")
    private List<Endereco> enderecos;
    @Field("tipoUsuario")
    private TipoUsuario tipoUsuario;

    public Usuario() {
    }

    public Usuario(String id, String nome, String email, String senha, String telefone, LocalDateTime dataCadastro, List<Endereco> enderecos, TipoUsuario tipoUsuario) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.dataCadastro = dataCadastro;
        this.enderecos = enderecos;
        this.tipoUsuario = tipoUsuario;
    }

    public Usuario(String nome, String email, String senha, String telefone, LocalDateTime dataCadastro, List<Endereco> enderecos, TipoUsuario tipoUsuario) {

        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.dataCadastro = dataCadastro;
        this.enderecos = enderecos;
        this.tipoUsuario = tipoUsuario;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", telefone='" + telefone + '\'' +
                ", dataCadastro=" + dataCadastro +
                ", enderecos=" + enderecos +
                ", tipoUsuario=" + tipoUsuario +
                '}';
    }
}
