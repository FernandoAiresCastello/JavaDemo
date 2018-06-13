package br.com.fernandoairescastello.demo;

import java.util.List;
import java.util.UUID;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import br.com.fernandoairescastello.demo.model.Post;
import br.com.fernandoairescastello.demo.service.PostService;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private PostService postService;
	
	@Test
	public void salvarPost() throws Exception {
		final String conteudoPostTeste = "Meu post de teste " + UUID.randomUUID();
		this.postService.salvarPost(conteudoPostTeste);
		List<Post> posts = this.postService.pesquisarPostsPorConteudo(conteudoPostTeste);
		assertThat(posts.size()).isEqualTo(1);
		this.postService.excluirPost(posts.get(0).getId());
	}

	@Test
	public void listarTodosPosts() throws Exception {
		final String conteudoPostTeste = "Meu post de teste " + UUID.randomUUID();
		this.postService.salvarPost(conteudoPostTeste);
		List<Post> posts = this.postService.obterListaPosts();
		assertThat(posts.isEmpty()).isFalse();
		posts = this.postService.pesquisarPostsPorConteudo(conteudoPostTeste);
		this.postService.excluirPost(posts.get(0).getId());
	}
	
	@Test
	public void upvotePost() throws Exception {
		final String conteudoPostTeste = "Meu post de teste " + UUID.randomUUID();
		this.postService.salvarPost(conteudoPostTeste);
		List<Post> posts = this.postService.pesquisarPostsPorConteudo(conteudoPostTeste);
		final long id = posts.get(0).getId();
		this.postService.upvotePost(id);
		assertThat(this.postService.obterPost(id).getUpvotes()).isEqualTo(1);
		this.postService.upvotePost(id);
		assertThat(this.postService.obterPost(id).getUpvotes()).isEqualTo(2);
		this.postService.upvotePost(id);
		assertThat(this.postService.obterPost(id).getUpvotes()).isEqualTo(3);
		this.postService.excluirPost(id);
	}
	
	@Test
	public void excluirPost() throws Exception {
		final String conteudoPostTeste = "Meu post de teste " + UUID.randomUUID();
		this.postService.salvarPost(conteudoPostTeste);
		List<Post> posts = this.postService.pesquisarPostsPorConteudo(conteudoPostTeste);
		assertThat(posts.size()).isEqualTo(1);
		final long id = posts.get(0).getId();
		this.postService.excluirPost(id);
		posts = this.postService.pesquisarPostsPorConteudo(conteudoPostTeste);
		assertThat(posts.isEmpty()).isTrue();
		Post post = this.postService.obterPost(id);
		assertThat(post).isNull();
	}
}
