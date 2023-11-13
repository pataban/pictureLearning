package package_Data;

import java.io.Serializable;

public class Pab<Ta, Tb> implements Serializable{
    private static final long serialVersionUID = 1L;
	Ta a;	Tb b;
	public Pab() {}	
	public Pab(Ta a, Tb b) 		{this.a = a;	this.b = b;	}
	public Pab(Pab<Ta, Tb> p)	{this.a = p.getA();	this.b = p.getB();	}
	public Ta getA() 			{return a;}
	public Tb getB() 			{return b;}
	public void setA(Ta a) 		{this.a = a;}
	public void setB(Tb b) 		{this.b = b;}
	public void set(Ta a,Tb b)	{this.a = a;	this.b = b;	}
	public String toString()	{return a+" \t"+b;	}
	public void prt()			{System.out.println(this);	}
}
