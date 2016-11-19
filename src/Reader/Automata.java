
package Reader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Stack;

public class Automata 
{
    public ArrayList<Transicion> _ListaTran = new ArrayList<Transicion>();
    public ArrayList<Estado> _ListaEst = new ArrayList<Estado>();
    public Estado _Inicial;
    public Estado _Final;
    public ArrayList _ListaSimbolos = new ArrayList();
    
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
    
    public void setEstadoFinal(Estado _Final)
    {
        this._Final = _Final;
    }
    
    public Estado getEstadoFinal()
    {
        return _Final;
    }
    //Automata de un simbolo(ej. A, B)
    public Automata(String Simbolo)
    {
        _Inicial = new Estado(Count.count);
        Count.count = Count.count + 1;
        _Final = new Estado(Count.count);
        Count.count = Count.count + 1;
        _ListaEst.add(_Inicial);
        _ListaEst.add(_Final);
        Transicion tran = new Transicion(_Inicial, _Final, Simbolo);
        _ListaTran.add(tran);         
        _ListaSimbolos.add(tran.getSimbolo());
    }
    
    public Automata(String Simbolo, Automata aut1)
    {
        _Inicial = new Estado(Count.count);
        Count.count = Count.count + 1;
        _Final = new Estado(Count.count);
        Count.count = Count.count + 1;
        _ListaEst.add(_Inicial);
        _ListaEst.add(_Final);
        Transicion tran1Kleene = new Transicion(aut1.getEstadoFinal(), aut1.getEstadoInicial(), "~");
        _ListaTran.add(tran1Kleene);
        Transicion tran2Kleene = new Transicion(_Inicial,_Final,"~");
        _ListaTran.add(tran2Kleene);
        Transicion tran1 = new Transicion(_Inicial, aut1.getEstadoInicial(), "~");
        _ListaTran.add(tran1);
        Transicion tran2 = new Transicion(aut1.getEstadoFinal(), _Final, "~");
        _ListaTran.add(tran2);
        for (int i = 0; i < aut1.getListaTran().size(); i++) 
        {
            _ListaTran.add((Transicion) aut1.getListaTran().get(i));
        }
        for (int i = 0; i < aut1.getListaEst().size(); i++)
        {
            _ListaEst.add((Estado) aut1.getListaEst().get(i));
        }
        for (int i = 0; i < aut1.getListaSimbolos().size(); i++)
        {
            _ListaSimbolos.add((String)aut1.getListaSimbolos().get(i));
        }
        
        
    }
    
    public Automata(String Simbolo,Automata aut1,Automata aut2)
    {
        if(Simbolo.equals("|"))
        {
            _Inicial = new Estado(Count.count);
            Count.count = Count.count + 1;
            _Final = new Estado(Count.count);
            Count.count = Count.count + 1;
            _ListaEst.add(_Inicial);
            _ListaEst.add(_Final);
            Transicion tran1 = new Transicion(_Inicial, aut1.getEstadoInicial(), "~");
            _ListaTran.add(tran1);
            Transicion tran2 = new Transicion(_Inicial, aut2.getEstadoInicial(), "~");
            _ListaTran.add(tran2);
            Transicion tran3 = new Transicion(aut1.getEstadoFinal(),_Final, "~");
            _ListaTran.add(tran3);
            Transicion tran4 = new Transicion(aut2.getEstadoFinal(),_Final, "~");
            _ListaTran.add(tran4);
            for (int i = 0; i < aut1.getListaTran().size(); i++) 
            {
                _ListaTran.add((Transicion) aut1.getListaTran().get(i));
            }
            for (int i = 0; i < aut1.getListaEst().size(); i++)
            {
                _ListaEst.add((Estado) aut1.getListaEst().get(i));
            }
            for (int i = 0; i < aut2.getListaTran().size(); i++) 
            {
                _ListaTran.add((Transicion) aut2.getListaTran().get(i));
            }
            for (int i = 0; i < aut2.getListaEst().size(); i++)
            {
                _ListaEst.add((Estado) aut2.getListaEst().get(i));
            }
            for (int i = 0; i < aut1.getListaSimbolos().size(); i++)
            {
                _ListaSimbolos.add((String)aut1.getListaSimbolos().get(i));
            }
            for (int i = 0; i < aut2.getListaSimbolos().size(); i++)
            {
                _ListaSimbolos.add((String)aut2.getListaSimbolos().get(i));
            }
            
        }
        if(Simbolo.equals("."))
        {
            _Inicial = aut2.getEstadoInicial();
            _Final = aut1.getEstadoFinal();
            _ListaEst.add(_Inicial);
            _ListaEst.add(_Final);
            Transicion tran = new Transicion(aut2.getEstadoFinal(), aut1.getEstadoInicial(), "~");
            _ListaTran.add(tran);
            for (int i = 0; i < aut1.getListaTran().size(); i++) 
            {
                _ListaTran.add((Transicion) aut1.getListaTran().get(i));
            }
            for (int i = 0; i < aut1.getListaEst().size(); i++)
            {
                _ListaEst.add((Estado) aut1.getListaEst().get(i));
            }
            for (int i = 0; i < aut2.getListaTran().size(); i++) 
            {
                _ListaTran.add((Transicion) aut2.getListaTran().get(i));
            }
            for (int i = 0; i < aut2.getListaEst().size(); i++)
            {
                _ListaEst.add((Estado) aut2.getListaEst().get(i));
            }
            for (int i = 0; i < aut1.getListaSimbolos().size(); i++)
            {
                _ListaSimbolos.add((String)aut1.getListaSimbolos().get(i));
            }
            for (int i = 0; i < aut2.getListaSimbolos().size(); i++)
            {
                _ListaSimbolos.add((String)aut2.getListaSimbolos().get(i));
            }
            
        }
    }
    
