package co.b2bginebra.dao;

import co.b2bginebra.modelo.HorarioAtencion;
import co.b2bginebra.dao.api.JpaDaoImpl;


import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class HorarioAtencionDAO extends JpaDaoImpl<HorarioAtencion, Long>{

    @PersistenceContext
    private EntityManager entityManager;
}
