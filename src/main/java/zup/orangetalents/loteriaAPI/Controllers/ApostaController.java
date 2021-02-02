package zup.orangetalents.loteriaAPI.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import zup.orangetalents.loteriaAPI.Models.Aposta;
import zup.orangetalents.loteriaAPI.Services.ApostaService;

@Controller
public class ApostaController {

	@Autowired
	private ApostaService apostaService;

	@GetMapping("/{email}")
	public ResponseEntity<Object> buscarAposta(@PathVariable String email) {
		return apostaService.buscarAposta(email);
	}

	@PostMapping("/")
	public ResponseEntity<Object> criarAposta(@RequestBody Aposta aposta) {
		return apostaService.criarAposta(aposta.getEmail());
	}
}



