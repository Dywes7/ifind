package br.edu.br.meuprimeirospringboot.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.edu.br.meuprimeirospringboot.entity.Role;
import br.edu.br.meuprimeirospringboot.entity.Usuario;
import br.edu.br.meuprimeirospringboot.repository.UsuarioRepository;
import br.edu.br.meuprimeirospringboot.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService{
	
	@Autowired
	private final UsuarioRepository usuario;
	
	// private final PasswordEncoder passwordEncoder;

	public UsuarioServiceImpl(UsuarioRepository usuario) {
	    this.usuario = usuario;
	}

	//@Override
	/*public Usuario findByUsername(String username) {
		return usuario.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
    }*/
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuario.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }
	
	@Override
    public void save(Usuario newusuario) {
        usuario.save(newusuario);  // Salva o usuário no banco de dados
    }

	/*@Override
	public void criarUsuario(String username, String password, Role role) {
		
		if (usuario.findByUsername(username).isEmpty()) {
			Usuario usuario = new Usuario();
			
			String senhaCriptografada = passwordEncoder.encode(password);
			usuario.setPassword(senhaCriptografada);
			
			usuario.setRole(role);
			
			usuario.save(usuario);
			System.out.println("Usuário " + username + " com a role " + role + " criado com sucesso.");
			
		} else {
			System.out.println("Usuário " + username + " já existe.");
		}
		
	}*/

	@Override
	public Usuario findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void criarUsuario(String username, String password, Role role) {
		
		Optional<Usuario> usuarioExistente = usuario.findByUsername(username);
		
		if (usuarioExistente.isEmpty()) {
			PasswordEncoder encoder = new BCryptPasswordEncoder();
			String senhaCriptografada = encoder.encode(password);
			
			Usuario newusuario = new Usuario();
			newusuario.setUsername(username);
	        newusuario.setPassword(senhaCriptografada);
	        newusuario.setRole(role);
	        
	        usuario.save(newusuario);
		}
	}

	@Override
	public List<Usuario> buscarTodos() {
		return usuario.findAllUsuarios();
	}

	@Override
	public Usuario buscarPorId(Long id) {
		return usuario.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuário " + id + " não encontrado"));
	}

	@Override
	public void excluirPorId(Long id) {
		usuario.deleteById(id);
	}

	@Override
	public Usuario cadastrar(Usuario u) {
		
		Optional<Usuario> usuarioExistente = usuario.findByUsername(u.getUsername());
		
		if (!usuarioExistente.isEmpty()) {
			
			throw new RuntimeException("Usuário já cadastrado!");
			
		}
		
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		String senhaCriptografada = encoder.encode(u.getPassword());
		
        u.setPassword(senhaCriptografada);
        u.setRole(Role.USER);
		
		return usuario.save(u);
		
	}

	@Override
	public Usuario editar(Usuario u) {
		// TODO Auto-generated method stub
		return null;
	}
}
