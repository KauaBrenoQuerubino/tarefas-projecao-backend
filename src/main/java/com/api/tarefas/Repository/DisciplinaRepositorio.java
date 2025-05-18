package com.api.tarefas.Repository;

import com.api.tarefas.Model.Curso;
import com.api.tarefas.Model.Disciplina;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DisciplinaRepositorio extends CrudRepository<Disciplina, Integer> {
    //Lista as Disciplinas
    List<Disciplina> findAll();

    //Pesquisa pelo id da disciplinas
    Disciplina findById(int id);

    //Cria/altera as disciplinas
    <DiscMod extends Disciplina> DiscMod save(DiscMod curso);

    //Deletar Disciplina
    void delete(Disciplina disciplina);
}
