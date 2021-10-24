package br.com.springboot.curso.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.curso.model.Usuario;
import br.com.springboot.curso.repository.UsuarioRepository;

@RestController
public class Controller {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@RequestMapping(value = "{nome}", method = RequestMethod.GET)
	public String Url(@PathVariable String nome) {

		Usuario usuario = new Usuario();

		usuario.setNome(nome);
		usuarioRepository.save(usuario);
		return nome;
	}

	@GetMapping(value = "listartodos")
	@ResponseBody
	public ResponseEntity<List<Usuario>> listarUsuario() {

		List<Usuario> usuarios = usuarioRepository.findAll();

		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
	}

	@PostMapping(value = "salvar")
	@ResponseBody
	public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) {

		Usuario user = usuarioRepository.save(usuario);

		return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
	}

	@PutMapping(value = "atualizar")
	@ResponseBody
	public ResponseEntity<Usuario> Atualizar(@RequestBody Usuario usuario) {

		Usuario user = usuarioRepository.saveAndFlush(usuario);

		return new ResponseEntity<Usuario>(user, HttpStatus.OK);
	}

	@DeleteMapping(value = "deletar")
	@ResponseBody
	public ResponseEntity<String> deletar(@RequestParam long idUser) {

		usuarioRepository.deleteById(idUser);

		return new ResponseEntity<String>("Apagado com sucesso !!!!", HttpStatus.OK);
	}

	@GetMapping(value = "buscaruserid")
	@ResponseBody
	public ResponseEntity<Usuario> BuscarUserId(@RequestParam(name = "idUser") long idUser) {

		Usuario user = usuarioRepository.findById(idUser).get();

		return new ResponseEntity<Usuario>(user, HttpStatus.OK);
	}

	@GetMapping(value = "buscarpornome")
	@ResponseBody
	public ResponseEntity<List<Usuario>> BuscarPorNome(@RequestParam(name = "name") String name) {

		List<Usuario> user = usuarioRepository.BuscarPorNome(name.trim().toUpperCase()); // trim() método para remover
																							// espaços em branco

		return new ResponseEntity<List<Usuario>>(user, HttpStatus.OK);
	}

}
