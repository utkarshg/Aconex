import java.io.*;

/**
 * @author Utkarsh Goel
 * @version 1.0.1 (1/7/2012)
 *	This class provides the writing functionality for the parser
 */

public class FileWrite
{
	private PrintWriter pw; 
	
	/*
	 * Constructor - Initialises the buffered writer and opens the file if exists or creates one
	 * @param - filename
	 */
	public FileWrite(String fileName)
	{														
		try
		{
			pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
			pw.println("<gedcom>");
		
		}
		catch (IOException e) // Catches any error conditions
		{
			System.err.println("Result directory does not exist.\nPlease create a result directory in the root folder." );
			System.exit(1);
		}
	}
	
	/*
	 * Method to write tags for which child nodes exist and hence doesn't close them
	 * @param - Array of values to write to the file
	 */
	public void writeOpenTag(String[] values)
	{
		int level = getLevel(values[0]);
		String tag = values[1].toLowerCase();
		addIndent(level+1);
		
		pw.println("<"+ tag+">");
	}
	
	/*
	 * Method to write tags for childless nodes.
	 * @param - Array of values to write to the file
	 */
	public void writeTags(String[] values)
	{
		int level = getLevel(values[0]);
		String tag = values[1].toLowerCase();
		String data = values[2];
		addIndent(level+1);
		pw.println("<"+ tag+">"+data+"</"+ tag+">");
	}
	
	/*
	 * Method to write tags for childless nodes which contain no value
	 * @param - Array of values to write to the file
	 */
	public void writeNoValueTag(String[] values)
	{
		int level = getLevel(values[0]);
		String tag = values[1].toLowerCase();
		addIndent(level+1);
		pw.println("<"+ tag+"></"+ tag+">");
	}
	
	/*
	 * Method to start new tags for an individual
	 * @param - Array of values to write to the file
	 */
	public void writeIndiTags(String[] values)
	{
		int level = getLevel(values[0]);
		String id = values[1];
		String tag = values[2].toLowerCase();
		addIndent(level+1);
		pw.println("<"+ tag+" id=\""+id+"\">");
	}
	
	
	/*
	 * Method to write tags for a node that has child nodes as well has an associated value
	 * @param - Array of values to write to the file
	 */
	public void writeValueTags(String[] values)
	{
		int level = getLevel(values[0]);
		String tag = values[1].toLowerCase();
		String value = values[2];
		addIndent(level+1);
		pw.println("<"+ tag+" value=\""+value+"\">");
	}
	
	/*
	 * Method to close off the tags after all of the child nodes have been expanded
	 * @param - Array of values to write to the file
	 */
	public void writeCloseTags(String val, int size)
	{
		addIndent(size-1);
		pw.println("</"+val+ ">");
	}
	
	/*
	 * Adds indentation depending on the depth
	 * @param - Integer value for depth 
	 */
	public void addIndent(int val)
	{
		for(int i=0; i<val; i++)
		{
			pw.print("	");
		}
	}
	
	/*
	 * Converts the level to int
	 * @param - String value for 
	 * @return - Integer value of level
	 */
	public int getLevel(String lvl)
	{
		try
		{
			int level = Integer.parseInt(lvl);
			return level;
		}
		catch (NumberFormatException e)
		{
			System.err.println("Input file seems corrupt or is missing data.\nParsing has been terminated");
			System.exit(1);
		}
		return -1;
	}
	
	/*
	 * Closes the gedcom tag and finally closes the output stream.
	 */
	public void close() throws IOException
    {
		pw.print("</gedcom>");
		pw.close();
    }
}