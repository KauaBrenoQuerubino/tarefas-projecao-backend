package com.api.tarefas.Controller;


import com.api.tarefas.DTO.LoginDTO;
import com.api.tarefas.DTO.TokenRequestDTO;
import com.api.tarefas.Model.Usuario;
import com.api.tarefas.Service.UsuariosRepositorio;
import com.api.tarefas.Token.Tokenjwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@CrossOrigin(origins = "*")
@RestController
public class UsuarioController {

    @Autowired
    private UsuariosRepositorio acao;

    @Autowired
    private Tokenjwt tokenjwt;


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
    public ResponseEntity<?> AtualizarUsuario(@RequestBody Usuario usuario) throws InterruptedException, ExecutionException{
        acao.save(usuario);
        return ResponseEntity.ok(Map.of("message", "Usuário atualizado com sucesso"));
    }

    //Delete
    @DeleteMapping(value = "/user/{matricula}")
    public String DeletarUsuario(@PathVariable Integer matricula) throws InterruptedException, ExecutionException{
        return acao.delete(matricula);
    }


    //Login
    @PostMapping(value = "/login")
    public  ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) throws InterruptedException, ExecutionException {

        CompletableFuture<Usuario> usuario = acao.findByMatricula(loginDTO.getMatricula());

        Map<String, Object> resposta = new HashMap<>();


        if (usuario != null && usuario.get().getSenha().equals(loginDTO.getSenha())) {

            String token = tokenjwt.gerarToken(usuario.get());
            resposta.put("mensagem", "Login efetuado com sucesso");
            resposta.put("token", token);

            return ResponseEntity.ok(resposta);
        }

        resposta.put("mensagem", "Error ao efetuar o Login");
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    //Sessao
    @PostMapping(value = "/sessao")
    public ResponseEntity<Usuario> sessao (@RequestBody TokenRequestDTO tokenjwtDTO) throws InterruptedException, ExecutionException{

        CompletableFuture<Usuario> usuario = acao.findByMatricula(Integer.parseInt(tokenjwt.validarToken(tokenjwtDTO.getToken())));

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(usuario.get());

    }









}
