package br.edu.ifpr.madeireira.controller;

import br.edu.ifpr.madeireira.dao.ProdutoDAO;
import br.edu.ifpr.madeireira.dao.CategoriaDAO;
import br.edu.ifpr.madeireira.dao.StatusDAO;
import br.edu.ifpr.madeireira.model.Produto;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProdutoServlet", urlPatterns = {"/produtos", "/produtos/novo", "/produtos/editar", "/produtos/excluir"})
public class ProdutoServlet extends HttpServlet {

    private ProdutoDAO produtoDAO;
    private CategoriaDAO categoriaDAO;
    private StatusDAO statusDAO;

    @Override
    public void init() throws ServletException {
        produtoDAO = new ProdutoDAO();
        categoriaDAO = new CategoriaDAO();
        statusDAO = new StatusDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/produtos/novo":
                mostrarFormularioNovo(request, response);
                break;
            case "/produtos/editar":
                mostrarFormularioEditar(request, response);
                break;
            case "/produtos/excluir":
                excluirProduto(request, response);
                break;
            default:
                listarProdutos(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getServletPath();

        if (action.equals("/produtos/editar")) {
            atualizarProduto(request, response);
        } else {
            inserirProduto(request, response);
        }
    }

    private void listarProdutos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Produto> listaProdutos = produtoDAO.listarTodosComJoin();
        request.setAttribute("listaProdutos", listaProdutos);
        request.getRequestDispatcher("/WEB-INF/views/produtos/lista.jsp").forward(request, response);
    }

    private void mostrarFormularioNovo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("categorias", categoriaDAO.listarTodas());
        request.setAttribute("status", statusDAO.listarTodos());
        request.getRequestDispatcher("/WEB-INF/views/produtos/form.jsp").forward(request, response);
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Produto produto = produtoDAO.buscarPorId(id);
        request.setAttribute("produto", produto);
        request.setAttribute("categorias", categoriaDAO.listarTodas());
        request.setAttribute("status", statusDAO.listarTodos());
        request.getRequestDispatcher("/WEB-INF/views/produtos/form.jsp").forward(request, response);
    }

    private void inserirProduto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Produto produto = new Produto();
        produto.setNome(request.getParameter("nome"));
        produto.setDescricao(request.getParameter("descricao"));
        produto.setFkCategoria(Integer.parseInt(request.getParameter("fkCategoria")));
        produto.setFkStatus(Integer.parseInt(request.getParameter("fkStatus")));
        produto.setQuantidade(Double.parseDouble(request.getParameter("quantidade")));
        produto.setUnidadeMedida(request.getParameter("unidadeMedida"));
        produto.setPreco(Double.parseDouble(request.getParameter("preco")));
        produto.setImagemUrl(request.getParameter("imagemUrl"));

        produtoDAO.inserir(produto);
        response.sendRedirect(request.getContextPath() + "/produtos");
    }

    private void atualizarProduto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Produto produto = new Produto();
        produto.setId(Integer.parseInt(request.getParameter("id")));
        produto.setNome(request.getParameter("nome"));
        produto.setDescricao(request.getParameter("descricao"));
        produto.setFkCategoria(Integer.parseInt(request.getParameter("fkCategoria")));
        produto.setFkStatus(Integer.parseInt(request.getParameter("fkStatus")));
        produto.setQuantidade(Double.parseDouble(request.getParameter("quantidade")));
        produto.setUnidadeMedida(request.getParameter("unidadeMedida"));
        produto.setPreco(Double.parseDouble(request.getParameter("preco")));
        produto.setImagemUrl(request.getParameter("imagemUrl"));

        produtoDAO.atualizar(produto);
        response.sendRedirect(request.getContextPath() + "/produtos");
    }

    private void excluirProduto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        produtoDAO.excluir(id);
        response.sendRedirect(request.getContextPath() + "/produtos");
    }
}
