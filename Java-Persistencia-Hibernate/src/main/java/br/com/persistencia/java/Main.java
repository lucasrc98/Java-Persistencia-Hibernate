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

        Lembrete lembrete2 = new Lembrete();
        lembrete2.setTitulo("Enviar trab persistencia");
        lembrete2.setDescricao("Terça, 15:00");

        Lembrete lembrete3 = new Lembrete();
        lembrete3.setTitulo("Fazer matriz origem destino");
        lembrete3.setDescricao("Quarta, 21:00");

//  Inserindo
//      criarLembrete(Lembrete lembrete);

//  Listando
//      listarLembretes();

//  Buscando por id de Lembrete
//      buscarLembrete(1L);

//  Buscando Lembretes por determinado texto no titulo
//      buscarLembretePorTitulo("entregar");

    }

    public static void buscarLembretePorTitulo(String contexto){

        EntityManager em = entityManagerFactory.createEntityManager();
        List<Lembrete> lembretesEncontrados = null;

        try{

            lembretesEncontrados = em.createQuery("from Lembrete l where l.titulo like '%"+contexto+"%'").getResultList();

        }catch (Exception e){
            System.out.println("FIND BY TITULO " + e.getMessage());
        }finally {
            em.close();
        }

        if (!lembretesEncontrados.isEmpty()){
            lembretesEncontrados.forEach(System.out::println);
        }else
            System.out.println("Nenhum lembrete encontrado");
    }

    public static void buscarLembrete(Long idLembrete){

        EntityManager em = entityManagerFactory.createEntityManager();
        Lembrete lembreteEncontrado = null;

        try{
            lembreteEncontrado = em.find(Lembrete.class, idLembrete);
            System.out.println(lembreteEncontrado);

        }catch (Exception e){
            System.out.println("FIND: " + e.getMessage());
        }finally {
            em.close();
        }

        if(lembreteEncontrado != null){
            System.out.println(lembreteEncontrado);
        }else
            System.out.println("Nenhum lembrete encontrado");
    }

    public static void criarLembrete(Lembrete lembreteLocal){
        EntityManager em = entityManagerFactory.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(lembreteLocal);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();

            System.out.println("INSERT " + e.getMessage());
        }finally {

            em.close();
        }
    }

    public static void listarLembretes(){

        EntityManager em = entityManagerFactory.createEntityManager();
        List <Lembrete> lembretes = null;

        try {
            lembretes = em.createQuery("from Lembrete ") .getResultList();

        }catch (Exception e){
            System.out.println("List ALL: " + e.getMessage());
        }finally {
            em.close();
        }

        if(lembretes != null){
            lembretes.forEach(System.out::println);
        }else
            System.out.println("Nenhum lembrete encontrado");
    }

}
