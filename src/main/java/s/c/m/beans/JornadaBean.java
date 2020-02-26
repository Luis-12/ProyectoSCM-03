package s.c.m.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import s.c.m.entities.Jornadas;
import s.c.m.services.JornadaService;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import java.util.List;

@Component
@ManagedBean
public class JornadaBean {

    @Autowired
    JornadaService jornadaService;
    private Jornadas jornada = new Jornadas();

    private List<Jornadas> jornadas;

    @PostConstruct
    public String init()
    {
        jornadas = jornadaService.getAllJornadas();
        return "jornadaList.xhtml";
    }

    public JornadaService getjornadaService() {
        return jornadaService;
    }

    public void setJornadaService(JornadaService jornadaService) {
        this.jornadaService = jornadaService;
    }


    public List<Jornadas> getJornadas() {
        return jornadas;
    }

    public void setJornadas(List<Jornadas> jornadas) {
        this.jornadas = jornadas;
    }

    public JornadaService getJornadaService() {
        return jornadaService;
    }

    public Jornadas getJornada() {
        return jornada;
    }

    public void setJornada(Jornadas jornada) {
        this.jornada = jornada;
    }

    public Jornadas obtieneJornadas(String jornada)
    {
        if(jornada == null){
            throw new IllegalArgumentException("no se provee el id");
        }
        for (Jornadas p : jornadas){
            if(jornadas.equals(p.getPk_idjornada())){
                return p;
            }
        }
        return null;
    }
}
