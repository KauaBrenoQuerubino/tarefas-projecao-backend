
package com.api.tarefas.Repository;

import com.api.tarefas.Model.Tarefas;
import com.api.tarefas.Model.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TarefasRepositorio extends CrudRepository<Tarefas, Integer> {

    //Lista as tarefas
    List<Tarefas> findAll();

    //Pesquisa por id
    Tarefas findById(int id);

    //Pesquisa por titulo
    Tarefas findByTitulo(String titulo);

    //Cria/altera tarefa
    <TarefaMod extends Tarefas> TarefaMod save(TarefaMod produtos);

    //Pesquisa por matricula
    List<Tarefas> findByUsuario(Usuario usuario);

    //Deletar Tarefa
    void delete(Tarefas tarefas);
}
