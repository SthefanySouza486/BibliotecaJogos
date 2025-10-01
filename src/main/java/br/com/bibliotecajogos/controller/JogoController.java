package br.com.bibliotecajogos.controller;

import br.com.bibliotecajogos.entity.Jogo;
import br.com.bibliotecajogos.service.JogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class JogoController {

    @Autowired
    private JogoService jogoService;

    @GetMapping("/jogos")
    public String listarJogos(Model model, @RequestParam(defaultValue = "titulo")String sortBy){
        model.addAttribute("jogos", jogoService.listAll(sortBy));
        return "jogos";
    }

    @GetMapping("/jogos/novo")
    public String novoJogoForm(Model model){
        model.addAttribute("jogo", new Jogo());
        return "formulario-jogo";
    }

    @PostMapping("/jogos")
    public String salvarJogo(@ModelAttribute("jogo") Jogo jogo){
        jogoService.save(jogo);
        return "redirect:/jogos";
    }

    @GetMapping("/jogos/editar/{id}")
    public String editarJogoForm(@PathVariable Long id, Model model){
        model.addAttribute("jogo", jogoService.findById(id));
        return "formulario-jogo";
    }

    @GetMapping("/jogos/excluir/{id}")
    public String excluirJogo(@PathVariable Long id){
        jogoService.delete(id);
        return "redirect:/jogos";
    }

    @GetMapping("/jogos/pesquisar")
    public String pesquisarJogos(@RequestParam String termo, @RequestParam String tipo, Model model){
        model.addAttribute("jogos",  jogoService.pesquisar(termo, tipo));
        return "jogos";
    }
}
