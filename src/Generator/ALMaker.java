
package Generator;

import java.util.ArrayList;
import java.util.Arrays;

public class ALMaker 
{
    public ALMaker()
    {
        
    }
    
    ArrayList<String> StringToAL(String cadena)
    {
        String[] Chain = cadena.split("");
        String  imp = "";
        ArrayList<String> Final = new ArrayList<String>();
        for(int i = 2;i<Chain.length-1; i++)
        {
            Final.add(Chain[i]);
        }

        return Final;
    }
    
    String ALToString(ArrayList<String> AL)
    {
        String Final = "";
        for(String s : AL)
        {
            Final = Final + s;
        }
        return Final;
    }
}
