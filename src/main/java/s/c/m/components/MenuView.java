package s.c.m.components;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;

@ManagedBean
public class MenuView {
    //Clase para generar los menus dinamicos

    private MenuModel model;

    public MenuModel construyeMenuPorRol(String rol,String nombreDept){//Funcion para construir menu dinamico por rol y nombre de dept
        model = new DefaultMenuModel();

        //First submenu
        //Se generan submenus de cada modulo
        DefaultSubMenu firstSubmenu = new DefaultSubMenu("Colaborador");
        DefaultMenuItem item = new DefaultMenuItem("Mantenimiento Colaborador");
        item.setUrl("MantenimientoColaborador.xhtml");
        item.setIcon("ui-icon-plus");
        firstSubmenu.addElement(item);
        //model.addElement(firstSubmenu);

        item = new DefaultMenuItem("Asignar Horario");
        item.setIcon("ui-icon-clipboard");
        item.setCommand("asignacionHorario.xhtml");
        firstSubmenu.addElement(item);

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

        item = new DefaultMenuItem("Colaborador Detallado");
        item.setIcon("ui-icon-clipboard");
        item.setCommand("#{reporteBean.limpiaObjetos}");
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
        item.setCommand("ListaSolicitud.xhtml");
        cuartoSubmenu.addElement(item);
        //model.addElement(cuartoSubmenu);

        DefaultSubMenu quintoSubmenu = new DefaultSubMenu("Solicitud de Vaciones");
        item = new DefaultMenuItem("Formulario de Solicitud");
        item.setIcon("ui-icon-clipboard");
        item.setCommand("SolicitudVacaciones.xhtml");
        quintoSubmenu.addElement(item);

        DefaultSubMenu sextoSubmenu = new DefaultSubMenu("Colaborador");
        item = new DefaultMenuItem("Asignar Horario");
        item.setIcon("ui-icon-clipboard");
        item.setCommand("asignacionHorario.xhtml");
        sextoSubmenu.addElement(item);

        //Segun el rol se agrupan los submenus
       if(((rol.equals("Dirección Corporativa") && nombreDept.equals("Recursos Humanos")) || rol.equals("Analista") || (rol.equals("Dirección Corporativa") && nombreDept.equals("Tecnología de Infomación")) || rol.equals("Dirección Corporativa") || rol.equals("Gerencia"))) {//
            model.addElement(firstSubmenu);//SUBMENU MANTENIMIENTO COLABORADOR
            model.addElement(secondSubmenu);//SUBMENU MANTENIMIENTO DEPARTAMENTO
            model.addElement(tercerSubmenu);//SUBMENU DE REPORTES
            model.addElement(cuartoSubmenu);//SUBMENU LISTA DE VACACIONES
            model.addElement(quintoSubmenu);//SUBMENU SOLICITUD DE VACACIONES
        }else if(rol.equals("Jefatura")||rol.equals("Supervisor")){//MENU DE JEFES QUE NO SON RECURSOS HUMANOS NI TI
            model.addElement(sextoSubmenu);
            model.addElement(cuartoSubmenu);//SUBMENU LISTA DE VACACIONES
            model.addElement(quintoSubmenu);//SUBMENU SOLICITUD DE VACACIONES
            //FALTA ASIGNACION DE HORARIOS
        }
        else if((rol.equals("Colaborador"))){
            model.addElement(quintoSubmenu);//SUBMENU SOLICITUD DE VACACIONES
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
