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
/*
    @PostConstruct
    public void init(){
        model = new DefaultMenuModel();

        //First submenu
        DefaultSubMenu firstSubmenu = new DefaultSubMenu("Colaborador");
        DefaultMenuItem item = new DefaultMenuItem("Incorporar Colaborador");
        item.setUrl("MantenimientoColaborador.xhtml");
        item.setIcon("ui-icon-plus");
        firstSubmenu.addElement(item);
        model.addElement(firstSubmenu);

        //Second submenu
        DefaultSubMenu secondSubmenu = new DefaultSubMenu("Departamento");
        item = new DefaultMenuItem("Mantenimiento Departamento");
        item.setIcon("ui-icon-wrench");
        item.setCommand("MantenimientoDepartamento.xhtml");
        secondSubmenu.addElement(item);
        model.addElement(secondSubmenu);

        //TERCER SUBMENU
        DefaultSubMenu tercerSubmenu = new DefaultSubMenu("Informes");
        item = new DefaultMenuItem("Cantidad Laborando");
        item.setIcon("ui-icon-clipboard");
        item.setCommand("CantidadLaborando.xhtml");
        tercerSubmenu.addElement(item);

        item = new DefaultMenuItem("Colaboradores laborando en un lapso de tiempo");
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

        item = new DefaultMenuItem("Reporte Tardias");
        item.setIcon("ui-icon-clipboard");
        item.setCommand("ReporteTardias.xhtml");
        tercerSubmenu.addElement(item);

        model.addElement(tercerSubmenu);

        //Cuarto submenu
        DefaultSubMenu cuartoSubmenu = new DefaultSubMenu("Necesito Vacaciones");
        item = new DefaultMenuItem("Lista Solicitudes");
        item.setIcon("ui-icon-clipboard");
        item.setCommand("ListaSolicitud.xhtml");
        cuartoSubmenu.addElement(item);
        model.addElement(cuartoSubmenu);

    }
*/
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

    item = new DefaultMenuItem("Colaboradores laborando en un lapso de tiempo");
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

    item = new DefaultMenuItem("Reporte Tardias");
    item.setIcon("ui-icon-clipboard");
    item.setCommand("ReporteTardias.xhtml");
    tercerSubmenu.addElement(item);

    //model.addElement(tercerSubmenu);

    //Cuarto submenu
    DefaultSubMenu cuartoSubmenu = new DefaultSubMenu("Vacaciones");
    item = new DefaultMenuItem("Lista Solicitudes");
    item.setIcon("ui-icon-clipboard");
    item.setCommand("ListaSolicitud.xhtml");
    cuartoSubmenu.addElement(item);
    //model.addElement(cuartoSubmenu);

    if(rol.equals("Jefatura") && nombreDept.equals("Recursos Humanos")) {
        model.addElement(firstSubmenu);
        model.addElement(secondSubmenu);
        model.addElement(tercerSubmenu);
        model.addElement(cuartoSubmenu);
    }else if(rol.equals("Jefatura") && (!nombreDept.equals("Recursos Humanos"))){
        model.addElement(tercerSubmenu);
        model.addElement(cuartoSubmenu);
    }
    return model;
}
    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }
}
