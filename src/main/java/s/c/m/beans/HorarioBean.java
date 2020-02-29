package s.c.m.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import s.c.m.entities.Horarios;
import s.c.m.entities.Jornadas;
import s.c.m.services.HorarioService;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import java.util.List;

@Component
@ManagedBean
public class HorarioBean {
    //Controlador que conecta el front end con el backend para IO para manejar horarios.

    @Autowired
    HorarioService horarioService;
    private Horarios horario = new Horarios();
    private Jornadas jornada = new Jornadas();

    private List<Horarios> horarios;

    @PostConstruct
    public String init()
    {
        horarios = horarioService.getAllHorarios();
        return "horarioList.xhtml";
    }

    public HorarioService getHorarioService() {
        return horarioService;
    }

    public void setHorarioService(HorarioService horarioService) {
        this.horarioService = horarioService;
    }

    public Horarios getHorario() {
        return horario;
    }

    public void setHorario(Horarios horario) {
        this.horario = horario;
    }

    public List<Horarios> getHorarios()
    {
        return horarios;
    }

    public void setHorarios(List<Horarios> horarios)
    {
        this.horarios = horarios;
    }

    public Horarios obtieneHorarios(String horario)//Funcion para buscar horarios por id
    {
        if(horario == null){
            throw new IllegalArgumentException("no se provee el id");
        }
        for (Horarios p : horarios){
            if(horarios.equals(p.getPk_idhorario())){
                return p;
            }
        }
        return null;
    }
}