    public void ToString()
    {
        Estado[] _ArrayEst = new Estado[_ListaEst.size()];
        _ArrayEst = (Estado[]) _ListaEst.toArray(_ArrayEst);
        Transicion[] _ArrayTran = new Transicion[_ListaTran.size()];
        _ArrayTran = (Transicion[]) _ListaTran.toArray(_ArrayTran);
        String Estados = "ESTADOS: ";
        ArrayList SymFinal = new ArrayList();
        for(int i = 0; i < _ListaEst.size();i++)
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
        System.out.println(Estados);
        System.out.println(Simbolos);
        System.out.println("INICIO: " + String.valueOf(_Inicial.getId()));
        System.out.println("ACEPTACION: " + String.valueOf(_Final.getId()));
        System.out.println(Transiciones);
        
    }
    
    public void ToTxtFile() throws FileNotFoundException, UnsupportedEncodingException
    {
        PrintWriter writer;
        writer= new PrintWriter("AFN.txt", "UTF-8");
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
        writer.println("ACEPTACION: " + String.valueOf(_Final.getId()));
        writer.println(Transiciones);
        writer.close();
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
            if(_Inicial.equals(s))
            {
                shape = "square";
            }
            if(_Final.equals(s))
            {
                shape = "doublecircle";
            }
            writer.println(s.getId() + " [ shape=" + shape + "]");
        }
        for (Transicion tran : _ListaTran)
        {
            
            writer.println(tran.getAnterior().getId() + "->" + tran.getPosterior().getId() + "[label=\"" + tran.getSimbolo() + "\"]");
        }
        writer.println("}");
        writer.close();
        Runtime rt = Runtime.getRuntime();
        
