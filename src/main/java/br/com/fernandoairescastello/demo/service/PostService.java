package br.com.fernandoairescastello.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.fernandoairescastello.demo.dao.PostDao;
import br.com.fernandoairescastello.demo.model.Post;

@Service
@Transactional
public class PostService {
	
	@Autowired
	private PostDao postDao;
	
	public Post obterPost(long id) throws Exception {
		return this.postDao.obterPost(id);
	}
	
	public List<Post> obterListaPosts() throws Exception {
		return this.postDao.obterListaPosts();
	}
	
	public void excluirPost(long id) throws Exception {
		this.postDao.excluirPost(id);
	}

	public List<Post> pesquisarPostsPorConteudo(String conteudo) throws Exception {
		return this.postDao.pesquisarPostsPorConteudo(conteudo);
	}

	public void salvarPost(String conteudo) throws Exception {
		this.postDao.salvarPost(new Post(conteudo));
	}
	
	public void upvotePost(long id) throws Exception {
		this.postDao.upvotePost(id);
	}
}
