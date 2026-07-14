package br.com.projeto.cadastro.dto.mapper;

import br.com.projeto.cadastro.dto.request.CadastroRequest;
import br.com.projeto.cadastro.dto.response.CadastroResponse;
import br.com.projeto.cadastro.dto.update.CadastroUpdate;
import br.com.projeto.cadastro.persistance.entity.Cadastro;

public class MapperCadastro {

    public static CadastroResponse toResponse(Cadastro cadastro) {
        return CadastroResponse.builder()
                               .id(cadastro.getId())
                               .nome(cadastro.getNome())
                               .email(cadastro.getEmail())
                               .senha(cadastro.getSenha())
                               .ativo(cadastro.getAtivo())
                               .dataCadastro(cadastro.getDataCadastro())
                               .dataAtualizacao(cadastro.getDataAtualizacao())
                               .build();
    }

    public static Cadastro toEntity(CadastroRequest dto) {
        return Cadastro.builder()
                       .nome(dto.getNome()
                                .trim()
                                .toUpperCase()
                                .replaceAll("\\s+", " "))
                       .email(dto.getEmail()
                                 .trim()
                                 .replaceAll("\\s+", ""))
                       .senha(dto.getSenha()
                                 .trim()
                                 .replaceAll("\\s+", ""))
                       .build();
    }

    public static void toUpdate(Cadastro cadastro, CadastroUpdate dto) {
        if( dto.getNome() != null && !dto.getNome()
                                         .equals(cadastro.getNome()) ) {
            cadastro.setNome(dto.getNome()
                                .trim()
                                .toUpperCase()
                                .replaceAll("\\s+", " "));
        }

        if( dto.getEmail() != null && !dto.getEmail()
                                          .equals(cadastro.getEmail()) ) {
            cadastro.setEmail(dto.getEmail()
                                 .trim()
                                 .replaceAll("\\s+", ""));
        }


    }

}
