package graphClustering;

import java.io.*;
import java.util.*;
public class readLabel {
	ArrayList<Integer> Label;
	public readLabel(String filename) throws Exception{
		ArrayList<Integer> Label = new ArrayList<Integer>();
		BufferedReader br = new BufferedReader(new FileReader(
				new File(filename)));
		String str[];
		try {
			String line = br.readLine();

			while (line != null) {

				str=line.split("\\s+");
				int length = str.length;
				int tmpdata;
				tmpdata=Integer.parseInt(str[0]);

				Label.add(tmpdata);
				line = br.readLine();
			}
		} finally {
			br.close();
		}
		this.Label = Label;
	}

	public ArrayList<Integer> getDataList() {
		return Label;
	}

	public int[] getData() {
		int len = Label.size();
		int[] data=new int[len];
		for(int i=0; i<len; i++){
			data[i]=Label.get(i);
		}
		return data;
	}

}
