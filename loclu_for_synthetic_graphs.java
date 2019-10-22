package graphClustering;

import java.io.*;

public class loclu_for_synthetic_graphs {

	public static void main(String[] args) throws Exception{
		String dir = args[0];

		String fileAffinity=dir+"edgeWeight.txt";
		readAffinity rdf = new readAffinity(fileAffinity);
		int[][] edgeList;
		edgeList=rdf.getData();
		String fileData=dir+"data.txt";
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


		int runtime=50;
		int query;

		double[] eclipsedTime=new double[runtime];
		int numUnimodalDim=9;

        String version = "LOCLU";
        String filename=dir+ version;
		File newFolder = new File(filename);
		boolean created =  newFolder.mkdir();

		for (int run=0; run<runtime; run++) {

			long start = System.currentTimeMillis();

			query=candidateSeed[run];
			int[] label;
			int[] index;
			int[] DM;
			double[] e;

			System.out.println("the " + run+"th running...");
            
			LOCLU loCLU=new LOCLU(edgeList, data, query, numUnimodalDim);
			label=loCLU.getLabel();
			index=loCLU.getIndex();
			DM=loCLU.getDM();
			e=loCLU.getEig();

			long end = System.currentTimeMillis();
			long difference = end - start;
			eclipsedTime[run]=difference/1000.0;

			System.out.println("length of label: "+label.length);
			filename=dir+ version+String.valueOf(run+1)+"_loclu_cluster.txt";
			PrintWriter clout = new PrintWriter(new FileWriter(filename));
			for (int i = 0; i < label.length; i++) {
				clout.println(label[i]+1);
			}
			clout.close();
			
			filename=dir+"DM_"+String.valueOf(run+1)+".dat";
			clout = new PrintWriter(new FileWriter(filename));
			for (int i = 0; i < DM.length; i++) {
				clout.println(DM[i]);
			}
			clout.close();

			filename=dir+version+"v_"+String.valueOf(run+1)+".dat";
			clout = new PrintWriter(new FileWriter(filename));
			for (int i = 0; i < e.length; i++) {
				clout.println(e[i]);
			}
			clout.close();

		}

		double eclipsedtime=0;
		for (int i=0; i<eclipsedTime.length; i++) {
			eclipsedtime+=eclipsedTime[i];
		}
		eclipsedtime/=runtime;
		System.out.println("average running time:    "+eclipsedtime);

		System.out.println("Done!");
	}

}

