package br.com.projeto.cadastro.persistance.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cadastro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nome", nullable = false, length = 255)
    private String nome;

    @Column(name = "email", nullable = false, unique = true, length = 255)
    private String email;

    @JsonIgnore
    @Column(name = "senha", nullable = false)
    private String senha;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo;

    @Column(name = "data_cadastro", nullable = false)
    private LocalDateTime dataCadastro;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @PostPersist
    public void onSave() {
        dataCadastro = LocalDateTime.now(ZoneId.of("America/Recife"));
    }

    @PostUpdate
    public void onUpdate() {
        dataAtualizacao = LocalDateTime.now(ZoneId.of("America/Recife"));
    }

}
