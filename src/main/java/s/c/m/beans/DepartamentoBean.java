package s.c.m.beans;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import s.c.m.entities.Colaborador;
import s.c.m.entities.Departamento;
import s.c.m.entities.Puesto;
import s.c.m.services.ColaboradorService;
import s.c.m.services.DepartamentoService;
import s.c.m.services.PuestoService;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.List;



@Component
@ManagedBean
public class DepartamentoBean {
    //Controlador que conecta el front end con el backend para IO para mantenimiento de departamento.
    @Autowired
    DepartamentoService departamentoService;
    @Autowired
    ColaboradorService colaboradorService;
    @Autowired
    PuestoService puestoService;
    private Departamento departamento = new Departamento();
    private Departamento selectDepartamento = new Departamento();
    private Colaborador colaborador = new Colaborador();
    private List<Departamento> departamentos;
    String idD;

    @PostConstruct
    public String init()
    {
        departamentos = departamentoService.getAllDepartamentosActivos();
        return "departamentoList.xhtml";
    }

    public Departamento getDepartamento()
    {
        return departamento;
    }

    public void setDepartamento(Departamento departamento)
    {
        this.departamento = departamento;
    }

    public Departamento getSelectDepartamento()
    {
        return selectDepartamento;
    }

    public void setSelectDepartamento(Departamento selectDepartamento)
    {
        this.selectDepartamento = selectDepartamento;
    }

    public List<Departamento> getDepartamentos()
    {
        return departamentos;
    }

    public void setDepartamentos(List<Departamento> departamentos)
    {
        this.departamentos = departamentos;
    }

    public Colaborador getColaborador()
    {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador)
    {
        this.colaborador = colaborador;
    }

    public Departamento obtieneDepartamento(String id)//Funcion para encontrar departamento por id
    {
        if (id == null) {
            throw new IllegalArgumentException("no se provee el id");
        }
        for (Departamento d : departamentos) {
            if (id.equals(d.getPk_idDepartamento())) {
                return d;
            }
        }
        return null;
    }

    public void checkSelection()//Se valida si selecciono el departamento
    {
        PrimeFaces current = PrimeFaces.current();

        if (selectDepartamento == null) {
            addMessage("Aviso", "Debe seleccionar un Departamento"); //si esta vacío muestra este mensaje
        } else {//Si se selecciono un dep
            current.executeScript("PF('dlUC').show();"); //si no esta vacío muestra el dialogo para actualizar el departamento
        }
    }

    public void checkSelectionD()//Funcion para validar que se selecciono departamento y consultar encargado
    {
        PrimeFaces current = PrimeFaces.current();

        if (selectDepartamento == null) {
            addMessage("Aviso", "Debe seleccionar un Departamento"); //si esta vacío muestra este mensaje
        } else {//Si se selecciono un departamento
            buscaEncargado();//Se invoca la funcion para consultar el encargado
            current.executeScript("PF('dE').show();"); //si muestra el dialogo con la informacion o no del encargado
        }
    }

    public void buscaEncargado()//Funcion para consultar de la base de datos el encargado del departamento
    {

        String idDepSelc = selectDepartamento.getPk_idDepartamento();//Saco id dept
        int idPuesto = puestoService.findIdPuesto("Jefatura").getPk_idPuesto();//saco id puesto
        Puesto puesto = puestoService.findIdPuesto("Jefatura");
        Puesto puesto1 = puestoService.findIdPuesto("Gerencia");
        if (colaboradorService.findColaboradorEncargado(selectDepartamento, puesto) != null) {//Si se encuentra el encargado
            colaborador = colaboradorService.findColaboradorEncargado(selectDepartamento, puesto);//Se llena el objeto para mostrarlo
        } else {//De no ser asi se llena el objeto con datos quemado vacios
            colaborador.setPk_idColaborador("No hay un Encargado Asignado");
            colaborador.setNombre("Vacío");
            colaborador.setTelefono(0);
            colaborador.setCorreo("Vacío");
            puesto.setDescripcion("Vacío");
            colaborador.setPuesto(puesto);
        }if(colaboradorService.findColaboradorEncargado(selectDepartamento, puesto1) != null){
            colaborador = colaboradorService.findColaboradorEncargado(selectDepartamento, puesto1);
        }
    }


    public void showconfirm() throws Exception {//Funcion para validar si mostrar validacion para eliminar el departamento
        PrimeFaces current = PrimeFaces.current();


        if (selectDepartamento == null) {//Valida si se selecciono el departamento
            addMessage("Aviso", "Debe Seleccionar un Departamento."); //si esta vacio muetra este mensaje
        } else {//Si se selecciono
            if(colaboradorService.findColaboradorDepartamento(selectDepartamento.getPk_idDepartamento()).size()!= 0){
                addMessage("Aviso", "NO puede Desactivar un Departamento con Colaboradores Asignados."); //si esta vacio muetra este mensaje
            }else{
            current.executeScript("PF('dlED').show();"); //si no esta vacio muestra el dialogo de confirmacion de eliminacion
            }
        }

    }

    public void close()
    {
        departamento = new Departamento();
    }

    public void delete()//Funcion para desactivar el departamento en la base de datos
    {
        departamentoService.deleteDepartamento(selectDepartamento);
        addMessage("Aviso", "Departamento Desactivado con Éxito.");
        departamentos = departamentoService.getAllDepartamentosActivos();//Se carga nuevamente los departementos restantes
        System.out.println("Eliminado");
    }

    public void create()//Funcion para crear un departamento
    {
        FacesMessage mensaje = null;
        PrimeFaces current = PrimeFaces.current();
        boolean existeDepartamento = false;
        for (Departamento d : departamentos) {//Se busca que no haya un departamento con ese id
            if (departamento.getPk_idDepartamento().equals(d.getPk_idDepartamento())) {
                existeDepartamento = true;
                break;
            } else {
                existeDepartamento = false;
            }
        }
        if (!existeDepartamento) {//Si no exite el departamento
            try {
                System.out.println("No existe el departamento");
                departamentoService.createDepartamento(departamento);//Se agrega el mismo a la base de datos
                mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Ingreso de Departamento con Éxito");
                departamentos = departamentoService.getAllDepartamentosActivos();//Y se vuelven a cargar los departmentos
                current.executeScript("PF('dlAC').hide();");//Se oculta el form
            } catch (Exception e) {
            } finally {
                departamento = new Departamento();
            }
        } else if (existeDepartamento) {//Si ya existe un departamento con es id
            System.out.println("Si existe el departamento con ese cod");
            mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Ya existe un departamento con ese código, pruebe nuevamente.");
        }
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
        PrimeFaces.current().ajax().addCallbackParam("existeDepartamento", existeDepartamento);
    }

    public void update()//Funcion para actualizar el departamento
    {
        try {
            System.out.println("El departamento actualizado es" + selectDepartamento.getNombre());
            departamentoService.updateDepartamento(selectDepartamento);//Se actualiza el departamento en la base
            addMessage("Aviso", "Departamento Actualizado con Éxito");
            departamentos = departamentoService.getAllDepartamentosActivos();//Se cargan nuevamente los departamentos
        } catch (Exception e) {
        } finally {
            departamento = new Departamento();
        }
    }

    public void find()
    {
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("DepartamentoIdBusqueda");
        departamentos.clear();
        departamentos.add(departamentoService.findDepartamento(id));
    }
    public void addMessage(String summary, String detail)//Funcion para construir mensajes
    {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}















