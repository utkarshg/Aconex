import java.io.IOException;
import java.util.Stack;

/**
 * @author Utkarsh Goel
 * @version 1.0.0 (1/7/2012)
 *	This class provides the logic for parsing and controls the read and write classes. 
 */

public class Parser
{
	private Stack<String> expandedNodes = new Stack<String>();
	private int thisLevel;
	private int previousLevel;
	
	/*
	 * Gets the contents of the file and after examination uses FileWrite to finish parsing.
	 * @param - Accepts instances of FileRead and FileWrite from main.
	 */
	public Parser(FileRead fr, FileWrite fw) throws IOException
	{
		String check = "";
		String previousLine = fr.read();						//reads the first line in the document
		String previousValues[] = previousLine.split("\\s+",3); // splits into three strings
		previousLevel = Integer.parseInt(previousValues[0]); 
		String thisLine = fr.read();	// reads another line which acts as a reference to first line
		
		while (!check.equals("EOF"))		// until end of file
		{	
			if(thisLine.equals("EOF"))		// check for looping through one final time before exiting the loop
			{
				thisLine = "0 FINA Finish off";		// dummy reference for the last line to print
				check = "EOF";
			}
			String thisValues[] = thisLine.split("\\s+",3);
			thisLevel = Integer.parseInt(thisValues[0]);
			
			if(thisLevel > previousLevel)	// first case
			{
				
				if(expandedNodes.empty() && previousValues[1].startsWith("@") && previousValues[1].endsWith("@"))	// checks if new individual
				{
					expandedNodes.push(previousValues[2].toLowerCase());		//push stack
					fw.writeIndiTags(previousValues);
				}
				else
				{
					if(expandedNodes.empty())	// if level 0 data is not an "individual" tag
					{
						if (previousValues.length == 3)	// if the node contains data
						{
							fw.writeTags(previousValues);
						}
						else
						{
							fw.writeNoValueTag(previousValues);
						}
					}
					else		//child nodes of root level
					{
						expandedNodes.push(previousValues[1].toLowerCase());
						
						if (previousValues.length == 3)
						{
							fw.writeValueTags(previousValues);
						}
						else
						{
							fw.writeOpenTag(previousValues);
						}
						
					}
					
				}
				
			}
			else // case 2 - same level nodes
			{
				if(thisLevel == previousLevel)
				{
					if (previousValues.length == 3)
					{
						fw.writeTags(previousValues);
					}
					else
					{
						fw.writeNoValueTag(previousValues);
					}
				}
				else		// final case, if previous level > this level
				{
					if (previousValues.length == 3)
					{
						fw.writeTags(previousValues);
					}
					else
					{
						fw.writeNoValueTag(previousValues);
					}
					
					//loops and pops the stack to the current level
					int count = previousLevel-thisLevel;
					for(int i=0; i<count; i++)
					{
						String temp = expandedNodes.pop();
						fw.writeCloseTags(temp, expandedNodes.size()+2); // +2 takes into account indentation need for gedcom and indi tags
					}
					
				}
			}
           
			previousLevel = thisLevel;
			previousValues = thisValues;
			if (!(check.equals("EOF")))		//needed as the program needs to loop one final time before exiting the loop to print the final line
			thisLine = fr.read();
			
		}
		
		fw.close(); // calls the close file close function
		
	}
}