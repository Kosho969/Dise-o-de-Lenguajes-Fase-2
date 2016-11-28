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
    static ArrayList<String> WhiteSpaceAL = new ArrayList<String>(Arrays.asList("\n","\r","\t"," "));


    static boolean[] EKTokenAL = {true, false, false, false, true};

   static ArrayList<String> KeyRegexAL = new ArrayList<String>(Arrays.asList("«bÇoÇoÇlÇeÇaÇn»","«bÇyÇtÇe»","«cÇhÇaÇr»","«cÇlÇaÇsÇs»","«dÇoÇuÇbÇlÇe»","«fÇaÇlÇsÇe»","«fÇiÇnÇaÇl»","«fÇlÇoÇaÇt»","«iÇnÇt»","«lÇoÇnÇg»","«nÇeÇw»","«nÇuÇlÇl»","«sÇhÇoÇrÇt»","«sÇtÇaÇtÇiÇc»","«sÇuÇpÇeÇr»","«tÇhÇiÇs»","«tÇrÇuÇe»","«vÇoÇiÇd»","«:»","«,»","«-Ç-»","«.»","«+Ç+»","«{»","«[»","«(»","«-»","«!»","«+»","«}»","«]»","«)»"));

    static ArrayList<String> KeyIdsAL = new ArrayList<String>(Arrays.asList("boolean","byte","char","class","double","false","final","float","int","long","new","null","short","static","super","this","true","void","colon","comma","dec","dot","inc","lbrace","lbrack","lpar","minus","not","plus","rbrace","rbrack","rpar"));

    static ArrayList<String> TokenRegexAL = new ArrayList<String>(Arrays.asList("«aØbØcØdØeØfØgØhØiØjØkØlØmØnØoØpØqØrØsØtØuØvØwØxØyØzØAØBØCØDØEØFØGØHØIØJØKØLØMØNØOØPØQØRØSØTØUØVØWØXØYØZ»Ç««aØbØcØdØeØfØgØhØiØjØkØlØmØnØoØpØqØrØsØtØuØvØwØxØyØzØAØBØCØDØEØFØGØHØIØJØKØLØMØNØOØPØQØRØSØTØUØVØWØXØYØZ»Ø«0Ø1Ø2Ø3Ø4Ø5Ø6Ø7Ø8Ø9»»º","««0»Ø«1Ø2Ø3Ø4Ø5Ø6Ø7Ø8Ø9»Ç««0Ø1Ø2Ø3Ø4Ø5Ø6Ø7Ø8Ø9»»ºØ««0Çx»Ø«0ÇX»»Ç«0Ø1Ø2Ø3Ø4Ø5Ø6Ø7Ø8Ø9ØAØBØCØDØEØFØaØbØcØdØeØf»Ç««0Ø1Ø2Ø3Ø4Ø5Ø6Ø7Ø8Ø9ØAØBØCØDØEØFØaØbØcØdØeØf»»ºØ0Ç«0Ø1Ø2Ø3Ø4Ø5Ø6Ø7»Ç««0Ø1Ø2Ø3Ø4Ø5Ø6Ø7»»º»Ç«««l»Ø«L»»Ø~»","«.»Ç«0Ø1Ø2Ø3Ø4Ø5Ø6Ø7Ø8Ø9»Ç««0Ø1Ø2Ø3Ø4Ø5Ø6Ø7Ø8Ø9»»ºÇ««««e»Ø«E»»Ç«««+»Ø«-»»Ø~»Ç«0Ø1Ø2Ø3Ø4Ø5Ø6Ø7Ø8Ø9»Ç««0Ø1Ø2Ø3Ø4Ø5Ø6Ø7Ø8Ø9»»º»Ø~»Ç«««F»Ø«f»Ø«D»Ø«d»»Ø~»Ø«0Ø1Ø2Ø3Ø4Ø5Ø6Ø7Ø8Ø9»Ç««0Ø1Ø2Ø3Ø4Ø5Ø6Ø7Ø8Ø9»»ºÇ««.»Ç««0Ø1Ø2Ø3Ø4Ø5Ø6Ø7Ø8Ø9»»ºÇ««««e»Ø«E»»Ç«««+»Ø«-»»Ø~»Ç«0Ø1Ø2Ø3Ø4Ø5Ø6Ø7Ø8Ø9»Ç««0Ø1Ø2Ø3Ø4Ø5Ø6Ø7Ø8Ø9»»º»Ø~»Ç«««F»Ø«f»Ø«D»Ø«d»»Ø~»Ø««e»Ø«E»»Ç«««+»Ø«-»»Ø~»Ç«0Ø1Ø2Ø3Ø4Ø5Ø6Ø7Ø8Ø9»Ç««0Ø1Ø2Ø3Ø4Ø5Ø6Ø7Ø8Ø9»»ºÇ«««F»Ø«f»Ø«D»Ø«d»»Ø~»Ø«F»Ø«f»Ø«D»Ø«d»»","«'»Ç««aØbØcØdØeØfØgØhØiØjØkØlØmØnØoØpØqØrØsØtØuØvØwØxØyØzØAØBØCØDØEØFØGØHØIØJØKØLØMØNØOØPØQØRØSØTØUØVØWØXØYØZØ0Ø1Ø2Ø3Ø4Ø5Ø6Ø7Ø8Ø9Ø Ø!Ø%Ø^Ø&Ø=Ø?Ø>Ø<Ø:Ø.Ø,Ø(Ø[Ø{Ø)Ø$Ø]Ø}Ø-Ø+Ø\\Ø/Ø\"Ø*»Ø«\\»Ç««b»Ø«t»Ø«n»Ø«f»Ø«r»Ø«\"»Ø«\\Ç'»Ø«\\»Ø«u»Ç««u»»ºÇ«0Ø1Ø2Ø3Ø4Ø5Ø6Ø7Ø8Ø9ØAØBØCØDØEØFØaØbØcØdØeØf»Ç«0Ø1Ø2Ø3Ø4Ø5Ø6Ø7Ø8Ø9ØAØBØCØDØEØFØaØbØcØdØeØf»Ç«0Ø1Ø2Ø3Ø4Ø5Ø6Ø7Ø8Ø9ØAØBØCØDØEØFØaØbØcØdØeØf»Ç«0Ø1Ø2Ø3Ø4Ø5Ø6Ø7Ø8Ø9ØAØBØCØDØEØFØaØbØcØdØeØf»Ø«0Ø1Ø2Ø3»Ç«««0Ø1Ø2Ø3Ø4Ø5Ø6Ø7»»Ø~»Ç«««0Ø1Ø2Ø3Ø4Ø5Ø6Ø7»»Ø~»Ø«0Ø1Ø2Ø3Ø4Ø5Ø6Ø7»Ç«««0Ø1Ø2Ø3Ø4Ø5Ø6Ø7»»Ø~»»»Ç«'»","«\"»Ç««aØbØcØdØeØfØgØhØiØjØkØlØmØnØoØpØqØrØsØtØuØvØwØxØyØzØAØBØCØDØEØFØGØHØIØJØKØLØMØNØOØPØQØRØSØTØUØVØWØXØYØZØ0Ø1Ø2Ø3Ø4Ø5Ø6Ø7Ø8Ø9Ø Ø!Ø%Ø^Ø&Ø=Ø?Ø>Ø<Ø:Ø.Ø,Ø(Ø[Ø{Ø)Ø$Ø]Ø}Ø-Ø+Ø/Ø'Ø*Ø\\»Ø«\\»Ç««b»Ø«t»Ø«n»Ø«f»Ø«r»Ø«\\Ç'»Ø«\\»Ø«u»Ç««u»»ºÇ«0Ø1Ø2Ø3Ø4Ø5Ø6Ø7Ø8Ø9ØAØBØCØDØEØFØaØbØcØdØeØf»Ç«0Ø1Ø2Ø3Ø4Ø5Ø6Ø7Ø8Ø9ØAØBØCØDØEØFØaØbØcØdØeØf»Ç«0Ø1Ø2Ø3Ø4Ø5Ø6Ø7Ø8Ø9ØAØBØCØDØEØFØaØbØcØdØeØf»Ç«0Ø1Ø2Ø3Ø4Ø5Ø6Ø7Ø8Ø9ØAØBØCØDØEØFØaØbØcØdØeØf»Ø«0Ø1Ø2Ø3»Ç«««0Ø1Ø2Ø3Ø4Ø5Ø6Ø7»»Ø~»Ç«««0Ø1Ø2Ø3Ø4Ø5Ø6Ø7»»Ø~»Ø«0Ø1Ø2Ø3Ø4Ø5Ø6Ø7»Ç«««0Ø1Ø2Ø3Ø4Ø5Ø6Ø7»»Ø~»»»ºÇ«\"»"));

    static ArrayList<String> TokenIdsAL = new ArrayList<String>(Arrays.asList("ident","intLit","floatLit","charLit","stringLit"));
    static AutMaker maker = new AutMaker();

    static ArrayList<AFD> KeyAuts = new ArrayList<AFD>();

    static ArrayList<AFD> TokenAuts = new ArrayList<AFD>();

    static ArrayList<String> currentToken = null;

    static int currentTokenIndex = -1;

    static ArrayList<ArrayList<String>> tokensAL = new ArrayList<ArrayList<String>>();
    public static void main(String[] args) throws FileNotFoundException, IOException
    {

        for(String s : KeyRegexAL)
        {
            KeyAuts.add(maker.MakeAut(s));
        }
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
        if (returnValue != JFileChooser.APPROVE_OPTION){
            System.exit(0);
        }
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
        ArrayList<String> Lexemas = new ArrayList<String>();
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
                    tok = tok + StrLine[i+j];
                    if(i+j+1 != StrLine.length)
                    {
                        j++;
                    }
                    else
                    {
                        break;
                    }
                }
                Lexemas.add(tok);
            }
            i=i+j;
        }

        ArrayList<String> forRemoval = new ArrayList<String>();

        for (String lexema : Lexemas)
        {
            ArrayList<String> token = null;

            // Si hace match con un token, excelente
            int matchedTokenIndex = getMatchedTokenIndex(lexema);
            if (-1 != matchedTokenIndex) {

                // Si la gramática especifica EXCEPT KEYWORKDS
                if (EKTokenAL[matchedTokenIndex] == true) {

                    int matchedKeywordIndex = getMatchedKeywordIndex(lexema);
                    if (-1 != matchedKeywordIndex) {



                        token = buildToken(lexema, KeyIdsAL.get(matchedKeywordIndex));
                    }

                    if (-1 == matchedKeywordIndex) {
                        token = buildToken(lexema, TokenIdsAL.get(matchedTokenIndex));
                    }
                } else {
                    token = buildToken(lexema, TokenIdsAL.get(matchedTokenIndex));
                }
            }

            if (null != token) {
                tokensAL.add(token);

                continue;
            }

            // Si hace match con un keyword, excelente
            int matchedKeywordIndex = getMatchedKeywordIndex(lexema);
            if (-1 != matchedKeywordIndex) {
                token = buildToken(lexema, KeyIdsAL.get(matchedKeywordIndex));
            }

            if (null != token) {
                tokensAL.add(token);

                continue;
            }

            token = buildToken(lexema, "unrecognized");
            tokensAL.add(token);
        }
        System.out.println(tokensAL);
    }

    private static int getMatchedTokenIndex(String lexema)
    {
        for (int i=0; i<TokenAuts.size(); i++)
        {
            if (TokenAuts.get(i).simulacionPegao(lexema)) {
                return i;
            }
        }

        return -1;
    }

    private static int getMatchedKeywordIndex(String lexema)
    {
        for (int i=0; i<KeyAuts.size(); i++)
        {
            if (KeyAuts.get(i).simulacionPegao(lexema)) {
                return i;
            }
        }

        return -1;
    }

    private static ArrayList<String> buildToken(String lexema, String tokenName)
    {
        return new ArrayList<String>(Arrays.asList(lexema, tokenName));
    }
}
