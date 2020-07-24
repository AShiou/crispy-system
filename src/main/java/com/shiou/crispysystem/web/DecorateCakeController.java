package com.shiou.crispysystem.web;

import com.shiou.crispysystem.Cake;
import com.shiou.crispysystem.Decoration;
import com.shiou.crispysystem.Order;
import com.shiou.crispysystem.data.CakeRepository;
import com.shiou.crispysystem.data.DecorationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    private CakeRepository decorateRepo;

    @Autowired
    public DecorateCakeController(DecorationRepository decorationRepo, CakeRepository decorateRepo) {
        this.decorationRepo = decorationRepo;
        this.decorateRepo = decorateRepo;
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "cake")
    public Cake cake() {
        return new Cake();
    }

    @GetMapping
    public ResponseEntity<List<Decoration>> showDecorateForm(Model model) {
        List<Decoration> decorations = new ArrayList<>();
        decorationRepo.findAll().forEach(decorations::add);

        Type[] types = Decoration.Type.values();
        for (Type type: types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(decorations, type));
        }
        return ResponseEntity.ok().body(decorations);
    }

    @PostMapping
    public ResponseEntity<Cake> processDecorate(@RequestBody Cake cake) {
        //Order order = new Order();
        //Cake saved = decorateRepo.save(decorate);
        //order.addDecorate(saved);
        return ResponseEntity.ok().body(cake);
    }

    private List<Decoration> filterByType(List<Decoration> decorations, Type type) {
        return decorations
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
