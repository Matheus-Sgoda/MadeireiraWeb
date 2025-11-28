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
}