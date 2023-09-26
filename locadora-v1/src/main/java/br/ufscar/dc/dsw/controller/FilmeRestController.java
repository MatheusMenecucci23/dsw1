package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufscar.dc.dsw.domain.Filme;
import br.ufscar.dc.dsw.domain.Produtora;
import br.ufscar.dc.dsw.service.spec.IFilmeService;

@CrossOrigin
@RestController
public class FilmeRestController {

	@Autowired
	private IFilmeService service;
	
	private boolean isJSONValid(String jsonInString) {
		try {
			return new ObjectMapper().readTree(jsonInString) != null;
		} catch (IOException e) {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	private void parse(Produtora estado, JSONObject json) {
		Map<String, Object> map = (Map<String, Object>) json.get("produtora");
		
		Object id = map.get("id");
		if (id instanceof Integer) {
			estado.setId(((Integer) id).longValue());
		} else {
			estado.setId((Long) id);
		}
		 		
		estado.setCNPJ((String) map.get("cnpj"));
		estado.setNome((String) map.get("nome"));
		System.out.println(estado.getCNPJ());

		System.out.println(json.toString());
	}

	private void parse(Filme filme, JSONObject json) {

		Object id = json.get("id");
		if (id != null) {
			if (id instanceof Integer) {
				filme.setId(((Integer) id).longValue());
			} else {
				filme.setId((Long) id);
			}
		}

		filme.setTitulo((String) json.get("titulo"));
		filme.setDiretor((String) json.get("diretor"));
		Object ano = json.get("ano");  // Corrigindo o erro de tipagem para "ano"
		if (ano != null) {
			if (ano instanceof Integer) {
				filme.setAno((Integer) ano);
			} else if (ano instanceof Long) {
				filme.setAno(((Long) ano).intValue());
			}
		}
		
		Object preco = json.get("preco");  // Corrigindo o erro de tipagem para "preco"
		if (preco != null && preco instanceof Number) {
			filme.setPreco(new BigDecimal(((Number) preco).doubleValue()));
		}

		Produtora produtora = new Produtora();
		parse(produtora, json);
		filme.setProdutora(produtora);
	}
	//OK
	@GetMapping(path = "/filmes")
	public ResponseEntity<List<Filme>> lista() {
		List<Filme> lista = service.findAll();
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}
	//OK
	@GetMapping(path = "/filmes/{id}")
	public ResponseEntity<Filme> lista(@PathVariable("id") long id) {
		Filme filme = service.findById(id);
		if (filme == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(filme);
	}
	//OK
	@GetMapping(path = "/filmes/produtoras/{id}")
	public ResponseEntity<List<Filme>> listaPorEstado(@PathVariable("id") long id) {
		
		List<Filme> lista = service.findByLocadora(id);
		
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}
	//OK
	@GetMapping(path = "/filmes/filtros")
	public ResponseEntity<List<String>> listaPorNome(@RequestParam(name = "term") String titulo) {
		List<Filme> filmes = service.findByNome("%" + titulo + "%");
		List<String> lista = new ArrayList<>();
		for (Filme f : filmes) {
			System.out.println(f.getTitulo());
			lista.add(f.getTitulo() + "/" + f.getProdutora().getCNPJ());
		}
		return ResponseEntity.ok(lista);
	}
	//OK
	@PostMapping(path = "/filmes")
	@ResponseBody
	public ResponseEntity<Filme> cria(@RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Filme filme = new Filme();
				parse(filme, json);
				//System.out.println(filme.getTitulo());
				//System.out.println(filme.getProdutora().getCNPJ());
				service.save(filme);
				return ResponseEntity.ok(filme);
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus. UNPROCESSABLE_ENTITY).body(null);
		}
	}
	//OK
	@PutMapping(path = "/filmes/{id}")
	public ResponseEntity<Filme> atualiza(@PathVariable("id") long id, @RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Filme filme = service.findById(id);
				if (filme == null) {
					return ResponseEntity.notFound().build();
				} else {
					parse(filme, json);
					service.save(filme);
					return ResponseEntity.ok(filme);
				}
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}


	//OK
	@DeleteMapping(path = "/filmes/{id}")
	public ResponseEntity<Boolean> remove(@PathVariable("id") long id) {

		Filme filme = service.findById(id);
		if (filme == null) {
			return ResponseEntity.notFound().build();
		} else {
			service.delete(id);
			return ResponseEntity.noContent().build();
		}
	}
}
