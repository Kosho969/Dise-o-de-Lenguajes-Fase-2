
package Generator;

import Reader.AFD;
import Reader.RegexTreeConstruction;
import Reader.ShuntingYard;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

public class AutMaker 
{
    public AutMaker() 
    {
    }

    public AFD MakeAut(String Regex)
    {
        RegexTreeConstruction Directa = new RegexTreeConstruction();
        ShuntingYard Parser =  new ShuntingYard();
        String forDirecta = "«" + Regex + "»Ç#";
        String PostFixeadoDirecta = Parser.infixToPostfix(forDirecta);
        System.out.println(PostFixeadoDirecta);

        //String PostFixeadoDirectaFinal = PostFixeadoDirecta.trim();
        for (String s : PostFixeadoDirecta.split(""))
            {
                if(s.isEmpty() == false)
                {
                    Directa.add(s);
                }
                
            }
        Directa.postOrder(Directa._root);
        AFD AutFinalCD = Directa.DirectConstruction();
        
        return AutFinalCD;
    }
    
    public ArrayList<String> StringToSet(String str)
    {
        String[] Str = str.split("");
        ArrayList<String> Final = new ArrayList<String>(Arrays.asList(Str));
        Final.remove(0);
        //Final.remove(0);
        Final.remove(Final.size()-1);
        for(String s : Final)
        {
            System.out.println(s);
        }
        return Final;
    }
    public ArrayList<String> CharasToSet(String CharaIni, String CharaFin)
    {
        
        char ini = CharaIni.charAt(1);
        System.out.println(ini);
        char fin = CharaFin.charAt(1);
        System.out.println(fin);
        int AsciiIni = (int) ini;
        System.out.println(AsciiIni);
        int AsciiFin = (int) fin;
        System.out.println(AsciiFin);
        
        ArrayList<String> Final = new ArrayList<String>();
        for(int i=ini; i<=fin; i++)
        {
            Final.add(String.valueOf((char)i));
        }
        for(String s : Final)
        {
            System.out.println(s);
        }
        
        return Final;
    }
    public AFD CharacterSetToAFD(ArrayList<String> str, String id) throws UnsupportedEncodingException, IOException
    {
        if(str.size()==0)
        {
            String regex = "«~»";
            AFD Final = MakeAut(regex);
            return Final;
        }
        String regex = "«";
        for(int i=0; i<str.size()-1; i++)
        {
            String s = str.get(i);
            regex = regex + s + "Ø";
        }
        System.out.println("DANDO CLAVOS?: " + regex);
        regex = regex + str.get(str.size()-1);
        regex = regex + "»";
        System.out.println(regex);
        AFD Final = MakeAut(regex);
        Final.toGraph(id);
        
        return Final;
        
    }
    public AFD KeyWordsSetToAFD(ArrayList<String> str, String id) throws UnsupportedEncodingException, IOException
    {
        String regex = "«";
        for(int i=0; i<str.size()-1; i++)
        {
            String s = str.get(i);
            if(s.equals("\\") || s.equals("\""))
            {
                s = "\\" + s;
            }
            regex = regex + s + "Ç";
        }
        regex = regex + str.get(str.size()-1);
        regex = regex + "»";
        System.out.println(regex);
        AFD Final = MakeAut(regex);
        Final.toGraph(id);
        
        return Final;
        
    }
    public String KeyWordsSetToRegex(ArrayList<String> str) throws UnsupportedEncodingException, IOException
    {
        System.out.println(str);
        String regex = "«";
        for(int i=0; i<str.size()-1; i++)
        {
            String s = str.get(i);
            if(s.equals("\\") || s.equals("\""))
            {
                s = "\\" + s;
            }
            regex = regex + s + "Ç";
        }
        String f = "";
        if ((str.size())-1 >= 0 )
        {
            f = str.get(str.size()-1);
        }
        else
        {
            f = str.get(str.size());
        }

        if(f.equals("\\") || f.equals("\""))
        {
            f = "\\" + f;
        }

        regex = regex + f;
        regex = regex + "»";
        System.out.println(regex);

        return regex;
    }
}
