import java.io.*;

/**
 * @author Utkarsh Goel
 * @version 1.0.1 (1/7/2012)
 *	This class provides the reading functionality for the parser
 */

public class FileRead
{
	BufferedReader br;
	
	/*
	 * Constructor - Initialises the buffered reader and attempts to open the file
	 * @param - filename
	 */
	public FileRead(String fileName)
	{
		try
		{
			br = new BufferedReader(new FileReader(fileName));	// set up a buffered input stream 
		}
		catch (IOException e)	// Catches any error conditions
		{
			System.err.println("The input file does not exist. Please check that the file\nis in correct location and the filename matches with the one provided.");
			System.exit(1);
		}
	}
	
	/*
	 * Reads single line at a time from the file. Also implements the functionality to ignore blank lines
	 * @return - returns a string with the contents of the file or returns "EOF" for end of file
	 */
	public String read() throws IOException
	{
		if (! br.ready())
		{
			br.close();	//closes the buffered reader
			return "EOF";
		}
		boolean blank = true;
		String line = "";
		
		//loop for ignoring blank lines
		while(blank)
		{
			line = br.readLine();
			if (!( line !=null && line.trim().compareTo("") == 0 ))
			{
				blank = false;
			}
		}
		return line;	
	}
}