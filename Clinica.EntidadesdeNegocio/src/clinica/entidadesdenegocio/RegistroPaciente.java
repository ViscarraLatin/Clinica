
package clinica.entidadesdenegocio;

import java.util.ArrayList;


public class RegistroPaciente {
     private int id;
    private String nombre;
    private String apellido;
     private String dui;
      private String genero;
       private String fechaNac;
        private String lugarNac;
         private String ocupacion;
         private String telefono;
          private String celular;
           private String email;
            private String estadoCivil;
             private int edad;
             private String direccion;
             private int peso;
             private String estatura;
             
    private int top_aux;
    private ArrayList<Expediente> expedientes;

    public RegistroPaciente() {
    }

    public RegistroPaciente(int id, String nombre, String apellido, String dui, String genero, String fechaNac, String lugarNac, String ocupacion, String telefono, String celular, String email, String estadoCivil, int edad, String direccion, int peso, String estatura) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dui = dui;
        this.genero = genero;
        this.fechaNac = fechaNac;
        this.lugarNac = lugarNac;
        this.ocupacion = ocupacion;
        this.telefono = telefono;
        this.celular = celular;
        this.email = email;
        this.estadoCivil = estadoCivil;
        this.edad = edad;
        this.direccion = direccion;
        this.peso = peso;
        this.estatura = estatura;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getLugarNac() {
        return lugarNac;
    }

    public void setLugarNac(String lugarNac) {
        this.lugarNac = lugarNac;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getEstatura() {
        return estatura;
    }

    public void setEstatura(String estatura) {
        this.estatura = estatura;
    }

    public int getTop_aux() {
        return top_aux;
    }

    public void setTop_aux(int top_aux) {
        this.top_aux = top_aux;
    }

    public ArrayList<Expediente> getExpedientes() {
        return expedientes;
    }

    public void setExpedientes(ArrayList<Expediente> expedientes) {
        this.expedientes = expedientes;
    }
    
    
}
