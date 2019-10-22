package graphClustering;

import java.io.*;
import java.util.*;
public class readAffinity {
	ArrayList<int[]> Affinity;
	public readAffinity(String filename) throws Exception{
		ArrayList<int[]> Affinity = new ArrayList<int[]>();
		BufferedReader br = new BufferedReader(new FileReader(
				new File(filename)));
		String str[];
		try {
			String line = br.readLine();

			while (line != null) {
				
				str=line.split("\\s+");
				int length = str.length;
				int[] tmpdata = new int[length];
				for (int j=0;j<length;j++){
					tmpdata[j]=Integer.parseInt(str[j]);
				}
				Affinity.add(tmpdata);
				line = br.readLine();
			}
		} finally {
			br.close();
		}
		this.Affinity = Affinity;
	}
	
	public ArrayList<int[]> getDataList() {
		return Affinity;
	}
	
	public int[][] getData() {
		int len = Affinity.size();
		int[][] data=new int[len][2];
		int p,q;
		for(int i=0; i<len; i++){
			p=Affinity.get(i)[0];
			q=Affinity.get(i)[1];
			data[i][0]=p;
			data[i][1]=q;
		}

		return data;
	}

}
