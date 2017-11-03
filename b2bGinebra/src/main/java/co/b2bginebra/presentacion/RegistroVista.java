package co.b2bginebra.presentacion;

import java.util.Date;


import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import co.b2bginebra.logica.EstadoLogica;
import co.b2bginebra.logica.NegocioLogica;
import co.b2bginebra.logica.NegocioRegistradoLogica;
import co.b2bginebra.logica.SolicitudRegLogica;
import co.b2bginebra.logica.UsuarioLogica;
import co.b2bginebra.modelo.Estado;
import co.b2bginebra.modelo.Negocio;
import co.b2bginebra.modelo.SolicitudReg;
import co.b2bginebra.modelo.Usuario;


/**
 * representa la vista para realizar el registro en la aplicacion
 *
 */

@ManagedBean
@ViewScoped
public class RegistroVista
{
	//Datos del usuario
	private String txtUsuNombre;
	private String txtUsuIdentificacion;
	private String txtUsuTelefono;
	private String txtUsuDireccion;
	private String txtUsuCorreo;
	private String txtUsuPassword;
	
	//Datos del negocio
	private String txtNegRazonSocial;
	private String txtNegDireccion;
	private String txtNegTelefono;
	private String txtNegSitioWeb;
	private String txtNegCorreo;
	private String txtNegDescripcion;
	//private String imagen;
	
	
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
			//creacion del usuario
			Usuario usuNuevo = new Usuario();
			usuNuevo.setNombre(txtUsuNombre.trim());
			usuNuevo.setIdentificacion(txtUsuIdentificacion.trim());
			usuNuevo.setTelefono(txtUsuTelefono.trim());
			usuNuevo.setDireccion(txtUsuDireccion.trim());
			usuNuevo.setCorreo(txtUsuCorreo.trim());
			usuNuevo.setPassword(txtUsuPassword.trim());
			
			//creacion del negocio
			Negocio negocioNuevo = new Negocio();
			negocioNuevo.setRazonSocial(txtNegRazonSocial.trim());
			negocioNuevo.setDireccion(txtNegDireccion.trim());
			negocioNuevo.setTelefono(txtNegTelefono.trim());
			negocioNuevo.setSitioWeb(txtNegSitioWeb.trim());
			negocioNuevo.setCorreo(txtNegCorreo.trim());
			negocioNuevo.setDescripcion(txtNegDescripcion.trim());
			//negocioNuevo.setImgPrincipal(imgPrincipal);
			
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
			}
			
		}
		catch(Exception e)
		{
			
		}
	}
}
