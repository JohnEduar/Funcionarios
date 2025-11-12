package View;

import Controller.FuncionarioController;
import Domain.Funcionario;
import java.util.List;

public class Funcionarios {

    public static void getFuncionarios(FuncionarioController funcionarioController) {
        try {
            List<Funcionario> funcionarios = funcionarioController.getFuncionarios();
            if (funcionarios.isEmpty()) {
                System.out.println("No hay funcionarios disponibles.");
            } else {
                for (Funcionario funcionario : funcionarios) {
                    String tipo = funcionario.getTipoIdentificacion() != null
                            ? funcionario.getTipoIdentificacion().getDescripcion()
                            : "null";
                    String estado = funcionario.getEstadoCivil() != null
                            ? funcionario.getEstadoCivil().getDescripcion()
                            : "null";
                    String sexo = funcionario.getSexo() != null
                            ? funcionario.getSexo().getDescripcion()
                            : "null";

                    System.out.println("ID: " + funcionario.getId() +
                            ", Tipo de Identificación: " + tipo +
                            ", Identificación: " + funcionario.getIdentificacion() +
                            ", Nombre: " + funcionario.getNombre() +
                            ", Apellido: " + funcionario.getApellidos() +
                            ", Estado Civil: " + estado +
                            ", Sexo: " + sexo +
                            ", Dirección: " + funcionario.getDireccion() +
                            ", Teléfono: " + funcionario.getTelefono() +
                            ", Fecha de Nacimiento: " + funcionario.getFechaNacimiento());
                    System.out.println("----------------------------------------");
                }
            }
        } catch (Exception e) {
            System.err.println("Error al obtener los funcionarios: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        FuncionarioController funcionarioController = new FuncionarioController();
        getFuncionarios(funcionarioController);
    }
}
