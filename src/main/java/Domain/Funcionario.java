package Domain;

import java.time.LocalDateTime;

public class Funcionario {

    private int id;

    public enum TipoIdentificacion {
        CEDULA_CIUDADANIA("Cédula Ciudadanía"),
        PASAPORTE("Pasaporte"),
        PPT("PPT");

        private final String valorBD;

        TipoIdentificacion(String valorBD) {
            this.valorBD = valorBD;
        }

        public String getValorBD() {
            return valorBD;
        }

        public static TipoIdentificacion fromValorBD(String valor) {
            for (TipoIdentificacion tipo : values()) {
                if (tipo.valorBD.equals(valor)) {
                    return tipo;
                }
            }
            throw new IllegalArgumentException("Tipo de identificación no válido: " + valor);
        }
    }

    public enum EstadoCivil {
        SOLTERO("Soltero"),
        CASADO("Casado"),
        VIUDO("Viudo");

        private final String valorBD;

        EstadoCivil(String valorBD) {
            this.valorBD = valorBD;
        }

        public String getValorBD() {
            return valorBD;
        }

        public static EstadoCivil fromValorBD(String valor) {
            for (EstadoCivil estado : values()) {
                if (estado.valorBD.equals(valor)) {
                    return estado;
                }
            }
            throw new IllegalArgumentException("Estado civil no válido: " + valor);
        }
    }

    public enum Sexo {
        MASCULINO("Masculino"),
        FEMENINO("Femenino"),
        OTRO("Otro");

        private final String valorBD;

        Sexo(String valorBD) {
            this.valorBD = valorBD;
        }

        public String getValorBD() {
            return valorBD;
        }

        public static Sexo fromValorBD(String valor) {
            for (Sexo s : values()) {
                if (s.valorBD.equals(valor)) {
                    return s;
                }
            }
            throw new IllegalArgumentException("Sexo no válido: " + valor);
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
    private LocalDateTime fechaNacimiento;

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

    public LocalDateTime getFechaNacimiento() {
        return fechaNacimiento;
    }
    public void setFechaNacimiento(LocalDateTime fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
