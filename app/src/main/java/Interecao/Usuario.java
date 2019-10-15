package Interecao;

import android.content.Intent;

/**
 * Created by Rafael Martins 09/11/2016.
 */

public class Usuario {

                                                                                                   // criando os atributos
    private Integer _id ;
    private String nome, login, senha ;



                                                                                                   // criando o metodo construtor que tem o mesmo nome da classe
    public  Usuario (){                                                                            // fica vazio por enquanto



    }
                                                                                                   //criando o mesmo metodo construtor, mas este recebe parametros
    public Usuario(Integer id, String nome, String login, String senha ){

                                                                                                   //variavel da classe vai receber a variavel que está vindo por parametro (This faz referencia a classe)
        this._id = id ;
        this.nome = nome ;
        this.login = login ;
        this.senha = senha ;


    }




    //metodos gerados Automaticamente pelo Gets And Seter (IR em -> CODE - GENERATE - GET AND SETER )
    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


}
