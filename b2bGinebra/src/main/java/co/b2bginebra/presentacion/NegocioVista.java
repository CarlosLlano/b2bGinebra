package co.b2bginebra.presentacion;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import co.b2bginebra.logica.NegocioLogica;
import co.b2bginebra.modelo.HorarioAtencion;
import co.b2bginebra.modelo.Imagen;
import co.b2bginebra.modelo.Negocio;
import net.bootsfaces.component.inputText.InputText;

/**
 * 
 * representa la vista de configuracion de los atributos de un negocio
 *
 */

@ManagedBean
@ViewScoped
public class NegocioVista 
{
	
	private InputText txtRazonSocial;
	private InputText txtDireccion;
	private InputText txtTelefono;
	private InputText txtTipoDeNegocio;
	private InputText txtSitioWeb;
	private InputText txtCorreo;
	private InputText txtDescripcion;
	private List<HorarioAtencion> horarios;
	private List<Imagen> imagenes;
	
	private Negocio negocioSeleccionado;
	
	@EJB
	private NegocioLogica negocioLogica;
	
	@PostConstruct
    public void postConstruct() {
       
		txtRazonSocial.setValue(negocioSeleccionado.getRazonSocial());
    		txtDireccion.setValue(negocioSeleccionado.getDireccion());
    		txtTelefono.setValue(negocioSeleccionado.getTelefono());
    		txtTipoDeNegocio.setValue(negocioSeleccionado.getTipoNegocio().getNombre());
    		txtSitioWeb.setValue(negocioSeleccionado.getSitioWeb());
    		txtCorreo.setValue(negocioSeleccionado.getCorreo());
    		txtDescripcion.setValue(negocioSeleccionado.getDescripcion());
    		horarios = negocioSeleccionado.getHorarioAtencions();
    		imagenes = negocioSeleccionado.getImagens();
    	
    }
	
	
	public void modificarInformacion()
	{
		negocioSeleccionado.setDireccion(txtDireccion.getValue().toString());
		negocioSeleccionado.setTelefono(txtTelefono.getValue().toString());
		negocioSeleccionado.setSitioWeb(txtSitioWeb.getValue().toString());
		negocioSeleccionado.setCorreo(txtCorreo.getValue().toString());
		negocioSeleccionado.setDescripcion(txtDescripcion.getValue().toString());
		negocioSeleccionado.setHorarioAtencions(horarios);
		negocioSeleccionado.setImagens(imagenes);
		
		try 
		{
			negocioLogica.modificarNegocio(negocioSeleccionado);
		} 
		catch (Exception e)
		{
			
		}
	}

	
}
