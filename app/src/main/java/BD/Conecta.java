package BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Rafael Martins on 03/11/2016.
 */

public class Conecta extends SQLiteOpenHelper {                                                    // Implementa esta classe pronta do banco de dados , que vai obrigar a criar dois novos methodos.


    private final static String BANCO_DE_DADOS = "Crud" ;                                          // "final" cria uma constante  do tipo string -> define o nome do banco.

    private final static int VERSAO = 1 ;                                                          // definindo a versão do banco do tipo inteiro  // Static Não precisa Instanciar


                                                                                                   // ALT + ENTER é pra importar a ação desejada

    public Conecta(Context context){                                                               // construtor é um objeto com o mesmo nome da classe -> Criando um novo objeto do tipo "Context"


        super(context, BANCO_DE_DADOS,null,VERSAO) ;                                               // "super'=> Metodo para fazer a conexeção com o banco de dados que recebe 4 parametros: O(contexto) objeto criando anteriormente, o nome do banco, um ponteiro que neste caso é nulo e a Versão do banco

    }



                                                                                                   // Metosdos implementados obrigatoriamente pelo 'extends SQLiteOpenHelper'

    @Override
    public void onCreate(SQLiteDatabase db) {                                                      // recebe uma instacia "DB" da clase SQLiteDatabase

                                                                                                   // Tabela Usuários

        String sql = "create table if not exists usuarios (_id integer primary key " + "autoincrement, nome text not null, login text not null, " + "senha text not null)";  // criando uma nova tabela no banco de dados e criando algumas lacunas e dados.
                                                                                                   //Atribuindo os valores criando para "sql"

        db.execSQL(sql);                                                                           // executa o sql que foi recem criado ( no parametro vai o nome que recem foi criado)

        sql = "insert into usuarios(nome,login, senha) values ('Admin', 'admin', 'admin')";        //criando algumas lacunas e dados no banco de dados

        db.execSQL(sql);

                                                                                                   // Criando uma sub-classe do tipo Statica e Atribuindo Dados a ela










    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
                                                                                                   // Criando uma sub-classe do tipo Statica e Atribuindo Dados a ela
                                                                                                   // Criando novas constantes do tipo estatico que não precisa instanciar e Definindo o Nome pra elas "em maisculo por padrão" e atribuindo oq foi criado no banco anterior ( exatamente com o mesmo nome)

    public static class Usuario{
        public static final String TABELA = "usuarios" ;

        public static final String _ID = "_id" ;

        public static final String NOME = "nome" ;

        public static final String LOGIN = "login" ;

        public static final String SENHA = "senha" ;


                                                                                                   // criando uma sub-claase para manipular os valores do banco de dados
                                                                                                   // utilizando atravez de um vetor chamado colunas
                                                                                                   // é informado nesse vetor os campos que se deseja manipular ( tem que ser igual os criado no banco de dados e os campos criandos anteriormente
        public  final static String [] COLUNAS  = new String[]{

                _ID, NOME, LOGIN, SENHA

        };
    }
}
