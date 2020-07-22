package com.shiou.crispysystem.web;

import com.shiou.crispysystem.Cake;
import com.shiou.crispysystem.Decoration;
import com.shiou.crispysystem.data.DecorationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.shiou.crispysystem.Decoration.Type;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/decorate")
@SessionAttributes("order")
public class DecorateCakeController {

    private final DecorationRepository decorationRepo;

    @Autowired
    public DecorateCakeController(DecorationRepository decorationRepo) {
        this.decorationRepo = decorationRepo;
    }

    @GetMapping
    public String showDecorateForm(Model model) {
//        List<Decoration> decorations = Arrays.asList(
//                new Decoration("CRCR", "Cream", Type.CREAM),
//                new Decoration("CRCH", "Chocolate Cream", Type.CREAM),
//                new Decoration("FRST", "Strawberry", Type.FRUIT),
//                new Decoration("FRAP", "Apple", Type.FRUIT),
//                new Decoration("FRBL", "Blueberry", Type.FRUIT),
//                new Decoration("CYFO", "Fondant", Type.CANDY),
//                new Decoration("CYCH", "Chocolate", Type.CANDY),
//                new Decoration("CEQU", "Question Mark", Type.CANDLE),
//                new Decoration("CEAG", "Age", Type.CANDLE)
//        );

        List<Decoration> decorations = new ArrayList<>();
        decorationRepo.findAll().forEach(i -> decorations.add(i));

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
