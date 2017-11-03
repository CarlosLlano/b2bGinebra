package co.b2bginebra.logica;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import co.b2bginebra.dao.UsuarioDAO;
import co.b2bginebra.modelo.Usuario;

@Stateless
public class UsuarioLogica
{
	
	@EJB
	private UsuarioDAO usuarioDAO;
	
	
	public void validarAtributos(Usuario usuario) throws Exception
	{
		if(usuario==null)
		{
			throw new Exception("el usuario no puede ser nulo");
		}
		if(usuario.getNombre()==null || usuario.getNombre().equals(""))
		{
			throw new Exception("el nombre es obligatorio");
		}
		if(usuario.getIdentificacion()==null || usuario.getIdentificacion().equals(""))
		{
			throw new Exception("La identificacion es obligatoria");
		}
		if(usuario.getTelefono()==null || usuario.getTelefono().equals(""))
		{
			throw new Exception("el telegono es obligatorio");
		}
		if(usuario.getDireccion()==null || usuario.getDireccion().equals(""))
		{
			throw new Exception("la direccion es obligatoria");
		}
		if(usuario.getCorreo()==null || usuario.getCorreo().equals(""))
		{
			throw new Exception("el correo es obligatorio");
		}
		if(usuario.getPassword()==null || usuario.getPassword().equals(""))
		{
			throw new Exception("La contraseña es obligatoria");
		}
	}
	
	public void crearUsuario(Usuario usuario) throws Exception
	{
		//se validan atributos
		validarAtributos(usuario);
		
		//se verifica que no exista un usuario con la identificacion dada
		Usuario usu = usuarioDAO.consultarUsuarioPorIdentificacion(usuario.getIdentificacion());
		if(usu != null)
		{
			throw new Exception("Ya existe un usuario con la misma identificacion");
		}
		else
		{
			usuarioDAO.crear(usuario);
		}
	}
	
	public void modificarUsuario(Usuario usuario) throws Exception
	{
		validarAtributos(usuario);
		usuarioDAO.modificar(usuario);
	}
	
	public void borrarUsuario(Usuario usuario) throws Exception
	{
		usuarioDAO.borrar(usuario);
	}
	
	public Usuario consultarUsuario(Long id) throws Exception
	{
		if(id==0)
		{
			throw new Exception("el id es obligatorio");
		}
		return usuarioDAO.consultarPorId(id);
	}
	
	public List<Usuario> consultarTodos() throws Exception 
	{
		return usuarioDAO.consultarTodos();
	}
	
	public Usuario validarUsuario(String usuLogin, String usuClave) throws Exception
	{
		Usuario usuario = usuarioDAO.consultarUsuarioPorIdentificacion(usuLogin);
		if(usuario==null)
		{
			throw new Exception("Usuario o password no valido");
		}
		if(usuario.getPassword().equals(usuClave)==true)
		{
			return usuario;
		}
		else
		{
			throw new Exception("Usuario o password no valido");
		}
	}

}
