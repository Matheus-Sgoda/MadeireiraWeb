package br.edu.ifpr.madeireira.dao;

import br.edu.ifpr.madeireira.model.Usuario;
import br.edu.ifpr.madeireira.util.ConexaoBanco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private Connection conexao;

    public UsuarioDAO() {
        this.conexao = ConexaoBanco.getConexao();
    }

    public void inserir(Usuario usuario) {
        String sql = "INSERT INTO usuario (nome, email, endereco, telefone, senha) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getEndereco());
            stmt.setString(4, usuario.getTelefone());
            stmt.setString(5, usuario.getSenha());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir usuário: " + e.getMessage());
        }
    }

    public List<Usuario> listarTodos() {
        List<Usuario> listaUsuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario ORDER BY nome ASC";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Usuario usuario = resultSetParaUsuario(rs);
                listaUsuarios.add(usuario);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar usuários: " + e.getMessage());
        }
        return listaUsuarios;
    }

    public Usuario buscarPorId(int id) {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuario WHERE id = ?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = resultSetParaUsuario(rs);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuário por ID: " + e.getMessage());
        }
        return usuario;
    }

    public Usuario buscarPorEmail(String email) {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuario WHERE email = ?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = resultSetParaUsuario(rs);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuário por Email: " + e.getMessage());
        }
        return usuario;
    }

    public void atualizar(Usuario usuario) {
        String sql = "UPDATE usuario SET nome=?, email=?, endereco=?, telefone=?, senha=? WHERE id=?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getEndereco());
            stmt.setString(4, usuario.getTelefone());
            stmt.setString(5, usuario.getSenha());
            stmt.setInt(6, usuario.getId());

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM usuario WHERE id = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir usuário: " + e.getMessage());
        }
    }

    public Usuario validarLogin(String email, String senha) {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = resultSetParaUsuario(rs);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao validar login: " + e.getMessage());
        }
        return usuario;
    }

    private Usuario resultSetParaUsuario(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(rs.getInt("id"));
        usuario.setNome(rs.getString("nome"));
        usuario.setEmail(rs.getString("email"));
        usuario.setEndereco(rs.getString("endereco"));
        usuario.setTelefone(rs.getString("telefone"));
        usuario.setSenha(rs.getString("senha"));

        Timestamp timestamp = rs.getTimestamp("dataCadastro");
        if (timestamp != null) {
            usuario.setDataCadastro(new java.util.Date(timestamp.getTime()));
        }

        return usuario;
    }
}