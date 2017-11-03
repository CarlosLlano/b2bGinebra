package co.b2bginebra.dao;




import co.b2bginebra.dao.api.JpaDaoImpl;

import co.b2bginebra.modelo.TipoNot;

import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class TipoNotDAO extends JpaDaoImpl<TipoNot, Long>{

    @PersistenceContext
    private EntityManager entityManager;
}
