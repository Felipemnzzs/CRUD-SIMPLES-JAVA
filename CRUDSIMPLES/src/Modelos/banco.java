package Modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Modelos.banco;
import Modelos.conexao;

public class banco {

   
    public void inserir(Usuario usuario) {
        String sql = "INSERT INTO usuarios(nome, idade, telefone, sexo) VALUES (?, ?, ?, ?)";
        try (Connection con = conexao.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setString(1, usuario.getNome());
            ps.setInt(2, usuario.getIdade());
            ps.setInt(3, usuario.getTelefone());
            ps.setString(4, usuario.getSexo());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public void atualizar(Usuario usuario) {
        String sql = "UPDATE usuarios SET nome=?, idade=?, telefone=?, sexo=? WHERE id=?";
        try (Connection con = conexao.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setString(1, usuario.getNome());
            ps.setInt(2, usuario.getIdade());
            ps.setInt(3, usuario.getTelefone());
            ps.setString(4, usuario.getSexo());
            ps.setInt(5, usuario.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   
    public void deletar(int id) {
        String sql = "DELETE FROM usuarios WHERE id=?";
        try (Connection con = conexao.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public List<Usuario> listar() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try (Connection con = conexao.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
             
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setIdade(rs.getInt("idade"));
                u.setTelefone(rs.getInt("telefone"));
                u.setSexo(rs.getString("sexo"));
                lista.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
  
    public Usuario buscarPorId(int id) {
        Usuario u = null;
        String sql = "SELECT * FROM usuarios WHERE id = ?";

        try (Connection con = conexao.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setInt(1, id);
            
            try (ResultSet rs = ps.executeQuery()) { 
                if (rs.next()) {
                    u = new Usuario();
                    u.setId(rs.getInt("id"));
                    u.setNome(rs.getString("nome"));
                    u.setIdade(rs.getInt("idade"));
                    u.setTelefone(rs.getInt("telefone"));
                    u.setSexo(rs.getString("sexo"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }

        return u;
    }

}

