package co.b2bginebra.presentacion;

import java.util.Date;


import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;

import co.b2bginebra.logica.EstadoLogica;
import co.b2bginebra.logica.NegocioLogica;
import co.b2bginebra.logica.NegocioRegistradoLogica;
import co.b2bginebra.logica.SolicitudRegLogica;
import co.b2bginebra.logica.UsuarioLogica;
import co.b2bginebra.modelo.Estado;
import co.b2bginebra.modelo.Negocio;
import co.b2bginebra.modelo.SolicitudReg;
import co.b2bginebra.modelo.Usuario;
import net.bootsfaces.component.inputText.InputText;


/**
 * representa la vista para realizar el registro en la aplicacion
 *
 */

@ManagedBean
@ViewScoped
public class RegistroVista
{
	//Datos del usuario
	private InputText txtUsuNombre;
	private InputText txtUsuIdentificacion;
	private InputText txtUsuTelefono;
	private InputText txtUsuDireccion;
	private InputText txtUsuCorreo;
	private InputText txtUsuPassword;
	
	//Datos del negocio
	private InputText txtNegRazonSocial;
	private InputText txtNegDireccion;
	private InputText txtNegTelefono;
	private InputText txtNegSitioWeb;
	private InputText txtNegCorreo;
	private InputText txtNegDescripcion;
	private byte[] imagen;
	
	//logica de negocio
	@EJB
	private UsuarioLogica usuarioLogica;
	@EJB
	private NegocioLogica negocioLogica;
	@EJB
	private SolicitudRegLogica solicitudRegLogica;
	@EJB
	private NegocioRegistradoLogica negocioRegistradoLogica;
	@EJB
	private EstadoLogica estadoLogica;
	
	public void registrar()
	{
		try
		{
			Usuario usuNuevo = crearUsuario();
			
			Negocio negocioNuevo = crearNegocio();
			negocioNuevo.setUsuario(usuNuevo);
			
			//se busca usuario-negocio en la tabla de negocios registrados
			boolean estaRegistrado = negocioRegistradoLogica.estaRegistradoNegocioConUsuario(usuNuevo, negocioNuevo);
			
			if(estaRegistrado == false)
			{
				//el usuario y el negocio quedan como inactivos
				Estado estado = estadoLogica.consultarEstadoPorNombre("Inactivo");
				usuNuevo.setEstado(estado);
				negocioNuevo.setEstado(estado);
				
				usuarioLogica.crearUsuario(usuNuevo);
				negocioLogica.crearNegocio(negocioNuevo);
				
				//se crea solicitud de registro
				SolicitudReg solicitudReg = new SolicitudReg();
				solicitudReg.setDescripcion("Datos de Usuario: \n" + usuNuevo.toString() + "\n\n" + "Datos de Negocio: \n" + negocioNuevo.toString());
				solicitudReg.setFechaCreacion(new Date());
				solicitudReg.setNegocio(negocioNuevo);
				solicitudReg.setEstado(estadoLogica.consultarEstadoPorNombre("Enviada"));
				solicitudRegLogica.crearSolicitudReg(solicitudReg);			
				
				//TODO Se manda correo de alerta al correo institucional de que se creo una solicitud
			}
			else
			{
				//No se manda solicitud de registro. El usuario y el negocio quedan en estado activo
				Estado estado = estadoLogica.consultarEstadoPorNombre("Activo");
				usuNuevo.setEstado(estado);
				negocioNuevo.setEstado(estado);
				
				usuarioLogica.crearUsuario(usuNuevo);
				negocioLogica.crearNegocio(negocioNuevo);
				
				//TODO Se manda correo de alerta al correo institucional de que la cuenta quedo activa
			}
			
		}
		catch(Exception e)
		{
			
		}
	}
	
	public Usuario crearUsuario()
	{
		Usuario usuNuevo = new Usuario();
		usuNuevo.setNombre(txtUsuNombre.getValue().toString().trim());
		usuNuevo.setIdentificacion(txtUsuIdentificacion.getValue().toString().trim());
		usuNuevo.setTelefono(txtUsuTelefono.getValue().toString().trim());
		usuNuevo.setDireccion(txtUsuDireccion.getValue().toString().trim());
		usuNuevo.setCorreo(txtUsuCorreo.getValue().toString().trim());
		usuNuevo.setPassword(txtUsuPassword.getValue().toString().trim());
		
		return usuNuevo;
	}
	
	public Negocio crearNegocio()
	{
		Negocio negocioNuevo = new Negocio();
		negocioNuevo.setRazonSocial(txtNegRazonSocial.getValue().toString().trim());
		negocioNuevo.setDireccion(txtNegDireccion.getValue().toString().trim());
		negocioNuevo.setTelefono(txtNegTelefono.getValue().toString().trim());
		negocioNuevo.setSitioWeb(txtNegSitioWeb.getValue().toString().trim());
		negocioNuevo.setCorreo(txtNegCorreo.getValue().toString().trim());
		negocioNuevo.setDescripcion(txtNegDescripcion.getValue().toString().trim());
		negocioNuevo.setImgPrincipal(imagen);
		
		return negocioNuevo;
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
}
