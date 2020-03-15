package s.c.m.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Service;
import s.c.m.entities.Colaborador;
import s.c.m.entities.Departamento;
import s.c.m.entities.Puesto;
import s.c.m.repositories.ColaboradorRepository;

import java.sql.DatabaseMetaData;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ColaboradorService {
    //Es esta clase se implementan las funciones desclaradas en los repositorios para acceder
    // y hacer transacciones en la tabla Colaborador

    @Autowired
    private ColaboradorRepository colaboradorRepository;//Se instancia la objeto para invocar los metodos del repository de colaborador
    private  Colaborador colaborador;

    public List<Colaborador> getAllColaboradores()//Funcion para listar todos los colaboradores
    {
        List<Colaborador> list = new ArrayList<Colaborador>();//Lista de colaboradores.
        colaboradorRepository.findAll().forEach(e -> list.add(e));//se invoca la funcion de repository para llenar la lista de colaboradores
        return list;
    }

    public List<Colaborador> getAllColaboradoresActivos()//Funcion para listar todos lo colaboradores por estado activos.
    {
        Colaborador miC=new Colaborador();
        List<Colaborador> list = new ArrayList<Colaborador>();//Se declara un la lista para consultar todos los colaboradores
        List<Colaborador> listA = new ArrayList<Colaborador>();//Se declara lista para guardar los colaboradores por estado activos
        colaboradorRepository.findAll().forEach(e -> list.add(e));//Se invoca la funcion del repository para llenar la lista

            for (Colaborador c : list) {//For para llenar la lista auxiliar solo con los colaboradores activos
                if (c.getEstado().equals("Activo")) {//If que evalua si el colaborador esta activo
                    listA.add(c);//Se agrega el colaborador a la lista
                }
            }
            return listA;
    }

    public List<Colaborador> getAllColaboradoresActivos(Colaborador a)//Funcion para listar todos lo colaboradores por estado activos.
            //Y solo para los usuarios de Recursos humanos
    {
        Colaborador miC=new Colaborador();
        List<Colaborador> list = new ArrayList<Colaborador>();//Se declara un la lista para consultar todos los colaboradores
        List<Colaborador> listA = new ArrayList<Colaborador>();//Se declara lista para guardar los colaboradores por estado activos
        colaboradorRepository.findAll().forEach(e -> list.add(e));//Se llena la lista con todos los colaboradores

        if(a.getPuesto().getDescripcion().equals("Gerencia")&&a.getDepartamento().getNombre().equals("Recursos Humanos")) {//Si el rol del usuario es Gerencia y del departamento de RH
            for (Colaborador c : list) {//Se procede a llenar la lista
                if (c.getEstado().equals("Activo")) {//cuando el estado sea activo
                    listA.add(c);//Se agrega a la lista
                }
            }

        }else {//Si el rol no es gerencia ni rh
            for (Colaborador c : list) {//Se lista

                if (c.getEstado().equals("Activo")&&c.getDepartamento().getNombre().equals(a.getDepartamento().getNombre())) {//solo si los departamentos del
                    //colaborador y del colaborador logueando son iguales
                    System.out.println(c.getDepartamento());
                    System.out.println(a.getDepartamento());
                    listA.add(c);//Se agregar el colaborador a la lista
                }

            }

        }
        return listA;

    }


    public Date calculaVencimiento() throws ParseException {//Funcion para calcular la fecha de vencimiento para guardar cuando se vencera la clave

        Date fechaDeVencimiento =null;//Se declara un parametro de tipo date
        Calendar fechaDeVencimiento2 = Calendar.getInstance();
        Calendar horaActual = Calendar.getInstance();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

        int mes;//parametro que guarde el mes de la fecha actual
        int dia;//parametro que guarde el dia de la fecha actual
        int year;//parametro que guarde el year de la fecha actual

        String dia2 = null;//atributos para dar formato al dia para guardar la fecha
        String mes2 = null;//atributos para dar formato el mes para guardar la fecha

        mes= horaActual.get(Calendar.MONTH) + 1;//Se llena el mes
        dia= horaActual.get(Calendar.DAY_OF_MONTH);//Se llena el dia
        year = horaActual.get(Calendar.YEAR);//Se llena el year

        if(dia==28 || dia==30||dia == 31){//Si el dia es casi fin de mes
            dia2="0"+1;//se carga dia
        }
        if(mes==10){//si el mes es igual a 10
            year = year +1;//Se le suma uno al year y cambia de anio.
            mes=1;//Y vencera el primer mes
        }
        if(mes==11){//Se el mes es 11
            year = year +1;//Se le suma uno al year y cambia de anio
            mes=2;//Y vencera el segundo mes
        }
        if(mes==12){//Se el mes es 11
            year = year +1;//Se le suma uno al year y cambia de anio
            mes=3;//Y vencera el tercer mes
        }
        else {//Sino es 10 ni 11 ni 12
            mes=mes+3;//Solo cambia el mes sumandole 3
        }

        if (mes < 10) {//Se da formato al string del mes si es menor a 10
            mes2 = "0" + mes;
        }else{
            mes2 = ""+mes;
        }

        if (dia < 10) {//Se da formato al string del dia si es menor a 10
            dia2 = "0" + dia;
        }else{
            dia2 = ""+dia;
        }
        String fVencimiento = year +"-"+mes2+"-"+dia2;//Se da la concatenacion para formar el formato
        fechaDeVencimiento = formato.parse(fVencimiento);
        //System.out.println("La fecha de vencimiento es: " + fechaDeVencimiento);
        return fechaDeVencimiento;//Se retorna la fecha de vencimiento de la clave
    }
    public void actualizaClave(Colaborador c) throws ParseException {//Funcion que se dispara cada vez que se actualiza la clave del colaborador
        c.setFechaVencimiento(calculaVencimiento());//Cuando se cambia la clave se vuelve a calcula la nueva fecha de vencimiento
        colaboradorRepository.save(c);//Se guarda solo la nueva fecha en la base
    }

    public void createColaborador(Colaborador colaborador) throws ParseException {//Funcion para agregar colaborador a la base
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaA = new Date();

        formato.format(fechaA);
        System.out.println("FECHA DE HOY: " + formato.format(fechaA));
        colaborador.setEstado("Activo");//Se llena el objeto colaborador en el campo estado
        colaborador.setJustificacion("N/A");//Y la justificacion de despido no aplica
        colaborador.setFechaVencimiento(fechaA);//se agrega la fecha de vencimiento que la primera vez es la actual de la incorporacion
        colaborador.setClave(colaborador.getPk_idColaborador());//Aca pongo de clave el mismo id del colaborador agregado
        colaboradorRepository.save(colaborador);//Aca se invoca la funcion para agregar el colaborador a la base
    }


    public void deleteColaborador(Colaborador colaborador)//Funcion para desvincular colaborador
    {
        colaborador.toString();
        colaborador.setEstado("Inactivo");//Se le cambia el estado a Inactivo
        colaboradorRepository.save(colaborador);//Se invoca funcion para actualizar estado del colaborador.
    }

    public Colaborador findColaborador(String id) throws Exception//Funcion para encontrar el colaborador por su id
    {
        try
        {
            colaborador=colaboradorRepository.findById(id).get();//Se invoca funcion del repository para consultar el cola por id
        }catch (Exception ex)
        {
            colaborador=null;
        }
        return colaborador;
    }



    public void updateColaborador(Colaborador colaborador) {//Funcion para actualizar al colaborador
        colaboradorRepository.save(colaborador);//Se invoca funcion para actualizar datos en el colaborador en la base
    }


    public Colaborador findColaboradorEncargado(Departamento idD, Puesto idP)//Funcion para encontrar el colaborador encargado del departamento
    {
        if(colaboradorRepository.findByDepartamentoAndPuestoAndEstado(idD,idP,"Activo")==null){//Primero se busca si exite colaborador de ese departamento con es puesto
            System.out.println("No se encontro el encargado con rol " + idP.getDescripcion());
        }else{
            System.out.println("Si encontro el encargado con rol " + idP.getDescripcion());
        }
        return colaboradorRepository.findByDepartamentoAndPuestoAndEstado(idD,idP,"Activo");//Si es asi se retorna el colaborador encargado
    }

    public List<Colaborador> findColaboradorDepartamento(Departamento idD){//Funcion para consultar colaborador por departamento
        List<Colaborador> list = new ArrayList<Colaborador>();
        List<Colaborador> listA = new ArrayList<Colaborador>();
        colaboradorRepository.findByDepartamento(idD).forEach(e -> list.add(e));//Se invoca la funcion para buscar los colaboradores por departamento
        for(Colaborador c: list){//Se lista de estos colaboradores encontrados
            if(c.getEstado().equals("Activo")){//Solo los activos
                listA.add(c);
            }
        }
        return listA;
    }


}
