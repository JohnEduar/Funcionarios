package Dao;

import Domain.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FuncionarioDao {

    private static final String GET_FUNCIONARIOS = "SELECT * FROM funcionarios";

    private static final String CREATE_FUNCIONARIO = "INSERT INTO funcionarios (tipo_identificacion, identificacion," +
            "nombre, apellidos, estado_civil, sexo, direccion, telefono, fecha_nacimiento) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String GET_FUNCIONARIO_BY_ID = "SELECT * FROM funcionarios WHERE idFuncionario = ?";

    private static final String UPDATE_FUNCIONARIO = "UPDATE funcionarios SET tipo_identificacion = ?, identificacion = ?, nombre = ?, apellidos = ?," +
            "estado_civil = ?, sexo = ?, direccion = ?, telefono = ?, fecha_nacimiento = ? WHERE idFuncionario = ?";

    private static final String DELETE_FUNCIONARIO = "DELETE FROM funcionarios WHERE idFuncionario = ?";

    public List<Funcionario> getFuncionarios() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Funcionario> funcionarios = new java.util.ArrayList<>();

        try {

            connection = Config.ConnectionConfig.getConnection();
            preparedStatement = connection.prepareStatement(GET_FUNCIONARIOS);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(resultSet.getInt("idFuncionario"));
                funcionario.setTipoIdentificacion(Funcionario.TipoIdentificacion.fromValorBD(resultSet.getString("tipo_identificacion")));
                funcionario.setIdentificacion(resultSet.getString("identificacion"));
                funcionario.setNombre(resultSet.getString("nombre"));
                funcionario.setApellidos(resultSet.getString("apellidos"));
                funcionario.setEstadoCivil(Funcionario.EstadoCivil.fromValorBD(resultSet.getString("estado_civil")));
                funcionario.setSexo(Funcionario.Sexo.fromValorBD(resultSet.getString("sexo")));
                funcionario.setDireccion(resultSet.getString("direccion"));
                funcionario.setTelefono(resultSet.getString("telefono"));
                funcionario.setFechaNacimiento(resultSet.getTimestamp("fecha_nacimiento").toLocalDateTime());
                funcionarios.add(funcionario);
            }
            return funcionarios;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
