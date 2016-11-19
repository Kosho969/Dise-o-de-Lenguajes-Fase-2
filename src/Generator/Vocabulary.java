
package Generator;

import Generator.AutMaker;
import Reader.AFD;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;


public class Vocabulary 
{
    // Conjuntos PreDeclarados
    ArrayList<String> letterAL = new ArrayList<String>(Arrays.asList("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"));
    ArrayList<String> digitAL = new ArrayList<String>(Arrays.asList("0","1","2","3","4","5","6","7","8","9"));
    ArrayList<String> anybutquoteAL = new ArrayList<String>(Arrays.asList("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","0","1","2","3","4","5","6","7","8","9"," ","!","%","^","&","=","?",">","<",":",".",",","(","[","{",")","$","]","}","-","+","'","\"", "\\"));
    ArrayList<String> anybutapostropheAL = new ArrayList<String>(Arrays.asList("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","0","1","2","3","4","5","6","7","8","9"," ","!","%","^","&","=","?",">","<",":",".",",","(","[","{",")","$","]","}","-","+","\\","\\'","\""));
    ArrayList<String> anyAL = new ArrayList<String>(Arrays.asList("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","0","1","2","3","4","5","6","7","8","9"," ","!","%","^","&","=","?",">","<",":",".",",","(","[","{",")","$","]","}","-","+","\\","\"","'"));
    String letter = "«aØbØcØdØeØfØgØhØiØjØkØlØmØnØoØpØqØrØsØtØuØvØwØxØyØzØAØBØCØDØEØFØGØHØIØJØKØLØMØNØOØPØQØRØSØTØUØVØWØXØYØZ»";
    String digit ="«0Ø1Ø2Ø3Ø4Ø5Ø6Ø7Ø8Ø9»";
    String anyButQuote ="«aØ ØbØcØdØeØfØgØhØiØjØkØlØmØnØoØpØqØrØsØtØuØvØwØxØyØzØAØBØCØDØEØFØGØHØIØJØKØLØMØNØOØPØQØRØSØTØUØVØWØXØYØZØ0Ø1Ø2Ø3Ø4Ø5Ø6Ø7Ø8Ø9Ø!Ø%Ø+Ø^Ø&Ø=Ø?Ø>Ø<Ø:Ø.Ø,Ø(Ø[Ø{Ø)Ø$Ø]Ø}Ø-Ø+Ø'Ø\\Ø«\\Ç'»Ø«\\Ç\"»»";
    String anyButApostrophe ="«aØbØcØdØeØfØgØhØiØjØkØlØmØnØoØpØqØrØsØtØuØvØwØxØyØzØAØBØCØDØEØFØGØHØIØJØKØLØMØNØOØPØQØRØSØTØUØVØWØXØYØZØ0Ø*Ø1Ø2Ø3Ø4Ø5Ø6Ø7Ø8Ø9Ø+Ø!Ø%Ø^Ø&Ø«\\Ç\\»Ø=Ø?Ø>Ø_Ø$Ø<Ø«\\Ç'»Ø«\\Ç\"»Ø:Ø.Ø,Ø(Ø[Ø{Ø)Ø$Ø]Ø}Ø-Ø+Ø\"»";
    String ANY ="«aØbØcØdØeØfØgØhØiØjØkØlØmØnØoØpØqØrØsØtØuØvØwØxØyØzØAØBØCØDØEØFØGØHØIØJØKØLØMØNØOØPØQØRØSØTØUØVØWØXØYØZØ0Ø1Ø2Ø3Ø4Ø5Ø6Ø7Ø8Ø9Ø\"Ø!Ø%Ø^Ø'Ø&Ø$Ø=Ø?Ø>Ø<Ø«\\Ç'»»";
    String ident = "«" + letter + "Ç" + "«" + letter + "Ø" + digit + "»º»";
    
    String attribute = "«"+"<Ç.Ç"+"«"+ANY+"º»"+"Ç.Ç>"+"»"; 
    String semAction = "«"+"(Ç.Ç"+"«"+ANY+"º»"+"Ç.Ç)"+"»";
    
