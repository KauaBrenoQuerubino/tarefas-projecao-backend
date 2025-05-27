package com.api.tarefas.Controller;


import com.api.tarefas.Model.Tarefas;
import com.api.tarefas.Model.Usuario;
import com.api.tarefas.Service.TarefasRepositorio;
import com.api.tarefas.Service.UsuariosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@CrossOrigin(origins = "*")
@RestController
public class TarefasController {

    @Autowired
    private TarefasRepositorio acao;

    @Autowired
    private UsuariosRepositorio acao_user;

    //Create
    @PostMapping(value = "/task")
    public ResponseEntity<?> cadastrarTarefas(@RequestBody Tarefas tarefas) throws InterruptedException, ExecutionException {

//        CompletableFuture<Usuario> usuario =  acao_user
//                        .findByMatricula(tarefas
//                        .getUsuario()
//                        .getMatricula());
//
//        if (usuario.get() == null){
//            return ResponseEntity
//                    .status(HttpStatus.CONFLICT)
//                    .body(Map.of("error", "Usuário nao encontrado cadastrado"));
//        }

        acao.save(tarefas);
        return ResponseEntity.ok(Map.of("message", "Tarefa cadastrada com sucesso"));

    }

    //Read
    @GetMapping(value = "/task/{id}")
    public @ResponseBody CompletableFuture<Tarefas> listarTarefa(@PathVariable String id) throws InterruptedException, ExecutionException{
        return acao.findById(id);

    }

    @GetMapping("/task/usuarios/{matricula}")
    public @ResponseBody CompletableFuture<List<Tarefas>> listarTarefaMatricula(@PathVariable Integer matricula) {
        return acao.findByMatricula(matricula);
    }

    //Update
    @PutMapping(value = "/task")
    public String EditarTarefa(@RequestBody Tarefas tarefas) throws InterruptedException, ExecutionException {

//        CompletableFuture<Usuario> usuario =  acao_user
//                .findByMatricula(tarefas
//                        .getUsuario()
//                        .getMatricula());
//
//        if (usuario.get() == null){
//            return "Nenhum usuario encontrado";
//        }

        return acao.save(tarefas);
    }

    //Delete
    @DeleteMapping(value = "/task/{id}")
    public String DeletarTarefas(@PathVariable String id) throws InterruptedException, ExecutionException{
        return acao.delete(id);
    }



}
