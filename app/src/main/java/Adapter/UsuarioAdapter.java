package Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.aluno.c_r_u_d__do__xk.R;

import java.util.List;

import Interecao.Usuario;

/**
 * Created by Rafael Martins on 16/11/2016.
 */

public class UsuarioAdapter extends BaseAdapter{                                                   //extendendo a classe para implantar automaticamente os metodos.

                                                                                                   //Criando novos objetos
    private Context context ;
    private List<Usuario> lista ;


                                                                                                   //Criando o metodo construtor
    public UsuarioAdapter(Context ctx , List<Usuario> usuarios){                                   //Recebendo por parametro e setando.
        this.context = ctx ;
        this.lista = usuarios ;
    }


                                                                                                   //criandos automaticmaente pela "BaseAdapter"
    @Override
    public int getCount() {
        return lista.size() ;                                                                      //pega o valor de tamanho total da lista
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);                                                                //pegando da lista , algo de uma determinada posição que se quer
    }

    @Override
    public long getItemId(int position) {
        return position;                                                                           // recebe por parametro uma posição e retorna ela
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Usuario usuario = lista.get(position) ;

        if (convertView == null){                                                                  //testando um componente grafico que está sendo pego pelo "getView" acima

            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) ;

            convertView = inflater.inflate(R.layout.usuarios , null) ;
        }

        TextView textView = (TextView) convertView.findViewById(R.id.usuario_lista_nome) ;
            textView.setText(usuario.getNome());

        return convertView;
    }
}
