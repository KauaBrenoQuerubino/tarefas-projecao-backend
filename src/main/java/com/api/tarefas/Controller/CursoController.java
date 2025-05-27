package com.api.tarefas.Controller;


import com.api.tarefas.Model.Curso;
import com.api.tarefas.Service.CursoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@CrossOrigin(origins = "*")
@RestController
public class CursoController {

    @Autowired
    private CursoRepositorio acao;

    //Create
    @PostMapping(value = "/curso")
    public ResponseEntity<?> cadastrarCurso(@RequestBody Curso curso) throws InterruptedException, ExecutionException {
        acao.save(curso);
        return ResponseEntity.ok(Map.of("message", "Curso cadastrada com sucesso"));
    }

    //Read
    @GetMapping(value = "/curso")
    public @ResponseBody CompletableFuture<List<Curso>> listarCurso() throws InterruptedException, ExecutionException{
        return acao.findAll();
    }

    //Update
    @PutMapping(value = "/curso")
    public String EditarCurso(@RequestBody Curso curso) throws InterruptedException, ExecutionException {
        return acao.save(curso);
    }

    //Delete
    @DeleteMapping(value = "/curso/{id}")
    public String DeletarCurso(@PathVariable String id) throws InterruptedException, ExecutionException{
        return acao.delete(id);
    }
}
