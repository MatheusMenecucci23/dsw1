package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Post;

public interface IRestClientService {
	
	Long create(Post cidade);	
	
	List<Post> get();
	Post get(Long id);
	
	boolean update(Post cidade);
	
	boolean delete(Long id);
}
