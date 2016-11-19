package Generator;
 
import Reader.AFD;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.io.File;
import javax.swing.JFileChooser;
public class AnalizadorLexico
{
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        ArrayList<String> WhiteSpaceAL = new ArrayList<String>(Arrays.asList("\n","\n","\r"," ","    "));
 
        boolean[] EKTokenAL = {true, true, false, false, false, true};
 
        ArrayList<String> KeyRegexAL = new ArrayList<String>(Arrays.asList("«bÇoÇoÇlÇeÇaÇn»","«bÇoÇoÇlÇeÇaÇn»","«bÇyÇtÇe»","«cÇhÇaÇr»","«cÇlÇaÇsÇs»","«dÇoÇuÇbÇlÇe»","«fÇaÇlÇsÇe»","«fÇiÇnÇaÇl»","«fÇlÇoÇaÇt»","«iÇnÇt»","«lÇoÇnÇg»","«nÇeÇw»","«nÇuÇlÇl»","«sÇhÇoÇrÇt»","«sÇtÇaÇtÇiÇc»","«sÇuÇpÇeÇr»","«tÇhÇiÇs»","«tÇrÇuÇe»","«vÇoÇiÇd»","«:»","«,»","«-Ç-»","«.»","«+Ç+»","«{»","«[»","«(»","«-»","«!»","«+»","«}»","«]»","«)»"));
 
        ArrayList<String> KeyIdsAL = new ArrayList<String>(Arrays.asList("boolean","boolean","byte","char","class","double","false","final","float","int","long","new","null","short","static","super","this","true","void","colon","comma","dec","dot","inc","lbrace","lbrack","lpar","minus","not","plus","rbrace","rbrack","rpar"));
 