        Process pr = rt.exec("C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot -Tpng C:\\Users\\Pablo\\Documents\\NetBeansProjects\\ThompsonConstruction\\" + fileName + ".dot -o C:\\Users\\Pablo\\Documents\\NetBeansProjects\\ThompsonConstruction\\" + fileName + ".png");
    }
    
    public String getEstadosFinal()
    {
         Estado[] _ArrayEst = new Estado[_ListaEst.size()];
        _ArrayEst = (Estado[]) _ListaEst.toArray(_ArrayEst);
        String Estados = "";
        for(int i = 0; i < _ListaEst.size();i++)
        {
            Estados = Estados  + String.valueOf(_ArrayEst[i].getId()) + ", ";
        }
        return Estados;
    }
    
    public String getSimbolosFinal()
    {
        Transicion[] _ArrayTran = new Transicion[_ListaTran.size()];
        _ArrayTran = (Transicion[]) _ListaTran.toArray(_ArrayTran);
        ArrayList SymFinal = new ArrayList();
        String Simbolos = "";
         for(int i = 0; i < _ListaTran.size();i++)
        {
            if (SymFinal.contains(_ArrayTran[i].getSimbolo()) == false)
            {
                SymFinal.add(_ArrayTran[i].getSimbolo());
                Simbolos = Simbolos + _ArrayTran[i].getSimbolo() + ", ";
            }
        }
        return Simbolos;
    }
    
    public String getInicialFinal()
        {
            String Inicial;
            Inicial = "INICIO: " + String.valueOf(_Inicial.getId());
            return Inicial;
        }
    public String getAceptacionFinal()
    {
        String Final;
        Final = "FINAL: " + String.valueOf(_Final.getId());
        return Final;
    }
    public String getTransicionesFinales()
    {
        Transicion[] _ArrayTran = new Transicion[_ListaTran.size()];
        _ArrayTran = (Transicion[]) _ListaTran.toArray(_ArrayTran);
        String Transiciones = "TRANSICIONES: ";
        for(int i = 0; i < _ListaTran.size();i++ )
        {
            Transiciones = Transiciones + ", " + _ArrayTran[i].toString();
        }
        
        return Transiciones;
    }
    public ArrayList<Estado> eClosureSet(ArrayList<Estado> state)
    {
        ArrayList<Estado> Final = new ArrayList<Estado>();
        for(int i=0; i<state.size(); i++)
        {
            Estado aClosurear = state.get(i);
            Final.addAll(eClosure(aClosurear));
        }
        Final = new ArrayList<Estado>(new LinkedHashSet(Final));
        
        return Final;
    }
    
    public ArrayList<Estado> eClosure(Estado state)
    {
        ArrayList<Estado> eClosureados = new ArrayList<Estado>();
        
        eClosureados.add(state);
        System.out.println("Se agrego " + state.getId() + " al e-Closure");
        for(int i=0; i<eClosureados.size(); i++)
        {
            Estado e = eClosureados.get(i);
            for(Transicion tran : _ListaTran)
            {
                if(tran.getAnterior().equals(e))
                {
                    if(tran.getSimbolo().equals("~"))
                    {
                        if(eClosureados.contains(tran.getPosterior()) == false)
                        {
                            eClosureados.add(tran.getPosterior());
                            System.out.println("Se agrego " + state.getId() + " al e-Closure");
                        }
                    }
                }
            }
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
    
    public AFD toAFD()
    {
        Count.count = 0;
        ArrayList<ArrayList<Estado>> sets = new ArrayList<ArrayList<Estado>>();
        ArrayList<Estado> AFDstates = new ArrayList<Estado>();
        ArrayList<Transicion> AFDtrans = new ArrayList<Transicion>();
        ArrayList<Estado> AFDaccept = new ArrayList<Estado>();
        
        sets.add(eClosure(_Inicial));
        String inicio = "";
        for(Estado e : eClosure(_Inicial))
        {
            inicio = inicio + " ";
        }
        System.out.println(inicio);
        Estado nuevo = new Estado(Count.count);
        Count.count = Count.count+1;
       
        if(eClosure(_Inicial).contains(_Final))
        {
            AFDaccept.add(nuevo);
        }
        AFDstates.add(nuevo);
        int i = 0;
        while(i<sets.size())
        {
            Estado origen = AFDstates.get(i);
            for(int j=0; j<_ListaSimbolos.size();j++)
            {
                
                ArrayList prova = eClosureSet(Move(sets.get(i), (String)_ListaSimbolos.get(j)));
                if(prova.isEmpty()== false)
                {
                    if(sets.contains(prova) == false)
                    {
                        sets.add(prova);
                        Estado novo = new Estado(Count.count);
                        Count.count = Count.count+1;
                        if(prova.contains(_Final))
                        {
                            AFDaccept.add(novo);
                        }
                        AFDstates.add(novo);
                        
                        Transicion tran = new Transicion(origen, novo, (String)_ListaSimbolos.get(j));
                        boolean Ans1 = true;
                        for( Transicion transition : AFDtrans)
                        {
                            if(tran.equals(transition) == true)
                            {
                                Ans1 = false;
                            }
                        }
                        if(Ans1 == true)
                        {
                            AFDtrans.add(tran);
                        }
                        
                    }
                    else
                    {
                        
                        Transicion tran = new Transicion(origen, AFDstates.get(sets.indexOf(prova)), (String)_ListaSimbolos.get(j));
                        boolean Ans = true;
                        for( Transicion transition : AFDtrans)
                        {
                            if(tran.equals(transition) == true)
                            {
                                Ans = false;
                            }
                        }
                        if(Ans == true)
                        {
                            AFDtrans.add(tran);
                        }
                        
                        
                    }
                }
            }
            i++;
        }
       
        
        
        AFD AutFinalD = new AFD(AFDtrans, AFDstates, nuevo, AFDaccept);
        return AutFinalD;
    }
    
    public Boolean simulacion(String cadena)
    {
        String[] Cadena = cadena.split(" ");
        ArrayList<String> ArrayCadena = new ArrayList<String>(Arrays.asList(Cadena));
        System.out.println(ArrayCadena.size());
        ArrayList<Estado> conjuntoFinal = new ArrayList<Estado>();
        System.out.println(ArrayCadena);
       
        conjuntoFinal = eClosure(_Inicial);
        String res = "";
        for(Estado estado : conjuntoFinal)
        {    
            res = res + ", " + String.valueOf(estado.getId()); 
        }
        System.out.println(res);
        for(int i=0; i<ArrayCadena.size(); i++)
        {
            System.out.println(ArrayCadena.get(i));
            
            conjuntoFinal = eClosureSet(Move(conjuntoFinal, ArrayCadena.get(i)));
            res = "";
            for(Estado estado : conjuntoFinal)
            {    
                res = res + ", " + String.valueOf(estado.getId()); 
            }
            System.out.println(res);
        }
        if(conjuntoFinal.contains(_Final))
        {
            return true;
        }
        else
        {
            System.out.println("Finishee");
            return false;
            
        }
        
    }
}
