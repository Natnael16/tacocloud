package com.natnael.tacocloud;

import java.util.*;

import java.util.stream.Collectors;

import javax.validation.Valid;

import com.natnael.tacocloud.Ingredient.Type;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@SessionAttributes("tacoOrder")
public class DesignTacoController {
    // List<Ingredient> ingredients = Arrays.asList(
    // new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
    // new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
    // new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
    // new Ingredient("CARN", "Carnitas", Type.PROTEIN),
    // new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
    // new Ingredient("LETC", "Lettuce", Type.VEGGIES),
    // new Ingredient("CHED", "Cheddar", Type.CHEESE),
    // new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
    // new Ingredient("SLSA", "Salsa", Type.SAUCE),
    // new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
    private final IngredientRepository repository;

    @GetMapping("/design")
    public String showDesignForm(Model model) {

        Type[] types = Type.values();

        List<Ingredient> ingredients = new ArrayList<>();
        this.repository.findAll().forEach(i -> ingredients.add(i));

        for (Type t : types) {
            model.addAttribute(t.toString().toLowerCase(), filterByType(ingredients, t));
        }
        return "design";
    }

    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream()
                .filter(i -> i.getType().equals(type))
                .collect(Collectors.toList());
    }

    @PostMapping("/design")
    public String processTaco(@Valid Taco taco, @ModelAttribute TacoOrder order, Errors errors) {
        if (errors.hasErrors()) {
            return "design";
        }
        log.info("Processing your Taco" + taco);
        order.addTaco(taco);
        
        return "redirect:/orders/current";
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }
}