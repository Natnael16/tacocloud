package com.natnael.tacocloud;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@SessionAttributes("tacoOrder")
public class OrderController {
    private final TacoOrderRepository repo;

    @GetMapping("/orders/current")
    public String orderForm(Model model) {
        // model.addAttribute("tacoOrder", new TacoOrder());
        return "orderForm";
    }

    @PostMapping("/orders")
    public String processOrder(@Valid TacoOrder order, Errors errors, SessionStatus stat) {
        log.info("Order submitted ###################################################: " + order);
        if (errors.hasErrors()) {
            return "orderForm";
        }
        this.repo.save(order);
        stat.setComplete();
        return "redirect:/";
    }
}
