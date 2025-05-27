package com.api.tarefas.Service;

import com.api.tarefas.Model.Curso;
import com.google.api.core.ApiFuture;
import com.google.firebase.database.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class CursoRepositorio{

    //CRUD curso

    public static final String COL_NAME = "Curso";

    //Create
    public String save(Curso curso) throws InterruptedException, ExecutionException {
        DatabaseReference dbFirebase = FirebaseDatabase
                .getInstance()
                .getReference(COL_NAME);

        String key = dbFirebase.push().getKey();
        curso.setId(key);

        ApiFuture<Void> colletionsApi = dbFirebase.child(String.valueOf(curso.getId()))
                .setValueAsync(curso);

        colletionsApi.get();

        return "Curso cadastrado com sucesso";
    }

    //Read
    public CompletableFuture<List<Curso>> findAll() {
        CompletableFuture<List<Curso>> cursosFuture = new CompletableFuture<>();

        DatabaseReference dbFirebase = FirebaseDatabase.getInstance().getReference(COL_NAME);

        dbFirebase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                List<Curso> lista = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Curso curso = dataSnapshot.getValue(Curso.class);
                    if (curso != null) {
                        lista.add(curso);
                    }
                }
                cursosFuture.complete(lista);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                cursosFuture.completeExceptionally(error.toException());
            }
        });

        return cursosFuture;
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
