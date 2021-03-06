package s.c.m.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import s.c.m.entities.Puesto;
import s.c.m.services.PuestoService;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import java.util.List;



@Component
@ManagedBean
public class PuestoBean {
    //Controlador que conecta el front end con el backend para IO para consultar puestos.
    @Autowired
    PuestoService puestoService;
    private Puesto puesto = new Puesto();

    private List<Puesto> puestos;

    @PostConstruct
    public String init()
    {
        puestos = puestoService.getAllPuestos();
        return "puestoList.xhtml";
    }

    public Puesto getPuesto()
    {
        return puesto;
    }

    public void setPuesto(Puesto puesto)
    {
        this.puesto = puesto;
    }

    public List<Puesto> getPuestos()
    {
        return puestos;
    }

    public void setPuestos(List<Puesto> puestos)
    {
        this.puestos = puestos;
    }

    public Puesto obtienePuestos(Integer id)//Funcion para consultar puestos por su id
    {
        if(id == null){
            throw new IllegalArgumentException("no se provee el id");
        }
        for (Puesto p : puestos){
            if(id.equals(p.getPk_idPuesto())){
                return p;
            }
        }
        return null;
    }
}
