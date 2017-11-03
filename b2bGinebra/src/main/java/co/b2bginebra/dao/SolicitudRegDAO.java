package co.b2bginebra.dao;




import co.b2bginebra.dao.api.JpaDaoImpl;

import co.b2bginebra.modelo.SolicitudReg;

import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class SolicitudRegDAO extends JpaDaoImpl<SolicitudReg, Long>{

    @PersistenceContext
    private EntityManager entityManager;
}
