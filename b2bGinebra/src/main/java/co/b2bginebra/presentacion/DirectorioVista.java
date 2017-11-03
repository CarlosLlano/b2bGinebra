package co.b2bginebra.presentacion;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import co.b2bginebra.logica.NegocioLogica;
import co.b2bginebra.logica.TipoNegocioLogica;
import co.b2bginebra.modelo.Negocio;
import co.b2bginebra.modelo.TipoNegocio;


/**
 * representa la vista destinada a mostrar todos los negocios
 * La vista contiene un filtro de busqueda por tipo de negocio
 */

@ManagedBean
@ViewScoped
public class DirectorioVista 
{

	private List<TipoNegocio> tipoNegocios;
	private List<Negocio> negocios;

	@EJB
	private TipoNegocioLogica tipoNegocioLogica;
	@EJB
	private NegocioLogica negocioLogica;


	public List<TipoNegocio> getTipoNegocios() 
	{
		try 
		{
			if(tipoNegocios==null)
			{
				tipoNegocios = tipoNegocioLogica.consultarTodos();
			}
		} 
		catch (Exception e) 
		{

		}
		return tipoNegocios;
	}
	public void setTipoNegocios(List<TipoNegocio> tipoNegocios) {
		this.tipoNegocios = tipoNegocios;
	}
	public List<Negocio> getNegocios() 
	{
		try 
		{
			if(negocios==null)
			{
				negocios = negocioLogica.consultarTodos();
			}
		} 
		catch (Exception e) 
		{

		}
		return negocios;
	}
	public void setNegocios(List<Negocio> negocios) {
		this.negocios = negocios;
	}



}
