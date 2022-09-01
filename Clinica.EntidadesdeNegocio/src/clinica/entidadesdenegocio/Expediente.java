package clinica.entidadesdenegocio;


public class Expediente {
     private int id;
     private int idRegistroPaciente;
     private String motivoConsulta;
     private String sintomas;
     private String signosVitales;
     private String descripcion;
     private String examenesComp;
     private String diagnostico;
     private String tratamiento;
     private int top_aux;
     private RegistroPaciente registroPaciente;

    public Expediente() {
    }

    public Expediente(int id, int idRegistroPaciente, String motivoConsulta, String sintomas, String signosVitales, String descripcion, String examenesComp, String diagnostico, String tratamiento) {
        this.id = id;
        this.idRegistroPaciente = idRegistroPaciente;
        this.motivoConsulta = motivoConsulta;
        this.sintomas = sintomas;
        this.signosVitales = signosVitales;
        this.descripcion = descripcion;
        this.examenesComp = examenesComp;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdRegistroPaciente() {
        return idRegistroPaciente;
    }

    public void setIdRegistroPaciente(int idRegistroPaciente) {
        this.idRegistroPaciente = idRegistroPaciente;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public String getSignosVitales() {
        return signosVitales;
    }

    public void setSignosVitales(String signosVitales) {
        this.signosVitales = signosVitales;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getExamenesComp() {
        return examenesComp;
    }

    public void setExamenesComp(String examenesComp) {
        this.examenesComp = examenesComp;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public int getTop_aux() {
        return top_aux;
    }

    public void setTop_aux(int top_aux) {
        this.top_aux = top_aux;
    }

    public RegistroPaciente getRegistroPaciente() {
        return registroPaciente;
    }

    public void setRegistroPaciente(RegistroPaciente registroPaciente) {
        this.registroPaciente = registroPaciente;
    }

    
    

}
