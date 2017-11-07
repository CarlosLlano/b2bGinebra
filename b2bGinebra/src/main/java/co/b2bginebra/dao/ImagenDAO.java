package co.b2bginebra.dao;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.b2bginebra.dao.api.JpaDaoImpl;
import co.b2bginebra.modelo.Imagen;


@Stateless
public class ImagenDAO extends JpaDaoImpl<Imagen, Long>{

    @PersistenceContext
    private EntityManager entityManager;
    
    public ImagenDAO()
	{
		super(Imagen.class);
	}
    
}
