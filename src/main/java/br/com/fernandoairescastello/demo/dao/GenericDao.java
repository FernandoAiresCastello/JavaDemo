package br.com.fernandoairescastello.demo.dao;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Selection;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GenericDao {

	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	protected Session session;
	
	@PostConstruct
	public void Init() {
		this.session = this.entityManagerFactory.unwrap(SessionFactory.class).openSession();
	}
	
	public List<?> obterTodos(Class<?> classe) {
		CriteriaBuilder builder = this.session.getCriteriaBuilder();
		CriteriaQuery<?> criteria = builder.createQuery(classe);
		criteria.multiselect((Selection<?>) criteria.from(classe));
		return session.createQuery(criteria).getResultList();
	}
	
	public Object obterPorId(Class<?> classe, long id) {
		return this.session.get(classe, id);
	}
	
	public void salvar(Object object) {
		this.session.save(object);
	}

	public void salvarOuAtualizar(Object object) {
		Transaction t = this.session.beginTransaction();
		try {
			this.session.saveOrUpdate(object);
			this.session.flush();
			t.commit();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			t.rollback();
		}
	}
	
	public void excluir(Class<?> classe, long id) {
		Transaction t = this.session.beginTransaction();
		try {
			this.session.delete(this.session.load(classe, id));
			this.session.flush();
			t.commit();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			t.rollback();
		}
	}
}
