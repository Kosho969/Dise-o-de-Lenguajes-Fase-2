
package Generator;

import Reader.AFD;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;

public class reader 
{
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        Vocabulary test = new Vocabulary();
        ALMaker arreglador = new ALMaker();
        test.String.toGraph("WithQuotes");
        test.Char.toGraph("WithApos");
        test.Ident.toGraph("PROBASS");
        test.Ident.ToTxtFile("LEE");
        test.symbol.toGraph("SYM");
        String line;
        BufferedReader in;
        int count;
        int linea=1;
        String NOMBRE = "";
        //Test.txt
        //TokenTest1.txt
        //Java.txt
        
        in = new BufferedReader(new FileReader("Java.txt"));
        ArrayList<String> saved = new ArrayList<String>();
        ArrayList<String[]> words = new ArrayList<String[]>();
        ArrayList<Integer> numbah = new ArrayList<Integer>();
        
        ArrayList<String> LexerIds = new ArrayList<String>(); //Characters
        LexerIds.add("letter");
        LexerIds.add("digit");
        LexerIds.add("AnyButQuote");
        LexerIds.add("AnyButApostrophe");
        LexerIds.add("ANY");

        ArrayList<ArrayList<String>> LexerSets = new ArrayList<ArrayList<String>>();//Characters
        LexerSets.add(test.letterAL);
        LexerSets.add(test.digitAL);
        LexerSets.add(test.anybutquoteAL);
        LexerSets.add(test.anybutapostropheAL);
        LexerSets.add(test.anyAL);
        ArrayList<AFD> LexerAFD = new ArrayList<AFD>();//Characters
        
        ArrayList<String> LexerKeyIds = new ArrayList<String>();//Keywords
        ArrayList<ArrayList<String>> LexerKeySets = new ArrayList<ArrayList<String>>();//Keywords
        ArrayList<String> LexerKeyRegex = new ArrayList<String>();//Keywords
        ArrayList<AFD> LexerKeyAFD = new ArrayList<AFD>();//Keywords
        
        ArrayList<String> LexerTokenIds = new ArrayList<String>();//TOKENS
        ArrayList<Boolean> EKToken = new ArrayList<Boolean>();//TOKENS
        ArrayList<String> LexerTokenRegex = new ArrayList<String>();//TOKENS
        ArrayList<AFD> LexerTokenAFD = new ArrayList<AFD>();//TOKENS
        
        ArrayList<String> LexerWDSet = new ArrayList<String>();//WHITESPACE
        AFD LexerWDAFD = new AFD();//WHITESPACE
        
        
        ArrayList<Produccion> prods = new ArrayList<Produccion>();
        AutMaker maker = new AutMaker();
        
        line = in.readLine();
        String error = "Error";
        String id = "";
        
        while(line != null)
        {
            if (line.replace(" ","").replace("\t", "").equals("") == false)
            { 
                saved.add(line.trim());
                numbah.add(linea);    
            }
            line = in.readLine();
            linea++;
        }

        for(String leer : saved)
        {
           //"([^\"]\\S*|\".+?\")\\s*" nueva
           //"[ ]+(?=([^\"]*\"(?:\"|[^\"])*\")*[^\"]*$)" vieja
           // (?:(['"])(.*?)(?<!\\)(?>\\\\)*\1|([^\s]+)) try
           String[] set = leer.split("[ ]+(?=([^\\\"]*\\\"(?:\\\"|[^\\\"])*\\\")*[^\\\"]*$)");
           
           for(String s : set)
           {
               System.out.println(s);
           }
           words.add(set);
           
        }
        count = 1;
        String[] actual;
        
