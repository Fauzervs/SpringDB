package br.edu.senaisp.SpringDB.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.edu.senaisp.SpringDB.model.Banda;


@Repository

public class BandaRepository implements ICrud{
	
	private String qrSelectAll = "SELECT id, nome, anolancamento FROM banda";
	

	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public List<Banda> lista() {
		
		return jdbcTemplate.query(qrSelectAll,(rs, rowNum) -> { return new Banda(rs.getInt("id"),rs.getString("nome"),rs.getInt("anolancamento"));
		
		});
	}

	//***************************COMANDO PARA INSERIR********************************
		@SuppressWarnings("deprecation")
		@Override
		public Banda buscaPorId(int id) {
		    String qrSelectById = "SELEct id, nome, anolancamento FROM banda WHERE id = ?";
		 
		    return jdbcTemplate.queryForObject(qrSelectById, new Object[]{id},
		        (rs, rowNum) -> new Banda(rs.getInt("id"), rs.getString("nome"), rs.getInt("anolancamento"))
		);
		}

	
		//***************************COMANDO PARA ALTERAR********************************
		@Override
		public Banda altera(Banda banda, int id) {
		    String qrUpdate = "UPDATE banda SET nome = ?, anolancamento = ? WHERE id = ?";
		    
		    jdbcTemplate.update(qrUpdate, banda.getNome(), banda.getAnoLancamento(), id);
		    
		    //retorna a Banda atualizada
		    return buscaPorId(id); 
		}

		
		
		//***************************COMANDO PARA INSERIR********************************
		public int insere(Banda banda) {
		    String qrInsert = "INSERT INTO banda(nome, anolancamento)VALUES (?, ?)";
		    
		    System.out.println(banda.getNome());
		    System.out.println(banda.getAnoLancamento());
		    
		    return jdbcTemplate.update(qrInsert, banda.getNome(), banda.getAnoLancamento());
		    
	
		}

		
		//***************************COMANDO PARA EXCLUIR********************************
		@Override
		public boolean exclui(int id) {
		    String qrDelete = "DELETE FROM banda WHERE id = ?";
		    
		    int rowsAffected = jdbcTemplate.update(qrDelete, id);
		    
		    return rowsAffected > 0; // Retorna true se pelo menos uma linha foi excluída
		}

		@Override
		public Banda insere(Banda banda, int id) {
			// TODO Auto-generated method stub
			return null;
		}

	
		

}
