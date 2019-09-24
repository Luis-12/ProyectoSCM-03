package s.c.m.converters;

import s.c.m.beans.DepartamentoBean;
import s.c.m.entities.Departamento;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "departamentoConverter")
public class DepartementoConverter implements Converter {
    @Override
    public Departamento getAsObject(FacesContext facesContext, UIComponent uiComponent, String idDepartamento) {
        ValueExpression vex =
                FacesContext.getCurrentInstance().getApplication().getExpressionFactory()
                        .createValueExpression(FacesContext.getCurrentInstance().getELContext(),
                                "#{departamentoBean}", DepartamentoBean.class);

        DepartamentoBean departamentos = (DepartamentoBean) vex.getValue(FacesContext.getCurrentInstance().getELContext());
        return departamentos.obtieneDepartamento(idDepartamento);//obtieneDepartemento es una funcion del bean
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object departamento) {
        String idD = ((Departamento)departamento).getPk_idDepartamento();
        return idD;//.toString();
    }
}
