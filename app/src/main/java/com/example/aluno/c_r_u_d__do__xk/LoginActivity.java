package com.example.aluno.c_r_u_d__do__xk;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import BD.UsuarioBD;
import Interecao.Usuario;
import util.Mensagem;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

                                                                                                //Inicializando os novos objetos
    private EditText editLoginNome;
    private EditText editLoginSenha;
    private Button btnFaceBook;
    private Button btnLoginEntrar;

    private CheckBox checkBoxLogin;


                                                                                                 //Criando as contantes
    private static final String MANTER_CONECTADO = "manter conectado";

    private static final String PREFERENCE_NAME = "LoginActivityPreferences";


                                                                                                // Criando um objeto da classe criada pro banco de dados.
    private UsuarioBD helper;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editLoginNome = (EditText) findViewById(R.id.editLoginNome);
        editLoginSenha = (EditText) findViewById(R.id.editLoginSenha);

        btnLoginEntrar = (Button) findViewById(R.id.btnLoginEntrar);
        btnFaceBook = (Button) findViewById(R.id.btnLoginEntrar);

        btnLoginEntrar.setOnClickListener(this);
        btnFaceBook.setOnClickListener(this);

        helper = new UsuarioBD(this);                                                           // Criando e Iniciando o Metodo Helper dentro do ON-CREATE

        checkBoxLogin = (CheckBox) findViewById(R.id.checkBoxLogin);


                                                                                                 // Irá Pegar o Arquivo de Preferencia que foi salvo e testar
        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);


                                                                                                //Caso não tenha encontrado nada no 'Manter_Conectado' será Atribuido o Valor Falso
        boolean conectado = sharedPreferences.getBoolean(MANTER_CONECTADO, false);


        if (conectado) {
            chamarMainActivity();                                                                // Se o teste for correto, irá chamar a Activity Desejada.
        }

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

                                                                                                // Criando um Metodo pra a partir de uma activity atual- chamar uma outra e encerrar a que chamou.
    public void chamarMainActivity() {
        startActivity(new Intent(this, MainActivity.class));                                       // comando para iniciar a Activity que recebe 2 parametros( qual local que tá chamando ,  e qual tela que tá sendo chamada )
        finish();                                                                                  // finalizando a activity que está chamando.

    }


    //----------------------------------------------------------------------------------------------


                                                                                                //Metodo Criado Automaticamente atravez do Implements View.On....
    @Override
    public void onClick(View v) {
                                                                                            //chamando o metodo Logar
        logar();


    }


                                                                                                //Criando um Novo Metodo para Logar
    public void logar() {

        String usuario = editLoginNome.getText().toString();                                // Criando variaveis que recebem o que foi digitado nos EditTexts
        String senha = editLoginSenha.getText().toString();

        boolean validacao = true;                                                               // criando um nova variavel que já começa com um valor definido.


                                                                                                // Fazendo o teste logico e testanto se o usuario não digitou nada ou deixou nulo
        if ((usuario == null) || (usuario.equals(""))) {
            validacao = false;
            editLoginNome.setError(getString(R.string.obrigaLogin));                            // mostrando de erro criado no <String>

        }
        if ((usuario == null) || (usuario.equals(""))) {
            validacao = false;
            editLoginSenha.setError(getString(R.string.obrigaSenha));

        }
        if (validacao) {                                                                        // A condição assim, significa que é Verdadeiro, pois no inicio do codigo da classe foi definido como True.

                                                                                                 // Se a validação tiver sido POSITIVA irá fazer os comando abaixo e enviar os parametros para a classe UsuárioDB
            if (helper.logar(usuario, senha)) {
                chamarMainActivity();

                if (checkBoxLogin.isChecked()) {
                                                                                            // Cria um arquivo de preferencia, onde é passado um nome para o arquivo e um tipo ou modo -> recomenda-se deixar privado
                    SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE); // Criando um nome objeto da classe SharedPreferences e usando um metodo que pega as preferencias e atribui neste objeto criado.
                    SharedPreferences.Editor editor = sharedPreferences.edit();             // Criando um novo objeto do tipo editor para fazer as edições no arquivo de preferencia

                    editor.putBoolean(MANTER_CONECTADO, true);                              // chamando o objeto criado 'editor' e deixando ele atravez do PUTBLOOLEAN como sempre conectado - utiliza de parametro (o nome da preferencia e o tipo( true ou false))
                    editor.commit();                                                            // comando para executar/enviar o que foi criado.
                }
            } else {
                                                                                            // Se o Login for Incorreto ele cai nesta condição, E chama o metodo mensagem - o objeto criado - por parametros recebe obrigatoriamente
                                                                                            // (A Activity que deseja que apareça a mensagem neste caso usa-se THIS pois é nesta activity atual 'LoginActivity', com o comando 'getString' está sendo
                                                                                            // setada a mensagem -> usa-se a Classe 'R' -  o Tipo de dados(String) e o metodo que foi criado com as mensagens)
                Mensagem.Msg(this, getString(R.string.msgLoginIncorreto));
            }
        } else {
            Mensagem.Msg(this, getString(R.string.msgLoginIncorreto));
        }

    }

}
