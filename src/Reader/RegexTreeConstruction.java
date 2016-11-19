

package Reader;

//import static Reader.Nivel.nivel;
//import static Reader.PosCounter.PosCount;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class RegexTreeConstruction 
{
  
    public Leaf _root = null;
    
    String[] _operadores = {"Ø", "Ç"};
    ArrayList<String> Simbolos = new ArrayList<String>();
    int PosCount = 0;
    int nivel = 0;
    public ArrayList Automatas =  new ArrayList();
    
    
    
    
    public RegexTreeConstruction()
    {
        nivel = 0;
    }
    
    public Automata getAutomataFinal()
    {
        Thompson(_root);
        return (Automata)Automatas.get(Automatas.size() -1);
    }
    public void add(String data, Leaf n)
    {
        
        if(_root == null)
        {
            _root = new Leaf(data);
            //System.out.println("Raiz: " + data + " Nivel: " + nivel);
            return;
        }
        
       
        //n = _root;
        //nivel = 1;
        if(n != null)
        {
            if(n.getRight() == null)
            {
                if(data.equals("Ø") || data.equals("Ç"))
                {
                    n.setRight(new Leaf(data));
                    //System.out.println("Der: " + data + " Nivel: " + nivel);
                    return;
                }
                if(data.equals("º"))
                {
                    n.setRight(new Leaf(data));
                    //System.out.println("Der: " + data + " Nivel: " + nivel);
                    //System.out.println("Me movi a la derecha");
                    n = n.getRight();
                    nivel = nivel+1;
                    n.setLeft(new Leaf("@",true));
                    //System.out.println("Izq:@  Nivel: " + nivel);
                    return;
                }
                if(data.equals("#"))
                {
                    PosCount = PosCount + 1;
                    n.setRight(new Leaf(data,true, PosCount, true));
                    
                    //System.out.println("Der: " + data + " Nivel: " + nivel + " Posicion: " + PosCount);
                    return;
                }
                else
                {
                    PosCount = PosCount + 1;
                    n.setRight(new Leaf(data,true, PosCount));
                    Simbolos.add(data);
                    //System.out.println("Der: " + data + " Nivel: " + nivel + " Posicion: " + PosCount);
                    return;

        
                }
            }
            if(n.getRight().getChecked() == false)
            {
                nivel = nivel +1;
                //System.out.println("Me movi a la derecha");
                add(data, n.getRight());
                return;
            }
            if(n.getRight().getChecked() == true)
            {
                if(n.getLeft() == null)
                {
                    if(data.equals("Ø") || data.equals("Ç"))
                    {
                        n.setLeft(new Leaf(data));
                        //System.out.println("Izq: " + data + " Nivel: " + nivel);
                        return;
                    }
                    if(data.equals("º"))
                    {
                        n.setLeft(new Leaf(data));
                        //System.out.println("Izq: " + data + " Nivel: " + nivel);
                       // System.out.println("Me movi a la izq");
                        n = n.getLeft();
                        nivel = nivel+1;
                        n.setLeft(new Leaf("@",true));
                        //System.out.println("Izq:@  Nivel: " + nivel);
                        return;
                    }
                    if(data.equals("#"))
                    {
                        PosCount = PosCount + 1;
                        n.setRight(new Leaf(data,true, PosCount, true));
                        
                        //System.out.println("Der: " + data + " Nivel: " + nivel + " Posicion: " + PosCount);
                        return;
                    }
                    else
                    {
                        PosCount = PosCount + 1;
                        n.setLeft(new Leaf(data,true, PosCount));
                        Simbolos.add(data);
                        //System.out.println("Izq: " + data + " Nivel: " + nivel + " Posicion: " + PosCount);
                        return;
                    }
                    
                }
                if(n.getLeft().getChecked() == false)
                {
                    nivel = nivel+1;
                    //System.out.println("Me movi a la izquierda");
                    add(data, n.getLeft());
                    return;
                }
                if(n.getLeft().getChecked() == true)
                {
                    n.setChecked(Boolean.TRUE);
                    nivel = nivel+1;
                    //System.out.println("Me movi para arriba");
                    add(data, n.getParent());
                    return;
                    
                }
            }
          
        }
        
    }
    
    public void add(String data)
    {
        //System.out.println("Agregando al arbol : " + data);
        if(_root == null)
        {
            _root = new Leaf(data);
            //System.out.println("Raiz: " + data + " Nivel: " + nivel);
            return;
        }
        
        //nivel = 1;
        Leaf n = _root;
        if(n != null)
        {
            if(n.getRight() == null)
            {
                if(data.equals("Ø") || data.equals("Ç"))
                {
                    n.setRight(new Leaf(data));
                    //System.out.println("Der: " + data + " Nivel: " + nivel);
                    return;
                }
                if(data.equals("º"))
                {
                    n.setRight(new Leaf(data));
                    //System.out.println("Der: " + data + " Nivel: " + nivel);
                    //System.out.println("Me movi a la derecha");
                    n = n.getRight();
                    nivel = nivel+1;
                    n.setLeft(new Leaf("@",true));
                    //System.out.println("Izq:@  Nivel: " + nivel);
                    return;
                }
                if(data.equals("#"))
                {
                    PosCount = PosCount + 1;
                    n.setRight(new Leaf(data,true, PosCount, true));
                   
                    //System.out.println("Der: " + data + " Nivel: " + nivel + " Posicion: " + PosCount);
                    return;
                }
                else
                {
                    PosCount = PosCount + 1;
                    n.setRight(new Leaf(data,true, PosCount));
                    Simbolos.add(data);
                    //System.out.println("Der: " + data + " Nivel: " + nivel + " Posicion: " + PosCount);
                    return;

        
                }
            }
            if(n.getRight().getChecked() == false)
            {
                nivel = nivel + 1;
                //System.out.println("Me movi a la derecha");
                add(data, n.getRight());
                return;
            }
            if(n.getRight().getChecked() == true)
            {
                if(n.getLeft() == null)
                {
                    if(data.equals("Ø") || data.equals("Ç"))
                    {
                        n.setLeft(new Leaf(data));
                        //System.out.println("Izq: " + data + " Nivel: " + nivel);
                        return;
                    }
                    if(data.equals("º"))
                    {
                        n.setLeft(new Leaf(data));
                        //System.out.println("Izq: " + data + " Nivel: " + nivel);
                        //System.out.println("Me movi a la izquierda");
                        n = n.getLeft();
                        nivel = nivel+1;
                        n.setLeft(new Leaf("@", true));
                        //System.out.println("Izq:@  Nivel: " + nivel);
                        return;
                    }
                    if(data.equals("#"))
                    {
                        PosCount = PosCount + 1;
                        n.setRight(new Leaf(data,true, PosCount, true));
                       
                        //System.out.println("Der: " + data + " Nivel: " + nivel + " Posicion: " + PosCount);
                        return;
                    }
                    else
                    {
                        PosCount = PosCount + 1;
                        n.setLeft(new Leaf(data,true, PosCount));
                        Simbolos.add(data);
                        //System.out.println("Izq: " + data + " Nivel: " + nivel + " Posicion: " + PosCount);
                        return;
                    }
                    
                    
                }
                if(n.getLeft().getChecked() == false)
                {
                    nivel = nivel+1;
                    //System.out.println("Me movi a la izquierda");
                    add(data, n.getLeft());
                    return;
                }
                if(n.getLeft().getChecked() == true)
                {
                    n.setChecked(Boolean.TRUE);
                    nivel = nivel+1;
                    //System.out.println("Me movi para arriba");
                    add(data, n.getParent());
                    return;
                    
                }
            }   
          
        }
        
    }
    
    public void postOrder(Leaf root)
    {
        if(root == null) return ;
        
        postOrder(root.getLeft());
        postOrder(root.getRight());
        //System.out.println("Simbolo: " + root.data);
        //System.out.println("Nullable: " +root.Nullable());
        //System.out.println("FirstPos: ");
        for(Leaf h  : root.FirstPos())
        {
            //System.out.println(h.position);
        }
        //System.out.println("LastPos: ");
        for(Leaf h  : root.LastPos())
        {
            //System.out.println(h.position);
        }
        root.FollowPos();
        
    }
    public AFD DirectConstruction()
    {
        //System.out.println("----------------------------------------------CONSTRUCCION--DIRECTA");
        Count.count = 0;
        Simbolos = new ArrayList<String>(new LinkedHashSet<String>(Simbolos));
        ArrayList<ArrayList<Leaf>> DirectSets = new ArrayList<ArrayList<Leaf>>();
        ArrayList<Estado> DirectStates = new ArrayList<Estado>();
        ArrayList<Estado> Finales = new ArrayList<Estado>();
        ArrayList<Transicion> DirectTrans = new ArrayList<Transicion>();
        ArrayList<Leaf> T1 = _root.FirstPos();
        
        
        
        DirectSets.add(T1);
        Estado state = new Estado(Count.count);
        Estado inicial = state;
        for( Leaf l : T1)
        {
            if(l._IsFinal == true)
            {
                Finales.add(state);
               // System.out.println("Es estado final");
            }
        }
        
        DirectStates.add(state);
        
        Count.count = Count.count + 1;
        
        ArrayList<ArrayList<Leaf>> temp = new ArrayList<ArrayList<Leaf>>();
        temp.add(T1);
        ArrayList<Leaf> T2 = new ArrayList<Leaf>();
        
        while(temp.isEmpty() == false)
        {
            T1 = temp.remove(0);
            
            String T1String = "";
            for(Leaf ho : T1)
            {
                T1String = T1String + ho.position;
            }
            //System.out.println("T1: " + T1String);
            
            String symbol = "";
            for(String s : Simbolos)
            {
                symbol = symbol + " " + s;
            }
            //System.out.println("Simbolos: " + symbol);
            for( String s : Simbolos)
            {
                T2 = new ArrayList<Leaf>();
                for (Leaf h : T1) 
                {   
                    //System.out.println(h.data + " igual a? " + s);
                    if(h.data.equals(s))
                    {
                        T2.addAll(h._FollowPos);
                    }
                }
                String T2String = "";
                for(Leaf hoj : T2)
                {
                    T2String = T2String + hoj.position;
                }
                //System.out.println("T2: " + T2String);
                T2 = new ArrayList<Leaf>(new LinkedHashSet<Leaf>(T2));
                if(T2.isEmpty() == false)
                {
                    if(DirectSets.contains(T2) == false)
                    {

                        DirectSets.add(T2);
                        temp.add(T2);
                        Estado destino = new Estado(Count.count);
                        //System.out.println("Se creo Estado " + Count.count);
                        for( Leaf l : T2)
                        {
                        if(l._IsFinal == true)
                        {
                            Finales.add(destino);
                            //System.out.println("Es estado final");
                        }
                        }
                        Count.count = Count.count + 1;
                        DirectStates.add(destino);
                        Estado origen = DirectStates.get(DirectSets.indexOf(T1));
                        Transicion tran  = new Transicion(origen, destino, s);
                        //System.out.println("Se creo la transicion: " + tran.toString());
                        DirectTrans.add(tran);

                    }
                    else
                    {

                        Estado destino = DirectStates.get(DirectSets.indexOf(T2));
                        Estado origen = DirectStates.get(DirectSets.indexOf(T1));
                        Transicion tran  = new Transicion(origen, destino, s);
                        //System.out.println("Se creo la transicion: " + tran.toString());
                        DirectTrans.add(tran);
                    }
                }
            }
            
        }
        AFD FinalAFD = new AFD(DirectTrans, DirectStates, inicial, Finales);
        return FinalAFD;
        
        
            
    }
    
    public void Thompson(Leaf root)
    {
        if(root == null) return;
        Thompson(root.getLeft());
        Thompson(root.getRight());
        //System.out.println("Construyendo Automata: " + root.getData());
        if (root.getData().equals("@")){return;}
        if (root.getData().equals("Ø") || root.getData().equals("Ç") || root.getData().equals("º"))
        {
            if (root.getData().equals("º"))
            {
                Automata aut = new Automata(root.getData(), (Automata)Automatas.get(Automatas.size() - 1));
                Automatas.remove(Automatas.size()-1);
                //aut.ToString();
                Automatas.add(aut);
            }
            if (root.getData().equals("Ø") || root.getData().equals("Ç"))
            {
                Automata aut = new Automata(root.getData(), (Automata)Automatas.get(Automatas.size() - 1), (Automata)Automatas.get(Automatas.size() - 2));
                Automatas.remove(Automatas.size()-1);
                Automatas.remove(Automatas.size()-1);
               //aut.ToString();
                Automatas.add(aut);
            }              
        }
        else
            {
                Automata aut = new Automata(root.getData());
                //aut.ToString();
                Automatas.add(aut);
            }
    }
    
    
    
}
