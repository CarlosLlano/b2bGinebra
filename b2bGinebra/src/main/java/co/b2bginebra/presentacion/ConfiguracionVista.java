package co.b2bginebra.presentacion;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;

import co.b2bginebra.logica.ParametroSistemaLogica;
import co.b2bginebra.modelo.ParametroSistema;
import net.bootsfaces.component.inputText.InputText;

/**
 * Representa la vista destinada la gestion de parametros de configuracion de la aplicacion
 */
@ManagedBean
@ViewScoped
public class ConfiguracionVista 
{
	@EJB
	private ParametroSistemaLogica parametroSistemaLogica;
	
	private List<ParametroSistema> parametros;

	private InputText valor;
	private ParametroSistema parametroSeleccionado;
	
	
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
	
	public void modificarParametro()
	{
		try 
		{
			parametroSeleccionado.setValor(valor.getValue().toString());
			parametroSistemaLogica.modificarParametroSistema(parametroSeleccionado);
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
		}
		
	}
	
	public void seleccionarParametro(SelectEvent event)
	{
		parametroSeleccionado = (ParametroSistema)event.getObject();
		
	}
}
