package com.api.tarefas.Database;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class FirebaseInit {

    @PostConstruct
    public void iniciarBanco() {
        try {
            FileInputStream serviceAccount;
            serviceAccount = new FileInputStream("./firebase/firebase-key.json");

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://atividade-fabrica-default-rtdb.firebaseio.com/")
                    .build();

            FirebaseApp.initializeApp(options);

        }catch (IOException e) {
            e.printStackTrace();
        }

    }

}
