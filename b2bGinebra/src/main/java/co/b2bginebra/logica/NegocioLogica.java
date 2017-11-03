package co.b2bginebra.logica;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import co.b2bginebra.dao.NegocioDAO;
import co.b2bginebra.modelo.Negocio;

@Stateless
public class NegocioLogica
{
	
	@EJB
	private NegocioDAO negocioDAO;
	
	
	public void validarAtributos(Negocio negocio) throws Exception
	{
		if(negocio==null)
		{
			throw new Exception("el negocio no puede ser nulo");
		}
		if(negocio.getRazonSocial()==null || negocio.getRazonSocial().equals(""))
		{
			throw new Exception("La razon social es obligatoria");
		}
		if(negocio.getDireccion()==null || negocio.getDireccion().equals(""))
		{
			throw new Exception("La direccion es obligatoria");
		}
		if(negocio.getTelefono()==null || negocio.getTelefono().equals(""))
		{
			throw new Exception("El telefono es obligatorio");
		}
		if(negocio.getCorreo()==null || negocio.getCorreo().equals(""))
		{
			throw new Exception("El correo es obligatorio");
		}
		if(negocio.getDescripcion()==null || negocio.getDescripcion().equals(""))
		{
			throw new Exception("La descripcion es obligatoria");
		}
		if(negocio.getImgPrincipal()==null)
		{
			throw new Exception("La imagen del negocio es obligatoria");
		}
		
	}
	
	public void crearNegocio(Negocio negocio) throws Exception
	{
		validarAtributos(negocio);
		negocioDAO.crear(negocio);
	}
	
	public void modificarNegocio(Negocio negocio) throws Exception
	{
		validarAtributos(negocio);
		negocioDAO.modificar(negocio);
	}
	
	public void borrarNegocio(Negocio negocio) throws Exception
	{
		negocioDAO.borrar(negocio);
	}
	
	public Negocio consultarNegocio(Long id) throws Exception
	{
		if(id==0)
		{
			throw new Exception("el id es obligatorio");
		}
		return negocioDAO.consultarPorId(id);
	}
	
	public List<Negocio> consultarTodos() throws Exception 
	{
		return negocioDAO.consultarTodos();
	}

}
