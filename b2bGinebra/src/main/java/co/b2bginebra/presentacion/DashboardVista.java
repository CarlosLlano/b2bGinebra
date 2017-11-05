package co.b2bginebra.presentacion;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import co.b2bginebra.logica.TipoNegocioLogica;
import co.b2bginebra.modelo.TipoNegocio;
import co.b2bginebra.presentacion.api.StatTipoNegocio;

/**
 * representa la vista destinada a mostrar estadisticas al administrador
 * las estadisticas son:
 * 	1.porcentaje de negocios REGISTRADOS EN LA PLATAFORMA por cada tipo de negocio
 * 		ej: 30% hoteles y restaurantes, 20% transporte, etc
 * 	2.porcentaje de negocios REGISTRADOS EN LA BASE DE DATOS de la alcaldia por cada tipo de negocio
 * El objetivo es ayudar a determinar el uso de la plataforma y tambien tener una idea general de la distribucion economica del municipio
 */
@ManagedBean
@ViewScoped
public class DashboardVista 
{

	private List<TipoNegocio> losTipoNegocio;
	private List<StatTipoNegocio> statTipoNegocios;
	@EJB
	private TipoNegocioLogica tipoNegocioLogica;
	
	
	@PostConstruct
	public void init()
	{
		getLosTipoNegocio();
		
		//creo las clases estadisticas
		statTipoNegocios = new ArrayList<StatTipoNegocio>();
		for (TipoNegocio tipoNegocio : losTipoNegocio) 
		{
			statTipoNegocios.add(new StatTipoNegocio(tipoNegocio.getNombre(), tipoNegocio.getNegocios().size()));
		} 
	} 
	
	
	public List<TipoNegocio> getLosTipoNegocio() 
	{
		try 
		{
			if(losTipoNegocio==null)
			{
				losTipoNegocio = tipoNegocioLogica.consultarTodos();
			}
		} 
		catch (Exception e) 
		{
			
		}
		return losTipoNegocio;
	}
	public void setLosTipoNegocio(List<TipoNegocio> losTipoNegocio) {
		this.losTipoNegocio = losTipoNegocio;
	}
	
	
	
	
	
}


