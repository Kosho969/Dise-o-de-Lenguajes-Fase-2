

package Generator;

import Reader.AFD;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;


public class Lexer 
{
    public ArrayList<String> WhiteSpace = new ArrayList<String>();
    boolean Enable = false;
    public ArrayList<Boolean> EKToken = new ArrayList<Boolean>();
    public ArrayList<AFD> CharacterAut = new ArrayList<AFD>();
    public ArrayList<AFD> KeyWordAut = new ArrayList<AFD>();
    public ArrayList<String> Charids = new ArrayList<String>();
    public ArrayList<String> Keyids = new ArrayList<String>();
    public ArrayList<ArrayList<String>> KeySets = new ArrayList<ArrayList<String>>(); 
    public ArrayList<String> TokenIds = new ArrayList<String>();
    public ArrayList<AFD> TokenAuts = new ArrayList<AFD>();
    public ArrayList<String> KeyRegex = new ArrayList<String>();
    public ArrayList<String> TokenRegex = new ArrayList<String>();
    public String name = "";
    
    public Lexer()
    {
    
    }
    public Lexer(String nombre)
    {
        this.name = nombre;
    }
    public Lexer(ArrayList<String> White, String nombre, ArrayList<AFD> CharAuts, ArrayList<AFD> KeyAuts,  ArrayList<String> CharId, ArrayList<String> KeyId, ArrayList<ArrayList<String>> KeySet)
    {
        this.WhiteSpace = White;
        this.name = nombre;
        this.CharacterAut = CharAuts;
        this.KeyWordAut = KeyAuts;
        this.Charids = CharId;
        this.Keyids = KeyId;
        this.KeySets = KeySet;
    }
    public Lexer(String nombre, ArrayList<AFD> CharAuts, ArrayList<AFD> KeyAuts,  ArrayList<String> CharId, ArrayList<String> KeyId, ArrayList<ArrayList<String>> KeySet, boolean enable)
    {
        this.Enable = true;
        this.name = nombre;
        this.CharacterAut = CharAuts;
        this.KeyWordAut = KeyAuts;
        this.Charids = CharId;
        this.Keyids = KeyId;
        this.KeySets = KeySet;
    }
    //Lexer Lex = new Lexer(LexerWDSet, NOMBRE, LexerAFD, LexerKeyAFD, LexerIds, LexerKeyIds, LexerKeySets, enable, LexerTokenIds, LexerTokenAFD);
    public Lexer(ArrayList<Boolean> EKToken, ArrayList<String> White, String nombre, ArrayList<AFD> CharAuts, ArrayList<AFD> KeyAuts,  ArrayList<String> CharId, ArrayList<String> KeyId, ArrayList<ArrayList<String>> KeySet, boolean enable, ArrayList<String> TokenIds, ArrayList<AFD> TokenAuts, ArrayList<String> KeyRegex, ArrayList<String> TokenRegex)
    {
        this.EKToken = EKToken;
        this.Enable = enable;
        this.name = nombre;
        this.CharacterAut = CharAuts;
        this.KeyWordAut = KeyAuts;
        this.Charids = CharId;
        this.Keyids = KeyId;
        this.KeySets = KeySet;
        this.TokenIds = TokenIds;
        this.TokenAuts = TokenAuts;
        this.WhiteSpace = White;
        this.KeyRegex = KeyRegex;
        this.TokenRegex = TokenRegex;
    }
    public void Lexear(String Filename) throws FileNotFoundException, IOException
    {
        BufferedReader in = new BufferedReader(new FileReader(Filename));
        
        int nul = in.read();
        String car = String.valueOf((char)nul);
        //System.out.println(car);
        String conc = "";
        boolean prueba = true;
        while(nul != -1)
        {
            conc = conc + car;
            //System.out.println(conc);
            nul = in.read();
            car = String.valueOf((char)nul);
        }
        //System.out.println(conc);
        String[] StrLine = conc.split("");
        
        //System.out.println(largo);
        for(int i=0; i<StrLine.length; i++)
        {
            //System.out.println("Checking: " + StrLine[i]);
            if(Enable == true && StrLine[i].equals(" ") )
            {
                //System.out.println("SPACE");
                //do nothing
            }
            else if(Enable == true && StrLine[i].equals("\r"))
            {
                //System.out.println("FOUND R");
                
            }
            else if(Enable == true && StrLine[i].equals("\t"))
            {
                //System.out.println("TAB");
            }
            else if(Enable == true && StrLine[i].equals("\n"))
            {
                //System.out.println("LINE CHANGE");
            }
            else if(Enable == false && WhiteSpace.contains(StrLine[i]))
            {
                //System.out.println("Do Nothing");
            }
            else
            {
                for(int k=0; k<KeySets.size(); k++)
                {
                    ArrayList<String> KW = KeySets.get(k);
                    String Key = "";
                    //System.out.println("Keyword de largo " + String.valueOf(KW.size()));
                    if((i+KW.size())<StrLine.length)
                    {
                        for(int j=i; j<i+KW.size(); j++)
                        {
                            Key = Key + " " + StrLine[j];
                        }
                    }
                    //System.out.println(Key);
                    Key = Key.trim();
                    //System.out.println(Key);
                    if(KeyWordAut.get(k).simulacion(Key))
                    {

                        String res = "";
                        for(int j=i; j<i+KW.size(); j++)
                        {
                            res = res + StrLine[j];
                        }
                        System.out.println("<" + Keyids.get(k) + ", \"" + res + "\">");
                        i = i + KW.size()-1;
                        prueba = false;
                        break;
                    }
                    else
                    {
                        prueba = true;
                    }

                }
                if(prueba)
                {
                    for(int l=0; l<CharacterAut.size(); l++)
                {
                    if(CharacterAut.get(l).simulacion(StrLine[i]))
                    {
                        System.out.println("<" + Charids.get(l) + ", '" + StrLine[i] + "'>");
                    }
                }
                }
                
            }

        }
        
    }
    
