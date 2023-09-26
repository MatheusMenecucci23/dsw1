package br.ufscar.dc.dsw.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.ufscar.dc.dsw.domain.Conversao;

@Controller
public class ConversaoController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping(path = "/conversao", method = {RequestMethod.GET, RequestMethod.POST})
    public String realizarConversao(@RequestParam(required = false, defaultValue = "-100") double minimo,
                                    @RequestParam(required = false, defaultValue = "100") double maximo,
                                    @RequestParam(required = false, defaultValue = "5") double incremento,
                                    Model model) {

        List<Conversao> listaConversao = calcularConversao(minimo, maximo, incremento);

        model.addAttribute("listaConversao", listaConversao);

        return "conversao";
    }

    private static List<Conversao> calcularConversao(double minimo, double maximo, double incremento) {
        List<Conversao> listaConversao = new ArrayList<>();

        for (double celsius = minimo; celsius <= maximo; celsius += incremento) {
            double fahrenheit = celsius * 1.8 + 32;
            listaConversao.add(new Conversao(celsius, fahrenheit));
        }

        return listaConversao;
    }
}
