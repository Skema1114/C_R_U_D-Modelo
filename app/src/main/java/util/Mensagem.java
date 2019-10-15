package util;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

/**
 * Created by Rafael Martins on 10/11/2016.
 */

public class Mensagem {

// criando um novo metodo que irá receber os parametros das outras classes e fará toda a interação
    public static void Msg (Activity activity , String mensagem){                   // por estar com o 'void' não retorna nada
        Toast.makeText(activity , mensagem , Toast.LENGTH_LONG).show();             //NÃO PODE ESQUECER DO ' .SHOW()'
    }

    public static void MsgOk(Activity activity, String titulo, String mensagem, int icone){
        AlertDialog.Builder dlg = new AlertDialog.Builder(activity);
        dlg.setTitle(titulo);
        dlg.setMessage(mensagem);
        dlg.setNeutralButton("OK", null);
        dlg.setIcon(icone);
        dlg.show();
    }

    public static AlertDialog criarAlertDialog(Activity activity){
        final CharSequence[] items = {
                "Editar",
                "Excluir"
        };
        AlertDialog.Builder dlg = new AlertDialog.Builder(activity);
        dlg.setTitle("Opções");
        dlg.setItems(items, (DialogInterface.OnClickListener) activity);

        return dlg.create();
    }

    public static AlertDialog CriarDialogConfirmacao (Activity activity, String sair, String s, int i, DialogInterface.OnClickListener onClickListener){
        AlertDialog.Builder dlg = new AlertDialog.Builder(activity);
        dlg.setMessage("Deseja Realmente Excluir?");
        dlg.setPositiveButton("Sim", (DialogInterface.OnClickListener) activity);
        dlg.setNegativeButton("Não", (DialogInterface.OnClickListener) activity);

        return dlg.create();
    }

    public static void MsgConfirmar (Activity activity, String titulo, String mensagem, int icone, DialogInterface.OnClickListener listener){
        AlertDialog.Builder dlg = new AlertDialog.Builder(activity);
        dlg.setTitle(titulo);
        dlg.setMessage(mensagem);
        dlg.setIcon(icone);
        dlg.setPositiveButton("Sim", listener);
        dlg.setNegativeButton("Não", null);
        dlg.show();
    }
}
