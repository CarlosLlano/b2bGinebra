package co.b2bginebra.dao;



import co.b2bginebra.modelo.Notificacion;
import co.b2bginebra.dao.api.JpaDaoImpl;


import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class NotificacionDAO extends JpaDaoImpl<Notificacion, Long>{

    @PersistenceContext
    private EntityManager entityManager;
}