    String number = digit + "Ç" + digit + "º";
    String string = "«\"Ç" + anyButQuote + "ºÇ\"»";
    String chara = "«'Ç" + anyButApostrophe + "Ç'»";
    String Chara = chara + "Ø" + "«CÇHÇR" + "Ç(Ç" + number + "Ç)»";
    String BasicSet = string + "Ø" + ident + "Ø«" + Chara + "Ø" + Chara + "Ç.Ç.Ç" + Chara + "»";
    String Symbol = "«" + string + "Ø" + ident + "Ø" + chara + "»";
    
    String Regex = "";
    
    
    
   
    
    
    
    
    
    
    
    
    //Sintaxis
    AutMaker AUT = new AutMaker();
    AFD Attribute = AUT.MakeAut(attribute);
    AFD SemAction = AUT.MakeAut(semAction);
    AFD Number = AUT.MakeAut(number);
    AFD Ident = AUT.MakeAut(ident);
    AFD String = AUT.MakeAut(string);
    AFD Char = AUT.MakeAut(chara);
    AFD CHAR = AUT.MakeAut(Chara);
    AFD symbol = AUT.MakeAut(Symbol);
    
    
    ArrayList<String> RegExTokens = new  ArrayList<String>();
    
    
    
    
    public Vocabulary()
    {
        
    }
    
    public void PruebaAFD() throws UnsupportedEncodingException, IOException
    {
        String exp = "a Ø « h Ø ~ »";
        AFD aProba = AUT.MakeAut(exp);
        aProba.toGraph("EPSILON");
        aProba.simulacion("a");
        aProba.simulacion("h");
        aProba.simulacion("");
    }
    //sets y gets
    
