import java.io.*;

public class FileData
{
	// class variables or attributes
	private BufferedWriter fW; // used to to write ie record data in a file
	private BufferedReader fR; // to read data stored in a file and then use that data in a program
	private char mode; // variable telling us whether we are reading from or writing to a file
	
	// the treatment of a file takes place in 3 stages: opening of the stream, treatment of the data and closing of the stream
	// the method below takes care of the opening of the stream
	public void open(String nameOfFile, String s) throws IOException
	{
		mode = (s.toUpperCase()).charAt(0); // we apply successively the methods toUpperCase() and charAt() which are built in and part of the String class
		if (mode == 'R') // for read
		fR = new BufferedReader(new FileReader(new File(nameOfFile)));
		else if (mode == 'W') // for write
		fW = new BufferedWriter(new FileWriter(new File(nameOfFile)));
	}	
	// once the file is open, the two treatments possible are reading data from the file or writing data to the file
	// methode to write data to a file, specifically an integer
	public void write(int n) throws IOException
	{
		String str = " "; // we define a string of one white space
		str = String.valueOf(n); // in the class String, there is a method valueOf() which takes an integer and converts it to a string which is then affected to the value of str
		if (str != null)
		{
			fW.write(str, 0, str.length()); // the method write() sends to the stream fW the string str. 0 is the position from which copying starts and str.length() is the number of characters to copy ie we copy in the whole of str
			fW.newLine();
		}
	}
	// methode to read data from a file
	public String read() throws IOException
	{
		String str = fR.readLine(); // readLine() sends first of all the line read to the stream fR then moves on to the next line. The line read is stored in str
		return str;
	}
	// we now need a method to close the stream
	public void close() throws IOException
	{
		if (mode == 'R')
		fR.close();
		else if (mode == 'W')
		fW.close();
	}
}