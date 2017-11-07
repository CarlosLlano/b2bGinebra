package co.b2bginebra.dao;




import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.b2bginebra.dao.api.JpaDaoImpl;
import co.b2bginebra.modelo.Usuario;


@Stateless
public class UsuarioDAO extends JpaDaoImpl<Usuario, Long>{

    @PersistenceContext
    private EntityManager entityManager;
    
    
    public UsuarioDAO()
   	{
   		super(Usuario.class);
   	}
    
    public Usuario consultarUsuarioPorIdentificacion(String identificacion)
    {
    		String jpql = "SELECT usu FROM Usuario usu WHERE usu.identificacion=:identificacion";
		return entityManager.createQuery(jpql, Usuario.class).setParameter("identificacion", identificacion).getSingleResult();
    }
}
