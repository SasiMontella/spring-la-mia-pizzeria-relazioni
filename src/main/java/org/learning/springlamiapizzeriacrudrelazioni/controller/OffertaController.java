package org.learning.springlamiapizzeriacrudrelazioni.controller;

import jakarta.validation.Valid;
import org.learning.springlamiapizzeriacrudrelazioni.model.Offerta;
import org.learning.springlamiapizzeriacrudrelazioni.model.Pizza;
import org.learning.springlamiapizzeriacrudrelazioni.repository.OffertaRepository;
import org.learning.springlamiapizzeriacrudrelazioni.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping("/offerte")
public class OffertaController {
        @Autowired
        private PizzaRepository pizzarepository;
        @Autowired
        private OffertaRepository offertarepository;

@GetMapping("/create")
public String create(@RequestParam Integer pizzaId, Model model){
    Optional<Pizza> result = pizzarepository.findById(pizzaId);
        if (result.isPresent()){
            Pizza offertaPizza = result.get();
            model.addAttribute("pizza", offertaPizza);

            Offerta newOfferta = new Offerta();
            newOfferta.setPizza(offertaPizza);
            newOfferta.setDataInizio(LocalDate.now());
            model.addAttribute("offerta", newOfferta);
            return "offerte/create";
        } else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza with id " + pizzaId + " not found");
        }
    }
@PostMapping("/create")
public String store(Offerta formOfferta){
        Offerta storedOfferta = offertarepository.save(formOfferta);
        return "redirect:/pizza/show/" +storedOfferta.getPizza().getId();
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Optional<Offerta> result = offertarepository.findById(id);
        if (result.isPresent()) {
            model.addAttribute("offerta", result.get());
            return "offerte/edit";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Offerta with id " + id
                    + " not found");
        }

    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("offerta") Offerta formOfferta, BindingResult bindingresult) {
        if (bindingresult.hasErrors()) {
            return "offerte/edit";
        }
        Offerta updatedOfferta = offertarepository.save(formOfferta);
        return "redirect:/pizza/show/" + updatedOfferta.getPizza().getId();
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Optional<Offerta> result = offertarepository.findById(id);
        if (result.isPresent()) {
            Offerta offertaToDelete = result.get();
            offertarepository.delete(offertaToDelete);
            return "redirect:/pizza/show/" + offertaToDelete.getPizza().getId();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Offerta with id " + id
                    + " not found");
        }
    }
}
