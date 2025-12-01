package br.edu.ifpr.madeireira.controller;

import br.edu.ifpr.madeireira.dao.UsuarioDAO;
import br.edu.ifpr.madeireira.model.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UsuarioServlet", urlPatterns = {"/usuarios", "/usuarios/novo", "/usuarios/editar", "/usuarios/excluir"})
public class UsuarioServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO;

    @Override
    public void init() throws ServletException {
        usuarioDAO = new UsuarioDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/usuarios/novo":
                mostrarFormularioNovo(request, response);
                break;
            case "/usuarios/editar":
                mostrarFormularioEditar(request, response);
                break;
            case "/usuarios/excluir":
                excluirUsuario(request, response);
                break;
            default:
                listarUsuarios(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getServletPath();

        if (action.equals("/usuarios/editar")) {
            atualizarUsuario(request, response);
        } else {
            inserirUsuario(request, response);
        }
    }

    private void listarUsuarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Usuario> listaUsuarios = usuarioDAO.listarTodos();
        request.setAttribute("listaUsuarios", listaUsuarios);
        request.getRequestDispatcher("/WEB-INF/views/usuarios/lista.jsp").forward(request, response);
    }

    private void mostrarFormularioNovo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/usuarios/form.jsp").forward(request, response);
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Usuario usuario = usuarioDAO.buscarPorId(id);
        request.setAttribute("usuario", usuario);
        request.getRequestDispatcher("/WEB-INF/views/usuarios/form.jsp").forward(request, response);
    }

    private void inserirUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Usuario usuario = new Usuario();
        usuario.setNome(request.getParameter("nome"));
        usuario.setEmail(request.getParameter("email"));
        usuario.setEndereco(request.getParameter("endereco"));
        usuario.setTelefone(request.getParameter("telefone"));
        usuario.setSenha(request.getParameter("senha"));
        usuarioDAO.inserir(usuario);
        response.sendRedirect(request.getContextPath() + "/usuarios");
    }

    private void atualizarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Usuario usuario = new Usuario();
        usuario.setId(Integer.parseInt(request.getParameter("id")));
        usuario.setNome(request.getParameter("nome"));
        usuario.setEmail(request.getParameter("email"));
        usuario.setEndereco(request.getParameter("endereco"));
        usuario.setTelefone(request.getParameter("telefone"));
        usuario.setSenha(request.getParameter("senha"));
        usuarioDAO.atualizar(usuario);
        response.sendRedirect(request.getContextPath() + "/usuarios");
    }

    private void excluirUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        usuarioDAO.excluir(id);
        response.sendRedirect(request.getContextPath() + "/usuarios");
    }
}
