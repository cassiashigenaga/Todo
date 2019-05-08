package com.example.cassiamshigenaga.todo;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.cassiamshigenaga.todo.Banco.ControlaBanco;

import java.util.List;

public class ListaActivity extends AppCompatActivity {

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        Intent intent = getIntent();

        final ControlaBanco crud = new ControlaBanco(getBaseContext());
        final Cursor cursor = crud.carregaDados();

        String[] nomecampos = new String[]{"_id", "nome"};
        int[] idView = new int[]{R.id.tvId, R.id.tvNome};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getBaseContext(), R.layout.layout_tarefas, cursor, nomecampos, idView, 0);

        lista = findViewById(R.id.listView);
        lista.setAdapter(adapter);

        //Clique longo para apagar um item da lista
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                int codigo;
                cursor.moveToPosition(position);
                codigo = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
                crud.deletaDado(codigo);
                finish();
                startActivity(getIntent());
                return false;
            }
        }); //final das atividades para o clique longo

        //Clique curto para redirecionar para uma nova activity
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Integer codigoId;

                cursor.moveToPosition(position);
                codigoId = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));

                Intent intent = new Intent(ListaActivity.this, AtualizaActivity.class);
                intent.putExtra("codigoId", codigoId.toString());
                startActivity(intent);
                finish();
            }
        }); //final do clique curto
    }
}
