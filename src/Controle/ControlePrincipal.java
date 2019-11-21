package Controle;

import Limite.*;
import java.io.Serializable;

public class ControlePrincipal{

    // Controle das outras classes
    private ControleComprador objCtrComprador;
    private ControleCorretor objCtrCorretor;
    private ControleImovel objCtrImovel;
    private ControleProposta objCtrProposta;
    private ControleVendedor objCtrVendedor;
    private ControleVisita objCtrVisita;
    
    private LimitePrincipal objLimPrincipal;

    public ControlePrincipal() {
        try{
            objCtrComprador = new ControleComprador();
            /*objCtrCorretor = new ControleCorretor();
            objCtrImovel = new ControleImovel();
            objCtrProposta = new ControleProposta();
            objCtrVendedor = new ControleVendedor();
            objCtrVisita = new ControleVisita();*/
        }catch (Exception error){
            System.out.println("Erro na abertura de arquivo! \n Dica: Exclua arquivos já criados na pasta NetBenas");
            System.exit(1);
        }
        objLimPrincipal = new LimitePrincipal(this);
    }
    
    public void teste(){
        System.out.println("okok");
    }
    
    public void finalize(){
        try{
            objCtrComprador.finalize();
        }catch (Exception error){
            System.out.println("Erro no fechamento dos arquivos");
        }finally{
            System.exit(0);
        }
        
    }

    public ControleComprador getObjCtrComprador() {
        return objCtrComprador;
    }

    public ControleCorretor getObjCtrCorretor() {
        return objCtrCorretor;
    }

    public ControleImovel getObjCtrImovel() {
        return objCtrImovel;
    }

    public ControleProposta getObjCtrProposta() {
        return objCtrProposta;
    }

    public ControleVendedor getObjCtrVendedor() {
        return objCtrVendedor;
    }

    public ControleVisita getObjCtrVisita() {
        return objCtrVisita;
    }

}