    //Caso Base
    public void TokenExpression(ArrayList<String> DondeVoy)
    {
        System.out.println(DondeVoy);
        boolean permiso = false;
        int cant=0;
        ArrayList<String> DondeIre = new ArrayList<String>(); 
        for(int j=0; j<DondeVoy.size(); j++)
        {
            DondeIre.clear();
            if(DondeVoy.get(j).equals("("))
            {
                for(int k=j+1; k<DondeVoy.size(); k++)
                {
                    DondeIre.add(DondeVoy.get(k));
                }
                System.out.println(DondeIre);
                Regex = Regex + " " + "(";
                System.out.println("Regex: " + Regex);
                System.out.println("voy de ida --> (");
                cant = TokenExpression("(", DondeIre);
                if(cant==-1)
                {
                    return;
                }
                else
                {
                    j=j+cant;
                }
                
                //TokenExpr
            }
            else if(DondeVoy.get(j).equals("["))
            {
                for(int k=j+1; k<DondeVoy.size(); k++)
                {
                    DondeIre.add(DondeVoy.get(k));
                }
                System.out.println(DondeIre);
                Regex = Regex + " " + "[";
                System.out.println("Regex: " + Regex);
                System.out.println("voy de ida--> [");
                cant = TokenExpression("[", DondeIre);
                if(cant==-1)
                {
                    return;
                }
                else
                {
                    j=j+cant;
                }
                
                //TokenExpr
            }
            else if(DondeVoy.get(j).equals("{"))
            {
                for(int k=j+1; k<DondeVoy.size(); k++)
                {
                    DondeIre.add(DondeVoy.get(k));
                }
                System.out.println(DondeIre);
                Regex = Regex + " " + "{";
                System.out.println("Regex: " + Regex);
                System.out.println("voy de ida --> {");
                cant = TokenExpression("{", DondeIre);
                if(cant==-1)
                {
                    RegExTokens.add(Regex);
                    return;
                }
                else
                {
                    j=j+cant;
                }
                
                //TokenExpr
            }
            else if(this.symbol.simulacionPegao(DondeVoy.get(j)))
            {
                Regex = Regex + " " + DondeVoy.get(j);
                System.out.println("Regex: " + Regex);
                permiso = true;
            }
            else if(permiso == true && DondeVoy.get(j).equals("|"))
            {
                Regex = Regex + " " + DondeVoy.get(j);
                System.out.println("Regex: " + Regex);
                System.out.println("or");
                permiso = false;
            }
            else
            {
                System.out.println("ERROR. Token not valid");
                System.exit(0);
                //Error
            }
        }
        RegExTokens.add(Regex);
        return;
    }
    //Caso Recursivo
    public int TokenExpression(String Predecesor, ArrayList<String> DondeVoy)
    {
        String PredecesorInverso="";
        if(Predecesor.equals("("))
        {
            PredecesorInverso = ")";
        }
        else if(Predecesor.equals("["))
        {
            PredecesorInverso = "]";
        }
        else if(Predecesor.equals("{"))
        {
            PredecesorInverso = "}";
        }
        int desfase = 0;
        int cant = 0;
        boolean permiso = false;
        ArrayList<String> DondeIre = new ArrayList<String>(); 
        for(int j=0; j<DondeVoy.size(); j++)
        {
            DondeIre.clear();
            if(DondeVoy.get(j).equals("("))
            {
                for(int k=j+1; k<DondeVoy.size(); k++)
                {
                    DondeIre.add(DondeVoy.get(k));
                }
                System.out.println(DondeIre);
                Regex = Regex + " " + "(";
                System.out.println("Regex: " + Regex);
                System.out.println("voy de ida --> (");
                desfase=desfase+1;
                System.out.println(desfase);
                cant = TokenExpression("(", DondeIre);
                if(cant==-1)
                {
                    return -1;
                }
                else
                {
                    j=j+cant;
                    desfase = desfase + cant;
                }
                
                //TokenExpr
            }
            else if(DondeVoy.get(j).equals("["))
            {
                for(int k=j+1; k<DondeVoy.size(); k++)
                {
                    DondeIre.add(DondeVoy.get(k));
                }
                System.out.println(DondeIre);
                Regex = Regex + " " + "[";
                System.out.println("Regex: " + Regex);
                System.out.println("voy de ida --> [");
                desfase=desfase+1;
                System.out.println(desfase);
                cant = TokenExpression("[", DondeIre);
                if(cant==-1)
                {
                    return -1;
                }
                else
                {
                    j=j+cant;
                    desfase = desfase + cant;
                }
                
                //TokenExpr
            }
            else if(DondeVoy.get(j).equals("{"))
            {
                for(int k=j+1; k<DondeVoy.size(); k++)
                {
                    DondeIre.add(DondeVoy.get(k));
                }
                System.out.println(DondeIre);
                Regex = Regex + " " + "{";
                System.out.println("Regex: " + Regex);
                System.out.println("voy de ida --> {");
                desfase=desfase+1;
                System.out.println(desfase);
                cant = TokenExpression("{", DondeIre);
                if(cant==-1)
                {
                    return -1;
                }
                else
                {
                    j=j+cant;
                    desfase = desfase + cant;
                }
                
                //TokenExpr
            }
            else if(this.symbol.simulacionPegao(DondeVoy.get(j)))
            {
                Regex = Regex + " " + DondeVoy.get(j);
                System.out.println("Regex: " + Regex);
                desfase=desfase+1;
                System.out.println(desfase);
                permiso = true;
            }
            else if(permiso == true && DondeVoy.get(j).equals("|"))
            {
                System.out.println("or");
                Regex = Regex + " " + DondeVoy.get(j);
                System.out.println("Regex: " + Regex);
                desfase=desfase+1;
                System.out.println(desfase);
                permiso = false;
            }
            else if(DondeVoy.get(j).equals(PredecesorInverso))
            {
                Regex = Regex + " " + PredecesorInverso;
                System.out.println("Regex: " + Regex);
                desfase=desfase+1;
                System.out.println(desfase);
                System.out.println("voy de regreso --> " + PredecesorInverso);
                return desfase;
            }
            else
            {
                System.out.println("ERROR. Token not valid");
                System.exit(0);
                //Error
            }
        }
        System.out.println("voy de regreso FINISH");
        desfase = -1;
        return desfase;
    }
    
