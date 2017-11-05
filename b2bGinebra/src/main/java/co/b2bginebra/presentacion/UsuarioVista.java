package co.b2bginebra.presentacion;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import co.b2bginebra.logica.UsuarioLogica;
import co.b2bginebra.modelo.Usuario;
import net.bootsfaces.component.inputText.InputText;

/**
 * representa la vista para la gestion de informacion de un usuario
 *
 */
@ManagedBean
@ViewScoped
public class UsuarioVista 
{
	
	private InputText txtNombre;
	private InputText txtDireccion;
	private InputText txtCorreo;
	private InputText txtTelefono;
	private InputText txtPassword;
	
	
	private Usuario usuLogueado;
	
	@EJB
	private UsuarioLogica usuarioLogica;
	
    @PostConstruct
    public void postConstruct() 
    {   
    		txtNombre.setValue(usuLogueado.getNombre());
    		txtDireccion.setValue(usuLogueado.getDireccion());
    		txtCorreo.setValue(usuLogueado.getCorreo());
    		txtTelefono.setValue(usuLogueado.getTelefono());
    		txtPassword.setValue(usuLogueado.getPassword());
    	
    }
	
	
	public void cambiarInformacionPersonal()
	{
		usuLogueado.setNombre(txtNombre.getValue().toString());
		usuLogueado.setDireccion(txtDireccion.getValue().toString());
		usuLogueado.setCorreo(txtCorreo.getValue().toString());
		usuLogueado.setTelefono(txtTelefono.getValue().toString());
		usuLogueado.setPassword(txtPassword.getValue().toString());
		
		try
		{
			usuarioLogica.modificarUsuario(usuLogueado);
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
		}
		
	}


	public InputText getTxtNombre() {
		return txtNombre;
	}


	public void setTxtNombre(InputText txtNombre) {
		this.txtNombre = txtNombre;
	}


	public InputText getTxtDireccion() {
		return txtDireccion;
	}


	public void setTxtDireccion(InputText txtDireccion) {
		this.txtDireccion = txtDireccion;
	}


	public InputText getTxtCorreo() {
		return txtCorreo;
	}


	public void setTxtCorreo(InputText txtCorreo) {
		this.txtCorreo = txtCorreo;
	}


	public InputText getTxtTelefono() {
		return txtTelefono;
	}


	public void setTxtTelefono(InputText txtTelefono) {
		this.txtTelefono = txtTelefono;
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

