package br.com.projeto.cadastro.dto.update;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CadastroUpdate {

    private Long id;
    private String nome;
    private String email;
    private String senha;
}
