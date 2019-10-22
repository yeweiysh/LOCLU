package graphClustering;

public class locluSync {

	public static void main(String[] args) throws Exception{
		String[] arg = new String[1];

		int[] index = new int[5];

		index[0]=Integer.parseInt(args[2]);
		index[1]=Integer.parseInt(args[3]);
		index[2]=Integer.parseInt(args[4]);
		index[3]=Integer.parseInt(args[5]);
		index[4]=Integer.parseInt(args[6]);

		for (int i=0;i<index.length;i++){
			System.out.print("running the number of ");
			System.out.print(index[i]);
			System.out.println(" attributes/nodes...");
			
			arg[0] = args[0]+String.valueOf(index[i])+"/";
			
			loclu_for_synthetic_graphs.main(arg);
		}
	}

}