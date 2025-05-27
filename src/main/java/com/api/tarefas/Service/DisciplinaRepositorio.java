package com.api.tarefas.Service;

import com.api.tarefas.Model.Curso;
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
public class DisciplinaRepositorio{

    //CRUD Disciplina

    public static final String COL_NAME = "Disciplina";

    //Create / Update
    public String save(Disciplina disciplina) throws InterruptedException, ExecutionException {
        DatabaseReference dbFirebase = FirebaseDatabase
                .getInstance()
                .getReference(COL_NAME);

        // Gera um ID único
        String key = dbFirebase.push().getKey();
        disciplina.setId(key);

        ApiFuture<Void> colletionsApi = dbFirebase.child(String.valueOf(disciplina.getId()))
                .setValueAsync(disciplina);

        colletionsApi.get();

        return "Disciplina cadastrada com sucesso";
    }

    //Read
    public CompletableFuture<List<Disciplina>> findAll() {
        CompletableFuture<List<Disciplina>> disciplinasFuture = new CompletableFuture<>();

        DatabaseReference dbFirebase = FirebaseDatabase.getInstance().getReference(COL_NAME);

        dbFirebase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                List<Disciplina> lista = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Disciplina disciplina = dataSnapshot.getValue(Disciplina.class);
                    if (disciplina != null) {
                        lista.add(disciplina);
                    }
                }
                disciplinasFuture.complete(lista);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                disciplinasFuture.completeExceptionally(error.toException());
            }
        });

        return disciplinasFuture;
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
