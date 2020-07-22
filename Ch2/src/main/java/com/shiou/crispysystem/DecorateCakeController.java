package com.shiou.crispysystem;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.shiou.crispysystem.Decoration.Type;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/decorate")
public class DecorateCakeController {

    @GetMapping
    public String showDecorateForm(Model model) {
        List<Decoration> decorations = Arrays.asList(
                new Decoration("CRCR", "Cream", Type.CREAM),
                new Decoration("CRCH", "Chocolate Cream", Type.CREAM),
                new Decoration("FRST", "Strawberry", Type.FRUIT),
                new Decoration("FRAP", "Apple", Type.FRUIT),
                new Decoration("FRBL", "Blueberry", Type.FRUIT),
                new Decoration("CYFO", "Fondant", Type.CANDY),
                new Decoration("CYCH", "Chocolate", Type.CANDY),
                new Decoration("CEQU", "Question Mark", Type.CANDLE),
                new Decoration("CEAG", "Age", Type.CANDLE)
        );

        Type[] types = Decoration.Type.values();
        for (Type type: types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(decorations, type));
        }
        model.addAttribute("decorate", new Cake());
        return "decorate";
    }

    @PostMapping
    public String processDecorate(@Valid Cake decorate, Errors errors) {
        if (errors.hasErrors()) {
            return "decorate";
        }
        log.info("Processing decorate: " + decorate);
        return "redirect:/orders/current";
    }

    private List<Decoration> filterByType(List<Decoration> decorations, Type type) {
        return decorations
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
