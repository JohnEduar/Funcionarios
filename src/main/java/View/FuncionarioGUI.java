package View;

import Dao.FuncionarioDao;
import Domain.Funcionario;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioGUI extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private List<Funcionario> funcionarios;

    private JTextField txtIdentificacion;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JComboBox<Funcionario.TipoIdentificacion> cmbTipoId;
    private JComboBox<Funcionario.EstadoCivil> cmbEstadoCivil;
    private JComboBox<Funcionario.Sexo> cmbSexo;
    private JTextField txtFechaNacimiento;
    private JTextField txtTelefono;
    private JTextField txtDireccion;

    public FuncionarioGUI() {
        funcionarios = new ArrayList<>();
        initComponents();
        cargarFuncionariosDesdeDB(); // Agregar esta línea
        setTitle("Gestión de Funcionarios");
        setSize(1400, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));

        // Panel de formulario
        JPanel formPanel = createFormPanel();
        add(formPanel, BorderLayout.NORTH);

        // Panel de tabla
        JPanel tablePanel = createTablePanel();
        add(tablePanel, BorderLayout.CENTER);

        // Panel de botones
        JPanel buttonPanel = createButtonPanel();
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void cargarFuncionariosDesdeDB() {
        try {
            FuncionarioDao dao = new FuncionarioDao();
            funcionarios = dao.getFuncionarios();

            // Limpiar la tabla antes de cargar
            tableModel.setRowCount(0);

            // Agregar cada funcionario a la tabla
            for (Funcionario f : funcionarios) {
                agregarFilaTabla(f);
            }

            System.out.println("Se cargaron " + funcionarios.size() + " funcionarios");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar funcionarios: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Datos del Funcionario"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Tipo Identificación
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Tipo Identificación:"), gbc);
        gbc.gridx = 1;
        cmbTipoId = new JComboBox<>(Funcionario.TipoIdentificacion.values());
        panel.add(cmbTipoId, gbc);

        // Identificación
        gbc.gridx = 2; gbc.gridy = 0;
        panel.add(new JLabel("Identificación:"), gbc);
        gbc.gridx = 3;
        txtIdentificacion = new JTextField(15);
        panel.add(txtIdentificacion, gbc);

        // Nombre
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        txtNombre = new JTextField(15);
        panel.add(txtNombre, gbc);

        // Apellido
        gbc.gridx = 2; gbc.gridy = 1;
        panel.add(new JLabel("Apellido:"), gbc);
        gbc.gridx = 3;
        txtApellido = new JTextField(15);
        panel.add(txtApellido, gbc);

        // Estado Civil
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Estado Civil:"), gbc);
        gbc.gridx = 1;
        cmbEstadoCivil = new JComboBox<>(Funcionario.EstadoCivil.values());
        panel.add(cmbEstadoCivil, gbc);

        // Sexo
        gbc.gridx = 2; gbc.gridy = 2;
        panel.add(new JLabel("Sexo:"), gbc);
        gbc.gridx = 3;
        cmbSexo = new JComboBox<>(Funcionario.Sexo.values());
        panel.add(cmbSexo, gbc);

        // Fecha Nacimiento
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Fecha Nacimiento (yyyy-MM-dd):"), gbc);
        gbc.gridx = 1;
        txtFechaNacimiento = new JTextField(15);
        panel.add(txtFechaNacimiento, gbc);

        // Teléfono
        gbc.gridx = 2; gbc.gridy = 3;
        panel.add(new JLabel("Teléfono:"), gbc);
        gbc.gridx = 3;
        txtTelefono = new JTextField(15);
        panel.add(txtTelefono, gbc);

        // Dirección
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Dirección:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 3;
        txtDireccion = new JTextField();
        panel.add(txtDireccion, gbc);

        return panel;
    }

    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Lista de Funcionarios"));

        String[] columns = {"Tipo ID", "Identificación", "Nombre", "Apellido",
                "Estado Civil", "Sexo", "Fecha Nac.", "Teléfono", "Dirección"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel);
        table.setRowHeight(35);
        configurarAnchoColumnas();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarDatosSeleccionados();
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel.setBackground(new Color(245, 245, 245));

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBackground(new Color(106, 230, 189));
        btnAgregar.addActionListener(e -> agregarFuncionario());

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setBackground(new Color(106, 226, 230));
        btnActualizar.addActionListener(e -> actualizarFuncionario());

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBackground(new Color(231, 142, 144));
        btnEliminar.addActionListener(e -> eliminarFuncionario());

        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBackground(new Color(106, 197, 230));
        btnLimpiar.addActionListener(e -> limpiarCampos());

        panel.add(btnAgregar);
        panel.add(btnActualizar);
        panel.add(btnLimpiar);
        panel.add(btnEliminar);

        return panel;
    }

    private void configurarAnchoColumnas() {
        table.getColumnModel().getColumn(0).setPreferredWidth(120);  // Tipo ID
        table.getColumnModel().getColumn(1).setPreferredWidth(120);  // Identificación
        table.getColumnModel().getColumn(2).setPreferredWidth(150);  // Nombre
        table.getColumnModel().getColumn(3).setPreferredWidth(150);  // Apellido
        table.getColumnModel().getColumn(4).setPreferredWidth(120);  // Estado Civil
        table.getColumnModel().getColumn(5).setPreferredWidth(80);   // Sexo
        table.getColumnModel().getColumn(6).setPreferredWidth(100);  // Fecha Nac.
        table.getColumnModel().getColumn(7).setPreferredWidth(120);  // Teléfono
        table.getColumnModel().getColumn(8).setPreferredWidth(250);  // Dirección
    }

    private void agregarFuncionario() {
        try {
            Funcionario funcionario = obtenerFuncionarioDesdeFormulario();
            funcionarios.add(funcionario);
            agregarFilaTabla(funcionario);
            limpiarCampos();
            JOptionPane.showMessageDialog(this, "Funcionario agregado exitosamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarFuncionario() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un funcionario");
            return;
        }

        try {
            Funcionario funcionario = obtenerFuncionarioDesdeFormulario();
            funcionarios.set(selectedRow, funcionario);
            actualizarFilaTabla(selectedRow, funcionario);
            limpiarCampos();
            JOptionPane.showMessageDialog(this, "Funcionario actualizado exitosamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarFuncionario() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un funcionario");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Está seguro de eliminar este funcionario?",
                "Confirmar", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            funcionarios.remove(selectedRow);
            tableModel.removeRow(selectedRow);
            limpiarCampos();
            JOptionPane.showMessageDialog(this, "Funcionario eliminado exitosamente");
        }
    }

    private Funcionario obtenerFuncionarioDesdeFormulario() {
        String identificacion = txtIdentificacion.getText().trim();
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        Funcionario.TipoIdentificacion tipoId = (Funcionario.TipoIdentificacion) cmbTipoId.getSelectedItem();
        Funcionario.EstadoCivil estadoCivil = (Funcionario.EstadoCivil) cmbEstadoCivil.getSelectedItem();
        Funcionario.Sexo sexo = (Funcionario.Sexo) cmbSexo.getSelectedItem();
        String telefono = txtTelefono.getText().trim();
        String direccion = txtDireccion.getText().trim();

        LocalDate fechaNacimiento = LocalDate.parse(txtFechaNacimiento.getText().trim());

        Funcionario funcionario = new Funcionario();
        funcionario.setIdentificacion(identificacion);
        funcionario.setNombre(nombre);
        funcionario.setApellidos(apellido);
        funcionario.setTipoIdentificacion(tipoId);
        funcionario.setEstadoCivil(estadoCivil);
        funcionario.setSexo(sexo);
        funcionario.setFechaNacimiento(fechaNacimiento);
        funcionario.setTelefono(telefono);
        funcionario.setDireccion(direccion);

        return funcionario;
    }

    private void agregarFilaTabla(Funcionario f) {
        Object[] row = {
                f.getTipoIdentificacion().getDescripcion(),
                f.getIdentificacion(),
                f.getNombre(),
                f.getApellidos(),
                f.getEstadoCivil().getDescripcion(),
                f.getSexo().getDescripcion(),
                f.getFechaNacimiento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                f.getTelefono(),
                f.getDireccion()
        };
        tableModel.addRow(row);
    }

    private void actualizarFilaTabla(int row, Funcionario f) {
        tableModel.setValueAt(f.getTipoIdentificacion().getDescripcion(), row, 0);
        tableModel.setValueAt(f.getIdentificacion(), row, 1);
        tableModel.setValueAt(f.getNombre(), row, 2);
        tableModel.setValueAt(f.getApellidos(), row, 3);
        tableModel.setValueAt(f.getEstadoCivil().getDescripcion(), row, 4);
        tableModel.setValueAt(f.getSexo().getDescripcion(), row, 5);
        tableModel.setValueAt(f.getFechaNacimiento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), row, 6);
        tableModel.setValueAt(f.getTelefono(), row, 7);
        tableModel.setValueAt(f.getDireccion(), row, 8);
    }

    private void cargarDatosSeleccionados() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            Funcionario f = funcionarios.get(selectedRow);
            cmbTipoId.setSelectedItem(f.getTipoIdentificacion());
            txtIdentificacion.setText(f.getIdentificacion());
            txtNombre.setText(f.getNombre());
            txtApellido.setText(f.getApellidos());
            cmbEstadoCivil.setSelectedItem(f.getEstadoCivil());
            cmbSexo.setSelectedItem(f.getSexo());
            txtFechaNacimiento.setText(f.getFechaNacimiento().toString());
            txtTelefono.setText(f.getTelefono());
            txtDireccion.setText(f.getDireccion());
        }
    }

    private void limpiarCampos() {
        txtIdentificacion.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtFechaNacimiento.setText("");
        txtTelefono.setText("");
        txtDireccion.setText("");
        cmbTipoId.setSelectedIndex(0);
        cmbEstadoCivil.setSelectedIndex(0);
        cmbSexo.setSelectedIndex(0);
        table.clearSelection();
    }

    public static void main(String[] args) {
        //Cambiar fuentes predeterminadas de UIManager
        UIManager.put("Label.font", new Font("Segoe UI", Font.PLAIN, 14));
        UIManager.put("TextField.font", new Font("Segoe UI", Font.PLAIN, 16));
        UIManager.put("ComboBox.font", new Font("Segoe UI", Font.PLAIN, 16));
        UIManager.put("Button.font", new Font("Segoe UI", Font.BOLD, 14));
        UIManager.put("Table.font", new Font("Segoe UI", Font.PLAIN, 16));
        UIManager.put("TableHeader.font", new Font("Segoe UI", Font.BOLD, 14));

        SwingUtilities.invokeLater(() -> {
            new FuncionarioGUI().setVisible(true);
        });
    }
}
