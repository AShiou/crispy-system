package com.shiou.crispysystem;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.shiou.crispysystem.Decoration.Type;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequestMapping("/decorate")
public class DecorateCakeController {

    @GetMapping
    public String showDecorateForm(Model model) {
        List<Decoration> decorations = Arrays.asList(
                new Decoration("CRCR", "Cream", Type.CREAM),
                new Decoration("CRCH", "Chocolate Cream", Type.CREAM),
                new Decoration("FRST", "Strawberry", Type.FRUIT),
                new Decoration("FRAP", "Apple", Type.FRUIT),
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

    private List<Decoration> filterByType(List<Decoration> decorations, Type type) {
        return decorations
                .stream()
                .filter(x -> x.getType().equals(Type))
                .collect(Collectors.toList(Collectors.toList());
    }
}
