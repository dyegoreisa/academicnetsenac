package br.com.senac.dao;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import br.com.senac.model.Aluno;
import br.com.senac.model.Curso;
import br.com.senac.model.Disciplina;
import br.com.senac.model.Matricula;
import br.com.senac.model.Turma;

@Stateless
@Local
public class MatriculaDAO {
	
	@PersistenceContext
	EntityManager em;		
			
	public boolean inserir(Matricula matricula) {
		em.persist(matricula);
		return true;
	}

	public boolean atualizar(Matricula matricula) {
		em.merge(matricula);
		return true;
	}
	
	public boolean apagar(Matricula matricula) {
		matricula = getByCodigo(matricula.getCodigo());
		if (matricula != null) {
			em.remove(matricula);
		}
		return true;
	}

	public Matricula getByCodigo(String codigo) {
		// TODO: Buscar por código
		return null;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Matricula> listar() {
		CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(Matricula.class));
		return (List<Matricula>) em.createQuery(cq).getResultList();
	}

	public List<Matricula> buscar(String texto) {
		texto = "%" + texto + "%";
		
		Query query = em.createNamedQuery("buscarMatriculas");
		query.setParameter("nome", texto);
		query.setMaxResults(10);
		
		List<Matricula> matriculas = query.getResultList();
		
		return matriculas;
	}

	public List<Matricula> getByTurma(Turma turma) {
		
		Query query = em.createQuery("SELECT m FROM Matricula m WHERE m.turma = :turma ");
		query.setParameter("turma", turma);
		query.setMaxResults(25);
		
		return query.getResultList();
	}
	
	
	public List<Matricula> getByAluno(Aluno aluno) {
		List<Matricula> matriculas = null;
		// TODO: fazer a busca por aluno
		return matriculas;
	}
	
	
	public List<Matricula> getByCurso(Curso curso) {
		List<Matricula> matriculas = null;
		// TODO: fazer a busca por curso
		return matriculas;
	}
	
	public List<Matricula> getByDisciplina(Disciplina disciplina) {
		List<Matricula> matriculas = null;
		// TODO: fazer a busca por disciplina
		return matriculas;
	}

//	
	
	public Object getById(Curso cursoSelecionada) {
		// TODO Auto-generated method stub
		return null;
	}
	
}