package hello;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
public class GreetingController {
    /*
        Obtem tupla da relação Ordem_Manutencao por PK id
    */
    @GetMapping("/obter_om/{id}")
    public OrdemManutencao ObterOm(@PathVariable int id){
        return new Dados().ObterOrdemManutencaoPelaId(id);
    }

    /**
    Obtem todas as tuplas da relação Ordem_Manutencao */
    @GetMapping("/obter_om")
    public List<OrdemManutencao> ObterOm(){
        return new Dados().ObterListaOrdemManutencao();
    }

    /**Insere tupla na relação Ordem_Manutencao */
    @PostMapping("/post_ordem_manutencao")    
    public String AddOrdemManutencao(@RequestBody Map<String, String> body) {
        try{
            int IdEquipamento = Integer.parseInt(body.get("Id_equipamento"));
            String prevManutencao = body.get("prev_manutencao");
            new Dados().InserirOrdemManutencao(new OrdemManutencao(-1, IdEquipamento, prevManutencao));
            return "{msg: 'Ordem de manutenção inserida com sucesso!.'}";
        } catch (Exception e){
            return e.getMessage();
        }
    }

    /**
        Altera a tupla de PK id na relação Ordem_Manutencao.
     */
    @PutMapping("/alterar_Ordem_Manutencao/{id}")
    public String alterarOm(@PathVariable int id, @RequestBody Map<String, String> body){
        try{
            int idEquip = Integer.parseInt(body.get("Id_equipamento"));
            String prevManutencao = body.get("prev_manutencao");
            new Dados().AlterarOrdemManutencao(new OrdemManutencao(id, idEquip, prevManutencao));
            return "{msg: 'Ordem de manutenção inserida com sucesso!.'}";
        } catch (Exception e){
            return e.getMessage();
        }
    }    

    /**
        Insere uma tupla na relação Equipamento
     */
    @PostMapping("/post_equipamento")    
    public String equipamento(@RequestBody Map<String, String> body) {
        try{
            String nomeEquip = body.get("nomeEquip");
            new Dados().InserirEquipamento(nomeEquip);
            return "{msg: 'Equipamento inserido com sucesso!.'}";
        } catch (Exception e){
            return e.getMessage();
        }      
    }    

    /*
        Obtem todas as tupla da relação Equipamento
    */
    @GetMapping("/obter_equipamento")
    public List<Equipamento> ObterEquips(){
        return new Dados().ObterListaEquipamentos();
    }

    /*
        Obtem tupla da relação Equipamento por PK id
    */
    @GetMapping("/obter_equipamento/{id}")
    public Equipamento ObterEquip(@PathVariable int id){
        return new Dados().ObterEquipamentoPelaId(id);
    }
}
