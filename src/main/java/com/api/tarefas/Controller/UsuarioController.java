package com.api.tarefas.Controller;


import com.api.tarefas.Model.Usuario;
import com.api.tarefas.Service.UsuariosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@CrossOrigin(origins = "*")
@RestController
public class UsuarioController {

    @Autowired
    private UsuariosRepositorio acao;

    //Create
    @PostMapping(value = "/user")
    public ResponseEntity<?> cadastrarUsuario(@RequestBody Usuario usuario) throws InterruptedException, ExecutionException{

        acao.save(usuario);
        return ResponseEntity.ok(Map.of("message", "Usuário cadastrado com sucesso"));
    }

    //Read
    @GetMapping(value = "/user/{matricula}")
    public @ResponseBody CompletableFuture<Usuario> listarUsuario(@PathVariable Integer matricula) throws InterruptedException, ExecutionException{
        return acao.findByMatricula(matricula);
    }

    //Update
    @PutMapping(value = "/user")
    public String AtualizarUsuario(@RequestBody Usuario usuario) throws InterruptedException, ExecutionException{
        return acao.save(usuario);
    }

    //Delete
    @DeleteMapping(value = "/user/{matricula}")
    public String DeletarUsuario(@PathVariable Integer matricula) throws InterruptedException, ExecutionException{
        return acao.delete(matricula);
    }









}
