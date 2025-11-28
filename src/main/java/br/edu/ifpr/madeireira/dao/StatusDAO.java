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
}