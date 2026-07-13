package br.com.projeto.cadastro.service;

import br.com.projeto.cadastro.dto.request.CadastroRequest;
import br.com.projeto.cadastro.dto.response.CadastroResponse;
import br.com.projeto.cadastro.dto.update.CadastroUpdate;
import br.com.projeto.cadastro.persistance.entity.Cadastro;

import java.util.List;

public interface CadastroService {

    List<CadastroResponse> getTodosCadastros();

    CadastroResponse setNovoCadastro(CadastroRequest dto);

    CadastroResponse setAtualizarCadastro(Long id, CadastroUpdate dto);

    void setApagarCadastro(Long id);

    void setMudarStatusAtivo(Long id);

    void setAlterarSenha(Long id, String senha);

}
