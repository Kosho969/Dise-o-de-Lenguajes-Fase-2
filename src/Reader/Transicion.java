

package Reader;


public class Transicion 
{
    private Estado _Anterior;
    private Estado _Posterior;
    private String _Simbolo;
    
    public Transicion (Estado _Anterior, Estado _Posterior, String _Simbolo)
    {
        this._Anterior = _Anterior;
        this._Posterior = _Posterior;
        this._Simbolo = _Simbolo;        
    }
    
    public  boolean equals(Transicion tran)
    {
        return (this._Anterior.equals(tran.getAnterior()) && this._Posterior.equals(tran.getPosterior()) && this._Simbolo.equals(tran.getSimbolo()));
            
    }
    
    public void setAnterior(Estado _Anterior)
    {
        this._Anterior = _Anterior;
    }
    
    public Estado getAnterior()
    {
        return _Anterior;
    }
    
    public void setPosterior(Estado _Posterior)
    {
        this._Posterior = _Posterior;
    }
    
    public Estado getPosterior()
    {
        return _Posterior;
    }
    
    public void setSimbolo(String _Simbolo)
    {
        this._Simbolo = _Simbolo;
    }
    
    public String getSimbolo()
    {
        return _Simbolo;
    }
    @Override
    public  String toString()
    {
        String text;
        text = "{ " + String.valueOf(_Anterior.getId()) + ", " + String.valueOf(_Posterior.getId()) + ", " + _Simbolo + "}";
        return text;
    }
}
