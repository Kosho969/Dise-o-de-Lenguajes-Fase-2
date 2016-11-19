

package Generator;

import java.util.ArrayList;

public class Produccion 
{
    public String cabeza;
    public ArrayList<String> cuerpo;
    
    public Produccion()
    {
    
    }
    public Produccion(String Cabeza, ArrayList<String> Cuerpo)
    {
        this.cabeza = Cabeza;
        this.cuerpo = Cuerpo;
    }
    
    public void setCabeza(String Cabeza)
    {
        this.cabeza = Cabeza;
    }
    public void setCuerpo(ArrayList<String> Cuerpo)
    {
        this.cuerpo = Cuerpo;
    }
    public String getCabeza()
    {
        return this.cabeza;
    }
    public String getCuerpoString()
    {
        String Scuerpo = "";
        for(String s : this.cuerpo)
        {
            Scuerpo = Scuerpo + " " + s;
        }
        return Scuerpo;
    }
    public ArrayList<String> getCuerpo()
    {
        return this.cuerpo;
    }
}
