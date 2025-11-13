package Controller;

import Dao.FuncionarioDao;
import Domain.Funcionario;
import java.sql.SQLException;
import java.util.List;

public class FuncionarioController {

    private FuncionarioDao funcionarioDao;

    public FuncionarioController() {
        funcionarioDao = new FuncionarioDao();
    }

    public List<Funcionario> getFuncionarios() throws SQLException {
        return funcionarioDao.getFuncionarios();
    }

    public Funcionario getFuncionarioById(int id) throws SQLException {
        return funcionarioDao.getFuncionarioById(id);
    }

    public void createFuncionario(Funcionario funcionario) throws SQLException {
        funcionarioDao.createFuncionario(funcionario);
    }

    public void updateFuncionario(Funcionario funcionario) throws SQLException {
        funcionarioDao.updateFuncionario(funcionario);
    }

    public void deleteFuncionario(int id) throws SQLException {
        funcionarioDao.deleteFuncionario(id);
    }
}