        ArrayList<String> TokenRegexAL = new ArrayList<String>(Arrays.asList("«aØbØcØdØeØfØgØhØiØjØkØlØmØnØoØpØqØrØsØtØuØvØwØxØyØzØAØBØCØDØEØFØGØHØIØJØKØLØMØNØOØPØQØRØSØTØUØVØWØXØYØZ»Ç««aØbØcØdØeØfØgØhØiØjØkØlØmØnØoØpØqØrØsØtØuØvØwØxØyØzØAØBØCØDØEØFØGØHØIØJØKØLØMØNØOØPØQØRØSØTØUØVØWØXØYØZ»Ø«0Ø1Ø2Ø3Ø4Ø5Ø6Ø7Ø8Ø9»»º","«aØbØcØdØeØfØgØhØiØjØkØlØmØnØoØpØqØrØsØtØuØvØwØxØyØzØAØBØCØDØEØFØGØHØIØJØKØLØMØNØOØPØQØRØSØTØUØVØWØXØYØZ»Ç««aØbØcØdØeØfØgØhØiØjØkØlØmØnØoØpØqØrØsØtØuØvØwØxØyØzØAØBØCØDØEØFØGØHØIØJØKØLØMØNØOØPØQØRØSØTØUØVØWØXØYØZ»Ø«0Ø1Ø2Ø3Ø4Ø5Ø6Ø7Ø8Ø9»»º","««0»Ø«1Ø2Ø3Ø4Ø5Ø6Ø7Ø8Ø9»Ç««0Ø1Ø2Ø3Ø4Ø5Ø6Ø7Ø8Ø9»»ºØ««0Çx»Ø«0ÇX»»Ç«0Ø1Ø2Ø3Ø4Ø5Ø6Ø7Ø8Ø9ØAØBØCØDØEØFØaØbØcØdØeØf»Ç««0Ø1Ø2Ø3Ø4Ø5Ø6Ø7Ø8Ø9ØAØBØCØDØEØFØaØbØcØdØeØf»»ºØ0Ç«0Ø1Ø2Ø3Ø4Ø5Ø6Ø7»Ç««0Ø1Ø2Ø3Ø4Ø5Ø6Ø7»»º»Ç«««l»Ø«L»»Ø~»","«.»Ç«0Ø1Ø2Ø3Ø4Ø5Ø6Ø7Ø8Ø9»Ç««0Ø1Ø2Ø3Ø4Ø5Ø6Ø7Ø8Ø9»»ºÇ««««e»Ø«E»»Ç«««+»Ø«-»»Ø~»Ç«0Ø1Ø2Ø3Ø4Ø5Ø6Ø7Ø8Ø9»Ç««0Ø1Ø2Ø3Ø4Ø5Ø6Ø7Ø8Ø9»»º»Ø~»Ç«««F»Ø«f»Ø«D»Ø«d»»Ø~»Ø«0Ø1Ø2Ø3Ø4Ø5Ø6Ø7Ø8Ø9»Ç««0Ø1Ø2Ø3Ø4Ø5Ø6Ø7Ø8Ø9»»ºÇ««.»Ç««0Ø1Ø2Ø3Ø4Ø5Ø6Ø7Ø8Ø9»»ºÇ««««e»Ø«E»»Ç«««+»Ø«-»»Ø~»Ç«0Ø1Ø2Ø3Ø4Ø5Ø6Ø7Ø8Ø9»Ç««0Ø1Ø2Ø3Ø4Ø5Ø6Ø7Ø8Ø9»»º»Ø~»Ç«««F»Ø«f»Ø«D»Ø«d»»Ø~»Ø««e»Ø«E»»Ç«««+»Ø«-»»Ø~»Ç«0Ø1Ø2Ø3Ø4Ø5Ø6Ø7Ø8Ø9»Ç««0Ø1Ø2Ø3Ø4Ø5Ø6Ø7Ø8Ø9»»ºÇ«««F»Ø«f»Ø«D»Ø«d»»Ø~»Ø«F»Ø«f»Ø«D»Ø«d»»","«'»Ç««aØbØcØdØeØfØgØhØiØjØkØlØmØnØoØpØqØrØsØtØuØvØwØxØyØzØAØBØCØDØEØFØGØHØIØJØKØLØMØNØOØPØQØRØSØTØUØVØWØXØYØZØ0Ø1Ø2Ø3Ø4Ø5Ø6Ø7Ø8Ø9Ø Ø!Ø%Ø^Ø&Ø=Ø?Ø>Ø<Ø:Ø.Ø,Ø(Ø[Ø{Ø)Ø$Ø]Ø}Ø-Ø+Ø\\Ø\"»Ø«\\»Ç««b»Ø«t»Ø«n»Ø«f»Ø«r»Ø«\"»Ø«\\Ç'»Ø«\\»Ø«u»Ç««u»»ºÇ«0Ø1Ø2Ø3Ø4Ø5Ø6Ø7Ø8Ø9ØAØBØCØDØEØFØaØbØcØdØeØf»Ç«0Ø1Ø2Ø3Ø4Ø5Ø6Ø7Ø8Ø9ØAØBØCØDØEØFØaØbØcØdØeØf»Ç«0Ø1Ø2Ø3Ø4Ø5Ø6Ø7Ø8Ø9ØAØBØCØDØEØFØaØbØcØdØeØf»Ç«0Ø1Ø2Ø3Ø4Ø5Ø6Ø7Ø8Ø9ØAØBØCØDØEØFØaØbØcØdØeØf»Ø«0Ø1Ø2Ø3»Ç«««0Ø1Ø2Ø3Ø4Ø5Ø6Ø7»»Ø~»Ç«««0Ø1Ø2Ø3Ø4Ø5Ø6Ø7»»Ø~»Ø«0Ø1Ø2Ø3Ø4Ø5Ø6Ø7»Ç«««0Ø1Ø2Ø3Ø4Ø5Ø6Ø7»»Ø~»»»Ç«'»","«\"»Ç««aØbØcØdØeØfØgØhØiØjØkØlØmØnØoØpØqØrØsØtØuØvØwØxØyØzØAØBØCØDØEØFØGØHØIØJØKØLØMØNØOØPØQØRØSØTØUØVØWØXØYØZØ0Ø1Ø2Ø3Ø4Ø5Ø6Ø7Ø8Ø9Ø Ø!Ø%Ø^Ø&Ø=Ø?Ø>Ø<Ø:Ø.Ø,Ø(Ø[Ø{Ø)Ø$Ø]Ø}Ø-Ø+Ø'Ø\\»Ø«\\»Ç««b»Ø«t»Ø«n»Ø«f»Ø«r»Ø«\\Ç'»Ø«\\»Ø«u»Ç««u»»ºÇ«0Ø1Ø2Ø3Ø4Ø5Ø6Ø7Ø8Ø9ØAØBØCØDØEØFØaØbØcØdØeØf»Ç«0Ø1Ø2Ø3Ø4Ø5Ø6Ø7Ø8Ø9ØAØBØCØDØEØFØaØbØcØdØeØf»Ç«0Ø1Ø2Ø3Ø4Ø5Ø6Ø7Ø8Ø9ØAØBØCØDØEØFØaØbØcØdØeØf»Ç«0Ø1Ø2Ø3Ø4Ø5Ø6Ø7Ø8Ø9ØAØBØCØDØEØFØaØbØcØdØeØf»Ø«0Ø1Ø2Ø3»Ç«««0Ø1Ø2Ø3Ø4Ø5Ø6Ø7»»Ø~»Ç«««0Ø1Ø2Ø3Ø4Ø5Ø6Ø7»»Ø~»Ø«0Ø1Ø2Ø3Ø4Ø5Ø6Ø7»Ç«««0Ø1Ø2Ø3Ø4Ø5Ø6Ø7»»Ø~»»»ºÇ«\"»"));
 
