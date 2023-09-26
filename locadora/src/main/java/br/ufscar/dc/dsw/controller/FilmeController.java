package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.ProdutoraDAO;
import br.ufscar.dc.dsw.dao.FilmeDAO;
import br.ufscar.dc.dsw.domain.Produtora;
import br.ufscar.dc.dsw.domain.Filme;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/filmes/*")
public class FilmeController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    private FilmeDAO dao;

    @Override
    public void init() {
        dao = new FilmeDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
                
        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "/cadastro":
                    apresentaFormCadastro(request, response);
                    break;
                case "/insercao":
                    insere(request, response);
                    break;
                case "/remocao":
                    remove(request, response);
                    break;
                case "/edicao":
                    apresentaFormEdicao(request, response);
                    break;
                case "/atualizacao":
                    atualize(request, response);
                    break;
                default:
                    lista(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    private void lista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Filme> listaFilmes = dao.getAll();
        request.setAttribute("listaFilmes", listaFilmes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/filme/lista.jsp");
        dispatcher.forward(request, response);
    }

    private Map<Long, String> getProdutoras() {
        Map <Long,String> produtoras = new HashMap<>();
        for (Produtora produtora: new ProdutoraDAO().getAll()) {
            produtoras.put(produtora.getId(), produtora.getNome());
        }
        return produtoras;
    }
    
    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("produtoras", getProdutoras());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/filme/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Filme filme = dao.get(id);
        request.setAttribute("filme", filme);
        request.setAttribute("produtoras", getProdutoras());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/filme/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        String titulo = request.getParameter("titulo");
        String diretor = request.getParameter("diretor");
        Integer ano = Integer.parseInt(request.getParameter("ano"));
        Float preco = Float.parseFloat(request.getParameter("preco"));
        
        Long produtoraID = Long.parseLong(request.getParameter("produtora"));
        Produtora produtora = new ProdutoraDAO().get(produtoraID);
        
        Filme filme = new Filme(titulo, diretor, ano, preco, produtora);
        dao.insert(filme);
        response.sendRedirect("lista");
    }

    private void atualize(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        Long id = Long.parseLong(request.getParameter("id"));
        String titulo = request.getParameter("titulo");
        String diretor = request.getParameter("diretor");
        Integer ano = Integer.parseInt(request.getParameter("ano"));
        Float preco = Float.parseFloat(request.getParameter("preco"));
        
        Long produtoraID = Long.parseLong(request.getParameter("produtora"));
        Produtora produtora = new ProdutoraDAO().get(produtoraID);
        
        Filme filme = new Filme(id, titulo, diretor, ano, preco, produtora);
        dao.update(filme);
        response.sendRedirect("lista");
    }

    private void remove(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));

        Filme filme = new Filme(id);
        dao.delete(filme);
        response.sendRedirect("lista");
    }
}
