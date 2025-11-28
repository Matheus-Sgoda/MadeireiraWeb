package br.edu.ifpr.madeireira.model;
import java.util.Date;

public class Produto {
    private int id;
    private String nome;
    private String descricao;
    private int fkCategoria;
    private int fkStatus;
    private double quantidade;
    private String unidadeMedida;
    private double preco;
    private Date dataCadastro;

    private String nomeCategoria;
    private String nomeStatus;

    public Produto() {
        this.dataCadastro = new Date();
        this.unidadeMedida = "un";
    }

    public Produto(int id, String nome, String descricao, int fkCategoria, int fkStatus, double quantidade, String unidadeMedida, double preco) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.fkCategoria = fkCategoria;
        this.fkStatus = fkStatus;
        this.quantidade = quantidade;
        this.unidadeMedida = unidadeMedida;
        this.preco = preco;
        this.dataCadastro = new Date();
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public int getFkCategoria() { return fkCategoria; }
    public void setFkCategoria(int fkCategoria) { this.fkCategoria = fkCategoria; }

    public int getFkStatus() { return fkStatus; }
    public void setFkStatus(int fkStatus) { this.fkStatus = fkStatus; }

    public double getQuantidade() { return quantidade; }
    public void setQuantidade(double quantidade) { this.quantidade = quantidade; }

    public String getUnidadeMedida() { return unidadeMedida; }
    public void setUnidadeMedida(String unidadeMedida) { this.unidadeMedida = unidadeMedida; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }

    public Date getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(Date dataCadastro) { this.dataCadastro = dataCadastro; }

    public String getNomeCategoria() { return nomeCategoria; }
    public void setNomeCategoria(String nomeCategoria) { this.nomeCategoria = nomeCategoria; }

    public String getNomeStatus() { return nomeStatus; }
    public void setNomeStatus(String nomeStatus) { this.nomeStatus = nomeStatus; }

    public String getPrecoFormatado() {
        return String.format("R$ %.2f", preco);
    }

    @Override
    public String toString() {
        return "Produto{" + "id=" + id + ", nome='" + nome + '\'' + ", preco=" + preco + '}';
    }
}
