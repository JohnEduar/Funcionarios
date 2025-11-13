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
                funcionario.setTipoIdentificacion(Funcionario.TipoIdentificacion.fromDescripcion(resultSet.getString("tipo_identificacion")));
                funcionario.setIdentificacion(resultSet.getString("identificacion"));
                funcionario.setNombre(resultSet.getString("nombre"));
                funcionario.setApellidos(resultSet.getString("apellidos"));
                funcionario.setEstadoCivil(Funcionario.EstadoCivil.fromDescripcion(resultSet.getString("estado_civil")));
                funcionario.setSexo(Funcionario.Sexo.fromDescripcion(resultSet.getString("sexo")));
                funcionario.setDireccion(resultSet.getString("direccion"));
                funcionario.setTelefono(resultSet.getString("telefono"));
                funcionario.setFechaNacimiento(resultSet.getDate("fecha_nacimiento").toLocalDate());
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

    public void createFuncionario(Funcionario funcionario) throws SQLException {
        // Implementation for creating a new Funcionario
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = Config.ConnectionConfig.getConnection();
            preparedStatement = connection.prepareStatement(CREATE_FUNCIONARIO);
            preparedStatement.setString(1, funcionario.getTipoIdentificacion().getDescripcion());
            preparedStatement.setString(2, funcionario.getIdentificacion());
            preparedStatement.setString(3, funcionario.getNombre());
            preparedStatement.setString(4, funcionario.getApellidos());
            preparedStatement.setString(5, funcionario.getEstadoCivil().getDescripcion());
            preparedStatement.setString(6, funcionario.getSexo().getDescripcion());
            preparedStatement.setString(7, funcionario.getDireccion());
            preparedStatement.setString(8, funcionario.getTelefono());
            preparedStatement.setDate(9, java.sql.Date.valueOf(funcionario.getFechaNacimiento()));
            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public Funcionario getFuncionarioById(int id) throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Funcionario funcionario = null;

        try {
            connection = Config.ConnectionConfig.getConnection();
            preparedStatement = connection.prepareStatement(GET_FUNCIONARIO_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                funcionario = new Funcionario();
                funcionario.setId(resultSet.getInt("idFuncionario"));
                funcionario.setTipoIdentificacion(Funcionario.TipoIdentificacion.fromDescripcion(resultSet.getString("tipo_identificacion")));
                funcionario.setIdentificacion(resultSet.getString("identificacion"));
                funcionario.setNombre(resultSet.getString("nombre"));
                funcionario.setApellidos(resultSet.getString("apellidos"));
                funcionario.setEstadoCivil(Funcionario.EstadoCivil.fromDescripcion(resultSet.getString("estado_civil")));
                funcionario.setSexo(Funcionario.Sexo.fromDescripcion(resultSet.getString("sexo")));
                funcionario.setDireccion(resultSet.getString("direccion"));
                funcionario.setTelefono(resultSet.getString("telefono"));
                funcionario.setFechaNacimiento(resultSet.getDate("fecha_nacimiento").toLocalDate());
            }
            return funcionario;

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

    public void updateFuncionario(Funcionario funcionario) throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = Config.ConnectionConfig.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_FUNCIONARIO);
            preparedStatement.setString(1, funcionario.getTipoIdentificacion().getDescripcion());
            preparedStatement.setString(2, funcionario.getIdentificacion());
            preparedStatement.setString(3, funcionario.getNombre());
            preparedStatement.setString(4, funcionario.getApellidos());
            preparedStatement.setString(5, funcionario.getEstadoCivil().getDescripcion());
            preparedStatement.setString(6, funcionario.getSexo().getDescripcion());
            preparedStatement.setString(7, funcionario.getDireccion());
            preparedStatement.setString(8, funcionario.getTelefono());
            preparedStatement.setDate(9, java.sql.Date.valueOf(funcionario.getFechaNacimiento()));
            preparedStatement.setInt(10, funcionario.getId());
            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void deleteFuncionario(int id) throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = Config.ConnectionConfig.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_FUNCIONARIO);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