    public void ParserExpression(ArrayList<String> DondeVoy)
    {
        System.out.println(DondeVoy);
        boolean permiso = false;
        int cant=0;
        ArrayList<String> DondeIre = new ArrayList<String>(); 
        for(int j=0; j<DondeVoy.size(); j++)
        {
            System.out.println("AQUI VOOOOOOY " + DondeVoy.get(j));
            DondeIre.clear();
            if(DondeVoy.get(j).equals("("))
            {
                for(int k=j+1; k<DondeVoy.size(); k++)
                {
                    DondeIre.add(DondeVoy.get(k));
                }
                //System.out.println(DondeIre);
                //Regex = Regex + " " + "(";
                //System.out.println("Regex: " + Regex);
                System.out.println("voy de ida --> (");
                permiso = true;
                cant = ParserExpression("(", DondeIre);
                if(cant==-1)
                {
                    return;
                }
                else
                {
                    j=j+cant;
                }
                
                //TokenExpr
            }
            else if(DondeVoy.get(j).equals("["))
            {
                for(int k=j+1; k<DondeVoy.size(); k++)
                {
                    DondeIre.add(DondeVoy.get(k));
                }
                //System.out.println(DondeIre);
                //Regex = Regex + " " + "[";
                //System.out.println("Regex: " + Regex);
                //System.out.println("voy de ida--> [");
                permiso = true;
                cant = ParserExpression("[", DondeIre);
                if(cant==-1)
                {
                    return;
                }
                else
                {
                    j=j+cant;
                }
                
                //TokenExpr
            }
            else if(DondeVoy.get(j).equals("{"))
            {
                for(int k=j+1; k<DondeVoy.size(); k++)
                {
                    DondeIre.add(DondeVoy.get(k));
                }
                //System.out.println(DondeIre);
                //Regex = Regex + " " + "{";
                //System.out.println("Regex: " + Regex);
                //System.out.println("voy de ida --> {");
                permiso = true;
                cant = ParserExpression("{", DondeIre);
                if(cant==-1)
                {
                    //RegExTokens.add(Regex);
                    return;
                }
                else
                {
                    j=j+cant;
                }
                
                //TokenExpr
            }
            else if(this.symbol.simulacionPegao(DondeVoy.get(j)))
            {
                System.out.println("Symbol");
                //Regex = Regex + " " + DondeVoy.get(j);
                //System.out.println("Regex: " + Regex);
                if(j+1 < DondeVoy.size())
                {
                    if(this.CheckAttribute(DondeVoy.get(j+1)))
                    {
                        j=j+1;
                    }   
                }
                
                permiso = true;
            }
            else  if(this.CheckSemAction(DondeVoy.get(j)))
            {
                permiso = true;
            }
            
            else if(permiso == true && DondeVoy.get(j).equals("|"))
            {
                //Regex = Regex + " " + DondeVoy.get(j);
                //System.out.println("Regex: " + Regex);
                System.out.println("or");
                permiso = false;
            }
            else
            {
                System.out.println("ERROR. Production not valid");
                System.exit(0);
                //Error
            }
        }
        //RegExTokens.add(Regex);
        return;
    }
    //Caso Recursivo
    public int ParserExpression(String Predecesor, ArrayList<String> DondeVoy)
    {
        String PredecesorInverso="";
        if(Predecesor.equals("("))
        {
            PredecesorInverso = ")";
        }
        else if(Predecesor.equals("["))
        {
            PredecesorInverso = "]";
        }
        else if(Predecesor.equals("{"))
        {
            PredecesorInverso = "}";
        }
        int desfase = 0;
        int cant = 0;
        boolean permiso = false;
        ArrayList<String> DondeIre = new ArrayList<String>(); 
        for(int j=0; j<DondeVoy.size(); j++)
        {
            System.out.println("AQUI VOOOOOOY " + DondeVoy.get(j));
            DondeIre.clear();
            if(DondeVoy.get(j).equals("("))
            {
                for(int k=j+1; k<DondeVoy.size(); k++)
                {
                    DondeIre.add(DondeVoy.get(k));
                }
                //System.out.println(DondeIre);
                //Regex = Regex + " " + "(";
                //System.out.println("Regex: " + Regex);
                //System.out.println("voy de ida --> (");
                desfase=desfase+1;
                //System.out.println(desfase);
                permiso = true;
                cant = ParserExpression("(", DondeIre);
                if(cant==-1)
                {
                    return -1;
                }
                else
                {
                    j=j+cant;
                    desfase = desfase + cant;
                }
                
                //TokenExpr
            }
            else if(DondeVoy.get(j).equals("["))
            {
                for(int k=j+1; k<DondeVoy.size(); k++)
                {
                    DondeIre.add(DondeVoy.get(k));
                }
                //System.out.println(DondeIre);
                //Regex = Regex + " " + "[";
                //System.out.println("Regex: " + Regex);
                //System.out.println("voy de ida --> [");
                desfase=desfase+1;
                //System.out.println(desfase);
                permiso = true;
                cant = ParserExpression("[", DondeIre);
                if(cant==-1)
                {
                    return -1;
                }
                else
                {
                    j=j+cant;
                    desfase = desfase + cant;
                }
                
                //TokenExpr
            }
            else if(DondeVoy.get(j).equals("{"))
            {
                for(int k=j+1; k<DondeVoy.size(); k++)
                {
                    DondeIre.add(DondeVoy.get(k));
                }
                //System.out.println(DondeIre);
                //Regex = Regex + " " + "{";
                //System.out.println("Regex: " + Regex);
                //System.out.println("voy de ida --> {");
                desfase=desfase+1;
                //System.out.println(desfase);
                permiso = true;
                cant = ParserExpression("{", DondeIre);
                if(cant==-1)
                {
                    return -1;
                }
                else
                {
                    j=j+cant;
                    desfase = desfase + cant;
                }
                
                //TokenExpr
            }
            else if(this.symbol.simulacionPegao(DondeVoy.get(j)))
            {
                System.out.println("Symbol");
                //Regex = Regex + " " + DondeVoy.get(j);
                //System.out.println("Regex: " + Regex);
                if(this.CheckAttribute(DondeVoy.get(j+1)))
                {
                    j=j+1;
                }
                desfase=desfase+1;
                //System.out.println(desfase);
                permiso = true;
            }
            else if(this.CheckSemAction(DondeVoy.get(j)))
            {
                desfase = desfase+1;
                permiso = true;
            }
            else if(permiso == true && DondeVoy.get(j).equals("|"))
            {
                //System.out.println("or");
                //Regex = Regex + " " + DondeVoy.get(j);
                //System.out.println("Regex: " + Regex);
                desfase=desfase+1;
                //System.out.println(desfase);
                permiso = false;
            }
            else if(DondeVoy.get(j).equals(PredecesorInverso))
            {
                //Regex = Regex + " " + PredecesorInverso;
                //System.out.println("Regex: " + Regex);
                desfase=desfase+1;
                //System.out.println(desfase);
                //System.out.println("voy de regreso --> " + PredecesorInverso);
                return desfase;
            }
            else
            {
                System.out.println("ERROR. Production not valid");
                System.exit(0);
                //Error
            }
        }
        //System.out.println("voy de regreso FINISH");
        desfase = -1;
        return desfase;
    }

