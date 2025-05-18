
package com.api.tarefas.Repository;

import com.api.tarefas.Model.Usuario;
import org.apache.catalina.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsuariosRepositorio extends CrudRepository<Usuario, Integer> {

    //Lista os Usuarios
    List<Usuario> findAll();

    //Pesquisa por matricula
    Usuario findByMatricula(int matricula);

    //Remove Usuario
    void delete(Usuario usuario);

    //Cria/altera Usuario
    <UserMod extends Usuario> UserMod save(UserMod produtos);

}
