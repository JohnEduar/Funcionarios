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
}
