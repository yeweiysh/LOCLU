package graphClustering;

import java.io.*;

public class loclu_for_real_graphs_desktop {

	public static void main(String[] args) throws Exception{

		String dir = args[0];

		String fileAffinity=dir+"edgeWeight.txt";
		readAffinity rdf = new readAffinity(fileAffinity);
		int[][] edgeList;
		edgeList=rdf.getData();
		String fileData=dir+"Data.txt";
		double[][] data;
		readData rdD=new readData(fileData);
		data=rdD.getData();


		String fileCandidate=dir+"candidateSeed.dat";
		int[] candidateSeed;
		readLabel rdL=new readLabel(fileCandidate);
		candidateSeed=rdL.getData();
		for (int i=0; i<candidateSeed.length; i++) {
			candidateSeed[i]-=1;
		}

	
		int numUnimodalDim=1;

		LOCLU_realdata loCLU=new LOCLU_realdata(edgeList, data, candidateSeed, numUnimodalDim,dir);

	}

}

