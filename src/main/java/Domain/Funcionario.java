package Domain;

import java.time.LocalDate;

public class Funcionario {

    private int id;

    public enum TipoIdentificacion {
        CEDULA_CIUDADANIA("Cédula Ciudadanía"),
        PASAPORTE("Pasaporte"),
        PPT("PPT");

        private final String descripcion;

        TipoIdentificacion(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getDescripcion() {
            return descripcion;
        }

        @Override
        public String toString() {
            return descripcion;
        }

        public static TipoIdentificacion fromDescripcion(String desc) {
            for (TipoIdentificacion tipo : values()) {
                if (tipo.descripcion.equalsIgnoreCase(desc)) {
                    return tipo;
                }
            }
            throw new IllegalArgumentException("Tipo de identificación no válido: " + desc);
        }
    }

    public enum EstadoCivil {
        SOLTERO("Soltero"),
        CASADO("Casado"),
        VIUDO("Viudo");

        private final String descripcion;

        EstadoCivil(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getDescripcion() {
            return descripcion;
        }

        @Override
        public String toString() {
            return descripcion;
        }

        public static EstadoCivil fromDescripcion(String desc) {
            for (EstadoCivil estado : values()) {
                if (estado.descripcion.equalsIgnoreCase(desc)) {
                    return estado;
                }
            }
            throw new IllegalArgumentException("Estado civil no válido: " + desc);
        }
    }

    public enum Sexo {
        MASCULINO("Masculino"),
        FEMENINO("Femenino");

        private final String descripcion;

        Sexo(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getDescripcion() {
            return descripcion;
        }

        @Override
        public String toString() {
            return descripcion;
        }

        public static Sexo fromDescripcion(String desc) {
            for (Sexo sexo : values()) {
                if (sexo.descripcion.equalsIgnoreCase(desc)) {
                    return sexo;
                }
            }
            throw new IllegalArgumentException("Sexo no válido: " + desc);
        }
    }

    private TipoIdentificacion tipoIdentificacion;
    private String identificacion;
    private String nombre;
    private String apellidos;
    private EstadoCivil estadoCivil;
    private Sexo sexo;
    private String direccion;
    private String telefono;
    private LocalDate fechaNacimiento;

    // Constructores, getters y setters

    public Funcionario() {
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public TipoIdentificacion getTipoIdentificacion() {
        return tipoIdentificacion;
    }
    public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getIdentificacion() {
        return identificacion;
    }
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }
    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public Sexo getSexo() {
        return sexo;
    }
    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "id=" + id +
                ", tipoIdentificacion=" + (tipoIdentificacion != null ? tipoIdentificacion.toString() : "null") +
                ", identificacion='" + identificacion + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", estadoCivil=" + (estadoCivil != null ? estadoCivil.toString() : "null") +
                ", sexo=" + (sexo != null ? sexo.toString() : "null") +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", fechaNacimiento=" + (fechaNacimiento != null ? fechaNacimiento.toString() : "null") +
                '}';
    }
}
