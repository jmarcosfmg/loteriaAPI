package zup.orangetalents.loteriaAPI.Services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import zup.orangetalents.loteriaAPI.Models.Aposta;
import zup.orangetalents.loteriaAPI.Repositories.ApostaRepository;

@Service
public class ApostaService {

	@Autowired
	private ApostaRepository apostaRepository;

	public ResponseEntity<Object> criarAposta(String email) {
		String emailValidado;
		Aposta apostaCriada;
		try {
			emailValidado = validarEmail(email);
			List<Integer> numerosEscolhidos = new ArrayList<Integer>(gerarNumerosAleatorios(1, 61, 6));
			apostaCriada = apostaRepository.save(new Aposta(emailValidado, numerosEscolhidos));
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch(Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_GATEWAY);
		}
		return new ResponseEntity<Object>(apostaCriada, HttpStatus.CREATED);
	}

	public ResponseEntity<Object> buscarAposta(String email) {
		String emailValidado;
		try {
			emailValidado = validarEmail(email);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Object>(apostaRepository.findAllByEmailIgnoreCaseOrderByCriacao(emailValidado),
				HttpStatus.OK);
	}

	private String validarEmail(String email) {
		if (email == null)
			throw new IllegalArgumentException("O email não pode ser nulo");
		if (email.isEmpty() || email.isBlank())
			throw new IllegalArgumentException("O email não pode ser vazio");
		if (!email.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$"))
			throw new IllegalArgumentException("Formato de email inválido");
		return email.trim().toLowerCase();
	}

	private Set<Integer> gerarNumerosAleatorios(Integer valorMinimo, Integer maximo, Integer quantidade) {
		Set<Integer> numerosEscolhidos = new HashSet<Integer>();
		Random rand = new Random();
		Integer range = Math.abs(maximo - valorMinimo);
		while (numerosEscolhidos.size() < quantidade) {
			numerosEscolhidos.add(rand.nextInt(range) + valorMinimo);
		}
		return numerosEscolhidos;
	}
}
