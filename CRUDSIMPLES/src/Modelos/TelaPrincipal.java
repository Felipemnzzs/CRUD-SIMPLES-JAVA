package Modelos;
import java.awt.EventQueue;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtIdade;
	private JTextField txtTelefone;
	JComboBox<String> txtGenero = new JComboBox<>();
	JButton btnAdicionar = new JButton("Adicionar");
	JButton btnAtualizar = new JButton("Atualizar");
	private int idSelecionado = -1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private boolean validarCampos() {
	    if (txtNome.getText().trim().isEmpty() ||
	        txtIdade.getText().trim().isEmpty() ||
	        txtTelefone.getText().trim().isEmpty() ||
	        txtGenero.getSelectedIndex() == 0) { // Corrigido para JComboBox
	        JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
	        return false;
	    }
	    return true;
	}
	
	 private void limparCampos() {
	        txtNome.setText("");
	        txtIdade.setText("");
	        txtTelefone.setText("");
	    }
	 
	 public void preencherCampos(Usuario usuario) {
		    idSelecionado = usuario.getId();
		    txtNome.setText(usuario.getNome());
		    txtIdade.setText(String.valueOf(usuario.getIdade()));
		    txtTelefone.setText(usuario.getTelefone());
		    
		   
		    txtGenero.setSelectedItem(usuario.getSexo());

		    btnAdicionar.setEnabled(false);
		    btnAtualizar.setEnabled(true);
		}

	
	@SuppressWarnings("deprecation")
	public TelaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 512, 275);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		txtNome = new JTextField();
		txtNome.setBounds(71, 28, 415, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNome.setBounds(20, 29, 46, 14);
		contentPane.add(lblNome);
		
		JLabel lblNewLabel = new JLabel("Idade:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(20, 59, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Telefone:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(145, 59, 86, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Gênero");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(73, 263, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		txtIdade = new JTextField();
		txtIdade.setBounds(71, 58, 64, 20);
		contentPane.add(txtIdade);
		txtIdade.setColumns(10);
		
		txtTelefone = new JTextField();
		txtTelefone.setBounds(208, 58, 122, 20);
		contentPane.add(txtTelefone);
		txtTelefone.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Gênero:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(340, 61, 56, 14);
		contentPane.add(lblNewLabel_3);
		
		JComboBox<String> txtGenero = new JComboBox<>();
		txtGenero.setBounds(394, 57, 92, 22); 
		txtGenero.addItem("Masculino");
		txtGenero.addItem("Feminino");
		contentPane.add(txtGenero); 
		
		

		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (validarCampos()) {
					Usuario usuario = new Usuario();
					usuario.setNome(txtNome.getText());
					usuario.setIdade(Integer.parseInt(txtIdade.getText()));
					usuario.setTelefone(txtTelefone.getText());
					usuario.setSexo(txtGenero.getSelectedItem().toString());
					
					new banco().inserir(usuario);
					JOptionPane.showMessageDialog(TelaPrincipal.this, "Usuário Adicionado");
					limparCampos();
				}
			}
		});
		
		

		btnAdicionar.setBounds(20, 109, 89, 23);
		contentPane.add(btnAdicionar);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.disable();
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    if (idSelecionado != -1 && validarCampos()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(idSelecionado);
                    usuario.setNome(txtNome.getText());
                    usuario.setIdade(Integer.parseInt(txtIdade.getText()));
                    usuario.setTelefone(txtTelefone.getText());
                    usuario.setSexo(txtGenero.getSelectedItem().toString());
                    
                    new banco().atualizar(usuario);
                    JOptionPane.showMessageDialog(TelaPrincipal.this, "Usuário atualizado!");
                    limparCampos();
                    btnAdicionar.setEnabled(true);
                    btnAtualizar.setEnabled(false);
                    idSelecionado = -1;
                }	
			}
		});
		
		btnAtualizar.setBounds(142, 109, 89, 23);
		contentPane.add(btnAtualizar);
		
		JButton btnListar = new JButton("Listar");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaListar(TelaPrincipal.this).setVisible(true);
			}
		});
		btnListar.setBounds(397, 109, 89, 23);
		contentPane.add(btnListar);

	}
	
}

