package zup.orangetalents.loteriaAPI.Models;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "Aposta")
@EntityListeners(AuditingEntityListener.class)
public class Aposta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Email
	@NotNull
	private String email;

	private String numerosSorteados;

	@CreatedDate
	private Date criacao;

	public Aposta() {

	}

	public Aposta(String email) {
		setEmail(email);
		this.numerosSorteados = null;
	}

	public String getEmail() {
		return email;
	}
	
	public Aposta(String email, List<Integer> numerosSorteados) {
		setEmail(email);
		setNumerosSorteados(numerosSorteados);
	}

	public void setEmail(String email) {
		if (!(email == null || email.isEmpty() || email.isBlank())) {
			this.email = email;
		}
	}

	public List<Integer> getNumerosSorteados() {
		return Arrays.asList(numerosSorteados.split(",")).stream().map(Integer::valueOf).collect(Collectors.toList());
	}

	public void setNumerosSorteados(List<Integer> numerosSorteados) {
		this.numerosSorteados = numerosSorteados.stream().map(String::valueOf).collect(Collectors.joining(","));
	}
	
	
	
	
}
