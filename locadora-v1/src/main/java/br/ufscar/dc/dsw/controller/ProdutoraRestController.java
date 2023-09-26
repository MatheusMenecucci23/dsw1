package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufscar.dc.dsw.domain.Produtora;
import br.ufscar.dc.dsw.service.spec.IProdutoraService;

@CrossOrigin
@RestController
public class ProdutoraRestController {

	@Autowired
	private IProdutoraService service;

	private boolean isJSONValid(String jsonInString) {
		try {
			return new ObjectMapper().readTree(jsonInString) != null;
		} catch (IOException e) {
			return false;
		}
	}

	private void parse(Produtora produtora, JSONObject json) {
		
		Object id = json.get("id");
		if (id != null) {
			if (id instanceof Integer) {
				produtora.setId(((Integer) id).longValue());
			} else {
				produtora.setId((Long) id);
			}
		}

		produtora.setNome((String) json.get("nome"));
		produtora.setCNPJ((String) json.get("CNPJ"));
	}
	//ok
	@GetMapping(path = "/produtoras")
	public ResponseEntity<List<Produtora>> lista() {
		List<Produtora> lista = service.findAll();
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}
	//ok
	@GetMapping(path = "/produtoras/{id}")
	public ResponseEntity<Produtora> lista(@PathVariable("id") long id) {
		Produtora estado = service.findById(id);
		if (estado == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(estado);
	}
	//ok
	@PostMapping(path = "/produtoras")
	@ResponseBody
	public ResponseEntity<Produtora> cria(@RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Produtora estado = new Produtora();
				parse(estado, json);
				service.save(estado);
				return ResponseEntity.ok(estado);
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}
	//ok
	@PutMapping(path = "/produtoras/{id}")
	public ResponseEntity<Produtora> atualiza(@PathVariable("id") long id, @RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Produtora estado = service.findById(id);
				if (estado == null) {
					return ResponseEntity.notFound().build();
				} else {
					parse(estado, json);
					service.save(estado);
					return ResponseEntity.ok(estado);
				}
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}
	//ok
	@DeleteMapping(path = "/produtoras/{id}")
	public ResponseEntity<Boolean> remove(@PathVariable("id") long id) {

		Produtora estado = service.findById(id);
		if (estado == null) {
			return ResponseEntity.notFound().build();
		} else {
			service.delete(id);
			return ResponseEntity.noContent().build();
		}
	}
}
