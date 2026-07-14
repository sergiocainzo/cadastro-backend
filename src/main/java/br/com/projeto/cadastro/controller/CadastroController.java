package br.com.projeto.cadastro.controller;

import br.com.projeto.cadastro.dto.request.CadastroRequest;
import br.com.projeto.cadastro.dto.response.CadastroResponse;
import br.com.projeto.cadastro.dto.update.CadastroUpdate;
import br.com.projeto.cadastro.service.CadastroService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cadastros")
@CrossOrigin(origins = "*")
public class CadastroController {

    private final CadastroService cadastroService;

    public CadastroController(CadastroService cadastroService) {
        this.cadastroService = cadastroService;
    }

    @GetMapping("/bemvindo")
    public String welcome() {
        return "Bem vindo ao sistemas de Cadastros";
    }

    @GetMapping
    public ResponseEntity<List<CadastroResponse>> exibirTodosCadastros() {
        List<CadastroResponse> todos = cadastroService.getTodosCadastros();
        return ResponseEntity.ok(todos);
    }

    @PostMapping
    public ResponseEntity<CadastroResponse> cadastrarNovoUsuario(@Valid @RequestBody CadastroRequest dto) {
        CadastroResponse novo = cadastroService.setNovoCadastro(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(novo);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CadastroResponse> atualizarCadastroDeUsuario(@PathVariable Long id, @RequestBody CadastroUpdate dto) {
        CadastroResponse atualizado = cadastroService.setAtualizarCadastro(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagarCadastroDeUsuario(@PathVariable Long id){
        cadastroService.setApagarCadastro(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/alterarstatus")
    public ResponseEntity<CadastroResponse> alterarStatusAtivo(@PathVariable Long id){
        CadastroResponse atualizado = cadastroService.setMudarStatusAtivo(id);
        return ResponseEntity.ok(atualizado);
    }

}
