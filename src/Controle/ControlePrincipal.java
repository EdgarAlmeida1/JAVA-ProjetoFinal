package Controle;

import java.io.Serializable;

public class ControlePrincipal implements Serializable {

    // Controel das outras classes
    private ControleComprador objCtrComprador;
    private ControleCorretor objCtrCorretor;
    private ControleImovel objCtrImovel;
    private ControleProposta objCtrProposta;
    private ControleVendedor objCtrVendedor;
    private ControleVisita objCtrVisita;

}
