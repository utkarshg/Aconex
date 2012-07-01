import java.io.IOException;

public class Index
{	
	public static void main(String[] args) throws IOException 
	{
	    String inputFile = "Data/"+args[0];
	    String outputFile = "Result/"+args[1];
	    FileRead fr = new FileRead(inputFile);
	    FileWrite fw = new FileWrite(outputFile);
	    @SuppressWarnings("unused")
		Parser ps = new Parser(fr, fw);
	}
}