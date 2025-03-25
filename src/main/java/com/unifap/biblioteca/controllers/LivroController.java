package com.unifap.biblioteca.controllers;

import com.unifap.biblioteca.entities.Livro;
import com.unifap.biblioteca.repositories.LivroRepository;
import com.unifap.biblioteca.services.LivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private LivroService livroService;

    @GetMapping
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView("livros/search");
        mv.addObject("livros", livroRepository.findAll());
        mv.addObject("menu", "livros");
        return mv;
    }

    @GetMapping("/new")
    public ModelAndView create(Livro livro) {
        ModelAndView mv = new ModelAndView("livros/create");
        mv.addObject("menu", "livros");
        return mv;
    }

    @PostMapping("/new")
    public ModelAndView create(@Valid Livro livro, BindingResult result) {
        if(result.hasErrors())
            return create(livro);
        livroService.persist(livro);
        return new ModelAndView("redirect:/livros");
    }

    @GetMapping("/{id}/edit")
    public ModelAndView update(@PathVariable("id") Long id, Livro livro, boolean isInvalid) {
        ModelAndView mv = new ModelAndView("livros/update");
        if(!isInvalid)
            livro = livroService.findOrFail(id);
        mv.addObject("livro", livro);
        mv.addObject("menu", "livros");
        return mv;
    }

    @PostMapping("/edit")
    public ModelAndView update(@Valid Livro livro, BindingResult result) {
        if(result.hasErrors())
            return update(livro.getId(), livro, true);
        livroService.persist(livro);
        return new ModelAndView("redirect:/livros");
    }

    @GetMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable("id") Long id) {
        Livro livro = livroService.findOrFail(id);
        livroService.delete(livro);
        return new ModelAndView("redirect:/livros");
    }
}
