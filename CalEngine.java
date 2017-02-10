import java.util.Vector;
import java.util.Stack;

public class CalEngine
{
	private String exp;
	
	public CalEngine()
	{
		exp="";
	}
	
	public CalEngine(String x)
	{
		exp=x;
	}
	
	private String addComma()
	{
		Vector<String> v=new Vector<String>(30);
		String temp="";
		
		for(int i=0;i<exp.length();i++)
		{
			Character t=exp.charAt(i);
			if(i==0&&t=='-')
			{
				temp=temp+t;
				continue;
			}
			
			if(t.isDigit(t)||t=='.')
			{
				temp=temp+t;
			}
			else
			{
				if(t=='-'&&(exp.charAt(i-1)=='+'||exp.charAt(i-1)=='-'||exp.charAt(i-1)=='*'||exp.charAt(i-1)=='/'||exp.charAt(i-1)=='('))
				{
					temp=temp+t;
					continue;
				}
				if(temp.length()!=0)
				{
					v.addElement(temp);
					temp="";
					v.addElement(t.toString());
				}
				else
					v.addElement(t.toString());
			}
				
		}
		if(temp.length()!=0)
			v.addElement(temp);		

		temp=v.toString();
		temp=temp.substring(1,temp.length()-1);
		return temp;
	}
	
	private int weight(char a)
	{
		int z=0;
		switch(a)
		{
			case '+':
			case '-':
				z=1;
				break;
			case '*':
			case '/':
				z=2;
				break;
			case '^':
				z=3;
				break;
		}
		return z;
	}
	
	private boolean change(char a,char b)
	{
		int x=weight(a);
		int y=weight(b);
		
		if(x==y||x>y)
			return true;
		else
			return false;	
	}
	
	public String toPostfixNum()
	{
		Stack<Character> s=new Stack<Character>();
		String post="";
		String q=addComma()+','+')';
		s.push('(');
		
		for(int i=0;i<q.length();i++)
		{
			Character t=q.charAt(i);
			Character t1='a';
			if(i<q.length()-1)
				t1=q.charAt(i+1);
			
			
			if(t=='-'&&(t1!='+'&&t1!='-'&&t1!='*'&&t1!='/'&&t1!='('&&t1!=')'&&t1!=','))
				post=post+t;
				
			else if(t!='+'&&t!='-'&&t!='*'&&t!='/'&&t!='^'&&t!='('&&t!=')')
			{
				post=post+t;
			}	
			else if(t==')')
			{
				while(s.peek()!='(')
				{
					post=post+","+s.pop();
				}
				s.pop();
			}
			else if(t=='(')
			{
				s.push(t);
			}
			else
			{
				if(s.peek()=='(')
					s.push(t);
				else
				{
					boolean b=change(s.peek(),t);
					if(b==true)
					{
						post=post+","+s.pop();
						s.push(t);
					}
					else
						s.push(t);
				}
			}
		}
		return post;
	
	}
	
	public double eval(String s)
	{
		String ar[]=s.split(",");
		double a=0,b=0;
		Stack<Double> st=new Stack<Double>();
		
		
		for(int i=0;i<ar.length;i++)
			ar[i]=ar[i].trim();
									
		for(int i=0;i<ar.length;i++)
		{
			if(ar[i].length()==0)
				continue;
				
			Character t=ar[i].charAt(0);
			
			if(t=='-'&&ar[i].length()>1)
			{
				double d=Double.parseDouble(ar[i]);
				st.push(d);
			}
			
			else if(t.isDigit(t)||t=='.')
			{
				double d=Double.parseDouble(ar[i]);
				st.push(d);
			}
			else
			{
				b=st.pop();
				a=st.pop();
				switch(t)
				{
					case '+':
						st.push(a+b);
						break;
					case '-':
						st.push(a-b);
						break;
					case '*':
						st.push(a*b);
						break;
					case '/':
						st.push(a/b);
						break;
					case '^':
						st.push(Math.pow(a,b));
						break;
				}
			}
		}
		return(st.pop()); 
				
	}
	
	
	
}