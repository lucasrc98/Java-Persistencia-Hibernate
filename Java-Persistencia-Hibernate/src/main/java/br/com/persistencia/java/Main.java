package br.com.persistencia.java;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Main {

//    Classe do JPA que permite a conexão com o Banco
    private static EntityManagerFactory entityManagerFactory;

    public static void main (String args[]){

/*  Instanciando EntityManeger passando com parametro o nome do database */
        entityManagerFactory = Persistence.createEntityManagerFactory("hibernatejpa");


        Lembrete lembrete1 = new Lembrete();
        lembrete1.setTitulo("Escrever artigo");
        lembrete1.setDescricao("Hoje, 12:00");

//        Inserindo

        EntityManager em = entityManagerFactory.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(lembrete1);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();

            System.out.println("INSERT " + e.getMessage());
        }finally {

            em.close();
        }

//        Listando
//        List <Lembrete> lembretes = null;
//
//        EntityManager em = entityManagerFactory.createEntityManager();
//
//        try {
//            lembretes = em.createQuery("from Lembrete ") .getResultList();
//
//        }catch (Exception e){
//            System.out.println("List ALL: " + e.getMessage());
//        }finally {
//            em.close();
//        }
//
//        if(lembretes != null){
//            lembretes.forEach(System.out::println);
//        }


    }
}
