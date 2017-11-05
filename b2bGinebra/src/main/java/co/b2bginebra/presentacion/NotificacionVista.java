package co.b2bginebra.presentacion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.w3c.dom.ls.LSOutput;

import co.b2bginebra.logica.NotificacionLogica;
import co.b2bginebra.logica.TipoNotLogica;
import co.b2bginebra.modelo.Imagen;
import co.b2bginebra.modelo.Negocio;
import co.b2bginebra.modelo.Notificacion;
import co.b2bginebra.modelo.TipoNot;
import net.bootsfaces.component.inputText.InputText;
import net.bootsfaces.component.inputTextarea.InputTextarea;
import net.bootsfaces.component.selectOneMenu.SelectOneMenu;

/**
 * representa la vista usada para gestionar notificaciones (crear, modificar, borrar, ver todas)
 *
 */
@ManagedBean
@ViewScoped
public class NotificacionVista 
{
	private InputText txtNombre;
	private InputTextarea txtDescripcion;
	private byte[] imagen;
	private Date fechaCreacion;
	private Date fechaTerminacion;
	private SelectOneMenu somTipoNotificacion;
	
	private List<TipoNot> notificaciones;
	private List<SelectItem> losItemsTipoNotificacion;
	private Notificacion notificacionSeleccionada;
	
	
	@EJB
	private NotificacionLogica notificacionLogica;
	@EJB
	private TipoNotLogica tipoNotLogica;
	
	
	public void crear()
	{
		try 
		{
			Notificacion notificacion = new Notificacion();
			notificacion.setNombre(txtNombre.getValue().toString());
			notificacion.setDescripcion(txtDescripcion.getValue().toString());
			notificacion.setImagen(imagen);
			notificacion.setFechaCreacion(fechaCreacion);
			notificacion.setFechaTerminacion(fechaTerminacion);
			notificacion.setTipoNot(tipoNotLogica.consultarTipoNot(Long.parseLong(somTipoNotificacion.getValue().toString())));
			
			notificacionLogica.crearNotificacion(notificacion);
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
		}
			
	}
	
	//solo aplica para las notificacion propias
	public void modificar()
	{
		try 
		{
			notificacionSeleccionada.setNombre(txtNombre.getValue().toString());
			notificacionSeleccionada.setDescripcion(txtDescripcion.getValue().toString());
			notificacionSeleccionada.setImagen(imagen);
			notificacionSeleccionada.setFechaCreacion(fechaCreacion);
			notificacionSeleccionada.setFechaTerminacion(fechaTerminacion);
			notificacionSeleccionada.setTipoNot(tipoNotLogica.consultarTipoNot(Long.parseLong(somTipoNotificacion.getValue().toString())));
			
			notificacionLogica.modificarNotificacion(notificacionSeleccionada);
		}
		catch (Exception e) 
		{
			// TODO: handle exception
		}

	}
	
	public void borrar()
	{
		try 
		{
			notificacionLogica.borrarNotificacion(notificacionSeleccionada);
		} 
		catch (Exception e) 
		{
			
		}
		
	}
	
	public void subirImagen(FileUploadEvent event)
	{
		FacesMessage mensaje = new FacesMessage();
	
		try 
		{	
			imagen = event.getFile().getContents();
			
			mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
			mensaje.setSummary("Imagen subida correctamente");
		} 
		catch (Exception e)
		{
			mensaje.setSeverity(FacesMessage.SEVERITY_ERROR);
			mensaje.setSummary("Error al subir la imagen");		
		}
		FacesContext.getCurrentInstance().addMessage("Mensaje", mensaje);
	}
	
	public List<TipoNot> getNotificaciones() 
	{
		try 
		{
			if(notificaciones==null)
			{
				notificacionLogica.consultarTodos();
			}
			
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
		}
		
		return notificaciones;
	}

	public List<SelectItem> getLosItemsTipoNotificacion()
	{
		try
		{
			if(losItemsTipoNotificacion==null)
			{
				List<TipoNot> lista = tipoNotLogica.consultarTodos();
				losItemsTipoNotificacion = new ArrayList<SelectItem>();
				for (TipoNot tipoNot : lista) 
				{
					SelectItem selectItem = new SelectItem(tipoNot.getIdTipoNot(), tipoNot.getNombre());
					losItemsTipoNotificacion.add(selectItem);
				}
				
			}
		} 
		catch (Exception e) {
			// TODO: handle exception
		}
		
		return losItemsTipoNotificacion;
	}




	public void setLosItemsTipoNotificacion(List<SelectItem> losItemsTipoNotificacion) {
		this.losItemsTipoNotificacion = losItemsTipoNotificacion;
	}
	
	public void seleccionarNotificacion(SelectEvent event)
	{
		notificacionSeleccionada = (Notificacion)event.getObject();
		
	}
	

}
