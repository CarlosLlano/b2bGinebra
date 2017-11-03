package co.b2bginebra.dao;




import co.b2bginebra.dao.api.JpaDaoImpl;
import co.b2bginebra.modelo.TipoEstado;

import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class TipoEstadoDAO extends JpaDaoImpl<TipoEstado, Long>{

    @PersistenceContext
    private EntityManager entityManager;
}