    // Convierte expresiones regulares genéricas en expresiones regulares procesables
    // por nuestros autómatas
    public ArrayList<String> RegExToMyRegex(ArrayList<String> Regex, ArrayList<String> CharIds, ArrayList<ArrayList<String>> CharSets)
    {
        ArrayList<String> opsAfter = new ArrayList<String>();
        ArrayList<String> opsBefore = new ArrayList<String>();
        ArrayList<String> ops = new ArrayList<String>();
        ops.add("(");
        ops.add("[");
        ops.add("{");
        ops.add("Ø");
        ops.add("Ç");
        ops.add(")");
        ops.add("]");
        ops.add("}");
        
        opsBefore.add("(");
        opsBefore.add("[");
        opsBefore.add("{");
        opsBefore.add("Ø");
        opsBefore.add("Ç");
        
        opsAfter.add(")");
        opsAfter.add("]");
        opsAfter.add("}");
        opsAfter.add("Ø");
        opsAfter.add("Ç");
        ArrayList<String> Final = new ArrayList<String>();
        ArrayList<ArrayList<String>> RegEx = new ArrayList<ArrayList<String>>();

        for(String s : Regex)
        {
            String[] Str = s.split(" ");
            ArrayList<String> ind = new ArrayList<String>();
            for(int i=1; i<Str.length; i++)
            {
                ind.add(Str[i]);
            }
            System.out.println(ind);
            RegEx.add(ind);
        }
        
        //Cambio de Simbolos
        for(ArrayList<String> AL : RegEx)
        {
            // |--> Ø
            for(int i=0; i<AL.size(); i++)
            {
                if(AL.get(i).equals("|"))
                {
                    AL.set(i, "Ø");
                }
            }
            // Ele Ele --> Ele Ç Ele 
            for(int j=0; j<AL.size(); j++)
            {
                String now = "";
                String next = "";
                now = AL.get(j);
                if(j+1<AL.size())
                {
                    next = AL.get(j+1);
                }
                if((now.equals("") == false) && (next.equals("") == false))
                {
                    if(opsBefore.contains(now) == false)
                    {
                        if(opsAfter.contains(next) == false)
                        {
                            AL.add(j+1, "Ç");
                        }
                    }
                }
            }
            // ( something ) --> « something »
            for(int k=0; k<AL.size(); k++)
            {
                if(AL.get(k).equals("("))
                {
                    AL.set(k, "«");
                }
                else if(AL.get(k).equals(")"))
                {
                    AL.set(k, "»");
                }
            }
            // { something } --> « something » º
            for(int k=0; k<AL.size(); k++)
            {
                if(AL.get(k).equals("{"))
                {
                    AL.set(k, "«");
                }
                else if(AL.get(k).equals("}"))
                {
                    AL.set(k, "»");
                    AL.add(k+1,"º");
                }
            }
            // [ something ] --> « something Ø ~ »
            for(int k=0; k<AL.size(); k++)
            {
                if(AL.get(k).equals("["))
                {
                    AL.set(k, "«");
                    AL.add(k, "«");
                }
                else if(AL.get(k).equals("]"))
                {
                    AL.set(k, "»");
                    AL.add(k, "~");
                    AL.add(k, "Ø");
                    AL.add(k, "»");
                }
            }
            
            
        }
        //Cambio de idents por Character Regex
        for(ArrayList<String> AL : RegEx)
        {
            for(int i=0; i<AL.size(); i++)
            {
                if(ops.contains(AL.get(i)) == false)
                {
                    //Cambio de idents por Character Regex
                    if(this.CheckIdent(AL.get(i)))
                    {
                        if(CharIds.contains(AL.get(i)))
                        {
                            int inde = CharIds.indexOf(AL.get(i));
                            ArrayList<String> rege = CharSets.get(inde);
                            AL.set(i, "«");
                            //System.out.println(AL);
                            AL.add(i+1, rege.get(0));
                            i=i+1;
                            if(rege.size() != 1)
                            {
                                AL.add(i+1, "Ø");
                                i=i+1;
                            }
                            //System.out.println(AL);
                            for(int j=1;j<rege.size(); j++)
                            {
                                AL.add(i+j, rege.get(j));
                                //if(rege.get(j).equals("\"") || rege.get(j).equals("\\"))
                                //{
                                //    AL.add(i+j, "\\");
                                //}
                                if(j != rege.size()-1)
                                {
                                    i=i+1;
                                    AL.add(i+j, "Ø");
                                }
                                //System.out.println(AL);
                            }
                            AL.add(i+rege.size(), "»");
                            i=i+1;
                            //System.out.println(AL);
                            //sacar index
                            
                        }
                        else
                        {
                            //token not valid, cant find symbol "ident" 
                        }
                    }
                    //Cambio de String por String Regex
                    else if(this.CheckString(AL.get(i)))
                    {
                        String[] str = AL.get(i).split("");
                        ArrayList<String> StrSet = new ArrayList<String>();
                        for(int j=0; j<str.length; j++)
                        {
                            if(j+1 != str.length)
                            {
                                if(str[j].equals("\\") && str[j+1].equals("\""))
                                {
                                    j=j+1;
                                }
                                if(str[j].equals("\\") && str[j+1].equals("\\"))
                                {
                                    j=j+1;
                                }
                            }
                            StrSet.add(str[j]);
                        }
                        StrSet.remove(0);
                        StrSet.remove(StrSet.size()-1);
                        AL.set(i, "«");
                        for(int j=1; j<StrSet.size()+1; j++)
                        {
                            
                            AL.add(i+j, StrSet.get(j-1));
                            
                            //if(StrSet.get(j-1).equals("\"") || StrSet.get(j-1).equals("\\"))
                            //{
                            //    AL.add(i+j, "\\");
                            //}
                            i=i+1;
                            if(j != StrSet.size()+1-1)
                            {
                                AL.add(i+j, "Ç");
                            }
                            
                        }
                        AL.add(i+StrSet.size(),"»");
                    }
                    else if(this.CheckChar(AL.get(i)))
                    {
                        String[] str = AL.get(i).split("");
                        ArrayList<String> StrSet = new ArrayList<String>();
                        for(int j=0; j<str.length; j++)
                        {
                            StrSet.add(str[j]);
                        }
                        StrSet.remove(0);
                        StrSet.remove(StrSet.size()-1);
                        AL.set(i, StrSet.get(0));
                    }
                            
                }
            }
            String toAdd = "";
            //ArrayList<Integer> toRemove = new ArrayList<Integer>();
            
            for(String s : AL)
            {
                if(s.equals("\"") || s.equals("\\"))
                {
                    toAdd = toAdd + "\\" + s;
               }
                else
                {
                    toAdd = toAdd + s;
                }
            }
            Final.add(toAdd);
        }
        
        
        //System.out.println("Cambio de simbolos");
        //System.out.println(Final);
        //Cambio de idents por Character Regex
        
        //Cambio de String por String Regex
        //Cambio de Chars por Chars Regex
        
        
        
        
        return Final;
    }

