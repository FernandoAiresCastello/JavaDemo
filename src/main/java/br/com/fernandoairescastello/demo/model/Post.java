package br.com.fernandoairescastello.demo.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="post")
public class Post implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id", unique=true, nullable=false)
	private long id;
	@Column(name="conteudo", length=500)
	private String conteudo;
	@Column(name="upvotes")
	private int upvotes;
	
	public Post() {
	}
	
	public Post(String conteudo) {
		this.conteudo = conteudo;
	}
	
	public Post(Post other) {
		this.id = other.id;
		this.conteudo = other.conteudo;
		this.upvotes = other.upvotes;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	public int getUpvotes() {
		return upvotes;
	}
	public void setUpvotes(int upvotes) {
		this.upvotes = upvotes;
	}
}
