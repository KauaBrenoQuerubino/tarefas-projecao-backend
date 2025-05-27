
package com.api.tarefas.Service;


import com.api.tarefas.Model.Usuario;
import com.google.api.core.ApiFuture;
import com.google.firebase.database.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class UsuariosRepositorio{

    //CRUD usuarios

    public static final String COL_NAME = "usuarios";


    //Create / Update
    public String save(Usuario usuario) throws InterruptedException, ExecutionException {
        DatabaseReference dbFirebase = FirebaseDatabase
                .getInstance()
                .getReference(COL_NAME);

        ApiFuture<Void> colletionsApi = dbFirebase.child(String.valueOf(usuario.getMatricula()))
                .setValueAsync(usuario);

        colletionsApi.get();

        return "Usuario cadastrado com sucesso";
    }


    //Read
    @Async
    public CompletableFuture<Usuario> findByMatricula(int matricula) throws InterruptedException, ExecutionException{

         CompletableFuture<Usuario> usuario = new CompletableFuture<>();

        DatabaseReference dbFirebase = FirebaseDatabase
                .getInstance()
                .getReference(COL_NAME)
                .child(String.valueOf(matricula));


        dbFirebase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    usuario.complete(dataSnapshot.getValue(Usuario.class));
                }
                else {
                    usuario.complete(null);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.err.println("Error");
            }
        });


        return usuario;
    }

    //Delete
    public String delete(int matricula) throws InterruptedException, ExecutionException  {
        DatabaseReference dbFirebase = FirebaseDatabase
                .getInstance()
                .getReference(COL_NAME);


        dbFirebase.child(String.valueOf(matricula)).removeValueAsync();

        return "Removido com sucesso";
    }



}
