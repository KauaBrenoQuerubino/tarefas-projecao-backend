
package com.api.tarefas.Service;

import com.api.tarefas.Model.Disciplina;
import com.api.tarefas.Model.Tarefas;
import com.google.api.core.ApiFuture;
import com.google.firebase.database.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class TarefasRepositorio {

    //CRUD tarefas

    public static final String COL_NAME = "tarefa";

    //Create / Update
    public String save(Tarefas tarefas) throws InterruptedException, ExecutionException {
        DatabaseReference dbFirebase = FirebaseDatabase
                .getInstance()
                .getReference(COL_NAME);

        // Gera um ID único
        String key = dbFirebase.push().getKey();
        tarefas.setId(key);

        ApiFuture<Void> colletionsApi = dbFirebase.child(key).setValueAsync(tarefas);
        colletionsApi.get();

        return "Tarefa cadastrada com sucesso";
    }

    //Read
    public CompletableFuture<Tarefas> findById(String id) throws InterruptedException, ExecutionException{

        CompletableFuture<Tarefas> tarefas = new CompletableFuture<>();

        DatabaseReference dbFirebase = FirebaseDatabase
                .getInstance()
                .getReference(COL_NAME)
                .child(String.valueOf(id));


        dbFirebase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    tarefas.complete(dataSnapshot.getValue(Tarefas.class));
                }
                else {
                    tarefas .complete(null);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.err.println("Error");
            }
        });


        return tarefas;
    }


    public CompletableFuture<List<Tarefas>> findByMatricula(int matricula) {
        CompletableFuture<List<Tarefas>> tarefas = new CompletableFuture<>();

        DatabaseReference dbFirebase = FirebaseDatabase.getInstance().getReference(COL_NAME);
        dbFirebase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                List<Tarefas> lista = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Tarefas tarefa = dataSnapshot.getValue(Tarefas.class);

                    if (tarefa != null && tarefa.getUsuario() != null && tarefa.getUsuario().getMatricula() == matricula) {
                        lista.add(tarefa);
                    }
                }
                tarefas.complete(lista);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                tarefas.completeExceptionally(error.toException());
            }
        });

        return tarefas;
    }



    //Delete
    public String delete(String id) throws InterruptedException, ExecutionException  {
        DatabaseReference dbFirebase = FirebaseDatabase
                .getInstance()
                .getReference(COL_NAME);


        dbFirebase.child(String.valueOf(id)).removeValueAsync();

        return "Removido com sucesso";
    }

}
