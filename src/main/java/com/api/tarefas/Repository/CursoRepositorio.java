package com.api.tarefas.Repository;

import com.api.tarefas.Model.Curso;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface CursoRepositorio extends CrudRepository<Curso, Integer> {

    //Lista os cursos
    List<Curso> findAll();

    //Pesquisa pelo id do curso
    Curso findById(int id);

    //Cria/altera os cursos
    <CursoMod extends Curso> CursoMod save(CursoMod curso);

    //Deletar Curso
    void delete(Curso curso);

}
