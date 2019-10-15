package com.example.aluno.c_r_u_d__do__xk;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import Adapter.UsuarioAdapter;
import BD.UsuarioBD;
import Interecao.Usuario;
import util.Mensagem;

public class ListarUsuariosActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, DialogInterface.OnClickListener{

    private ListView lista ;
    private List<Usuario> usuarioList ;
    private UsuarioAdapter usuarioAdapter ;
    private UsuarioBD usuarioBD  ; //no slide é UsuarioBD
    private int idPosicao;
    private AlertDialog alertDialog, alertConfirmacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_usuarios);

        //ESTA PARTE NÃO ESTA NO SLIDE, É É UMA PARTA IMPOERTANMTE
        alertDialog = Mensagem.criarAlertDialog(this);
        alertConfirmacao = Mensagem.CriarDialogConfirmacao(this, "Sair",
                "Deseja Realmente Sair?", R.drawable.sair,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

        usuarioBD =  new UsuarioBD(this) ;
        usuarioList = usuarioBD.ListaUsuario() ;
        usuarioAdapter = new UsuarioAdapter(this , usuarioList) ; // ta passando a lista do banco de dados para o adaptador

        lista = (ListView) findViewById(R.id.lvUsuario);
        lista.setAdapter(usuarioAdapter);
        lista.setOnItemClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cadastrar_usuario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.activity_cadastro_usuario){
            startActivity(new Intent(this, CadastroUsuarioActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    // metodos implementados pelas interfaces "AdapterView.OnClickListener" e "DialogInterface.OnCancelListener" que foram implementados...
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        idPosicao = position;
        alertDialog.show();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        int id = usuarioList.get(idPosicao).get_id();

        switch(which){
            case 0: //EDITAR
                Intent intent = new Intent(this, CadastroUsuarioActivity.class);
                intent.putExtra("USUARIO_ID", id);
                startActivity(intent);
                finish();
                break;

            case 1:     //EXCLUIR
                alertConfirmacao.show();
                break;

            case DialogInterface.BUTTON_POSITIVE:
                usuarioList.remove(idPosicao);  //Remove apenas da lista
                usuarioBD.removerUsuario(id); //remove BD
                lista.invalidateViews(); //atualiza
                break;

            case DialogInterface.BUTTON_NEGATIVE:
                alertConfirmacao.dismiss();
                break;
        }
    }
}
