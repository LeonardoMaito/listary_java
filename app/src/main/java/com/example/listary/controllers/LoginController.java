package com.example.listary.controllers;

import android.util.Patterns;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.example.listary.model.Firestore;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class LoginController {

    private Firestore firestore = new Firestore();
    private String email,password;
    private boolean result;

    public boolean checkAllFields(EditText edEmailLogin, EditText edPasswordLogin) {
        email = edEmailLogin.getText().toString();
        password = edPasswordLogin.getText().toString();

        if (email.length() == 0) {
            edEmailLogin.setError("Esse campo é obrigatório");
            return false;

        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            edEmailLogin.setError("Informe um email válido");
            return false;
        }

        if (password.length() == 0) {
            edPasswordLogin.setError("Esse campo é obrigatório");
            return false;

        } else if (password.length() < 6) {
            edPasswordLogin.setError("A senha deve conter ao menos 6 caracteres");
            return false;
        }
        return true;
    }

    public boolean signInFirestore(String email, String password){
        firestore.getAuth().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            result = true;
                        }
                        else{
                            result = false;
                        }
                    }
                });
        return result;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
