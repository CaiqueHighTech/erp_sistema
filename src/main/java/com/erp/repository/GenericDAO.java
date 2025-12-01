package com.erp.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Classe base para o padrão Data Access Object (DAO) com Hibernate.
 * Esta classe é responsável por fornecer operações CRUD genéricas.
 * A SessionFactory será injetada pelo Spring.
 */
public abstract class GenericDAO<T, ID extends Serializable> {

    private SessionFactory sessionFactory;
    private Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    public GenericDAO() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    // Setter para injeção de dependência do Spring
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getSession() {
        // Usando openSession() para simplificar o controle de transação manual
        return sessionFactory.openSession();
    }

    @SuppressWarnings("unchecked")
    public T findById(ID id) {
        Session session = getSession();
        session.beginTransaction();
        T entity = (T) session.get(persistentClass, id);
        session.getTransaction().commit();
        session.close();
        return entity;
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        Session session = getSession();
        session.beginTransaction();
        List<T> entities = session.createQuery("from " + persistentClass.getName()).list();
        session.getTransaction().commit();
        session.close();
        return entities;
    }

    public T save(T entity) {
        Session session = getSession();
        session.beginTransaction();
        session.saveOrUpdate(entity);
        session.getTransaction().commit();
        session.close();
        return entity;
    }

    public void delete(T entity) {
        Session session = getSession();
        session.beginTransaction();
        session.delete(entity);
        session.getTransaction().commit();
        session.close();
    }
}