        // Recorer el listado de palabras de la gramática recibida y alimentar
        // las estructuras definidas arriba
        for(int lin=0; lin < saved.size(); lin++)
        {
            actual = words.get(lin);
            
            if(count == 1)//COMPILER name
            {
                if(actual.length == 2)
                {
                    if(actual[0].equals("COMPILER"))
                    {
                        error = "Correct";
                        if(test.CheckIdent(actual[1]))
                        {
                            //Correct
                            NOMBRE = actual[1];
                            System.out.println("NOMBRE: " + actual[1]);
                            error = "Correct";
                            count++;
                        }
                        else
                        {
                            error = "Error, ID not valid, line: " + String.valueOf(numbah.get(lin));
                            System.out.println(error);
                            System.exit(0);
                        }
                    }
                    else
                    {
                        error="Error,'COMPILER' expected, line: " + String.valueOf(numbah.get(lin));
                        System.out.println(error);
                        System.exit(0);
                    }
                }
                else
                {
                    error="Error,'COMPILER' *name* expected, name can't contain more than 1 word line: " + String.valueOf(numbah.get(lin));
                    System.out.println(error);
                    System.exit(0);
                }
            }
            else if(count == 2)//********************************Characters or Keywords
            {
                if(actual.length == 1 && actual[0].equals("CHARACTERS"))
                {
                    error = "Correct";
                    count = 3;
                }
                else if (actual.length == 1 && actual[0].equals("KEYWORDS"))
                {
                    error = "Correct";
                    count = 4;
                }
                else if (actual.length == 1 && actual[0].equals("TOKENS"))
                {
                    error = "Correct";
                    count = 5;
                }
                else if (actual[0].equals("IGNORE"))
                {
                    count = 6;
                }
                else
                {
                    if(actual.length == 3)
                    {
                        if(actual[0].equals("END"))
                        {
                            if(actual[1].equals(NOMBRE))
                            {
                                if(actual[2].equals("."))
                                {
                                    //correct
                                    error = "Correct";
                                }
                                else
                                {
                                    //point missing
                                    error="Error, '.' expected at end of line, line: " + String.valueOf(numbah.get(lin));
                                    System.out.println(error);
                                    System.exit(0);
                                }
                            }
                            else
                            {
                                // name is not the same
                                error="Error, Compiler name is not the same, line: " + String.valueOf(numbah.get(lin));
                                System.out.println(error);
                                System.exit(0);
                            }
                        }
                        else
                        {
                            // END expected
                            error="Error, 'END' expected, line: " + String.valueOf(numbah.get(lin));
                            System.out.println(error);
                            System.exit(0);
                        }
                    }
                    else
                    {
                        // END 'name' '.' expected
                        error="Error, END 'name' '.' expected, line: " + String.valueOf(numbah.get(lin));
                        System.out.println(error);
                        System.exit(0);
                    }
                }
                
            }
            else if(count == 3) // CHARACTERS
            {
                //----------------------------------------------SetDecls----------------------------------------------------------------------------
                if (actual.length == 1 && actual[0].equals("KEYWORDS"))
                {
                    error = "Correct";
                    count = 4;
                }
                else if (actual.length ==1 && actual[0].equals("TOKENS"))
                {
                    error = "Correct";
                    count = 5;
                }
                else if (actual[0].equals("IGNORE"))
                {
                    error = "Correct";
                    count = 6;
                }
                else if (actual[0].equals("PRODUCTIONS"))
                {
                    error = "Correct";
                    count = 7;
                }
                else if(test.CheckIdent(actual[0]))
                {
                    System.out.println("Ident");
                    id = actual[0];
                    LexerIds.add(actual[0]);
                    
                    if(actual[1].equals("="))
                    {
                        System.out.println("Ident =");
                        ArrayList<String> set = new ArrayList<String>();
                        String SimboloOCoso = "simbolo";
                        String AddOrNot = "Add";
                       
                        for(int i=2; i<actual.length-1; i++)//set
                        {

                            if(SimboloOCoso.equals("simbolo"))
                            {
                                System.out.println("SIMBOLO");
                                if(test.CheckString(actual[i]))
                                {
                                    ArrayList<String> string  = maker.StringToSet(actual[i]);
                                    if(AddOrNot.equals("Add"))
                                    {
                                        set.addAll(string);
                                    }
                                    else if(AddOrNot.equals("Not"))
                                    {
                                        set.removeAll(string);
                                    }
                                    
                                    System.out.println("STRING");
                                    SimboloOCoso = "coso";
                                }
                                else if (test.CheckIdent(actual[i]))
                                {
                                    
                                    int search = LexerIds.indexOf(actual[i]);
                                    if(search != -1 && search <= LexerSets.size())
                                    {
                                        if(AddOrNot.equals("Add"))
                                        {
                                            set.addAll(LexerSets.get(search));
                                        }
                                        else if(AddOrNot.equals("Not"))
                                        {
                                            set.removeAll(LexerSets.get(search));
                                        }
                                        
                                    }
                                    System.out.println("IDENT");
                                    SimboloOCoso = "coso";
                                }
                                else if (test.CheckChara(actual[i]))
                                {
                                    System.out.println("CHARA HASTA AHORA");
                                    SimboloOCoso = "coso";
                                    
                                    if(i<actual.length)
                                    {
                                        if(actual[i+1].equals(".."))
                                        {
                                            if(i<actual.length)
                                            {
                                                if(test.CheckChara(actual[i+2]))   
                                                {
                                                    maker.CharasToSet(actual[i], actual[i+2]);
                                                    ArrayList<String> string  = maker.CharasToSet(actual[i], actual[i+2]);
                                                    if(AddOrNot.equals("Add"))
                                                    {
                                                        set.addAll(string);
                                                    }
                                                    else if(AddOrNot.equals("Not"))
                                                    {
                                                        set.removeAll(string);
                                                    }
                                                    i = i+2;
                                                    SimboloOCoso = "coso";
                                                    
                                                    
                                                }
                                                else
                                                {
                                                    //Char not valid after .. 
                                                    error="Error, Char not valid after '..', line: " + String.valueOf(numbah.get(lin));
                                                    System.out.println(error);
                                                    System.exit(0);
                                                }
                                            }
                                            else
                                            {
                                                //Char missing after ..
                                                error="Error, Char missing after '..', line: " + String.valueOf(numbah.get(lin));
                                                System.out.println(error);
                                                System.exit(0);
                                            }
                                        }
                                        else if (actual[i+1].equals("+"))
                                        {
                                            if(String.valueOf(actual[i].charAt(0)).equals("'"))
                                            {
                                                set.add(String.valueOf(actual[i].charAt(1)));
                                            }
                                            else if(String.valueOf(actual[i].charAt(0)).equals("C"))
                                            {
                                                if(String.valueOf(actual[i].charAt(1)).equals("H"))
                                                {
                                                    if(String.valueOf(actual[i].charAt(2)).equals("R"))
                                                    {
                                                        if(String.valueOf(actual[i].charAt(3)).equals("("))
                                                        {
                                                            int inde = 4;
                                                            String num = "";
                                                            while(String.valueOf(actual[i].charAt(inde)).equals(")") == false)
                                                            {
                                                                num = num + String.valueOf(actual[i].charAt(inde));
                                                                if(test.CheckNumber(num))
                                                                {
                                                                    //do nothing
                                                                }
                                                                else
                                                                {
                                                                    error="Error, CHR() must contain a number, line: " + String.valueOf(numbah.get(lin));
                                                                    System.out.println(error);
                                                                    System.exit(0);
                                                                }
                                                                inde++;
                                                            }
                                                            int finale = Integer.parseInt(num);
                                                            set.add(Character.toString((char) finale));
                                                            
                                                        }
                                                    }
                                                }
                                            }
                                            AddOrNot = "Add";
                                            SimboloOCoso = "coso";
                                            
                                        }
                                        else if (actual[i+1].equals("-"))
                                        {
                                            if(String.valueOf(actual[i].charAt(0)).equals("'"))
                                            {
                                                set.add(String.valueOf(actual[i].charAt(1)));
                                            }
                                            else if(String.valueOf(actual[i].charAt(0)).equals("C"))
                                            {
                                                if(String.valueOf(actual[i].charAt(1)).equals("H"))
                                                {
                                                    if(String.valueOf(actual[i].charAt(2)).equals("R"))
                                                    {
                                                        if(String.valueOf(actual[i].charAt(3)).equals("("))
                                                        {
                                                            int inde = 4;
                                                            String num = "";
                                                            while(String.valueOf(actual[i].charAt(inde)).equals(")") == false)
                                                            {
                                                                num = num + String.valueOf(actual[i].charAt(inde));
                                                                if(test.CheckNumber(num))
                                                                {
                                                                    //do nothing
                                                                }
                                                                else
                                                                {
                                                                    error="Error, CHR() must contain a number, line: " + String.valueOf(numbah.get(lin));
                                                                    System.out.println(error);
                                                                    System.exit(0);
                                                                }
                                                                inde++;
                                                            }
                                                            int finale = Integer.parseInt(num);
                                                            set.add(Character.toString((char) finale));
                                                            
                                                        }
                                                    }
                                                }
                                            }
                                            AddOrNot = "Not";
                                            SimboloOCoso = "coso";
                                            
                                        }
                                        else if(actual[i+1].equals("."))
                                        {
                                            if(String.valueOf(actual[i].charAt(0)).equals("'"))
                                            {
                                                set.add(String.valueOf(actual[i].charAt(1)));
                                            }
                                            else if(String.valueOf(actual[i].charAt(0)).equals("C"))
                                            {
                                                if(String.valueOf(actual[i].charAt(1)).equals("H"))
                                                {
                                                    if(String.valueOf(actual[i].charAt(2)).equals("R"))
                                                    {
                                                        if(String.valueOf(actual[i].charAt(3)).equals("("))
                                                        {
                                                            int inde = 4;
                                                            String num = "";
                                                            while(String.valueOf(actual[i].charAt(inde)).equals(")") == false)
                                                            {
                                                                num = num + String.valueOf(actual[i].charAt(inde));
                                                                if(test.CheckNumber(num))
                                                                {
                                                                    //do nothing
                                                                }
                                                                else
                                                                {
                                                                    error="Error, CHR() must contain a number, line: " + String.valueOf(numbah.get(lin));
                                                                    System.out.println(error);
                                                                    System.exit(0);
                                                                }
                                                                inde++;
                                                            }
                                                            int finale = Integer.parseInt(num);
                                                            set.add(Character.toString((char) finale));
                                                            
                                                        }
                                                    }
                                                }
                                            }
                                            //correct
                                            error = "Correct";
                                            System.out.println("POINT FOUND");
                                        }
                                        else
                                        {
                                            // .. or +,- operator expected
                                            error="Error, '..' or '+','-' operator expected, line: " + String.valueOf(numbah.get(lin));
                                            System.out.println(error);
                                            System.exit(0);
                                        }
                                    }
                                    
                                }
                                else
                                {
                                    //can't find simbol
                                    error="Error, cannot find symbol, line: " + String.valueOf(numbah.get(lin));
                                    System.out.println(error);
                                    System.exit(0);
                                }
                                
                            }
                            else if(SimboloOCoso.equals("coso"))
                            {
                                System.out.println("COSO");
                                if(actual[i].equals("+"))
                                {
                                    AddOrNot = "Add";
                                    SimboloOCoso = "simbolo";
                                }
                                else if(actual[i].equals("-"))
                                {
                                    AddOrNot = "Not";
                                    SimboloOCoso = "simbolo";
                                }
                                else
                                {
                                    //operator not valid
                                    error="Error, operator not valid, line: " + String.valueOf(numbah.get(lin));
                                    System.out.println(error);
                                    System.exit(0);
                                }
                            }


                        }
                        ArrayList<String> list = new ArrayList<String>(new LinkedHashSet<String>(set));
                        LexerSets.add(list);
                        AFD toAdd = maker.CharacterSetToAFD(list, id);
                        LexerAFD.add(toAdd);
                        //**********************************************END IF*********************************************************************
                        if(actual[actual.length-1].equals("."))
                        {
                            //correct
                            error = "Correct";
                            System.out.println("POINT FOUND");
                        }
                        else
                        {
                            // point missing
                            error="Error, '.' expected at end of line, line: " + String.valueOf(numbah.get(lin));
                            System.out.println(error);
                            System.exit(0);
                        }
                    }
                    else
                    {
                        // id can contain only one word
                        error="Error, id can contain only one word, line: " + String.valueOf(numbah.get(lin));
                        System.out.println(error);
                        System.exit(0);
                    }
                }
                else
                {
                    //id not valid
                    error="Error, id not valid, line: " + String.valueOf(numbah.get(lin));
                    System.out.println(error);
                    System.exit(0);
                }
            }
                
            
            else if(count == 4)
            {
                //KeyWordDecl
                ArrayList<String> KeySet = new ArrayList<String>();
                if (actual[0].equals("TOKENS"))
                {
                    count = 5;
                }
                else if (actual[0].equals("IGNORE"))
                {
                    count = 6;
                }
                else if (actual[0].equals("PRODUCTIONS"))
                {
                    error = "Correct";
                    count = 7;
                }
                else if (actual[0].equals("END"))
                {
                    count = 8;
                }
                else if(test.CheckIdent(actual[0]))
                {
                    String keyId = actual[0];
                    LexerKeyIds.add(actual[0]);
                    System.out.println("Ident");
                    if(actual[1].equals("="))
                    {
                        System.out.println("Ident =");
                        if(test.CheckString(actual[2]))
                        {
                            System.out.println("STRING");
                            if(actual[3].equals("."))
                            {
                                //correct
                                KeySet = maker.StringToSet(actual[2]);
                                System.out.print("keyset "+ KeySet);
                                LexerKeySets.add(KeySet);
                                String KeyRegex = maker.KeyWordsSetToRegex(KeySet);
                                AFD KeyAdd = maker.KeyWordsSetToAFD(KeySet, keyId);
                                LexerKeyAFD.add(KeyAdd);
                                LexerKeyRegex.add(KeyRegex);
                                error = "Correct";
                                System.out.println("POINT FOUND");
                            }
                            else
                            {
                                //Point missing
                                error="Error, '.' expected at end of line, line: " + String.valueOf(numbah.get(lin));
                                System.out.println(error);
                                System.exit(0);
                            }
                        }
                        else
                        {
                            //String not valid
                            error="Error, String not valid, line: " + String.valueOf(numbah.get(lin));
                            System.out.println(error);
                            System.exit(0);
                        }
                    }
                    else
                    {
                        //ident can only contain one word
                        error="Error, id can only contain one word, line: " + String.valueOf(numbah.get(lin));
                        System.out.println(error);
                        System.exit(0);
                    }
                }
                else
                {
                    //id not valid
                    error="Error, id not valid, line: " + String.valueOf(numbah.get(lin));
                    System.out.println(error);
                    System.exit(0);
                }
                
            }
            else if(count == 5)
            {
                
                if (actual[0].equals("IGNORE"))
                {
                    error = "Correct";
                    count = 6;
                }
                else if (actual[0].equals("PRODUCTIONS"))
                {
                    error = "Correct";
                    count = 7;
                }
                else if(actual[0].equals("END"))
                {
                    error = "Correct";
                    count = 8;
                }
                else if(test.CheckIdent(actual[0]))
                {
                    LexerTokenIds.add(actual[0]);
                     if(actual[1].equals("="))
                    {
                        System.out.println("Ident =");
                        ArrayList<String> TE = new ArrayList<String>();
                        for(int i=2; i<actual.length; i++)//set
                        {
                            TE.add(actual[i]);
                        }
                        //System.out.println(TE);
                        if(TE.get(TE.size()-1).equals("."))
                        {
                            TE.remove(TE.size()-1);
                            
                            if(TE.get(TE.size()-1).equals("KEYWORDS"))
                            {
                                System.out.println("KEYWORDS");
                                TE.remove(TE.size()-1);
                                if(TE.get(TE.size()-1).equals("EXCEPT"))
                                {
                                    System.out.println("EXCEPT");
                                    TE.remove(TE.get(TE.size()-1));
                                    test.TokenExpression(TE);
                                    EKToken.add(Boolean.TRUE);
                                    System.out.println("FINISHEEE----------------------------------------");
                                    System.out.println("REGEX = " + test.Regex);
                                    test.Regex = "";
                                }
                                else
                                {
                                    //error EXCEPT MISSING
                                    System.out.println("EXCEPT MISSING");
                                    System.exit(0);
                                }
                            }
                            else
                            {
                                //llama al metodo
                                test.TokenExpression(TE);
                                EKToken.add(Boolean.FALSE);
                                System.out.println("FINISHEEE----------------------------------------");
                                System.out.println("REGEX = " + test.Regex);
                                test.Regex = "";
                            }
                        }
                        else
                        {
                            //point missing
                            System.out.println("POINT MISSING");
                            System.exit(0);
                        }
                    }
                    else  if(actual[1].equals("EXCEPT"))
                    {
                        if(actual[2].equals("KEYWORDS"))
                        {
                            if(actual[3].equals("."))
                            {
                                //correct
                            }
                            else
                            {
                                System.out.println("POINT MISSING");
                                System.exit(0);
                                //point missing
                            }
                        }
                        else
                        {
                            //KEYWORDS EXPECTED
                            System.out.println("KEYWORDS EXPECTED");
                            System.exit(0);
                        }
                    }
                    else if(actual[1].equals("."))
                    {
                        //correct
                    }
                    else
                    {
                        System.out.println("EXCEPT KEYWORDS OR POINT MISSING");
                        System.exit(0);
                        //EXCEPT KEYWORDS or point missing
                    }
                }
                else
                {
                    //Error, ID not valid
                    System.out.println("ID NOT VALID");
                    System.exit(0);
                }
            }
            else if(count == 6)
            {
                if (actual[0].equals("PRODUCTIONS"))
                {
                    error = "Correct";
                    count = 7;
                }
                else if(actual[0].equals("IGNORE"))//WHITESPACE
                {
                    if(actual[1].equals("."))
                    {
                        error = "Correct";
                        count = 7;
                    }
                    else
                    {
                       ArrayList<String> set = new ArrayList<String>();
                        String SimboloOCoso = "simbolo";
                        String AddOrNot = "Add";
                        String LexerWDId = "Ignore";
                        for(int i=1; i<actual.length-1; i++)//set
                        {

                            if(SimboloOCoso.equals("simbolo"))
                            {
                                System.out.println("SIMBOLO");

                                if(test.CheckString(actual[i]))
                                {
                                    ArrayList<String> string  = maker.StringToSet(actual[i]);
                                    if(AddOrNot.equals("Add"))
                                    {
                                        set.addAll(string);
                                    }
                                    else if(AddOrNot.equals("Not"))
                                    {
                                        set.removeAll(string);
                                    }
                                    System.out.println("STRING");
                                    SimboloOCoso = "coso";
                                    count = 7;
                                }
                                else if (test.CheckIdent(actual[i]))
                                {
                                    int search = LexerIds.indexOf(actual[i]);
                                    if(search != -1 && search <= LexerSets.size())
                                    {
                                        if(AddOrNot.equals("Add"))
                                        {
                                            set.addAll(LexerSets.get(search));
                                        }
                                        else if(AddOrNot.equals("Not"))
                                        {
                                            set.removeAll(LexerSets.get(search));
                                        }

                                    }
                                    System.out.println("IDENT");
                                    SimboloOCoso = "coso";
                                    count = 7;
                                }
                                else if (test.CheckChara(actual[i]))
                                {
                                    System.out.println("CHARA HASTA AHORA");
                                    SimboloOCoso = "coso";
                                    set.add(String.valueOf(actual[i].charAt(1)));
                                    count = 7;

                                    if(i<actual.length)
                                    {
                                        if(actual[i+1].equals(".."))
                                        {
                                            if(i<actual.length)
                                            {
                                                if(test.CheckChara(actual[i+2]))   
                                                {
                                                    maker.CharasToSet(actual[i], actual[i+2]);
                                                    ArrayList<String> string  = maker.CharasToSet(actual[i], actual[i+2]);
                                                    if(AddOrNot.equals("Add"))
                                                    {
                                                        set.addAll(string);
                                                    }
                                                    else if(AddOrNot.equals("Not"))
                                                    {
                                                        set.removeAll(string);
                                                    }
                                                    i = i+2;
                                                    SimboloOCoso = "coso";
                                                    count = 7;
                                                }
                                                else
                                                {
                                                    //Char not valid after .. 
                                                    error="Error, Char not valid after '..', line: " + String.valueOf(numbah.get(lin));
                                                    System.out.println(error);
                                                    System.exit(0);
                                                }
                                            }
                                            else
                                            {
                                                //Char missing after ..
                                                error="Error, Char missing after '..', line: " + String.valueOf(numbah.get(lin));
                                                System.out.println(error);
                                                System.exit(0);
                                            }
                                        }
                                        else if (actual[i+1].equals("+"))
                                        {
                                            AddOrNot = "Add";
                                            SimboloOCoso = "coso";
                                        }
                                        else if (actual[i+1].equals("-"))
                                        {
                                            AddOrNot = "Not";
                                            SimboloOCoso = "coso";
                                        }
                                        else if  (actual[i+1].equals("."))
                                        {
                                            //correct
                                            error = "Correct";
                                            System.out.println("POINT FOUND");
                                        }
                                        else
                                        {
                                            // .. or +,- operator expected
                                            error="Error, '..' or '+','-' operator expected, line: " + String.valueOf(numbah.get(lin));
                                            System.out.println(error);
                                            System.exit(0);
                                        }
                                    }
                                }
                                else
                                {
                                    //can't find simbol
                                    error="Error, cannot find symbol, line: " + String.valueOf(numbah.get(lin));
                                    System.out.println(error);
                                    System.exit(0);
                                }

                            }
                            else if(SimboloOCoso.equals("coso"))
                            {
                                System.out.println("COSO");
                                if(actual[i].equals("+"))
                                {
                                    AddOrNot = "Add";
                                    SimboloOCoso = "simbolo";
                                }
                                else if(actual[i].equals("-"))
                                {
                                    AddOrNot = "Not";
                                    SimboloOCoso = "simbolo";
                                }
                                else
                                {
                                    //operator not valid
                                    error="Error, operator not valid, line: " + String.valueOf(numbah.get(lin));
                                    System.out.println(error);
                                    System.exit(0);
                                }
                            }
                        }
                        ArrayList<String> list = new ArrayList<String>(new LinkedHashSet<String>(set));
                        LexerWDSet = list;
                        AFD toAdd = maker.CharacterSetToAFD(list, LexerWDId);
                        LexerWDAFD = toAdd;
                        if(actual[actual.length-1].equals("."))
                        {
                            //correct
                            error = "Correct";
                            System.out.println("POINT FOUND");
                        }
                        else
                        {
                            // point missing
                            error="Error, '.' expected at end of line, line: " + String.valueOf(numbah.get(lin));
                            System.out.println(error);
                            System.exit(0);
                        } 
                    }
                }
            }

            //Parser Specification
            else if (count == 7)
            {
                String cabeza = "";
                ArrayList<String> cuerpo = new ArrayList<String>();
                
                System.out.println("PRODUCTIONS");
                if (actual[0].equals("END"))
                {
                    error = "correct";
                    count = 8;
                }
                else if (test.CheckIdent(actual[0]))
                {
                    cabeza = actual[0];
                     
                    System.out.println("CABEZA");
                    if (test.CheckAttribute(actual[1]))
                    {
                        if (test.CheckSemAction(actual[2]))
                        {
                            if (actual[3].equals("="))
                            {
                                ArrayList<String> TE = new ArrayList<String>();
                                for (int i=4; i<actual.length; i++)//set
                                {
                                    TE.add(actual[i]);
                                    
                                }
                                if(TE.get(TE.size()-1).equals("."))
                                {
                                    TE.remove(TE.size()-1);
                                    test.ParserExpression(TE);
                                    System.out.println(TE);
                                    cuerpo = TE;
                                }
                            }
                        }
                        else if(actual[2].equals("="))
                        {
                            ArrayList<String> TE = new ArrayList<String>();
                            for(int i=3; i<actual.length; i++)//set
                            {
                                TE.add(actual[i]);
                            }
                            if(TE.get(TE.size()-1).equals("."))
                            {
                                TE.remove(TE.size()-1);
                                test.ParserExpression(TE);
                                System.out.println(TE);
                                cuerpo = TE;
                            }
                        }
                    }
                    else if(test.CheckSemAction(actual[1]))
                    {
                        if(actual[2].equals("="))
                        {
                            ArrayList<String> TE = new ArrayList<String>();
                            for(int i=3; i<actual.length; i++)//set
                            {
                                TE.add(actual[i]);
                            }
                            if(TE.get(TE.size()-1).equals("."))
                            {
                                TE.remove(TE.size()-1);
                                test.ParserExpression(TE);
                                System.out.println(TE);
                                cuerpo = TE;
                            }
                        }
                    }
                    else if(actual[1].equals("="))
                    {
                        System.out.println("==");
                        ArrayList<String> TE = new ArrayList<String>();
                        for(int i=2; i<actual.length; i++)//set
                        {
                            TE.add(actual[i]);
                        }
                        if(TE.get(TE.size()-1).equals("."))
                        {
                            System.out.println("QUITE PUNTO");
                            TE.remove(TE.size()-1);
                            test.ParserExpression(TE);
                            System.out.println(TE);
                            cuerpo = TE;
                        }
                    }
                        
                }

                if(cabeza.equals("") == false)
                {
                    Produccion prod = new Produccion(cabeza, cuerpo); 
                    prods.add(prod);
                }
            }
            else if(count == 8)
            {
                if(actual.length == 3)
                {
                    if(actual[0].equals("END"))
                    {
                        if(actual[1].equals(NOMBRE))
                        {
                            if(actual[2].equals("."))
                            {
                                //correct
                                error = "Correct";
                            }
                            else
                            {
                                //point missing
                                error="Error, '.' expected at end of line, line: " + String.valueOf(numbah.get(lin));
                                System.out.println(error);
                                System.exit(0);
                            }
                        }
                        else
                        {
                            // name is not the same
                            error="Error, Compiler name is not the same, line: " + String.valueOf(numbah.get(lin));
                            System.out.println(error);
                            System.exit(0);
                        }
                    }
                    else
                    {
                        // END expected
                        error="Error, 'END' expected, line: " + String.valueOf(numbah.get(lin));
                        System.out.println(error);
                        System.exit(0);
                    }
                }
                else
                {
                    // END 'name' '.' expected
                    error="Error, END 'name' '.' expected, line: " + String.valueOf(numbah.get(lin));
                    System.out.println(error);
                    System.exit(0);
                }
            }
        }

