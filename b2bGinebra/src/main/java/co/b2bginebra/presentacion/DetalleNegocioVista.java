package co.b2bginebra.presentacion;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import co.b2bginebra.modelo.Negocio;

/**
 * representa la vista que muestra la informacion detallada de un negocio
 *
 */
@ManagedBean
@ViewScoped
public class DetalleNegocioVista {

	private Negocio negocio;

	
	@PostConstruct
	public void update()
	{
		//TODO actualizar vista con los datos del negocio
	}
	
	public Negocio getNegocio() {
		return negocio;
	}

	public void setNegocio(Negocio negocio) {
		this.negocio = negocio;
	}
	
	
	
	
}
