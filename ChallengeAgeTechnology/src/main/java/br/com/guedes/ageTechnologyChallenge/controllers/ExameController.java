
package br.com.guedes.ageTechnologyChallenge.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.guedes.ageTechnologyChallenge.entities.Exame;
import br.com.guedes.ageTechnologyChallenge.services.ExameService;
import jakarta.validation.Valid;

@Controller
public class ExameController {

	@Autowired
	private ExameService exameService;

	@GetMapping("/listar-exames")
	public String listarExames(Model model) {
		List<Exame> exames = exameService.findAll();
		model.addAttribute("exames", exames);
		return "ViewsExames/listar-exames";
	}

	@GetMapping("/ver-exame/{id}")
	public String verExame(@PathVariable("id") Long id, Model model) {
		Exame exame = exameService.findById(id);
		model.addAttribute("exame", exame);
		return "ViewsExames/ver-exame";
	}
	
	@GetMapping("/novo-exame")
	public String novoExame(Model model) {
		Exame exame = new Exame();
		model.addAttribute("exame", exame);
		return "ViewsExames/novo-exame";
	}

	@GetMapping("/atualizar-exame/{id}")
	public String editarExame(@PathVariable("id") Long id, Model model) {
		Exame exame = exameService.findById(id);
		model.addAttribute("exame", exame);
		return "ViewsExames/atualizar-exame";
	}
	
	@PostMapping("/atualizar-exame")
    public String salvarExameAtualizado(@Valid Exame exame, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/atualizar-exame";
        }

        exameService.save(exame);
        return "redirect:/listar-exames";
    }

	@PostMapping("/salvar-exame")
	public String salvarExame(@ModelAttribute("exame") Exame exame) {
		exameService.save(exame);
		return "redirect:/listar-exames";
	}
	
	@GetMapping("/deletar-exame/{id}")
	public String deletarExame(@PathVariable("id") Long id) {
	    exameService.deleta(id);
	    return "redirect:/listar-exames";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		binder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
}
