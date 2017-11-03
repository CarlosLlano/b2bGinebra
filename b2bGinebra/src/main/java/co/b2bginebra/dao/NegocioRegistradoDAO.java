package co.b2bginebra.dao;


import co.b2bginebra.modelo.NegocioRegistrado;
import co.b2bginebra.dao.api.JpaDaoImpl;


import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class NegocioRegistradoDAO extends JpaDaoImpl<NegocioRegistrado, Long>{

    @PersistenceContext
    private EntityManager entityManager;
    
    public boolean estaRegistradoNegocioConUsuario(String razonSocial, String docRepr)
    {
    		boolean respuesta = false;
    		String jpql = "SELECT n FROM NegocioRegistrado n WHERE n.razonSocial=:razonSocial AND n.docRepr=:docRepr" ;
		NegocioRegistrado negReg =  entityManager.createQuery(jpql, NegocioRegistrado.class).setParameter("razonSocial", razonSocial)
				.setParameter("docRepr", docRepr).getSingleResult();
		if(negReg != null)
		{
			respuesta = true; 
		}
		return respuesta;
    }
}
