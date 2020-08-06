package EstruturasDeDados;

import java.lang.reflect.Method;

public class Fila <X>
{
    private ListaSimplesDesordenada<X> elementos;

    public Fila ()
    {
        this.elementos = new ListaSimplesDesordenada<X> ();
    }

    public void guardeUmItem (X x) throws Exception
    {
        if (x==null)
            throw new Exception ("Falta o que guardar");

        this.elementos.insiraNoFim (x);
    }

    public X recupereUmItem () throws Exception
    {
        if (this.elementos.isVazia())
            throw new Exception ("Nada a recuperar");

        return this.elementos.getDoInicio();
    }

    public void removaUmItem () throws Exception
    {
        if (this.elementos.isVazia())
            throw new Exception ("Nada a remover");

        this.elementos.removaDoInicio();
    }

    public boolean isVazia ()
    {
        return this.elementos.isVazia();
    }

    public String tostring()
    {
        String ret = this.elementos.getQtd() + " elementos";

        try
		{
            ret += ", sendo o ultimo "+this.elementos.getDoInicio();
        }
        catch (Exception erro)
        {}

        return ret;
    }

    public boolean equals (Object obj)
    {
        if(this==obj)
            return true;

        if(obj==null)
            return false;

        if(this.getClass()!=obj.getClass())
            return false;

        Fila<X> fil = (Fila<X>) obj;

        if(this.elementos.equals (fil.elementos))
            return false;

        return true;
    }

    public int hashCode ()
    {
        int ret=666;

        ret = ret*7 + this.elementos.hashCode();

        if (ret<0)
            ret=-ret;

        return ret;
    }

    public Fila(Fila<X> modelo) throws Exception
    {
        if(modelo == null)
            throw new Exception("modelo ausente");

        this.elementos = new ListaSimplesDesordenada<X> (modelo.elementos);
    }

    public Object clone()
    {
        Fila<X> ret = null;

        try
        {
            ret  = new Fila(this);
        }
        catch(Exception erro)
        {}

        return ret;
    }
}
