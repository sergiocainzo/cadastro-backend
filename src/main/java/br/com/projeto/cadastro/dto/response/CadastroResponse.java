package br.com.projeto.cadastro.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@JsonPropertyOrder({"id", "nome", "email", "senha","ativo", "dataCadastro", "dataAtualizacao"})
public class CadastroResponse {

    private Long id;
    private String nome;
    private String email;
    private String senha;
    private Boolean ativo;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataAtualizacao;

}
