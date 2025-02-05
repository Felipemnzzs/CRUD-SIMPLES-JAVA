package Modelos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import Modelos.Usuario;
import Modelos.banco;

public class TelaListar extends JFrame {
    
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnRemover, btnAtualizar;
    private TelaPrincipal principal; 
    
    public TelaListar(TelaPrincipal principal) {
        this.principal = principal;
        setTitle("Listar Usuários");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initComponents();
        carregarUsuarios();
    }
    
    private void initComponents() {
        tableModel = new DefaultTableModel(new Object[]{"ID", "Nome", "Idade", "Telefone", "Sexo"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        
        btnRemover = new JButton("Remover");
        btnAtualizar = new JButton("Atualizar");
        
        JPanel panelBotoes = new JPanel();
        panelBotoes.add(btnRemover);
        panelBotoes.add(btnAtualizar);
        
        add(scrollPane, BorderLayout.CENTER);
        add(panelBotoes, BorderLayout.SOUTH);
        
       
        btnRemover.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int linhaSelecionada = table.getSelectedRow();
                if (linhaSelecionada != -1) {
                    int id = (int) tableModel.getValueAt(linhaSelecionada, 0);
                    new banco().deletar(id);
                    JOptionPane.showMessageDialog(TelaListar.this, "Usuário removido!");
                    carregarUsuarios();
                } else {
                    JOptionPane.showMessageDialog(TelaListar.this, "Selecione um usuário para remover!");
                }
            }
        });
        
        
        btnAtualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int linhaSelecionada = table.getSelectedRow();
                if (linhaSelecionada != -1) {
                    int id = (int) tableModel.getValueAt(linhaSelecionada, 0);
                    Usuario usuario = new banco().buscarPorId(id);
                    if (usuario != null) {
                        principal.preencherCampos(usuario);
                        dispose(); 
                    }
                } else {
                    JOptionPane.showMessageDialog(TelaListar.this, "Selecione um usuário para atualizar!");
                }
            }
        });
    }
    
    
    private void carregarUsuarios() {
        tableModel.setRowCount(0); 
        List<Usuario> usuarios = new banco().listar();
        for (Usuario u : usuarios) {
            tableModel.addRow(new Object[]{
                u.getId(), u.getNome(), u.getIdade(), u.getTelefone(), u.getSexo()
            });
        }
    }
}