        ArrayList<String> TokenIdsAL = new ArrayList<String>(Arrays.asList("ident","ident","intLit","floatLit","charLit","stringLit"));
 
        AutMaker maker = new AutMaker();
        ArrayList<AFD> KeyAuts = new ArrayList<AFD>();
        for(String s : KeyRegexAL)
        {
            KeyAuts.add(maker.MakeAut(s));
        }
        ArrayList<AFD> TokenAuts = new ArrayList<AFD>();
        for(String s : TokenRegexAL)
        {
            TokenAuts.add(maker.MakeAut(s));
        }
        for(int i=0; i<KeyAuts.size(); i++)
        {
            KeyAuts.get(i).toGraph("Generado" + KeyIdsAL.get(i));
        }
        for(int k=0; k<TokenAuts.size(); k++)
        {
            TokenAuts.get(k).toGraph("Generado" + TokenIdsAL.get(k));
        }
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println(selectedFile.getName());
            BufferedReader in = new BufferedReader(new FileReader(selectedFile.getName()));
            int nul = in.read();
            String car = String.valueOf((char)nul);
            String conc = "";
            while(nul != -1)
            {
                conc = conc + car;
                nul = in.read();
                car = String.valueOf((char)nul);
            }
            System.out.println(conc);
            String[] StrLine = conc.split("");
            ArrayList<String> Tokens = new ArrayList<String>();
            for(int i=0; i<StrLine.length; i++)
            {
                String tok = "";
                int j=0;
                if(WhiteSpaceAL.contains(StrLine[i]))
                {
                    //do nothing
                }
                else
                {
                    while(WhiteSpaceAL.contains(StrLine[i+j]) == false)
                    {
                        tok  = tok + StrLine[i+j];
                        if(i+j+1 != StrLine.length)
                        {
                            j++;
                        }
                        else
                        {
                            break;
                        }
                    }
                    Tokens.add(tok);
                }
                i=i+j;
            }
            System.out.println(Tokens);
            ArrayList<String> forRemoval = new ArrayList<String>();
            for(int i=0; i<TokenAuts.size(); i++)
            {
                for(String s : Tokens)
                {
                    if(TokenAuts.get(i).simulacionPegao(s))
                    {
                        boolean found = false;
                        if(EKTokenAL[i]==true)
                        {
                            for(int j=0; j<KeyAuts.size(); j++)
                            {
                                if(KeyAuts.get(j).simulacionPegao(s))
                                {
                                    System.out.println("<"+s+", "+KeyIdsAL.get(j)+">");
                                    forRemoval.add(s);
                                    found = true;
                                }
                            }
                            if(found == false)
                            {
                                System.out.println("<"+s+", "+TokenIdsAL.get(i)+">");
                                forRemoval.add(s);
                            }
                        }
                        else
                        {
                            System.out.println("<"+s+", "+TokenIdsAL.get(i)+">");
                            forRemoval.add(s);
                        }
                    }
                }
            }
            Tokens.removeAll(forRemoval);
            forRemoval.clear();
            for(int k=0; k<KeyAuts.size(); k++)
            {
                for(String s : Tokens)
                {
                    if(KeyAuts.get(k).simulacionPegao(s))
                    {
                        System.out.println("<"+s+", "+KeyIdsAL.get(k)+">");
            forRemoval.add(s);
                    }
                }
            }
            Tokens.removeAll(forRemoval);
            if(Tokens.size() != 0)
            {
                System.out.println("Error, not recognized: " + Tokens);
            }
            ////Fin de lo agregado
        }
        else if(returnValue == JFileChooser.CANCEL_OPTION)
        {
            System.exit(0);
        }
    }
}
