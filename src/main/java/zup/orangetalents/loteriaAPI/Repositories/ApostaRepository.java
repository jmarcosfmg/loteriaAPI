package zup.orangetalents.loteriaAPI.Repositories;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import zup.orangetalents.loteriaAPI.Models.Aposta;

public interface ApostaRepository extends CrudRepository<Aposta, Long>{

	public List<Aposta> findAllByEmailIgnoreCaseOrderByCriacao(String email);
	
}
