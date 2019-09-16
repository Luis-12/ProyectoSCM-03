package s.c.m.converters;

import s.c.m.beans.PuestoBean;
import s.c.m.entities.Puesto;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "puestoConverter")
public class PuestoConverter implements Converter {
    @Override
    public Puesto getAsObject(FacesContext facesContext, UIComponent uiComponent, String idPuesto) {
        ValueExpression vex =
                FacesContext.getCurrentInstance().getApplication().getExpressionFactory()
                        .createValueExpression(FacesContext.getCurrentInstance().getELContext(),
                                "#{puestoBean}", PuestoBean.class);

        PuestoBean puestos = (PuestoBean) vex.getValue(FacesContext.getCurrentInstance().getELContext());
        System.out.println(puestos.obtienePuestos(Integer.valueOf(idPuesto)).toString());
        return puestos.obtienePuestos(Integer.valueOf(idPuesto));

    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object puesto) {
        //((TipoMocion)tipoMocion).getID_TIPO_MOCION();
        String idP = String.valueOf(((Puesto)puesto).getPk_idPuesto());
        return idP;//.toString();
    }
}
