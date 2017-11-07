package co.b2bginebra.dao;



import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.b2bginebra.dao.api.JpaDaoImpl;
import co.b2bginebra.modelo.Notificacion;


@Stateless
public class NotificacionDAO extends JpaDaoImpl<Notificacion, Long>{

    @PersistenceContext
    private EntityManager entityManager;
    
    public NotificacionDAO()
	{
		super(Notificacion.class);
	}
}
