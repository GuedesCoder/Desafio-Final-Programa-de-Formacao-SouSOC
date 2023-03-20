package br.com.guedes.ageTechnologyChallenge.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.guedes.ageTechnologyChallenge.entities.Exame;
import br.com.guedes.ageTechnologyChallenge.entities.ExamesRealizados;
import br.com.guedes.ageTechnologyChallenge.entities.Funcionario;
import br.com.guedes.ageTechnologyChallenge.services.ExameService;
import br.com.guedes.ageTechnologyChallenge.services.ExamesRealizadosService;
import br.com.guedes.ageTechnologyChallenge.services.FuncionarioService;

@Controller
public class ExamesRealizadosController {

	private final ExamesRealizadosService examesRealizadosService;
	private final FuncionarioService funcionarioService;
	private final ExameService exameService;

	public ExamesRealizadosController(ExamesRealizadosService examesRealizadosService,
			FuncionarioService funcionarioService, ExameService exameService) {
		this.examesRealizadosService = examesRealizadosService;
		this.funcionarioService = funcionarioService;
		this.exameService = exameService;
	}

	@GetMapping("/listar-examesRealizados")
	public String listar(Model model) {
		List<ExamesRealizados> examesRealizados = examesRealizadosService.findAll();
		List<Funcionario> funcionarios = funcionarioService.findAll();
		List<Exame> exames = exameService.findAll();
		model.addAttribute("examesRealizados", examesRealizados);
		model.addAttribute("funcionarios", funcionarios);
		model.addAttribute("exames", exames);
		return "viewsExamesRealizados/listar-examesRealizados";
	}

	@GetMapping("/exames-cadastrados")
	public String examesCadastrados(Model model) {
	    List<Exame> exames = exameService.findAll();
	    List<Funcionario> funcionarios = funcionarioService.findAll();
	    model.addAttribute("exames", exames);
	    model.addAttribute("funcionarios", funcionarios);
	    return "viewsExamesRealizados/exames-cadastrados";
	}

	
	
	@GetMapping("/examesRealizados/{id}")
	public String verDetalhes(@PathVariable("id") Long id, Model model) {
		ExamesRealizados examesRealizados = examesRealizadosService.findById(id);
		model.addAttribute("examesRealizados", examesRealizados);
		return "viewsExamesRealizados/ver-examesRealizados";
	}

	@PostMapping("/examesRealizados/deletar/{id}")
	public String excluir(@PathVariable("id") Long id) {
		examesRealizadosService.delete(id);
		return "redirect:/examesRealizados";
	}

	@GetMapping("/examesRealizados/novo")
	public String cadastrar(Model model) {
		model.addAttribute("funcionarios", funcionarioService.findAll());
		model.addAttribute("exames", exameService.findAll());
		model.addAttribute("exameRealizado", new ExamesRealizados());
		return "viewsExamesRealizados/form-examesRealizados";
	}

	@PostMapping("/examesRealizados/novo")
	public String cadastrar(@ModelAttribute("exameRealizado") ExamesRealizados exameRealizado) {
		examesRealizadosService.save(exameRealizado);
		return "redirect:/examesRealizados";
	}

	@GetMapping("/examesRealizados/{id}/editar")
	public String editar(@PathVariable("id") Long id, Model model) {
		ExamesRealizados examesRealizados = examesRealizadosService.findById(id);
		model.addAttribute("examesRealizados", examesRealizados);
		model.addAttribute("funcionarios", funcionarioService.findAll());
		model.addAttribute("exames", exameService.findAll());
		return "viewsExamesRealizados/form-examesRealizados";
	}

	@PostMapping("/examesRealizados/{id}/editar")
	public String editar(@PathVariable("id") Long id,
			@ModelAttribute("examesRealizados") ExamesRealizados examesRealizados) {
		examesRealizadosService.update(id, examesRealizados);
		return "redirect:/examesRealizados";
	}
}
