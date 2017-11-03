package co.b2bginebra.presentacion;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import co.b2bginebra.logica.UsuarioLogica;
import co.b2bginebra.modelo.Usuario;

/**
 * representa la vista para el inicio de sesion
 *
 */
@ManagedBean
@ViewScoped
public class LoginVista
{
	
	private String txtNombreUsuario;
	private String txtPassword;
	
	@EJB
	private UsuarioLogica usuarioLogica;
	
	private Usuario usuLogueado;
	
	public void validarUsuario()
	{
		try 
		{
			usuLogueado = usuarioLogica.validarUsuario(txtNombreUsuario, txtPassword);
			
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
		}
		
	}


	public String getTxtNombreUsuario() {
		return txtNombreUsuario;
	}


	public void setTxtNombreUsuario(String txtNombreUsuario) {
		this.txtNombreUsuario = txtNombreUsuario;
	}


	public String getTxtPassword() {
		return txtPassword;
	}


	public void setTxtPassword(String txtPassword) {
		this.txtPassword = txtPassword;
	}


	public Usuario getUsuLogueado() {
		return usuLogueado;
	}


	public void setUsuLogueado(Usuario usuLogueado) {
		this.usuLogueado = usuLogueado;
	}
	
	
	
	

}
