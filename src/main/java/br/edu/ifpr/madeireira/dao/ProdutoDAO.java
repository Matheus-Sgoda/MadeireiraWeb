package br.edu.ifpr.madeireira.dao;

import br.edu.ifpr.madeireira.model.Produto;
import br.edu.ifpr.madeireira.util.ConexaoBanco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private Connection conexao;

    public ProdutoDAO() {
        this.conexao = ConexaoBanco.getConexao();
    }

    public void inserir(Produto produto) {
        String sql = "INSERT INTO produto (nome, descricao, fkCategoria, fkStatus, quantidade, unidadeMedida, preco, imagemUrl) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setInt(3, produto.getFkCategoria());
            stmt.setInt(4, produto.getFkStatus());
            stmt.setDouble(5, produto.getQuantidade());
            stmt.setString(6, produto.getUnidadeMedida());
            stmt.setDouble(7, produto.getPreco());
            stmt.setString(8, produto.getImagemUrl());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir produto: " + e.getMessage());
        }
    }

    public List<Produto> listarTodosComJoin() {
        List<Produto> listaProdutos = new ArrayList<>();


        String sql = "SELECT p.*, c.descricao as nomeCategoria, s.descricao as nomeStatus " +
                "FROM produto p " +
                "INNER JOIN categoria c ON p.fkCategoria = c.id " +
                "INNER JOIN status s ON p.fkStatus = s.id " +
                "ORDER BY p.nome ASC";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produto produto = resultSetParaProduto(rs);
                produto.setNomeCategoria(rs.getString("nomeCategoria"));
                produto.setNomeStatus(rs.getString("nomeStatus"));

                listaProdutos.add(produto);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar produtos: " + e.getMessage());
        }
        return listaProdutos;
    }

    public Produto buscarPorId(int id) {
        Produto produto = null;
        String sql = "SELECT * FROM produto WHERE id = ?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                produto = resultSetParaProduto(rs);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar produto: " + e.getMessage());
        }
        return produto;
    }

    public void atualizar(Produto produto) {
        String sql = "UPDATE produto SET nome=?, descricao=?, fkCategoria=?, fkStatus=?, quantidade=?, unidadeMedida=?, preco=?, imagemUrl=? WHERE id=?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setInt(3, produto.getFkCategoria());
            stmt.setInt(4, produto.getFkStatus());
            stmt.setDouble(5, produto.getQuantidade());
            stmt.setString(6, produto.getUnidadeMedida());
            stmt.setDouble(7, produto.getPreco());
            stmt.setString(8, produto.getImagemUrl());
            stmt.setInt(9, produto.getId());

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar produto: " + e.getMessage());
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM produto WHERE id = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir produto: " + e.getMessage());
        }
    }

    private Produto resultSetParaProduto(ResultSet rs) throws SQLException {
        Produto p = new Produto();
        p.setId(rs.getInt("id"));
        p.setNome(rs.getString("nome"));
        p.setDescricao(rs.getString("descricao"));
        p.setFkCategoria(rs.getInt("fkCategoria"));
        p.setFkStatus(rs.getInt("fkStatus"));
        p.setQuantidade(rs.getDouble("quantidade"));
        p.setUnidadeMedida(rs.getString("unidadeMedida"));
        p.setPreco(rs.getDouble("preco"));
        p.setImagemUrl(rs.getString("imagemUrl"));

        Timestamp timestamp = rs.getTimestamp("dataCadastro");
        if (timestamp != null) {
            p.setDataCadastro(new java.util.Date(timestamp.getTime()));
        }

        return p;
    }
}