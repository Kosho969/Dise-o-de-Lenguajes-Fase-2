

package Reader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;

public class AFD 
{
    public ArrayList<Transicion> _ListaTran = new ArrayList<Transicion>();
    public ArrayList<Estado> _ListaEst = new ArrayList<Estado>();
    public Estado _Inicial;
    public ArrayList<Estado> _Finales =  new ArrayList<Estado>();
    public ArrayList<String> _ListaSimbolos = new ArrayList<String>();
    
    public AFD()
    {
    
    }
    public void setListaSimbolos(ArrayList _ListaSimbolos)
    {
        this._ListaSimbolos = _ListaSimbolos;
    }
    
    public ArrayList getListaSimbolos()
    {
        return _ListaSimbolos;
    }
    
    public void setListaTran(ArrayList _ListaTran)
    {
        this._ListaTran = _ListaTran;
    }
    
    public ArrayList getListaTran()
    {
        return _ListaTran;
    }
    
    public void setListaEst(ArrayList _ListaEst)
    {
        this._ListaEst = _ListaEst;
    }
    
    public ArrayList getListaEst()
    {
        return _ListaEst;
    }
    
    public void setEstadoInicial(Estado _Inicial)
    {
        this._Inicial = _Inicial;
    }
    
    public Estado getEstadoInicial()
    {
        return _Inicial;
    }
    
    public void setEstadosFinales(ArrayList _Finales)
    {
        this._Finales = _Finales;
    }
    
    public ArrayList getEstadosFinales()
    {
        return _Finales;
    }
    
    //Constructor simple porque los datos vienen de la 
    //construccion por subconjuntos
    
    public AFD(ArrayList Transiciones, ArrayList Estados, Estado Inicial, ArrayList Finales)
    {
        _ListaTran = Transiciones;
        _ListaEst =  Estados;
        _Inicial = Inicial;
        _Finales = Finales;
              
    }
    
    public void ToTxtFile(String name) throws FileNotFoundException, UnsupportedEncodingException
    {
        PrintWriter writer;
        writer= new PrintWriter(name + ".txt", "UTF-8");
        ArrayList listaEstados = new ArrayList(new LinkedHashSet(_ListaEst));
        Estado[] _ArrayEst = new Estado[listaEstados.size()];
        _ArrayEst = (Estado[]) listaEstados.toArray(_ArrayEst);
        Transicion[] _ArrayTran = new Transicion[_ListaTran.size()];
        _ArrayTran = (Transicion[]) _ListaTran.toArray(_ArrayTran);
        String Estados = "ESTADOS: ";
        ArrayList SymFinal = new ArrayList();
        
        for(int i = 0; i < listaEstados.size();i++)
        {
            Estados = Estados + ", " + String.valueOf(_ArrayEst[i].getId());
        }
        String Simbolos = "SIMBOLOS: ";
         for(int i = 0; i < _ListaTran.size();i++)
        {
            if (SymFinal.contains(_ArrayTran[i].getSimbolo()) == false)
            {
                SymFinal.add(_ArrayTran[i].getSimbolo());
                Simbolos = Simbolos + ", " + _ArrayTran[i].getSimbolo();
            }
            
        }
        String Transiciones = "TRANSICIONES: ";
        for(int i = 0; i < _ListaTran.size();i++ )
        {
            Transiciones = Transiciones + ", " + _ArrayTran[i].toString();
        }
        writer.println(Estados);
        writer.println(Simbolos);
        writer.println("INICIO: " + String.valueOf(_Inicial.getId()));
        String Aceptacion = "ACEPTACION: ";
        for(int k=0;k<_Finales.size();k++)
        {
            Aceptacion = Aceptacion + ", " + String.valueOf(_Finales.get(k).getId());
        }
        writer.println(Aceptacion);
        writer.println(Transiciones);
        writer.close();
    }
    public ArrayList<Estado> eClosureSet(ArrayList<Estado> state)
    {
        ArrayList<Estado> Final = new ArrayList();
        for(int i=0; i<state.size(); i++)
        {
            Estado aClosurear = state.get(i);
            Final.addAll(eClosure(aClosurear));
        }
        return Final;
    }
    
