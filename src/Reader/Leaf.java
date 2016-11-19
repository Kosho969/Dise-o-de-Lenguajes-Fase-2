

package Reader;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public class Leaf {
    String data;
    int position;
    Leaf _Right;
    Leaf _Left;
    Leaf _Parent;
    Boolean _Checked = false;
    Boolean _IsFinal = false;
    ArrayList<Leaf> _FollowPos = new ArrayList<Leaf>();
    //Constructor
    public Leaf(String data)
    {
        this.data = data;
    }
    public Leaf(String data, Boolean _Checked)
    {
        this.data = data;
        this._Checked = _Checked;
    }
    public Leaf(String data, Boolean _Checked, int position, Boolean _IsFinal)
    {
        this.data = data;
        this._IsFinal = _IsFinal;
        this._Checked = _Checked;
        this.position = position;
    }
    public Leaf(String data, Boolean _Checked, int position)
    {
        this.data = data;
        this._Checked = _Checked;
        this.position = position;
    }
    public Boolean getChecked()
    {
        return _Checked;
    }
    public void setChecked(Boolean _Checked)
    {
        this._Checked = _Checked;
    }
    
    public void CheckIt()
    {
        if((_Right.getChecked() == true) && (_Left.getChecked() == true))
        {
            _Checked = true;
            
        }
        else
        {
            
        }
    }
    
    public String getData()
    {
        return data;
    }
    
    public void setData(String data)
    {
        this.data = data;
    }
    
    public Leaf getParent()
    {
        return _Parent;
    }
    
    public Leaf getLeft()
    {
        return _Left;
    }
    
    public Leaf getRight()
    {
        return _Right;
    }
    
    public void setLeft(Leaf child)
    {
        for (Leaf n = this; n!= null; n = n._Parent)
        {
            if (n == child)
            {
                throw new IllegalArgumentException();
            }
        }
        
        Leaf childNode = (Leaf) child;
        
        if (this._Left != null)
        {
            _Left._Parent = null;
        }
        if (childNode != null)
        {
            childNode.removeFromParent();
            childNode._Parent = this;
        }
        this._Left = childNode;
    }
    
    public void setRight(Leaf child)
    {
        for (Leaf n = this; n != null; n = n._Parent)
        {
            if(n == child)
            {
                throw new IllegalArgumentException();
            }
        }
        Leaf childNode = (Leaf)child;
        if (_Right != null)
        {
            _Right._Parent = null;
        }
        if (childNode != null)
        {
            childNode.removeFromParent();
            childNode._Parent = this;
        }
        this._Right = childNode;
    }
    
    public void removeFromParent()
    {
        if(_Parent != null)
        {
            if(_Parent._Left == this)
            {
                _Parent._Left = null;
            }
            else if (_Parent._Right == this)
            {
                _Parent._Right = null;
            }
            this._Parent = null;
        }
    }
    public Boolean Nullable()
    {
        if(data.equals("~"))
        {
            return true;
        }
        if(position != 0)
        {
            return false;
        }
        if(data.equals("Ø"))
        {
            return (_Left.Nullable() || _Right.Nullable());
        }
        if(data.equals("Ç"))
        {
            return (_Left.Nullable() && _Right.Nullable());
        }
        if(data.equals("º"))
        {
            return true;
        }
        return false;
    }
    //ArrayList<Leaf> _FirstPos = new ArrayList<Leaf>();
    public ArrayList<Leaf> FirstPos()
    {
        ArrayList<Leaf> _FirstPos = new ArrayList<Leaf>();
        
        if(data.equals("~"))
        {
            return _FirstPos;
        }
        if(position != 0)
        {
            _FirstPos.add(this);
            return _FirstPos;
        }
        if(data.equals("Ø"))
        {
            _FirstPos.addAll(_Left.FirstPos());
            _FirstPos.addAll(_Right.FirstPos());
            _FirstPos = new ArrayList<Leaf>(new LinkedHashSet<Leaf>(_FirstPos));
            return _FirstPos;
        }
        if(data.equals("Ç"))
        {
            if(_Left.Nullable())
            {
                _FirstPos.addAll(_Left.FirstPos());
                _FirstPos.addAll(_Right.FirstPos());
                _FirstPos = new ArrayList<Leaf>(new LinkedHashSet<Leaf>(_FirstPos));
                return _FirstPos;
            }
            else
            {
                return _Left.FirstPos();
            }
        }
        if(data.equals("º"))
        {
            return _Right.FirstPos();
        }
        return _FirstPos;
    }
    //ArrayList<Leaf> _LastPos = new ArrayList<Leaf>();
    public ArrayList<Leaf> LastPos()
    {
        ArrayList<Leaf> _LastPos = new ArrayList<Leaf>();
        
        if(data.equals("~"))
        {
            return _LastPos;
        }
        if(position != 0)
        {
            _LastPos.add(this);
            return _LastPos;
        }
        if(data.equals("Ø"))
        {
            _LastPos.addAll(_Left.LastPos());
            _LastPos.addAll(_Right.LastPos());
            _LastPos = new ArrayList<Leaf>(new LinkedHashSet<Leaf>(_LastPos));
            return _LastPos;
        }
        if(data.equals("Ç"))
        {
            if(_Right.Nullable())
            {
                _LastPos.addAll(_Left.LastPos());
                _LastPos.addAll(_Right.LastPos());
                _LastPos = new ArrayList<Leaf>(new LinkedHashSet<Leaf>(_LastPos));
                return _LastPos;
            }
            else
            {
                return _Right.LastPos();
            }
        }
        if(data.equals("º"))
        {
            return _Right.LastPos();
        }
        return _LastPos;
    }
    //ArrayList<Leaf> _FollowPos = new ArrayList<Leaf>(); 
    public void FollowPos()
    {
       
        if(data.equals("Ç"))
        {
            for( Leaf hoja : _Left.LastPos())
            {
                hoja._FollowPos.addAll(_Right.FirstPos());
                for ( Leaf h : hoja._FollowPos)
                {
                    //System.out.println("FollowPos( " + hoja.position + " ) --> " + h.position);
                }
                
                
            }
        }
        if(data.equals("º"))
        {
            for(Leaf hoja : _Right.LastPos())
            {
                
                hoja._FollowPos.addAll(_Right.FirstPos());
                for ( Leaf h : hoja._FollowPos)
                {
                    //System.out.println("FollowPos( " + hoja.position + " ) --> " + h.position);
                }
            }
            return;
        }
    }
    
}
