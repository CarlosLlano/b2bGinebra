package co.b2bginebra.presentacion;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import org.omnifaces.util.Ajax;

import co.b2bginebra.logica.EstadoLogica;
import co.b2bginebra.logica.GestionCorreosLogica;
import co.b2bginebra.logica.ParametroSistemaLogica;
import co.b2bginebra.logica.UsuarioLogica;
import co.b2bginebra.modelo.Estado;
import co.b2bginebra.modelo.ParametroSistema;
import co.b2bginebra.modelo.Usuario;
import co.b2bginebra.seguridad.CipherTools;

import net.bootsfaces.component.inputText.InputText;
import net.bootsfaces.component.selectOneMenu.SelectOneMenu;
import net.bootsfaces.utils.FacesMessages;

/**
 * Representa la vista destinada a la gestion de parametros de configuracion de la aplicacion
 */
@ManagedBean(name="configuracionVista")
@ViewScoped
public class ConfiguracionVista 
{
	
	//parametros
	private List<ParametroSistema> parametros;
	private String valor;
	private String nombre;
	private ParametroSistema parametroSeleccionado;
	
	//cambio de contraseña
	private InputText txtCorreo;
	private String txtNuevoPassword;
	private String txtNuevoPasswordConfirmar;
	
	//estados de usuarios
	private SelectOneMenu somEstadoUsuarios;
	private List<SelectItem> estadosUsuarios;
	private List<Usuario> usuarios;
	private SelectOneMenu somEstadoSeleccionado;
	private Usuario usuarioSeleccionado;
	
	@EJB
	private ParametroSistemaLogica parametroSistemaLogica;
	@EJB
	private UsuarioLogica usuarioLogica;
	@EJB
	private GestionCorreosLogica gestionCorreosLogica;
	@EJB
	private EstadoLogica estadoLogica;
	
	private Usuario usuCambioPassword;
	
	public void verificarPlazoParaCambioPassword()
	{		
		HttpServletRequest origRequest = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String parametro = origRequest.getParameter("recover");

		//la url tiene el parametro recover
		if(parametro!=null && parametro.trim().equals("")==false) 
		{
			Calendar fecha = Calendar.getInstance();
			String cambioUnico = fecha.get(Calendar.HOUR_OF_DAY)+""+
					  fecha.get(Calendar.DAY_OF_MONTH)+""+
					  fecha.get(Calendar.MONTH)+""+fecha.get(Calendar.YEAR);
			
			parametro = parametro.replace(" ", "+");
			String[] desincriptado = CipherTools.desencriptar(parametro).split(";");
			
			
			long idUsuario = Long.parseLong(desincriptado[0]);
			
			try
			{
				Usuario usu = usuarioLogica.consultarUsuario(idUsuario);
				if(usu!=null && cambioUnico.equals(desincriptado[1]))
				{
					//continuar en la vista
					usuCambioPassword = usu;
					
				}
				else
				{
					//redirect access denied
					ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		            externalContext.redirect("home.xhtml?faces-redirect=true");
				}
			}
			catch(Exception e)
			{
				
			}
		}
	}
	public void modificarParametro()
	{
		try 
		{	
			parametroSeleccionado.setValor(valor);
			parametroSistemaLogica.modificarParametroSistema(parametroSeleccionado);	
		} 
		catch (Exception e) 
		{
			
		}
		parametroSeleccionado = null;
		nombre = "";
		valor = "";
		
	}
	
	public void enviarCorreoResetPassword()
	{
		
		if(txtCorreo.getValue()==null || txtCorreo.getValue().toString().trim().equals(""))
		{
			mostrarMensaje("Debe especificar un correo");
		}
		else
		{
			String correo = txtCorreo.getValue().toString().trim();
			Usuario usuario = usuarioLogica.darUsuarioConCorreo(correo);
			if(usuario==null)
			{
				
				mostrarMensaje("No existe usuario con el correo especificado");
			}
			else
			{
				//ruta
				String protocol = FacesContext.getCurrentInstance().getExternalContext().getRequestScheme();
				String serverName = FacesContext.getCurrentInstance().getExternalContext().getRequestServerName();
				int serverPort = FacesContext.getCurrentInstance().getExternalContext().getRequestServerPort();
				String contextPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
				
				String ruta = protocol+ "://" + serverName + ":" + serverPort + contextPath + "/cambiarContrasena.xhtml";
				
				gestionCorreosLogica.enviarCorreoResetPassword(usuario,ruta);
				mostrarMensaje("Consulte su correo para continuar con el proceso de cambio "
						+ "de contraseña");
			}
		}
	}
	
	public void cambiarPassword()
	{
		
		if(txtNuevoPassword.equals(txtNuevoPasswordConfirmar)==false)
		{
			mostrarMensaje("Las contraseñas no coinciden");
		}
		else
		{
			usuCambioPassword.setPassword(txtNuevoPassword);
			try 
			{
				usuarioLogica.modificarUsuario(usuCambioPassword);
				
				ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();	
	            externalContext.redirect("inicio.xhtml?faces-redirect=true");
			} 
			catch (Exception e) 
			{
				mostrarMensaje(e.getMessage());
				
			}
		}
	}
	
