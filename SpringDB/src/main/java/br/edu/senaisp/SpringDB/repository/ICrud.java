package br.edu.senaisp.SpringDB.repository;

import java.util.List;

import br.edu.senaisp.SpringDB.model.Banda;

public interface ICrud {
    
    public List<Banda> lista();
    
    public Banda buscaPorId(int id);
    
    public Banda altera(Banda banda, int id);
    
    public Banda insere(Banda banda, int id);
    
    public boolean exclui(int id);
}
