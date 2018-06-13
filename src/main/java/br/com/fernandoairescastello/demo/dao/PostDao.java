package br.com.fernandoairescastello.demo.dao;

import java.util.*;
import javax.persistence.*;
import org.springframework.stereotype.Repository;
import br.com.fernandoairescastello.demo.model.Post;

@Repository
public class PostDao extends GenericDao {
	
	@SuppressWarnings("unchecked")
	public List<Post> obterListaPosts() {
		return (List<Post>) super.obterTodos(Post.class);
	}
	
	public void salvarPost(Post post) {
		super.salvar(post);
	}
	
	public void upvotePost(long id) {
		Post post = this.obterPost(id);
		post.setUpvotes(post.getUpvotes() + 1);
		super.salvarOuAtualizar(post);
	}
	
	public Post obterPost(long id) {
		return (Post) super.obterPorId(Post.class, id);
	}
	
	public void excluirPost(long id) {
		super.excluir(Post.class, id);
	}
	
	public List<Post> pesquisarPostsPorConteudo(String conteudo) {
		EntityManager em = super.session.getEntityManagerFactory().createEntityManager();
		TypedQuery<Post> query = em.createQuery("select p from Post p where p.conteudo like :conteudo", Post.class);
		query.setParameter("conteudo", "%" + conteudo + "%");
		return query.getResultList();
	}
}
