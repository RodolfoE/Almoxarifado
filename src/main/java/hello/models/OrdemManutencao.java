package hello;
public class OrdemManutencao {
    private int Id;
    private int IdEquipamento;
    private String PrevManutencao;
    
    public OrdemManutencao(int Id, int IdEquipamento, String PrevManutencao){
        this.Id = Id;
        this.PrevManutencao = PrevManutencao;
        this.IdEquipamento = IdEquipamento;
    }

    public int getId(){
        return this.Id;
    }

    public String getPrevManutencao(){
        return this.PrevManutencao;
    }

    public int getIdEquipamento(){
        return this.IdEquipamento;
    }
}