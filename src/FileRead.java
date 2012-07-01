import java.io.*;

public class FileRead
{
	BufferedReader br;
	
	public FileRead(String fileName)
	{
		try
		{
			br = new BufferedReader(new FileReader(fileName));	// set up a buffered input stream 
		}
		catch (IOException e)	// Catches any error conditions
		{
			System.out.println("Got an IOException: " + e.getMessage());
		}
	}
	
	public String read() throws IOException
	{
		if (! br.ready())
		{
			br.close();	//closes the buffered reader
			return "EOF";
		}
		boolean blank = true;
		String line = "";
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