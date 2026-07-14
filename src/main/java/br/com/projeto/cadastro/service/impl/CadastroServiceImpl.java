package br.com.projeto.cadastro.service.impl;

import br.com.projeto.cadastro.dto.mapper.MapperCadastro;
import br.com.projeto.cadastro.dto.request.CadastroRequest;
import br.com.projeto.cadastro.dto.response.CadastroResponse;
import br.com.projeto.cadastro.dto.update.CadastroUpdate;
import br.com.projeto.cadastro.persistance.entity.Cadastro;
import br.com.projeto.cadastro.persistance.repository.CadastroRepository;
import br.com.projeto.cadastro.service.CadastroService;
import org.apache.catalina.mapper.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CadastroServiceImpl implements CadastroService {

    // Atributo do repositorio
    private final CadastroRepository cadastroRepository;

    // Construtor
    public CadastroServiceImpl(CadastroRepository cadastroRepository) {
        this.cadastroRepository = cadastroRepository;
    }

    // Metodo para Listar Todos os cadastros
    @Transactional(readOnly = true)
    @Override
    public List<CadastroResponse> getTodosCadastros() {
        return cadastroRepository.findAll()
                                 .stream()
                                 .map(MapperCadastro::toResponse)
                                 .toList();
    }

    @Transactional
    @Override
    public CadastroResponse setNovoCadastro(CadastroRequest dto) {

        if( cadastroRepository.existsByEmailIgnoreCase(dto.getEmail()) ) {
            throw new RuntimeException("Email já existe");
        }

        Cadastro novo = MapperCadastro.toEntity(dto);

        novo.setAtivo(true);

        cadastroRepository.save(novo);

        return MapperCadastro.toResponse(novo);
    }

    @Override
    public CadastroResponse setAtualizarCadastro(Long id, CadastroUpdate dto) {

        Cadastro existe = cadastroRepository.findById(id)
                                            .orElseThrow(() -> new RuntimeException("ID Não encontrado"));

        MapperCadastro.toUpdate(existe, dto);

        if( dto.getSenha() != null && !dto.getSenha()
                                          .equals(existe.getSenha()) ) {
            existe.setSenha(dto.getSenha());
        }

        Cadastro atualizado = cadastroRepository.save(existe);

        return MapperCadastro.toResponse(atualizado);
    }

    @Override
    public void setApagarCadastro(Long id) {

        Cadastro existe = cadastroRepository.findById(id)
                                            .orElseThrow(() -> new RuntimeException("ID não encontrado"));

        cadastroRepository.delete(existe);
    }

    @Override
    public CadastroResponse setMudarStatusAtivo(Long id) {
        Cadastro existe = cadastroRepository.findById(id).orElseThrow(() -> new RuntimeException("ID não encontrado"));

        existe.setAtivo(!existe.getAtivo());

        Cadastro atualizado = cadastroRepository.save(existe);

        return MapperCadastro.toResponse(atualizado);

    }

    @Override
    public void setAlterarSenha(Long id, String senha) {

    }

}
