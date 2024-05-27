package br.edu.senaisp.SpringDB.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.senaisp.SpringDB.model.Banda;
import br.edu.senaisp.SpringDB.repository.BandaRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/banda")
public class BandaController {
	
	@Autowired
	BandaRepository repository;
	private int id;
	
	@GetMapping()
	public String ListaBandas() {
	    StringBuilder tmp = new StringBuilder();
	    
	    for(Banda banda : repository.lista()) {
	        tmp.append(banda.getNome()).append("<br>");
	    }
	    
	    return tmp.toString();
	}



	
	@GetMapping("/{id}")
	public String BuscaPorId(@PathVariable int id) {
	
		return repository.buscaPorId(id).getNome();
}
	
	
	@PutMapping("/{id}")
    public String AlteraBanda(@RequestBody Banda banda, @PathVariable int id) {
        Banda bandaAtualizada = repository.altera(banda, id);
        
        if (bandaAtualizada != null) {
            return "Banda atualizada com sucesso";
        } else {
            return "Não foi possível atualizar a banda";
        }
        }
        
	@PostMapping
	public ResponseEntity<Banda> gravar(@RequestBody @Valid Banda banda) {
    	repository.insere(banda);
	   	        return ResponseEntity.ok(banda);
	    }
	


        // 
        @DeleteMapping("/{id}")
        public String ExcluiBanda(@PathVariable int id) {
            boolean excluiu = repository.exclui(id);
            
            if (excluiu) {
                return "Banda excluída com sucesso";
            } else {
                return "Não foi possível excluir a banda";
            }
    }
	

}