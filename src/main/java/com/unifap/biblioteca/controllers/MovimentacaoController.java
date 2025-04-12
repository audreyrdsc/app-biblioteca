package com.unifap.biblioteca.controllers;

import com.unifap.biblioteca.entities.Cliente;
import com.unifap.biblioteca.entities.Livro;
import com.unifap.biblioteca.repositories.ClienteRepository;
import com.unifap.biblioteca.repositories.LivroRepository;
import com.unifap.biblioteca.repositories.MovimentacaoRepository;
import com.unifap.biblioteca.services.ClienteService;
import com.unifap.biblioteca.services.LivroService;
import com.unifap.biblioteca.services.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/movimentacoes")
public class MovimentacaoController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Autowired
    private MovimentacaoService movimentacaoService;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private LivroService livroService;

    @GetMapping
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView("movimentacoes/search");
        mv.addObject("clientes", clienteRepository.findAll());
        mv.addObject("menu", "movimentacoes");
        return mv;
    }

    @GetMapping("/{id}/view") //Visualizar livros emprestados pelo cliente
    public ModelAndView view(@PathVariable("id") Long id, Cliente cliente, boolean isInvalid) {
        ModelAndView mv = new ModelAndView("movimentacoes/view");
        if(!isInvalid) {
            cliente = clienteService.findOrFail(id);
        }
        mv.addObject("cliente", cliente);
        mv.addObject("livrosEmprestados", movimentacaoRepository.findLivrosEmprestadosAtivosPorCliente(id));
        mv.addObject("menu", "movimentacoes");
        return mv;
    }

    @GetMapping("/{id}/emprestimo") //Visualizar livros disponíveis para empréstimo
    public ModelAndView emprestimo(@PathVariable("id") Long id, Cliente cliente, boolean isInvalid) {
        ModelAndView mv = new ModelAndView("movimentacoes/emprestimo");
        if(!isInvalid) {
            cliente = clienteService.findOrFail(id);
        }
        System.out.println("Id Cliente: " + cliente.getId()); //////
        mv.addObject("cliente", cliente);
        mv.addObject("livrosDisponiveis", livroRepository.findByDisponivelTrue());
        mv.addObject("menu", "movimentacoes");
        return mv;
    }

    @GetMapping("/{id}/emprestar")
    public ModelAndView emprestar(@PathVariable("id") Long idLivro, @RequestParam("clienteId") Long clienteId) {
        movimentacaoService.emprestar(idLivro, clienteId);
        return new ModelAndView("redirect:/movimentacoes");
    }


    @GetMapping("/{id}/devolver") //Realizar devolução de livro emprestado
    public ModelAndView devolver(@PathVariable("id") Long idLivro, @RequestParam("clienteId") Long clienteId,
                                 Livro livro, boolean isInvalid) {
        if(!isInvalid) {
            livro = livroService.findOrFail(idLivro);
        }
        movimentacaoService.devolver(idLivro, clienteId);
        return new ModelAndView("redirect:/movimentacoes");
    }

}
