package br.com.projeto.cadastro.persistance.repository;

import br.com.projeto.cadastro.persistance.entity.Cadastro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CadastroRepository extends JpaRepository<Cadastro, Long> {

}