        Gramatica gram = new Gramatica(prods);
        gram.toScreen();
        System.out.println(error);

        for (int i=0; i < LexerTokenIds.size(); i++)
        {
            System.out.println("TOKEN: " + LexerTokenIds.get(i) + " = " + test.RegExTokens.get(i));
        }

        LexerTokenRegex = test.RegExToMyRegex(test.RegExTokens, LexerIds, LexerSets);

        for (String r : LexerTokenRegex)
        {
            System.out.println("TOKEN REGEX");
            System.out.println(r);
        }

        //System.out.println(LexerTokenRegex);
        System.out.println(EKToken);

        for (int i=0; i < LexerTokenRegex.size(); i++)
        {
            AFD aut = maker.MakeAut(LexerTokenRegex.get(i));
            aut.toGraph(LexerTokenIds.get(i));

            LexerTokenAFD.add(aut);
        }

        for (String s : LexerWDSet)
        {
            if(s.equals(String.valueOf((char) 10)))
            {
                s = "\\" +"n";
            }
            else if(s.equals(String.valueOf((char) 13)))
            {
                s = "\\"+"r";
            }
        }

        System.out.println(LexerWDSet);
        boolean enable = false;

        if (LexerWDSet.size() == 0)
        {
            LexerWDSet.add("\\n");
            LexerWDSet.add("\\r");
            LexerWDSet.add(" ");
            LexerWDSet.add("    ");
        }
        else
        {
            enable = false;
        }
        
        Lexer Lex = new Lexer(
            EKToken,
            LexerWDSet,
            NOMBRE,
            LexerAFD,
            LexerKeyAFD,
            LexerIds,
            LexerKeyIds,
            LexerKeySets,
            enable,
            LexerTokenIds,
            LexerTokenAFD,
            LexerKeyRegex,
            LexerTokenRegex
        );

        Lex.GenerarAnalizador();
        //Lexer Lex = new Lexer(LexerWDSet, NOMBRE, LexerAFD, LexerKeyAFD, LexerIds, LexerKeyIds, LexerKeySets);
        //Lexer Lex = new Lexer(NOMBRE, LexerAFD, LexerKeyAFD, LexerIds, LexerKeyIds, LexerKeySets, true);
        //Lex.Lexear("Acomp.txt");   
    }
}
