package co.b2bginebra.presentacion;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import co.b2bginebra.logica.EstadoLogica;
import co.b2bginebra.logica.NegocioRegistradoLogica;
import co.b2bginebra.logica.SolicitudRegLogica;
import co.b2bginebra.modelo.Negocio;
import co.b2bginebra.modelo.SolicitudReg;
import co.b2bginebra.modelo.Usuario;

/**
 * representa la vista para la gestion de solicitudes
 *
 */

@ManagedBean
@ViewScoped
public class SolicitudesVista 
{

	private List<SolicitudReg> solicitudes;

	@EJB
	private SolicitudRegLogica solicitudRegLogica;
	@EJB
	private NegocioRegistradoLogica negocioRegistradoLogica;
	@EJB
	private EstadoLogica estadoLogica;

	private SolicitudReg solicitudRegSeleccionada;


	/**
	 * Comprueba si el usuario y el negocio de la solicitud seleccionada ya se encuentran registrados para aceptar inmediatamente.
	 */
	public void verificarSolicitud()
	{
		Negocio negocio = solicitudRegSeleccionada.getNegocio();
		Usuario usuario = negocio.getUsuario();
		
		FacesMessage mensaje = new FacesMessage();
		
		try 
		{
			boolean estaRegistrado = negocioRegistradoLogica.estaRegistradoNegocioConUsuario(usuario, negocio);
			
			
			if(estaRegistrado)
			{
				
				mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
				mensaje.setSummary("La solicitud puede ser aceptada");
			}
			else
			{
				mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
				mensaje.setSummary("Es necesario verificar los datos");		
			}
		} 
		catch (Exception e) 
		{
			mensaje.setSeverity(FacesMessage.SEVERITY_ERROR);
			mensaje.setSummary("Ocurrio un error");
		}
		finally
		{
			FacesContext.getCurrentInstance().addMessage("Mensaje", mensaje);
		}
		
	}

	public void aceptarSolicitud()
	{
		FacesMessage mensaje = new FacesMessage();
		try 
		{
			solicitudRegLogica.aceptar(solicitudRegSeleccionada);
			mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
			mensaje.setSummary("Solicitud aceptada correctamente");
		} 
		catch (Exception e) 
		{
			
			mensaje.setSeverity(FacesMessage.SEVERITY_ERROR);
			mensaje.setSummary("Ocurrio un error");
		}		
		finally
		{
			FacesContext.getCurrentInstance().addMessage("Mensaje", mensaje);
		}
	}

	public void rechazarSolicitud()
	{
		FacesMessage mensaje = new FacesMessage();
		try 
		{
			solicitudRegLogica.rechazar(solicitudRegSeleccionada);
			mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
			mensaje.setSummary("Solicitud rechazada correctamente");
		} 
		catch (Exception e) 
		{
			mensaje.setSeverity(FacesMessage.SEVERITY_ERROR);
			mensaje.setSummary("Ocurrio un error");
		}
		finally
		{
			FacesContext.getCurrentInstance().addMessage("Mensaje", mensaje);
		}

	}

	public List<SolicitudReg> getSolicitudes() {

		try 
		{
			if(solicitudes==null)
			{
				solicitudes = solicitudRegLogica.consultarTodos();
			}
		} 
		catch (Exception e) 
		{

		}
		return solicitudes;
	}

	public void setSolicitudes(List<SolicitudReg> solicitudes) {
		this.solicitudes = solicitudes;
	}

	public SolicitudReg getSolicitudRegSeleccionada() {
		return solicitudRegSeleccionada;
	}

	public void setSolicitudRegSeleccionada(SolicitudReg solicitudRegSeleccionada) {
		this.solicitudRegSeleccionada = solicitudRegSeleccionada;
	}
	
	public void seleccionarSolicitud(SelectEvent event)
	{
		solicitudRegSeleccionada = (SolicitudReg)event.getObject();
		
	}
	
	
}
