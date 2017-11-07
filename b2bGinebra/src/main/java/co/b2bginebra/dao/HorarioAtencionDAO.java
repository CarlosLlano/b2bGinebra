package co.b2bginebra.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.b2bginebra.dao.api.JpaDaoImpl;
import co.b2bginebra.modelo.HorarioAtencion;


@Stateless
public class HorarioAtencionDAO extends JpaDaoImpl<HorarioAtencion, Long>{

    @PersistenceContext
    private EntityManager entityManager;
    
    public HorarioAtencionDAO()
	{
		super(HorarioAtencion.class);
	}
}