    public void GenerarAnalizador() throws FileNotFoundException, UnsupportedEncodingException
    {
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(
       new FileOutputStream("C:\\Users\\Pablo\\Documents\\NetBeansProjects\\Fase2\\src\\Generator\\AnalizadorLexico.java")));
        
        //Escribir imports
        writer.println("package Generator;");
        writer.println(" ");
        writer.println("import Reader.AFD;");
        writer.println("import java.io.BufferedReader;");
        writer.println("import java.io.FileNotFoundException;");
        writer.println("import java.io.FileReader;");
        writer.println("import java.io.IOException;");
        writer.println("import java.io.IOException;");
        writer.println("import java.util.Arrays;");
        writer.println("import java.util.ArrayList;");
        
        
        writer.println("import java.io.File;");
        
        writer.println("import javax.swing.JFileChooser;");
        
        
        
        writer.println("public class AnalizadorLexico");
        writer.println("{");
        writer.println("    public static void main(String[] args) throws FileNotFoundException, IOException");
        writer.println("    {");
        String WDASList = "";
        
        
        WDASList = WDASList + "\"" + WhiteSpace.get(0) + "\"";
        
        for(int t=0; t<WhiteSpace.size(); t++)
        {

            WDASList = WDASList + "," + "\"" + WhiteSpace.get(t) + "\"";
        }
            
        
        writer.println("        ArrayList<String> WhiteSpaceAL = new ArrayList<String>(Arrays.asList(" + WDASList + "));");
            writer.println(" ");
        
        String EKASList = "";
        EKASList = EKASList + EKToken.get(0);
        
        for(int t=0; t<EKToken.size(); t++)
        {
            EKASList = EKASList + ", " + EKToken.get(t);
        }
        writer.println("        boolean[] EKTokenAL = {" + EKASList + "};");
        
        writer.println(" ");
        
        
        
