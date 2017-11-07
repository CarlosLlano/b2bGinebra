package co.b2bginebra.presentacion;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import co.b2bginebra.logica.GestionCorreosLogica;
import co.b2bginebra.logica.UsuarioLogica;
import co.b2bginebra.modelo.Usuario;
import net.bootsfaces.component.inputText.InputText;

/**
 * representa la vista para el inicio de sesion
 *
 */
@ManagedBean
@ViewScoped
public class LoginVista
{
	
	private InputText txtNombreUsuario;
	private InputText txtPassword;
	
	@EJB
	private UsuarioLogica usuarioLogica;
	@EJB
	private GestionCorreosLogica gestionCorreosLogica;
	
	private Usuario usuLogueado;
	
	public void validarUsuario()
	{
		try 
		{
			usuLogueado = usuarioLogica.validarUsuario(txtNombreUsuario.getValue().toString(), txtPassword.getValue().toString());
			
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
		}
		
	}
	
	/**
	 * En caso de que el usuario no se acuerde de su clave,
	 * se le enviara un correo con una clave aleatoria unica
	 * que se recomienda cambiar
	 */
	public void cambiarClave()
	{
		String claveTemporal = System.currentTimeMillis() + "";
		
		//TODO:cambiar password al usuario
		String correo=null; //debe obtenerse de un modal
		
		//TODO:enviar correo
		String mensaje = "Utilice la siguiente clave de ingreso para acceder a la plataforma: " + claveTemporal + "\n" +
				"Se recomienda cambiar la clave tan pronto pueda acceder.";
		gestionCorreosLogica.enviarCorreo(correo, "CAMBIO DE CONTRASEÑA", mensaje);
	}

	public InputText getTxtNombreUsuario() {
		return txtNombreUsuario;
	}

	public void setTxtNombreUsuario(InputText txtNombreUsuario) {
		this.txtNombreUsuario = txtNombreUsuario;
	}

	public InputText getTxtPassword() {
		return txtPassword;
	}

	public void setTxtPassword(InputText txtPassword) {
		this.txtPassword = txtPassword;
	}

	public Usuario getUsuLogueado() {
		return usuLogueado;
	}

	public void setUsuLogueado(Usuario usuLogueado) {
		this.usuLogueado = usuLogueado;
	}

	
	
	
	

}