    public ArrayList<Estado> eClosure(Estado state)
    {
        ArrayList<Estado> PorEClosurear = new ArrayList<Estado>();
        ArrayList<Estado> eClosureados = new ArrayList<Estado>();
        
        eClosureados.add(state);
        for(int i=0; i<_ListaTran.size(); i++)
        {   
            
            if(_ListaTran.get(i).getAnterior().equals(state))
            {
                if(_ListaTran.get(i).getSimbolo().equals("~"))
                {
                    PorEClosurear.add(_ListaTran.get(i).getPosterior());
                }
            }
        }
        for(int j=0; j<PorEClosurear.size();j++)
        {
            Estado aAgregar = PorEClosurear.get(j);
            //PorEClosurear.remove(j);
            eClosureados.addAll(eClosure(aAgregar));
            
        }
        return eClosureados;
    }
    public ArrayList<Estado> Move(ArrayList<Estado> Estados, String simbolo)
    {
        ArrayList<Estado> Resultado = new ArrayList<Estado>();
        
        for(int i=0; i<Estados.size(); i++)
        {
            for(int j=0; j<_ListaTran.size();j++)
            {
                if(_ListaTran.get(j).getAnterior().equals(Estados.get(i)))
                {
                    if(_ListaTran.get(j).getSimbolo().equals(simbolo))
                    {
                        Resultado.add(_ListaTran.get(j).getPosterior());
                    }
                }
            }
            
        }
        return Resultado;
    }
    public Boolean simulacionPegao(String cadena)
    {
        String[] Cadena = cadena.split("");
        ArrayList<String> ArrayCadena = new ArrayList<String>();
        for(int i=0; i<Cadena.length; i++)
        {
            ArrayCadena.add(Cadena[i]);
            //System.out.println("A SIMULAR " + s);
        }
        ArrayList<String> Clean = new ArrayList<String>();
        for(String str : ArrayCadena)
        {
            if(str.equals("") == false)
            {
                Clean.add(str);
            }
        }
        ArrayCadena = Clean;
        //System.out.println(ArrayCadena.size());
        ArrayList<Estado> conjuntoFinal = new ArrayList<Estado>();
        //System.out.println(ArrayCadena);
        conjuntoFinal.add(_Inicial);
        String res = "";
        for(Estado estado : conjuntoFinal)
        {    
            res = res + ", " + String.valueOf(estado.getId()); 
        }
        //System.out.println(res);
        for(int i=0; i<ArrayCadena.size(); i++)
        {
            //System.out.println(ArrayCadena.get(i));
            conjuntoFinal = Move(conjuntoFinal, ArrayCadena.get(i));
            res = "";
            for(Estado estado : conjuntoFinal)
            {    
                res = res + ", " + String.valueOf(estado.getId()); 
            }
            if(res.equals(""))
            {
                //System.out.println("Failed");
                return false;
            }
            else
            {
                //System.out.println(res);
            }
            
        }
        int dis = 0;
        for(int j=0; j<_Finales.size(); j++)
        {
            if(conjuntoFinal.contains(_Finales.get(j)))
            {
                dis =1;
            }
        }
            
        if(dis == 1)
        {
            //System.out.println("True");

            return true;
        }
        else
        {
            //System.out.println("False");
            return false;
            
        }
    }
    
