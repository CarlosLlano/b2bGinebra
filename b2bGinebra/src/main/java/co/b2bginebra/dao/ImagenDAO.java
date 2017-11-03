package co.b2bginebra.dao;


import co.b2bginebra.modelo.Imagen;
import co.b2bginebra.dao.api.JpaDaoImpl;


import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class ImagenDAO extends JpaDaoImpl<Imagen, Long>{

    @PersistenceContext
    private EntityManager entityManager;
    
}
