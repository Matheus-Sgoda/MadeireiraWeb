package br.edu.ifpr.madeireira.dao;

import br.edu.ifpr.madeireira.model.Status;
import br.edu.ifpr.madeireira.util.ConexaoBanco;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StatusDAO {
    private Connection conexao;

    public StatusDAO() {
        this.conexao = ConexaoBanco.getConexao();
    }

    public List<Status> listarTodos() {
        List<Status> lista = new ArrayList<>();
        String sql = "SELECT * FROM status";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Status s = new Status();
                s.setId(rs.getInt("id"));
                s.setDescricao(rs.getString("descricao"));
                lista.add(s);
            }
            rs.close(); stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar status: " + e.getMessage());
        }
        return lista;
    }

    public Status buscarPorId(int id) {
        Status status = null;
        String sql = "SELECT * FROM status WHERE id = ?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                status = new Status();
                status.setId(rs.getInt("id"));
                status.setDescricao(rs.getString("descricao"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar status por ID: " + e.getMessage());
        }
        return status;
    }

    public int atualizar(Status status) {
        String sql = "UPDATE status SET descricao = ? WHERE id = ?";
        int linhasAfetadas = 0;
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, status.getDescricao());
            stmt.setInt(2, status.getId());
            linhasAfetadas = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar status: " + e.getMessage());
        }
        return linhasAfetadas;
    }

    public void inserir(Status status) {
        String sql = "INSERT INTO status (descricao) VALUES (?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, status.getDescricao());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir status: " + e.getMessage());
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM status WHERE id = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir status: " + e.getMessage());
        }
    }
}