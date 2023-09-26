package br.ufscar.dc.dsw.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.ufscar.dc.dsw.domain.Post;
import br.ufscar.dc.dsw.service.spec.IRestClientService;

@Service
public class RestClientService implements IRestClientService {

	@Autowired
	private RestTemplate restTemplate;

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Override
	public Long create(Post cidade) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Post> entity = new HttpEntity<Post>(cidade, headers);
		String url = "http://jsonplaceholder.typicode.com/posts";
		ResponseEntity<Post> res = restTemplate.postForEntity(url, entity, Post.class);
		Post c = res.getBody();

		return c.getId();
	}
	
	@Override
	public List<Post> get() {
		String url = "T http://jsonplaceholder.typicode.com/posts";
		Post[] cidades = restTemplate.getForObject(url, Post[].class);
		return Arrays.asList(cidades);
	}
	
	@Override
	public Post get(Long id) {
		String url = " http://jsonplaceholder.typicode.com/posts/" + id;
		return restTemplate.getForObject(url, Post.class);
	}
	
	
	@Override
	public boolean update(Post post) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Post> entity = new HttpEntity<Post>(post, headers);
		String url = " http://jsonplaceholder.typicode.com/posts/" + post.getId();
		ResponseEntity<Post> res = restTemplate.exchange(url, HttpMethod.PUT, entity, Post.class);
		return res.getStatusCode() == HttpStatus.OK;
	}
	
	@Override
	public boolean delete(Long id) {
		try {
			String url = "http://jsonplaceholder.typicode.com/posts/" + id;
			restTemplate.delete(url);
			return true;
		} catch (RestClientException e) {
			return false;
		}
	}
}