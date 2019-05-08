package com.example.cassiamshigenaga.todo.Banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

//Definição no nome da classe ControlaBanco. Segue o padrão de nomes: classes começam com a primeira letra maiúscula
public class ControlaBanco {

    //Declaração de objeto "db" do tipo "SQLiteDatabase"
    private SQLiteDatabase db;

    //Declaração do objeto "banco" do tipo "CriaBanco". Objeto privado -> só pode ser acessado pela classe atual
    private CriaBanco banco;

    //Construtor da classe. Recebe como parâmetro o contexto. Público -> pode ser acessado de fora da classe atual
    public ControlaBanco(Context context) {
        banco = new CriaBanco(context);
    }

    //Método do tipo String (deve ser retornnado um valor String)
    //Público -> pode ser acessado de fora da classe
    //Recebe como parâmetro uma variável do tipo String
    public String insereDado(String nome) {

        //Declara o objeto "valores" do tipo "ContentValues" - ContentValues (classe), valores (objeto)
        //Padrão de nome para objeto é começar com a primeira letra minúscula
        ContentValues valores;

        //Declara a variável "resultado" do tipo "long" - long (tipo primitivo de dado), resultado (variável)
        //Padrão de nome para objeto é começar com a primeira letra minúscula
        long resultado;

        //Retorna o valor do método "getWritableDatabase" e adiciona no objeto "db"
        //Diz que o banco está preparado para escrit de novos dados
        db = banco.getWritableDatabase();

        //Instância o objeto "valores" com o construtor da classe "ContentValues"
        valores = new ContentValues();

        //put é um método que recebe como parâmetro duas Strings
        //Primeira String é nome da coluna no banco de dados
        //Segunda String é o dado que será inserido na linha
        valores.put("nome", nome);

        //insert é um método que recebe como parâmetro três valores
        //Primeiro valor é uma String com o nome da tabela
        //Segundo valor é uma String para os campos nulos
        //Terceiro valor é um objeto do tipo "ContentValues" com o valor a ser inserido
        //método retorna o valor na variável resultado
        resultado = db.insert("tarefa", null, valores);
        //close é o método que não recebe nenhum parâmetro
        db.close();

        if (resultado == -1){
            return "Erro ao inserir dado";
        }

        else {
            return "Dado inserido com sucesso";
        }
    }

    //leitura dos dados - Cursor
    //Declarando um método público do tipo Cursor que não recebe nenhum parâmetro
    public Cursor carregaDados(){

        //Objeto "cursor" do tipo/classe "Cursor"
        Cursor cursor;

        //Um array de String com o nome "campos" que recebe os valores entre as chaves
        String [] campos = {"_id", "nome"};

        //getReadableDatabase() é um método do objeto "banco" que retorna um valor para o objeto "db"
        //Esse método diz que o banco estará disponível para leitura
        db = banco.getReadableDatabase();

        //query é um método do objeto db que recebe como parâmetro o que está entre os parenteses
        //o valor retornado do método query será inserido no objeto cursor
        cursor = db.query("tarefa", campos, null, null, null, null, null);

        //se o objeto "cursor" for diferente de vazio - Compara se o "cursor" não é vazio
        if(cursor != null){
            //o método "moveToFirst" posiciona para o cursor para a primeira posição
            cursor.moveToFirst();
        }

        //close é o método do objeto "db" que não recebe nenhum parâmetro, que fecha o banco de dados(db)
        db.close();
        //retorna o objeto cursor no método
        return cursor;
    }

    public void deletaDado (int id){
        String where = "_id = " + id;
        db = banco.getReadableDatabase();
        db.delete("tarefa", where, null);
        db.close();
    }

    public Cursor carregaDadoPorId(int id){
        Cursor cursor;
        String [] campos = {"_id", "nome"};
        String where = "_id = " + id;
        db = banco.getReadableDatabase();
        cursor = db.query("tarefa", campos, where, null, null, null, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }

    public void alteraDado (int id, String nome){
     ContentValues valores;
     String where;

     db = banco.getWritableDatabase();
     where = "_id=" + id;
     valores = new ContentValues();
     valores.put("nome", nome);

     db.update("tarefa", valores, where, null);
     db.close();
    }
}
