package com.api.tarefas.Controller;

import com.api.tarefas.Model.Disciplina;
import com.api.tarefas.Service.DisciplinaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@CrossOrigin(origins = "*")
@RestController
public class DisciplinaController {

    @Autowired
    private DisciplinaRepositorio acao;

    //Create
    @PostMapping(value = "/disciplina")
    public ResponseEntity cadastrarDisciplina(@RequestBody Disciplina disciplina) throws InterruptedException, ExecutionException {
       acao.save(disciplina);
       return ResponseEntity.ok(Map.of("message", "disciplina cadastrada com sucesso"));
    }

    //Read
    @GetMapping(value = "/disciplina")
    public @ResponseBody CompletableFuture<List<Disciplina>> listarDisciplina() throws InterruptedException, ExecutionException{
        return acao.findAll();
    }


    //Update
    @PutMapping(value = "/disciplina")
    public String EditarDisciplina(@RequestBody Disciplina disciplina) throws InterruptedException, ExecutionException {
        return acao.save(disciplina);
    }

    //Delete
    @DeleteMapping(value = "/disciplina/{id}")
    public String DeletarDisciplina(@PathVariable String id) throws InterruptedException, ExecutionException{
        return acao.delete(id);
    }

}
