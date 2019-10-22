package graphClustering;

import java.io.*;
import java.util.*;
public class readData {
	ArrayList<double[]> Data;
	public readData(String filename) throws Exception{
		ArrayList<double[]> Data = new ArrayList<double[]>();
		BufferedReader br = new BufferedReader(new FileReader(
				new File(filename)));
		String str[];
		try {
			String line = br.readLine();

			while (line != null) {
				
				str=line.split("\\s+");
				int length = str.length;
				double[] tmpdata = new double[length];
				for (int j=0;j<length;j++){
					tmpdata[j]=Double.parseDouble(str[j]);
				}
				Data.add(tmpdata);
				line = br.readLine();
			}
		} finally {
			br.close();
		}
		this.Data = Data;
	}
	
	public ArrayList<double[]> getDataList() {
		return Data;
	}
	
	public double[][] getData() {
		int len = Data.size();
		int width=Data.get(0).length;
		double[][] data=new double[len][width];
		for(int i=0; i<len; i++){
			for (int j=0;j<width;j++)
				data[i][j]=Data.get(i)[j];
		}

		return data;
	}

}
