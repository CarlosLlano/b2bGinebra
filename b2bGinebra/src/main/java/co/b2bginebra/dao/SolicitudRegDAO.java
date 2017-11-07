package co.b2bginebra.dao;




import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.b2bginebra.dao.api.JpaDaoImpl;
import co.b2bginebra.modelo.SolicitudReg;


@Stateless
public class SolicitudRegDAO extends JpaDaoImpl<SolicitudReg, Long>{

    @PersistenceContext
    private EntityManager entityManager;
    
    public SolicitudRegDAO()
	{
		super(SolicitudReg.class);
	}
}
