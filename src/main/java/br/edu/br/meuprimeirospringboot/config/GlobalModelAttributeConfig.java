package br.edu.br.meuprimeirospringboot.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import br.edu.br.meuprimeirospringboot.entity.Usuario;


@ControllerAdvice
public class GlobalModelAttributeConfig {
	
	@ModelAttribute
    public void adicionarUsuarioLogado(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && auth.getPrincipal() instanceof Usuario) {
            Usuario usuario = (Usuario) auth.getPrincipal();
            model.addAttribute("username", usuario.getUsername());
        }
    }

}
