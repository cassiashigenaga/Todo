package com.example.cassiamshigenaga.todo;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cassiamshigenaga.todo.Banco.ControlaBanco;
import com.example.cassiamshigenaga.todo.Banco.Main2Activity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void botaoListar(View view){
          Intent intent = new Intent(this, ListaActivity.class);
          startActivity(intent);
    }

    public void botaoCadastro(View view){
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }
}