    public boolean CheckIdent(String ident)
    {
        String[] TempIdent = ident.split("");
        String IdentString = "";

        for(int i=0; i < TempIdent.length; i++)
        {
            IdentString = IdentString + TempIdent[i] + " ";
        }
        System.out.println(IdentString);

        return Ident.simulacion(IdentString);
    }

    public boolean CheckAttribute(String Attribute)
    {
        String[] TempAttribute = Attribute.split("");
        String AttributeString = "";
        for(int i=0; i<TempAttribute.length;i++)
        {
            AttributeString = AttributeString + TempAttribute[i] + " ";
                    
        }
        System.out.println(AttributeString);
        return Ident.simulacion(AttributeString);
        
    }
    public  boolean CheckSemAction(String SemAction)
    {
        String[] TempSemAction = SemAction.split("");
        String SemActionString = "";
        for(int i=0; i<TempSemAction.length;i++)
        {
            SemActionString = SemActionString + TempSemAction[i] + " ";
                    
        }
        System.out.println(SemActionString);
        return Ident.simulacion(SemActionString);
    }
    
    
    public boolean CheckNumber(String num)
    {
        String[] Num = num.split("");
        String numString = "";
        for(int i=0; i<Num.length;i++)
        {
            numString = numString + Num[i] + " ";
                    
        }
        System.out.println(numString);
        return Number.simulacion(numString);
    }
    
   
    public boolean CheckString(String str)
    {
        System.out.println("SIMULANDO: " + str);
        return String.simulacionPegao(str);
    }
    public boolean CheckChar(String chara)
    {
        String[] Str = chara.split("");
        String strString = "";
        for(int i=0; i<Str.length;i++)
        {
            strString = strString + Str[i] + " ";
                    
        }
        System.out.println(strString);
        return Char.simulacion(strString);
    }
    public boolean CheckChara(String chara)
    {
        String[] Str = chara.split("");
        
        String strString = "";
        for(int i=0; i<Str.length;i++)
        {
            strString = strString + Str[i] + " ";
                    
        }
        System.out.println(strString);
        return CHAR.simulacion(strString);
    }
    
    
    
    
}
