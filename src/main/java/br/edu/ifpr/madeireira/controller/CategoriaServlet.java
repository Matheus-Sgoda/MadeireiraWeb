package br.edu.ifpr.madeireira.controller;

import br.edu.ifpr.madeireira.dao.CategoriaDAO;
import br.edu.ifpr.madeireira.model.Categoria;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CategoriaServlet", urlPatterns = {"/categorias", "/categorias/novo", "/categorias/editar", "/categorias/excluir"})
public class CategoriaServlet extends HttpServlet {

    private CategoriaDAO categoriaDAO;

    @Override
    public void init() throws ServletException {
        categoriaDAO = new CategoriaDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/categorias/novo":
                mostrarFormularioNovo(request, response);
                break;
            case "/categorias/editar":
                mostrarFormularioEditar(request, response);
                break;
            case "/categorias/excluir":
                excluirCategoria(request, response);
                break;
            default:
                listarCategorias(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getServletPath();

        if (action.equals("/categorias/editar")) {
            atualizarCategoria(request, response);
        } else {
            inserirCategoria(request, response);
        }
    }

    private void listarCategorias(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Categoria> listaCategorias = categoriaDAO.listarTodas();
        request.setAttribute("listaCategorias", listaCategorias);
        request.getRequestDispatcher("/WEB-INF/views/categorias/lista.jsp").forward(request, response);
    }

    private void mostrarFormularioNovo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/categorias/form.jsp").forward(request, response);
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Categoria categoria = categoriaDAO.buscarPorId(id);
        request.setAttribute("categoria", categoria);
        request.getRequestDispatcher("/WEB-INF/views/categorias/form.jsp").forward(request, response);
    }

    private void inserirCategoria(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Categoria categoria = new Categoria();
        categoria.setDescricao(request.getParameter("descricao"));
        categoriaDAO.inserir(categoria);
        response.sendRedirect(request.getContextPath() + "/categorias");
    }

    private void atualizarCategoria(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Categoria categoria = new Categoria();
        categoria.setId(Integer.parseInt(request.getParameter("id")));
        categoria.setDescricao(request.getParameter("descricao"));
        categoriaDAO.atualizar(categoria);
        response.sendRedirect(request.getContextPath() + "/categorias");
    }

    private void excluirCategoria(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        categoriaDAO.excluir(id);
        response.sendRedirect(request.getContextPath() + "/categorias");
    }
}