        String KeyRegexAsList = "";
        if (KeyRegex.size() != 0)
        {
            KeyRegexAsList = KeyRegexAsList + "\"" + KeyRegex.get(0) +"\"";
            for(int i=0; i<KeyRegex.size(); i++)
            {
                    KeyRegexAsList = KeyRegexAsList + "," +"\"" + KeyRegex.get(i) +"\"";
                }
                
            
        }
        writer.println("        ArrayList<String> KeyRegexAL = new ArrayList<String>(Arrays.asList(" + KeyRegexAsList + "));");
        writer.println(" ");
        String KeyIdsAsList = "";
        if(Keyids.size() != 0)
        {
            KeyIdsAsList = KeyIdsAsList + "\"" + Keyids.get(0) +"\"";
            for(int i=0; i<Keyids.size(); i++)
            {
                KeyIdsAsList = KeyIdsAsList + "," +"\"" + Keyids.get(i) +"\"";
            }
        }
        
        writer.println("        ArrayList<String> KeyIdsAL = new ArrayList<String>(Arrays.asList(" + KeyIdsAsList + "));");
        writer.println(" ");
        String TokenRegexAsList = "";
        TokenRegexAsList = TokenRegexAsList + "\"" + TokenRegex.get(0) +"\"";
        for(int i=0; i<TokenRegex.size(); i++)
        {
            TokenRegexAsList = TokenRegexAsList + "," +"\"" + TokenRegex.get(i) +"\"";
        }
        writer.println("        ArrayList<String> TokenRegexAL = new ArrayList<String>(Arrays.asList(" + TokenRegexAsList + "));");
        writer.println(" ");
        String TokenIdsAsList = "";
        TokenIdsAsList = TokenIdsAsList + "\"" + TokenIds.get(0) +"\"";
        for(int i=0; i<TokenIds.size(); i++)
        {
            TokenIdsAsList = TokenIdsAsList + "," +"\"" + TokenIds.get(i) +"\"";
        }
        writer.println("        ArrayList<String> TokenIdsAL = new ArrayList<String>(Arrays.asList(" + TokenIdsAsList + "));");
        writer.println(" ");
        writer.println("        AutMaker maker = new AutMaker();");
        writer.println("        ArrayList<AFD> KeyAuts = new ArrayList<AFD>();");
        writer.println("        for(String s : KeyRegexAL)");
        writer.println("        {");
        writer.println("            KeyAuts.add(maker.MakeAut(s));");
        writer.println("        }");
        writer.println("        ArrayList<AFD> TokenAuts = new ArrayList<AFD>();");
        writer.println("        for(String s : TokenRegexAL)");
        writer.println("        {");
        writer.println("            TokenAuts.add(maker.MakeAut(s));");
        writer.println("        }");
        writer.println("        for(int i=0; i<KeyAuts.size(); i++)");
        writer.println("        {");
        writer.println("            KeyAuts.get(i).toGraph(\"Generado\" + KeyIdsAL.get(i));");
        writer.println("        }");
        writer.println("        for(int k=0; k<TokenAuts.size(); k++)");
        writer.println("        {");
        writer.println("            TokenAuts.get(k).toGraph(\"Generado\" + TokenIdsAL.get(k));");
        writer.println("        }");
        writer.println("        JFileChooser fileChooser = new JFileChooser();");
        writer.println("        int returnValue = fileChooser.showOpenDialog(null);");
        writer.println("        if (returnValue == JFileChooser.APPROVE_OPTION)");
        writer.println("        {");
        writer.println("            File selectedFile = fileChooser.getSelectedFile();");
        writer.println("            System.out.println(selectedFile.getName());");
        writer.println("            BufferedReader in = new BufferedReader(new FileReader(selectedFile.getName()));");
        writer.println("            int nul = in.read();");
        writer.println("            String car = String.valueOf((char)nul);");
        writer.println("            String conc = \"\";");
        writer.println("            while(nul != -1)");
        writer.println("            {");
        writer.println("                conc = conc + car;");
        writer.println("                nul = in.read();");
        writer.println("                car = String.valueOf((char)nul);");
        writer.println("            }");
        writer.println("            System.out.println(conc);");
        writer.println("            String[] StrLine = conc.split(\"\");");
        writer.println("            ArrayList<String> Tokens = new ArrayList<String>();");
        writer.println("            for(int i=0; i<StrLine.length; i++)");
        writer.println("            {");
        writer.println("                String tok = \"\";");
        writer.println("                int j=0;");
        writer.println("                if(WhiteSpaceAL.contains(StrLine[i]))");
        writer.println("                {");
        writer.println("                    //do nothing");
        writer.println("                }");
        writer.println("                else");
        writer.println("                {");
        writer.println("                    while(WhiteSpaceAL.contains(StrLine[i+j]) == false)");
        writer.println("                    {");
        writer.println("                        tok  = tok + StrLine[i+j];");
        writer.println("                        if(i+j+1 != StrLine.length)");
        writer.println("                        {");
        writer.println("                            j++;");
        writer.println("                        }");
        writer.println("                        else");
        writer.println("                        {");
        writer.println("                            break;");
        writer.println("                        }");
        writer.println("                    }");
        writer.println("                    Tokens.add(tok);");
        writer.println("                }");
        writer.println("                i=i+j;");
        writer.println("            }");
        writer.println("            System.out.println(Tokens);");
        