    public boolean reconoce(String cadena)
//Solo devuelve False si no se reconoce la cadena, true si la reconoce aunque no sea aceptada
    {
        String[] Cadena = cadena.split("");
        ArrayList<String> ArrayCadena = new ArrayList<String>();
        for(int i=1; i<Cadena.length; i++)
        {
            ArrayCadena.add(Cadena[i]);
            //System.out.println("A SIMULAR " + s);
        }
        ArrayList<String> Clean = new ArrayList<String>();
        for(String str : ArrayCadena)
        {
            if(str.equals("") == false)
            {
                Clean.add(str);
            }
        }
        ArrayCadena = Clean;
        //System.out.println(ArrayCadena.size());
        ArrayList<Estado> conjuntoFinal = new ArrayList<Estado>();
        //System.out.println(ArrayCadena);
        conjuntoFinal.add(_Inicial);
        String res = "";
        for(Estado estado : conjuntoFinal)
        {    
            res = res + ", " + String.valueOf(estado.getId()); 
        }
        System.out.println(res);
        for(int i=0; i<ArrayCadena.size(); i++)
        {
            System.out.println(ArrayCadena.get(i));
            conjuntoFinal = Move(conjuntoFinal, ArrayCadena.get(i));
            res = "";
            for(Estado estado : conjuntoFinal)
            {    
                res = res + ", " + String.valueOf(estado.getId()); 
            }
            if(res.equals(""))
            {
                System.out.println("Failed");
                return false;
            }
            else
            {
                System.out.println(res);
            }
            
        }
        int dis = 0;
        for(int j=0; j<_Finales.size(); j++)
        {
            if(conjuntoFinal.contains(_Finales.get(j)))
            {
                dis =1;
            }
        }
            
        if(dis == 1)
        {
            //System.out.println("True");

            return true;
        }
        else
        {
            //System.out.println("False");
            return true;
            
        }
    }
    public Boolean simulacion(String cadena)
    {
        String[] Cadena = cadena.split(" ");
        
        System.out.println(Cadena.length);
        
        if(Cadena.length == 0)
        {
            return true;
        }
        for(String s : Cadena)
        {
            //System.out.println("A SIMULAR " + s);
        }
        ArrayList<String> ArrayCadena = new ArrayList<String>(Arrays.asList(Cadena));
        ArrayList<String> Clean = new ArrayList<String>();
        for(String str : ArrayCadena)
        {
            if(str.equals("") == false)
            {
                Clean.add(str);
            }
        }
        ArrayCadena = Clean;
        //System.out.println(ArrayCadena.size());
        ArrayList<Estado> conjuntoFinal = new ArrayList<Estado>();
        //System.out.println(ArrayCadena);
        conjuntoFinal.add(_Inicial);
        String res = "";
        for(Estado estado : conjuntoFinal)
        {    
            res = res + ", " + String.valueOf(estado.getId()); 
        }
        //System.out.println(res);
        for(int i=0; i<ArrayCadena.size(); i++)
        {
            System.out.println(ArrayCadena.get(i));
            conjuntoFinal = Move(conjuntoFinal, ArrayCadena.get(i));
            res = "";
            for(Estado estado : conjuntoFinal)
            {    
                res = res + ", " + String.valueOf(estado.getId()); 
            }
            if(res.equals(""))
            {
                System.out.println("Failed");
                return false;
            }
            else
            {
                System.out.println(res);
            }
            
        }
        int dis = 0;
        for(int j=0; j<_Finales.size(); j++)
        {
            if(conjuntoFinal.contains(_Finales.get(j)))
            {
                dis =1;
            }
        }
            
        if(dis == 1)
        {
            //System.out.println("True");

            return true;
        }
        else
        {
            //System.out.println("False");
            return false;
            
        }
    }
    public AFD Minimizar()
    {
        System.out.println("-----------------------------MINIMIZACION");
        ArrayList<ArrayList<Estado>> temp = new ArrayList<ArrayList<Estado>>();
        ArrayList<ArrayList<Estado>> P1 = new ArrayList<ArrayList<Estado>>();
        ArrayList<ArrayList<Integer>> N1 = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> NT1 = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> N11 = new ArrayList<Integer>(); 
        ArrayList<Estado> AFDMinStates = new ArrayList<Estado>();
        ArrayList<Transicion> AFDMinTrans = new ArrayList<Transicion>();
        ArrayList<Estado> AFDMinFinales = new ArrayList<Estado>();
        Estado basura = new Estado(-1);
        Count.count = 0;
        Estado AFDMinInicial = new Estado(Count.count);
        String states = "";
        for(Estado e : _ListaEst)
        {
            states = states + " " + e.getId();
        }
        System.out.println("Estados: " + states);
        states = "";
        for(Estado e : _Finales)
        {
            states = states + " " + e.getId();
        }
        System.out.println("Aceptacion: " + states);
        ArrayList<Estado> T1 = _ListaEst;
        
        for(Transicion tran : _ListaTran)
        {
            if(_ListaSimbolos.contains(tran.getSimbolo()) == false)
            {
                _ListaSimbolos.add(tran.getSimbolo());
            }
        }
        
        int counter = 0;
        int agrego = 0;
        int inde = 0;
        T1.removeAll(_Finales);
        String T1String = "";
        for(Estado e1 : T1)
        {
            T1String = T1String +" "+e1.getId();
        }
        System.out.println("T1" + T1String);
        ArrayList<Estado> T2 = new ArrayList<Estado>();
        T2.addAll(_Finales);
        String T2String = "";
        for(Estado e2 : T2)
        {
            T2String = T2String +" "+e2.getId();
        }
        System.out.println("T2: " + T2String);
        temp.add(T1);
        temp.add(T2);
        //size = temp.size();
        
        while(temp.size() != inde)
        {
            counter = 0;
            T1 = new ArrayList<Estado>();
            T2 = new ArrayList<Estado>();
            P1 = new ArrayList<ArrayList<Estado>>();
            N1 = new ArrayList<ArrayList<Integer>>();
            N11 = new ArrayList<Integer>();
            NT1 = new ArrayList<ArrayList<Integer>>();
            
            T1 = temp.get(inde);
            T1String = "";
            for(Estado e3 : T1)
            {
                T1String = T1String +" "+e3.getId();
            }
            System.out.println("T1" + T1String);
            for(Estado e : T1)
            {
                T2 = new ArrayList<Estado>(_ListaSimbolos.size());
                System.out.println("revisando estado " + e.getId());
                System.out.println("Con los simbolos: " + _ListaSimbolos);
                for(String s : _ListaSimbolos)
                {
                    agrego = 0;
                    for(Transicion tran : _ListaTran)
                    {
                        System.out.println("Revisando transicion: " + tran.toString());
                        if(tran.getAnterior().equals(e))
                        {
                            
                            if(tran.getSimbolo().equals(s)==true)
                            {
                                System.out.println("Deberia agregarse: " + tran.getPosterior().getId());
                                T2.add(tran.getPosterior());
                            }
                            if(tran.getSimbolo().equals(s)==false)
                            {
                                T2.add(basura);
                            }
                            
                            
                            
                        }
                    }
                    
                }
                System.out.println("transiciones hacia");
                for(Estado es : T2)
                {
                    System.out.println(es.getId());
                }
                    
                P1.add(T2);
            }
            System.out.println("Tabla de transiciones");
            for(ArrayList<Estado> AE : P1)
            {
                String Fila = "";
                for(Estado e : AE)
                {
                    Fila = Fila + " " + e.getId();
                }
                System.out.println(Fila);
            }
            for(ArrayList<Estado> AE : P1)
            {
                N11 = new ArrayList<Integer>();
                for(Estado e : AE)
                {
                   
                    for(ArrayList<Estado> ae : temp)
                    {
                        if(ae.contains(e)==true)
                        {
                            N11.add(temp.indexOf(ae));
                           
                        }
                        if(ae.contains(e)==false)
                        {
                            N11.add(1000000);
                        }
                        
                        
                    }
                }
                N1.add(N11);
                for(Integer i : N11)
                {
                    System.out.println("No. " +i);
                }
            }
            System.out.println("Tabla de indices");
            for(ArrayList<Integer> ai : N1)
            {   
                String intFila = "";
                for(Integer i : ai)
                {
                    intFila = intFila + " " + i;
                }
                System.out.println(intFila);
            }
            
            for(ArrayList<Integer> ai : N1)
            {   
                if(NT1.contains(ai) == false)
                {
                    NT1.add(ai);
                }
            }
            
            System.out.println("Nuevas tipos de particion");
            for(ArrayList<Integer> ai : NT1)
            {   
                String intFila = "";
                for(Integer i : ai)
                {
                    intFila = intFila + " " + i;
                }
                System.out.println(intFila);
            }
           //T2 = new ArrayList<Estado>();
            T1String = "";
            for(Estado e3 : T1)
            {
                T1String = T1String +" "+e3.getId();
            }
            System.out.println("T1" + T1String);
            for(ArrayList<Integer> ai : NT1)
            { 
                T2 = new ArrayList<Estado>();
                for(int i = 0; i<N1.size(); i++)
                {   
                    System.out.println("Ando en el if 1 en la pos: " + i);
                    if(N1.get(i).equals(ai))
                    {
                        T2.add(T1.get(i));
                        System.out.println("Se agrego " + T1.get(i).getId());
                    }
                }
                T2String = "";
                for(Estado e2 : T2)
                {
                    T2String = T2String +" "+e2.getId();
                }
                System.out.println("T2: " + T2String);
                
                if(temp.contains(T2) == false)
                {
                    temp.add(T2);
                    counter = counter +1;
                }
                
                if(counter == 0)
                {
                    inde = inde + 1;
                    System.out.println("No se encontraron nuevas particiones");
                    
                }
                else
                {
                    temp.remove(T1);
                    System.out.println("Se encontraron nuevas particiones");
                    //size = temp.size();
                    inde = 0;
                }
            }
            
        }
        System.out.println("Resultado");
        System.out.println("Numero de particiones: " + temp.size());
        System.out.println("Particiones: ");
        for(ArrayList<Estado> ae : temp)
        {
            String Particion = "";
            for(Estado e : ae)
            {
            Particion = Particion + " " + e.getId();
            }
            System.out.println(Particion);
        }
        for(ArrayList<Estado> ae : temp)
        {
            Estado st = new Estado(Count.count);
            System.out.println("Se creo el estado: " + st.getId());
            Count.count = Count.count + 1;
            if(ae.contains(_Inicial))
            {
                AFDMinInicial = st;
            }
            for(Estado es : _Finales)
            {
                if(ae.contains(es))
                {
                    if(AFDMinFinales.contains(st) == false)
                    {
                        AFDMinFinales.add(st);
                    }
                }
            }
            AFDMinStates.add(st);
        }
        for(int j =0;j<temp.size(); j++)
        {
            
                for(String simbolo : _ListaSimbolos)
                {
                    for(Transicion tran : _ListaTran)
                    {
                        if(tran.getAnterior().equals(temp.get(j).get(0)))
                        {
                            if(tran.getSimbolo().equals(simbolo))
                            {
                                int indiceOrigen = j;
                                for(int k=0;k<temp.size();k++)
                                {
                                    if(temp.get(k).contains(tran.getPosterior()))
                                    {
                                        int indiceDestino = k;
                                        Transicion AFDMinTran = new Transicion(AFDMinStates.get(indiceOrigen), AFDMinStates.get(indiceDestino), simbolo);
                                        System.out.println("Se creo la transicion: " + AFDMinTran.toString());
                                        AFDMinTrans.add(AFDMinTran);
                                    }
                                }
                            }
                        }
                                
                    }
                }
            
        }
        AFD AFDMin = new AFD(AFDMinTrans, AFDMinStates, AFDMinInicial, AFDMinFinales);
        return AFDMin;
    }
    public void toGraph(String fileName) throws FileNotFoundException, UnsupportedEncodingException, IOException
    {
        PrintWriter writer;
        writer= new PrintWriter(fileName + ".dot", "UTF-8");
        writer.println("digraph " + fileName + " {");
        writer.println("rankdir=LR;");
        for (Estado s : _ListaEst)
        {
            String shape = "circle";
            if(this._Inicial.equals(s))
            {
                shape = "square";
            }
            if(this._Finales.contains(s))
            {
                shape = "doublecircle";
            }
            writer.println(s.getId() + " [ shape=" + shape + "]");
        }
        for (Transicion tran : _ListaTran)
        {
            String escape = "";
            if(tran.getSimbolo().equals("\""))
            {
                escape = " \\";
            }
            writer.println(tran.getAnterior().getId() + "->" + tran.getPosterior().getId() + "[label=\"" + escape + tran.getSimbolo() + " \"]");
        }
        writer.println("}");
        writer.close();
        Runtime rt = Runtime.getRuntime();
        Process pr = rt.exec("C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot -Tpng C:\\Users\\Pablo\\Documents\\NetBeansProjects\\Fase2\\" + fileName + ".dot -o C:\\Users\\Pablo\\Documents\\NetBeansProjects\\Fase2\\" + fileName + ".png");
    }
    
}
