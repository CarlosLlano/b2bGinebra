package co.b2bginebra.dao;

import co.b2bginebra.modelo.Estado;
import co.b2bginebra.dao.api.JpaDaoImpl;


import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class EstadoDAO extends JpaDaoImpl<Estado, Long>{

	@PersistenceContext
	private EntityManager entityManager;

	public Estado consultarEstadoPorNombre(String nombreEstado)
	{
		String jpql = "SELECT e FROM Estado e WHERE e.nombre=:nombreEstado";
		return entityManager.createQuery(jpql, Estado.class).setParameter("nombreEstado", nombreEstado).getSingleResult();

	}
}
