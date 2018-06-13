package br.com.fernandoairescastello.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import br.com.fernandoairescastello.demo.service.PostService;

@Controller
public class HomeController {
	
	@Autowired
	private PostService postService;
	
	@GetMapping({"/", "/postar"})
	public String index(Model model) {
		
		try {
			model.addAttribute("posts", this.postService.obterListaPosts());
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return "index";
	}
	
	@PostMapping(path="/postar", consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public String postar(Model model, @RequestParam Map<String, String> body) {
		
		try {
			this.postService.salvarPost(body.get("content"));
			model.addAttribute("posts", this.postService.obterListaPosts());
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return "index";
	}
	
	@PostMapping(path="/upvote", consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public String upvote(Model model, @RequestParam Map<String, String> body) {
		
		try {
			this.postService.upvotePost(Long.parseLong(body.get("id")));
			model.addAttribute("posts", this.postService.obterListaPosts());
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return "index";
	}
}