	public void mostrarMensaje(String mensaje)
	{	
		FacesMessages.info(mensaje);		
		Ajax.update("formulario:growl");
	}
	public List<ParametroSistema> getParametros() 
	{
		try 
		{
			if(parametros==null)
			{
				parametros = parametroSistemaLogica.consultarTodos();
			}
		}
		catch (Exception e) 
		{
			
		}
		return parametros;
	}

	public void setParametros(List<ParametroSistema> parametros) {
		this.parametros = parametros;
	}
	
	
	
	public void cancelar()
	{
		parametroSeleccionado = null;
		nombre = "";
		valor = "";
	}
	
	public void seleccionarParametro(ParametroSistema parametroSistema)
	{
		parametroSeleccionado = parametroSistema;
		
		nombre = parametroSeleccionado.getNombre();
		valor = parametroSeleccionado.getValor();
		
	}
	
	
	public void somEstadoUsuariosOnChange()
	{
		try 
		{
			long id = Long.parseLong(somEstadoUsuarios.getValue().toString());
			if(id < 0)
			{
				usuarios = usuarioLogica.consultarTodos();
			}
			else
			{
				
				usuarios = usuarioLogica.consultarUsuariosPorIdEstado(id);
				
			}
		} 
		catch (Exception e) 
		{
			
			e.printStackTrace();
		}
	}
	
	public ParametroSistema getParametroSeleccionado() {
		return parametroSeleccionado;
	}
	
	public void setParametroSeleccionado(ParametroSistema parametroSeleccionado) {
		this.parametroSeleccionado = parametroSeleccionado;
	}
	
	
	
	
	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public InputText getTxtCorreo() {
		return txtCorreo;
	}

	public void setTxtCorreo(InputText txtCorreo) {
		this.txtCorreo = txtCorreo;
	}

	

	public String getTxtNuevoPassword() {
		return txtNuevoPassword;
	}

	public void setTxtNuevoPassword(String txtNuevoPassword) {
		this.txtNuevoPassword = txtNuevoPassword;
	}

	public String getTxtNuevoPasswordConfirmar() {
		return txtNuevoPasswordConfirmar;
	}

	public void setTxtNuevoPasswordConfirmar(String txtNuevoPasswordConfirmar) {
		this.txtNuevoPasswordConfirmar = txtNuevoPasswordConfirmar;
	}

	public Usuario getUsuCambioPassword() {
		return usuCambioPassword;
	}

	public void setUsuCambioPassword(Usuario usuCambioPassword) {
		this.usuCambioPassword = usuCambioPassword;
	}

	public SelectOneMenu getSomEstadoUsuarios() {
		return somEstadoUsuarios;
	}

	public void setSomEstadoUsuarios(SelectOneMenu somEstadoUsuarios) {
		this.somEstadoUsuarios = somEstadoUsuarios;
	}

	public List<SelectItem> getEstadosUsuarios() 
	{
		try 
		{
			if(estadosUsuarios==null)
			{
				List<Estado> estados = estadoLogica.consultarTodos();
				estadosUsuarios = new ArrayList<SelectItem>();
				for (Estado est : estados) 
				{
					if(est.getTipoEstado().getNombre().equals("General"))
					{
						SelectItem item = new SelectItem(est.getIdEstado(), est.getNombre());
						estadosUsuarios.add(item);
					}
					
				}
			}
		} 
		catch (Exception e) 
		{
			//handle error
		}
		return estadosUsuarios;
	}

	public void setEstadosUsuarios(List<SelectItem> estadosUsuarios) {
		this.estadosUsuarios = estadosUsuarios;
	}

	
	public List<Usuario> getUsuarios() 
	{
		try 
		{
			if(usuarios==null)
			{
				usuarios = usuarioLogica.consultarTodos();
			}
		}
		catch (Exception e) 
		{
			
		}
		return usuarios;
	}

	public SelectOneMenu getSomEstadoSeleccionado() {
		return somEstadoSeleccionado;
	}

	public void setSomEstadoSeleccionado(SelectOneMenu somEstadoSeleccionado) {
		this.somEstadoSeleccionado = somEstadoSeleccionado;
	}

	public Usuario getUsuarioSeleccionado() {
		return usuarioSeleccionado;
	}

	public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
		this.usuarioSeleccionado = usuarioSeleccionado;
	}
	
	public void guardarEstadoUsuario()
	{
		try 
		{
			Estado estado = estadoLogica.consultarEstado(Long.parseLong(somEstadoSeleccionado.getValue().toString()));		
			usuarioSeleccionado.setEstado(estado);	
			usuarioLogica.modificarUsuario(usuarioSeleccionado);
			
			usuarios = usuarioLogica.consultarTodos();
			somEstadoUsuarios.setValue("-1");
			Ajax.update("formulario:somEstadoUsuarios");
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		} 
		
		setUsuarioSeleccionado(null);
	}
	
	
}
