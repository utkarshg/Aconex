import java.io.*;

public class FileWrite
{
	private PrintWriter pw; 
	
	public FileWrite(String fileName)
	{														
		try
		{
			pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
			pw.println("<gedcom>");
		
		}
		catch (IOException e)
		{
			System.out.println("Got an IOException: " + e.getMessage());
		}
	}
	
	public void writeOpenTag(String[] values)
	{
		int level = Integer.parseInt(values[0]);
		String tag = values[1].toLowerCase();

		addIndent(level+1);
		
		pw.println("<"+ tag+">");
	}
	
	public void writeTags(String[] values)
	{
		int level = Integer.parseInt(values[0]);
		String tag = values[1].toLowerCase();
		String data = values[2];
		addIndent(level+1);
		pw.println("<"+ tag+">"+data+"</"+ tag+">");
	}
	
	public void writeNoValueTag(String[] values)
	{
		int level = Integer.parseInt(values[0]);
		String tag = values[1].toLowerCase();
		addIndent(level+1);
		pw.println("<"+ tag+"></"+ tag+">");
	}
	
	public void writeIndiTags(String[] values)
	{
		int level = Integer.parseInt(values[0]);
		String id = values[1];
		String tag = values[2].toLowerCase();
		addIndent(level+1);
		pw.println("<"+ tag+" id=\""+id+"\">");
	}
	
	public void writeValueTags(String[] values)
	{
		int level = Integer.parseInt(values[0]);
		String tag = values[1].toLowerCase();
		String value = values[2];
		addIndent(level+1);
		pw.println("<"+ tag+" value=\""+value+"\">");
	}
	
	public void writeCloseTags(String val, int size)
	{
		addIndent(size-1);
		pw.println("</"+val+ ">");
	}
	
	public void addIndent(int val)
	{
		for(int i=0; i<val; i++)
		{
			pw.print("	");
		}
	}
	
	public void check()
	{
		pw.println("check");
	}
	
	public void close() throws IOException
    {
		pw.print("</gedcom>");
		pw.close();
    }
}