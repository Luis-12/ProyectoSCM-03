package s.c.m.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import s.c.m.entities.Colaborador;
import s.c.m.entities.Departamento;
import s.c.m.services.ColaboradorService;
import s.c.m.services.DepartamentoService;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import java.util.List;

@Component
@ManagedBean
public class ColaboradorBean {
    @Autowired
    ColaboradorService colaboradorService;
    private Colaborador colaborador = new Colaborador();

    private List<Colaborador> departamentos;

    @PostConstruct
    public String init() {
        departamentos = colaboradorService.getAllColaboradores();
        return "colaboradorList.xhtml";
    }

    public ColaboradorService getColaboradorService() {
        return colaboradorService;
    }

    public void setColaboradorService(ColaboradorService colaboradorService) {
        this.colaboradorService = colaboradorService;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public List<Colaborador> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<Colaborador> departamentos) {
        this.departamentos = departamentos;
    }
}
