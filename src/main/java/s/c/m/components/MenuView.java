package s.c.m.components;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;

@ManagedBean
public class MenuView {

    private MenuModel model;

public MenuModel construyeMenuPorRol(String rol,String nombreDept){
    model = new DefaultMenuModel();

    //First submenu
    DefaultSubMenu firstSubmenu = new DefaultSubMenu("Colaborador");
    DefaultMenuItem item = new DefaultMenuItem("Mantenimiento Colaborador");
    item.setUrl("MantenimientoColaborador.xhtml");
    item.setIcon("ui-icon-plus");
    firstSubmenu.addElement(item);
    //model.addElement(firstSubmenu);

    //Second submenu
    DefaultSubMenu secondSubmenu = new DefaultSubMenu("Departamento");
    item = new DefaultMenuItem("Mantenimiento Departamento");
    item.setIcon("ui-icon-wrench");
    item.setCommand("MantenimientoDepartamento.xhtml");
    secondSubmenu.addElement(item);
    //model.addElement(secondSubmenu);

    //TERCER SUBMENU
    DefaultSubMenu tercerSubmenu = new DefaultSubMenu("Informes");
    item = new DefaultMenuItem("Cantidad Laborando");
    item.setIcon("ui-icon-clipboard");
    item.setCommand("CantidadLaborando.xhtml");
    tercerSubmenu.addElement(item);

    item = new DefaultMenuItem("Colaboradores Laborando en un Lapso de Tiempo");
    item.setIcon("ui-icon-clipboard");
    item.setCommand("ColaboradorLapso.xhtml");
    tercerSubmenu.addElement(item);

    item = new DefaultMenuItem("Colaborador Detallado");
    item.setIcon("ui-icon-clipboard");
    item.setCommand("InformeColaborador.xhtml");
    tercerSubmenu.addElement(item);

    item = new DefaultMenuItem("Colaborador por Departamento");
    item.setIcon("ui-icon-clipboard");
    item.setCommand("InformeColaDepartamento.xhtml");
    tercerSubmenu.addElement(item);

    item = new DefaultMenuItem("Reporte Horarios");
    item.setIcon("ui-icon-clipboard");
    item.setCommand("ReporteHorarios.xhtml");
    tercerSubmenu.addElement(item);

    item = new DefaultMenuItem("Reporte Tardías");
    item.setIcon("ui-icon-clipboard");
    item.setCommand("ReporteTardias.xhtml");
    tercerSubmenu.addElement(item);
    //model.addElement(tercerSubmenu);

    //Cuarto submenu
    DefaultSubMenu cuartoSubmenu = new DefaultSubMenu("Vacaciones");
    item = new DefaultMenuItem("Lista Solicitudes");
    item.setIcon("ui-icon-clipboard");
    item.setCommand("/administracion/ListaSolicitud.xhtml");
    cuartoSubmenu.addElement(item);
    //model.addElement(cuartoSubmenu);

    DefaultSubMenu quintoSubmenu = new DefaultSubMenu("Solicitud de Vaciones");
    item = new DefaultMenuItem("Formulario de Solicitud");
    item.setIcon("ui-icon-clipboard");
    item.setCommand("/colaboradores/SolicitudVacaciones.xhtml");
    quintoSubmenu.addElement(item);

    if(((rol.equals("Jefatura") && nombreDept.equals("Recursos Humanos")) || (rol.equals("Jefatura") && nombreDept.equals("Tecnología de Infomación")) || rol.equals("Dirección Corporativa") || rol.equals("Gerencia")) && (!rol.equals("Colaborador"))) {//
        model.addElement(firstSubmenu);//SUBMENU MANTENIMIENTO COLABORADOR
        model.addElement(secondSubmenu);//SUBMENU MANTENIMIENTO DEPARTAMENTO
        model.addElement(tercerSubmenu);//SUBMENU DE REPORTES
        model.addElement(cuartoSubmenu);//SUBMENU LISTA DE VACACIONES
        model.addElement(quintoSubmenu);//SUBMENU SOLICITUD DE VACACIONES
    }else if(rol.equals("Analista") && (!rol.equals("Colaborador"))){
        model.addElement(tercerSubmenu);//SUBMENU DE REPORTES
        model.addElement(cuartoSubmenu);//SUBMENU LISTA DE VACACIONES
        model.addElement(quintoSubmenu);//SUBMENU SOLICITUD DE VACACIONES
    }else if(rol.equals("Jefatura") && (!nombreDept.equals("Recursos Humanos")) && (!nombreDept.equals("Tecnología de Infomación")) && (!rol.equals("Dirección Corporativa")) && (!rol.equals("Supervisor")) &&  (!rol.equals("Analista")) && (!rol.equals("Colaborador"))){//MENU DE JEFES QUE NO SON RECURSOS HUMANOS NI TI
        model.addElement(cuartoSubmenu);//SUBMENU LISTA DE VACACIONES
        model.addElement(quintoSubmenu);//SUBMENU SOLICITUD DE VACACIONES
        //FALTA ASIGNACION DE HORARIOS
    }else if(rol.equals("Supervisor") && (!rol.equals("Colaborador"))){
        model.addElement(cuartoSubmenu);//SUBMENU LISTA DE VACACIONES
        model.addElement(quintoSubmenu);//SUBMENU SOLICITUD DE VACACIONES
    }
    /*else if(rol.equals("Direccion") || rol.equals("Gerencia")||rol.equals("Supervisor")||rol.equals("Analista")){
        model.addElement(tercerSubmenu);
        model.addElement(cuartoSubmenu);
    }*/
    return model;
}
    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }
}