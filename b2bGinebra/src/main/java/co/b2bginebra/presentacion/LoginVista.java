package co.b2bginebra.presentacion;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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
