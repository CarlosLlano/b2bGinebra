package co.b2bginebra.presentacion;

import java.io.ByteArrayInputStream;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import co.b2bginebra.logica.NegocioLogica;
import co.b2bginebra.logica.TipoNegocioLogica;
import co.b2bginebra.modelo.Negocio;
import co.b2bginebra.modelo.ParametroSistema;
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
	
	private Negocio negocioSeleccionado;



	public StreamedContent getImage() throws Exception 
	{
		FacesContext context = FacesContext.getCurrentInstance();

		String negocioId = context.getExternalContext().getRequestParameterMap().get("negocioId");

		if (negocioId == null) {
			
			return new DefaultStreamedContent();
		}

		Negocio negocio = negocioLogica.consultarNegocio(Long.valueOf(negocioId));

		return new DefaultStreamedContent(new ByteArrayInputStream(negocio.getImgPrincipal()));
	}
	
	
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
			//handle error
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
			//handle error
		}
		return negocios;
	}
	public void setNegocios(List<Negocio> negocios) {
		this.negocios = negocios;
	}
	
	public void seleccionarNegocio(SelectEvent event)
	{
		negocioSeleccionado = (Negocio)event.getObject();
		
	}
	
	public Negocio getNegocioSeleccionado() {
		return negocioSeleccionado;
	}


	public void setNegocioSeleccionado(Negocio negocioSeleccionado) {
		this.negocioSeleccionado = negocioSeleccionado;
	}




}
