package co.b2bginebra.dao;




import co.b2bginebra.dao.api.JpaDaoImpl;
import co.b2bginebra.modelo.Oferta;

import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class OfertaDAO extends JpaDaoImpl<Oferta, Long>{

    @PersistenceContext
    private EntityManager entityManager;
}
