package BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import Interecao.Usuario;

/**
 * Created by Rafael Martins on 09/11/2016.
 */

public class UsuarioBD {


                                                                                                   //criado uma variavel para abrir e manipular o Banco de Dados
    private Conecta dataBaseHelper;


                                                                                                   //Criando variavel que Permite que interações sejam feitas no Banco
    private SQLiteDatabase sqLiteDatabase;


                                                                                                   //criando o metodo contrutor ou Instanciando -> Recebe por padrão um contexto
    public UsuarioBD(Context context) {                                                            //Ira passar qual Activity que está chamando esta classe
        dataBaseHelper = new Conecta(context);                                                     // abrindo o banco de dados.
    }


                                                                                                   //Metodo com Retorno então não usa-se VOID
    public SQLiteDatabase getDataBase() {

        if (sqLiteDatabase == null){
            sqLiteDatabase = dataBaseHelper.getWritableDatabase() ;                                // Prepara o Banco de Dados para a manipulação (Escrita, Remoção ETC)

        }return sqLiteDatabase ;
    }


                                                                                                   // Criando um Metodo para fechar o Banco de Dados de maneira pratica
    public void fechar(){

        dataBaseHelper.close();                                                                    //fechando o banco
        dataBaseHelper = null ;                                                                    //atribuindo null pra ele

    }

                                                                                                   //Criando um Retorno a Classe Usuário  e um novo objeto
    private Usuario criarUsuario(Cursor cursor){

                                                                                                   //instanciando um novo objeto da classe usuário
        Usuario usuario = new Usuario(
                cursor.getInt(cursor.getColumnIndex(Conecta.Usuario._ID)),
                cursor.getString(cursor.getColumnIndex(Conecta.Usuario.NOME)),
                cursor.getString(cursor.getColumnIndex(Conecta.Usuario.LOGIN)),
                cursor.getString(cursor.getColumnIndex(Conecta.Usuario.SENHA)));

                return usuario;
    }
                                                                                                   //Maneira de Listar Todos os Usuarios do Banco de Dados

    public List<Usuario> ListaUsuario() {

                                                                                                   //Gerando um Cursor ( Quase que tem os QUERYs)
        Cursor cursor = getDataBase().query(Conecta.Usuario.TABELA,                                //Cursor ta executando essa seleção que tá sendo feita
                Conecta.Usuario.COLUNAS, null, null, null, null, null);


                                                                                                   //Criando uma Lista de Usuários
        List<Usuario> usuarios = new ArrayList<Usuario>();

        while (cursor.moveToNext()) {                                                              //Enquanto o cursos se mover para o proximo ou outra posição

            Usuario modelo = criarUsuario(cursor);                                                 //criando um novo objeto e passando parametros do cursor
            usuarios.add(modelo);                                                                  //adcionando um novo usuario a lista

        }

        cursor.close();                                                                            //fechando o cursor e retornando para usuarios
        return usuarios ;

    }

                                                                                                   //Maneira de Salvar os dados no Banco de Dados seja eles não existente ou já existentes

                                                                                                   //Criando um novo metodo e passando um novo objeto da classe usuario
    public long salvarUsuario(Usuario usuario) {


                                                                                                   //Classe que recebe os valores que serão salvos no banco de dados
                                                                                                   //Tem que informar O nome da Coluna e a Tabela e tambem as informações que deseja salvar nessa Tabela
        ContentValues valores = new ContentValues();

                                                                                                   //Metodo PUT recebe por parametro os tipos de dados String Key e Short Value
        valores.put(Conecta.Usuario.NOME, usuario.getNome());
        valores.put(Conecta.Usuario.LOGIN, usuario.getLogin());
        valores.put(Conecta.Usuario.SENHA, usuario.getSenha());

        if (usuario.get_id() != null) {
            return sqLiteDatabase.update(Conecta.Usuario.TABELA , valores, "_id = ?" ,
                    new String[]{usuario.get_id().toString()}    );

        }
        return getDataBase().insert(Conecta.Usuario.TABELA , null , valores) ;


    }

                                                                                                   //Maneira de REMOVER os dados no Banco de Dados seja eles não existente ou já existentes

                                                                                                   //REMOVENDO USUARIOS DE ACORDO COM O ID
    public boolean removerUsuario(int id){

        return getDataBase().delete(Conecta.Usuario.TABELA , "_id = ?" , new String[]{(Integer.toString(id))}) > 0 ;
    }


                                                                                                   //BUSCANDO USUARIO DE ACORDO COM O ID
    public Usuario buscarUsuario(int id){
        Cursor cursor = getDataBase().query(Conecta.Usuario.TABELA , Conecta.Usuario.COLUNAS , "_id = ?" , new String[]{Integer.toString(id)} , null , null , null) ;

        List<Usuario> usuarios = new ArrayList<Usuario>() ;

        while(cursor.moveToNext()){
            Usuario modelo = criarUsuario(cursor) ;
            usuarios.add(modelo);
            cursor.close();
            return modelo ;
        }
            return null;                                                                           //fazendo Casting para o Tipo Usuário
    }

    public boolean logar (String usuario , String senha) {                                         //Criando novo objeto e recebendo parametros do "loginActivity"
        Cursor cursor = getDataBase().query(Conecta.Usuario.TABELA, null, "LOGIN = ? AND SENHA = ?", new String[]{usuario, senha}, null, null, null);

        if (cursor.moveToFirst()) {                                                                //Procura e Retorna o Primeiro que for encontrado
            return true;

        } else {
            return false;
        }


    }

}