        writer.println("            ArrayList<String> forRemoval = new ArrayList<String>();");
        writer.println("            for(int i=0; i<TokenAuts.size(); i++)");
        writer.println("            {");
        writer.println("                for(String s : Tokens)");
        writer.println("                {");
        writer.println("                    if(TokenAuts.get(i).simulacionPegao(s))");
        writer.println("                    {");
        writer.println("                        boolean found = false;");
        writer.println("                        if(EKTokenAL[i]==true)");
        writer.println("                        {");
        writer.println("                            for(int j=0; j<KeyAuts.size(); j++)");
        writer.println("                            {");
        writer.println("                                if(KeyAuts.get(j).simulacionPegao(s))");
        writer.println("                                {");
        writer.println("                                    System.out.println(\"<\"+s+\", \"+KeyIdsAL.get(j)+\">\");");
        writer.println("                                    forRemoval.add(s);");
        writer.println("                                    found = true;");
        writer.println("                                }");
        writer.println("                            }");
        writer.println("                            if(found == false)");
        writer.println("                            {");
        writer.println("                                System.out.println(\"<\"+s+\", \"+TokenIdsAL.get(i)+\">\");");
        writer.println("                                forRemoval.add(s);");
        writer.println("                            }");
        writer.println("                        }");
        writer.println("                        else");
        writer.println("                        {");
        writer.println("                            System.out.println(\"<\"+s+\", \"+TokenIdsAL.get(i)+\">\");");
        writer.println("                            forRemoval.add(s);");
        writer.println("                        }");
        writer.println("                    }");
        writer.println("                }");
        writer.println("            }");
        writer.println("            Tokens.removeAll(forRemoval);");
        writer.println("            forRemoval.clear();");
        writer.println("            for(int k=0; k<KeyAuts.size(); k++)");
        writer.println("            {");
        writer.println("                for(String s : Tokens)");
        writer.println("                {");
        writer.println("                    if(KeyAuts.get(k).simulacionPegao(s))");
        writer.println("                    {");
        writer.println("                        System.out.println(\"<\"+s+\", \"+KeyIdsAL.get(k)+\">\");");
        writer.println("            forRemoval.add(s);");
        writer.println("                    }");
        writer.println("                }");
        writer.println("            }");
        
        
        
        writer.println("            Tokens.removeAll(forRemoval);");
        writer.println("            if(Tokens.size() != 0)");
        writer.println("            {");
        writer.println("                System.out.println(\"Error, not recognized: \" + Tokens);");
        writer.println("            }");
        
        
        writer.println("            ////Fin de lo agregado");
        
        writer.println("        }");
        writer.println("        else if(returnValue == JFileChooser.CANCEL_OPTION)");
        writer.println("        {");
        writer.println("            System.exit(0);");
        writer.println("        }");
        
        writer.println("    }");
        writer.println("}");
        writer.close();
        
        
    }
    
    

        
}
