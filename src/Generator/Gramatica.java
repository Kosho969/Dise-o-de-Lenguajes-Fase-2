package Generator;

import java.util.ArrayList;

public class Gramatica 
{
    public ArrayList<Produccion> producciones;

    public Gramatica(ArrayList<Produccion> prod)
    {
       this.producciones = prod; 
    }
    
    public void AddProd(Produccion prod)
    {
        this.producciones.add(prod);
    }

    public void toScreen()
    {
        System.out.println("Producciones");

        for (Produccion p : this.producciones)
        {
            System.out.println("Cabeza: " + p.getCabeza() + " " + "Cuerpo: " + p.getCuerpoString());
        }
    }
}
