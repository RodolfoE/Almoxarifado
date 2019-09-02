package hello;
public class Equipamento {
    private int Id;
    private String NomeEquip;
    
    public Equipamento(int Id, String NomeEquip){
        this.Id = Id;
        this.NomeEquip = NomeEquip;
    }

    public int getId(){
        return this.Id;
    }
    public String getNomeEquip(){
        return this.NomeEquip;
    }
}