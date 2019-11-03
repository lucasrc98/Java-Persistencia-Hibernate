package br.com.persistencia.java;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

//    Classe do JPA que permite a conexão com o Banco
    private static EntityManagerFactory entityManagerFactory;

    public static void main (String args[]){

/*  Instanciando EntityManeger passando com parametro o nome do database */
        entityManagerFactory = Persistence.createEntityManagerFactory("hibernatejpa");
    }
}
