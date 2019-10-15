package com.example.aluno.c_r_u_d__do__xk;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import util.Mensagem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

            public boolean onCreateOptionsMenu (Menu menu){

            getMenuInflater().inflate(R.menu.main, menu); // comando para mostrar os menus
            return true;

        }

        public boolean onOptionsItemSelected (MenuItem item){
            int id = item.getItemId();

            switch (id) {
                case R.id.action_listar_usuarios:
                    startActivity(new Intent(this , ListarUsuariosActivity.class));
                    break ;

                case R.id.action_cadastrar_usuarios:
                    startActivity(new Intent(this, CadastroUsuarioActivity.class));
                    break;

                case R.id.activity_list_sair:
                    Mensagem.MsgConfirmar(this, "Sair", "Deseja realmente Sair do Programa?", R.drawable.sair, new DialogInterface.OnClickListener(){

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //finish();
                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            finish();
                            startActivity(intent);
                        }
                    });
                    break;
                case R.id.activity_list_logout:
                    SharedPreferences preferences = getSharedPreferences("LoginActivityPreferences", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.clear();
                    editor.commit();
                    startActivity(new Intent(this, LoginActivity.class));
                    finish();
                    break;

            }
            return super.onOptionsItemSelected(item);

        }

}

