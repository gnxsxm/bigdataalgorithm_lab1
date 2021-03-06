package ecp.bigdata.Tutorial1;


import java.io.*;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

public class DisplayContentCompact {

	public static void main(String[] args) throws IOException {
		
		
		Path filename = new Path("/home/isd-history.txt");
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(conf);
		FSDataInputStream inStream = fs.open(filename);
		int no_of_lines = 0;
		
		try{
			
			InputStreamReader isr = new InputStreamReader(inStream);
			BufferedReader br = new BufferedReader(isr);	
			
			File file = new File ("output.txt");
            PrintStream printStreamToFile = new PrintStream(file);
            System.setOut(printStreamToFile);
            
			String line = br.readLine();
			while (line !=null){
				no_of_lines++;
				if (no_of_lines >= 23){
					if (line.length() > 0){
						String station = line.substring(13, 42);
						String fips = line.substring(43,45);
						String altitude = line.substring(74,81);
						System.out.println("station : " + station + " FIPS : " + fips + " Altitude : " + altitude);
						line = br.readLine();
						continue;
					}
					line = br.readLine();
					continue;
				}
				line = br.readLine();
				continue;
			}
		}
		finally{
			System.out.println("The number of lines is " + no_of_lines);
			inStream.close();
			fs.close();
		}
	}
}
