package com.unifap.biblioteca.controllers;

import com.unifap.biblioteca.entities.Cliente;
import com.unifap.biblioteca.repositories.ClienteRepository;
import com.unifap.biblioteca.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView("clientes/search");
        mv.addObject("clientes", clienteRepository.findAll());
        mv.addObject("menu", "clientes");
        return mv;
    }

    @GetMapping("/new")
    public ModelAndView create(Cliente cliente) {
        ModelAndView mv = new ModelAndView("clientes/create");
        mv.addObject("menu", "clientes");
        return mv;
    }

    @PostMapping("/new")
    public ModelAndView create(@Valid Cliente cliente, BindingResult result) {
        if(result.hasErrors())
            return create(cliente);
        clienteService.persist(cliente);
        return new ModelAndView("redirect:/clientes");
    }

    @GetMapping("/{id}/edit")
    public ModelAndView update(@PathVariable("id") Long id, Cliente cliente, boolean isInvalid) {
        ModelAndView mv = new ModelAndView("clientes/update");
        if(!isInvalid)
            cliente = clienteService.findOrFail(id);
        mv.addObject("cliente", cliente);
        mv.addObject("menu", "clientes");
        return mv;
    }

    @PostMapping("/edit")
    public ModelAndView update(@Valid Cliente cliente, BindingResult result) {
        if(result.hasErrors())
            return update(cliente.getId(), cliente, true);
        clienteService.persist(cliente);
        return new ModelAndView("redirect:/clientes");
    }

    @GetMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable("id") Long id) {
        Cliente cliente = clienteService.findOrFail(id);
        clienteService.delete(cliente);
        return new ModelAndView("redirect:/clientes");
    }


}
