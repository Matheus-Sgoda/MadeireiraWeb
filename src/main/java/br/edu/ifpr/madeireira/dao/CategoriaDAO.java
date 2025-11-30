package br.edu.ifpr.madeireira.dao;

import br.edu.ifpr.madeireira.model.Categoria;
import br.edu.ifpr.madeireira.util.ConexaoBanco;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    private Connection conexao;

    public CategoriaDAO() {
        this.conexao = ConexaoBanco.getConexao();
    }

    public List<Categoria> listarTodas() {
        List<Categoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM categoria ORDER BY descricao ASC";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Categoria c = new Categoria();
                c.setId(rs.getInt("id"));
                c.setDescricao(rs.getString("descricao"));
                lista.add(c);
            }
            rs.close(); stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar categorias: " + e.getMessage());
        }
        return lista;
    }

    public Categoria buscarPorId(int id) {
        Categoria categoria = null;
        String sql = "SELECT * FROM categoria WHERE id = ?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setDescricao(rs.getString("descricao"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar categoria por ID: " + e.getMessage());
        }
        return categoria;
    }

    public int atualizar(Categoria categoria) {
        String sql = "UPDATE categoria SET descricao = ? WHERE id = ?";
        int linhasAfetadas = 0;

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, categoria.getDescricao());
            stmt.setInt(2, categoria.getId());

            linhasAfetadas = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar categoria: " + e.getMessage());
        }
        return linhasAfetadas;
    }

    public int inserir(Categoria categoria) {
        String sql = "INSERT INTO categoria (descricao) VALUES (?)";
        int linhasAfetadas = 0;

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, categoria.getDescricao());

            linhasAfetadas = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir categoria: " + e.getMessage());
        }
        return linhasAfetadas;
    }

    public int excluir(int id) {
        String sql = "DELETE FROM categoria WHERE id = ?";
        int linhasAfetadas = 0;

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);

            linhasAfetadas = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir categoria: " + e.getMessage());
        }
        return linhasAfetadas;
    }
}