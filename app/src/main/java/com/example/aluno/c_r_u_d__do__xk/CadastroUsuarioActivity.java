package com.example.aluno.c_r_u_d__do__xk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import BD.UsuarioBD;
import Interecao.Usuario;
import util.Mensagem;

public class CadastroUsuarioActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText usuario_editNome ;
    private EditText usuario_informeLogin ;
    private EditText usuario_informeSenha ;

        private Usuario usuario ;
        private UsuarioBD usuarioBD ;
            private int idUsuario ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        usuarioBD = new UsuarioBD(this) ;                                                          // Atribuindo a activity atual ao usuárioBD

        usuario_editNome = (EditText) findViewById(R.id.usuario_editNome) ;
        usuario_informeLogin = (EditText) findViewById(R.id.usuario_informeLogin) ;
        usuario_informeSenha = (EditText) findViewById(R.id.usuario_informeSenha) ;

        idUsuario = getIntent().getIntExtra("USUARIO_ID", 0);

        if(idUsuario > 0){
            Usuario model = usuarioBD.buscarUsuario(idUsuario);
            usuario_editNome.setText(model.getNome());
            usuario_informeLogin.setText(model.getLogin());
            usuario_informeSenha.setText(model.getSenha());

            setTitle(R.string.atualizar_usuario);
        }
    }

    protected void onDestoy(){
        usuarioBD.fechar();
            super.onDestroy();
    }

    public void cadastrar(){

        boolean validacao = true ;

        String nome = usuario_editNome.getText().toString() ;
        String login = usuario_informeLogin.getText().toString() ;
        String senha = usuario_informeSenha.getText().toString() ;

        if((nome == null) || (nome.equals(""))){
            validacao = false ;
                usuario_editNome.setError(getString(R.string.obrigatorio));
        }

        if((login == null) || (login.equals(""))){
            validacao = false ;
                usuario_informeLogin.setError(getString(R.string.obrigatorio));
        }

        if((senha == null) || (senha.equals(""))){
                validacao = false ;
                     usuario_informeSenha.setError(getString(R.string.obrigatorio));

        }

        if(validacao){
            usuario = new Usuario() ;
                    usuario.setNome(nome);
                    usuario.setLogin(login);
                    usuario.setSenha(senha);

                                                                                                   // Se for alteração
            if(idUsuario >0){
                usuario.set_id(idUsuario);
            }

            long resultado = usuarioBD.salvarUsuario(usuario) ;

                if(resultado != -1){
                    if(idUsuario >0){
                        Mensagem.Msg(this ,getString(R.string.mensagem_atualizar));

                                                                                                   // Se for Cadastrar
                    }else{
                        Mensagem.Msg(this , getString(R.string.mensagem_cadastrar));
                    }
                        finish();
                        startActivity(new Intent(this , MainActivity.class));


                                                                                                   // Se for dar algum Erro
                }else{
                    Mensagem.Msg(this , getString(R.string.mensagem_erro));
                }
        }
    }

                                                                                                   // Ctriando novos metodos para lidar com os botões

    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.menu , menu);                                             // comando para mostrar os menus

        if(idUsuario > 0){                                                                         // se o If for maior que zero, no menu já mostra a opçõa excluir
            menu.findItem(R.id.action_menu_excluir).setVisible(true);

        }
        return true ;

    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId() ;

        switch (id){
            case R.id.action_menu_salvar:
                this.cadastrar();
                break;

            case R.id.action_menu_sair:
                finish();
                    startActivity(new Intent(this , MainActivity.class));
        }
        return super.onOptionsItemSelected(item) ;

    }





    @Override
    public void onClick(View v) {



    }
}
