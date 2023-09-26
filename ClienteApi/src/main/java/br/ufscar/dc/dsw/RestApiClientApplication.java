package br.ufscar.dc.dsw;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.ufscar.dc.dsw.domain.Post;

@SpringBootApplication
public class RestApiClientApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(RestApiClientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Criar um novo post
        Post newPost = new Post();
        newPost.setUserId(1L);
        newPost.setTitle("Teste Title");
        newPost.setBody("Teste Body");

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Post> createdPost = restTemplate.postForEntity("http://jsonplaceholder.typicode.com/posts", newPost, Post.class);
        System.out.println("Novo post criado: " + createdPost.getBody());

        // Imprimir a lista de posts
        ResponseEntity<Post[]> postsResponse = restTemplate.exchange("http://jsonplaceholder.typicode.com/posts", HttpMethod.GET, null, Post[].class);
        Post[] posts = postsResponse.getBody();
        System.out.println("Lista de Posts:");
        for (Post post : posts) {
            System.out.println(post);
        }

        // Imprimir o post de id = {id}
        Long postIdToFetch = 1L;
        Post fetchedPost = restTemplate.getForObject("http://jsonplaceholder.typicode.com/posts/" + postIdToFetch, Post.class);
        System.out.println("Post com ID " + postIdToFetch + ": " + fetchedPost);

        // Atualizar o post de id = {id}
        Long postIdToUpdate = 1L;
        Post updatedPost = new Post();
        updatedPost.setUserId(1L);
        updatedPost.setTitle("Update Title");
        updatedPost.setBody("Update Body");

        restTemplate.put("http://jsonplaceholder.typicode.com/posts/" + postIdToUpdate, updatedPost);
        System.out.println("Post com ID " + postIdToUpdate + " atualizado.");

        // Remover o post de id = {id}
        Long postIdToDelete = 1L;
        restTemplate.delete("http://jsonplaceholder.typicode.com/posts/" + postIdToDelete);
        System.out.println("Post com ID " + postIdToDelete + " removido.");
    }
}
