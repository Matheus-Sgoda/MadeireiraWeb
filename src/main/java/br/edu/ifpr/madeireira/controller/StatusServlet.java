package br.edu.ifpr.madeireira.controller;

import br.edu.ifpr.madeireira.dao.StatusDAO;
import br.edu.ifpr.madeireira.model.Status;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "StatusServlet", urlPatterns = {"/status", "/status/novo", "/status/editar", "/status/excluir"})
public class StatusServlet extends HttpServlet {

    private StatusDAO statusDAO;

    @Override
    public void init() throws ServletException {
        statusDAO = new StatusDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/status/novo":
                mostrarFormularioNovo(request, response);
                break;
            case "/status/editar":
                mostrarFormularioEditar(request, response);
                break;
            case "/status/excluir":
                excluirStatus(request, response);
                break;
            default:
                listarStatus(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getServletPath();

        if (action.equals("/status/editar")) {
            atualizarStatus(request, response);
        } else {
            inserirStatus(request, response);
        }
    }

    private void listarStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Status> listaStatus = statusDAO.listarTodos();
        request.setAttribute("listaStatus", listaStatus);
        request.getRequestDispatcher("/WEB-INF/views/status/lista.jsp").forward(request, response);
    }

    private void mostrarFormularioNovo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/status/form.jsp").forward(request, response);
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Status status = statusDAO.buscarPorId(id);
        request.setAttribute("status", status);
        request.getRequestDispatcher("/WEB-INF/views/status/form.jsp").forward(request, response);
    }

    private void inserirStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Status status = new Status();
        status.setDescricao(request.getParameter("descricao"));
        statusDAO.inserir(status);
        response.sendRedirect(request.getContextPath() + "/status");
    }

    private void atualizarStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Status status = new Status();
        status.setId(Integer.parseInt(request.getParameter("id")));
        status.setDescricao(request.getParameter("descricao"));
        statusDAO.atualizar(status);
        response.sendRedirect(request.getContextPath() + "/status");
    }

    private void excluirStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        statusDAO.excluir(id);
        response.sendRedirect(request.getContextPath() + "/status");
    }
}
