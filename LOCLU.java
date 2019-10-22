package graphClustering;
import java.lang.Math.*;

public class LOCLU_Uni {
	int[] label;
	int[] indexLabel;
	int[] DM;
	double[] e;

	public LOCLU(int[][] edgeList, double[][] data, int query, int numUnimodalDim) throws Exception{

		int n=data.length;
		int d=data[0].length;

		double[] D = new double[n];

		int[] ind = new int[n+1];
		ind[0]=0;
		ind[n]=edgeList.length;

		for (int i = 1; i < n; i++) {
			for (int j = 0; j < edgeList.length-1; j++) {
				if ((edgeList[j][0]==i-1)&&(edgeList[j+1][0]==i)){
					ind[i]=j+1;
					break;
				}
			}
		}

		for (int i=0; i<n; i++) {
			D[i]=ind[i+1]-ind[i];
		}

		double[][] W = new double[edgeList.length][3];
		for (int i = 0; i < edgeList.length; i++) {
			for (int j = 0; j < 2; j++) {
				W[i][j] = edgeList[i][j];
			}
			W[i][2] = 1;
		}

		for (int i=0; i<n;i++){
			for (int j=ind[i]; j<ind[i+1];j++)
				W[j][2]=1.0*W[j][2]/D[i];
		}

		double[] e=new double[n];

		PI powerIter = new PI(W,n,ind);
		e=powerIter.getPseudoEig();

		double norm = 0.0;
		for (int i=0; i< n; i++) {
			norm += e[i] * e[i];
		}
		double sqrtnorm = Math.sqrt(norm);
		for (int i=0; i<n; i++) {
			e[i] /=sqrtnorm;
		}
		
		double[] temp = new double[n];
		for (int i=0; i< n; i++) {
			temp[i] = e[i];
		}
		
		myQuickSort.quickSort(temp, 0, n-1);
		hartigansDipSignifTest Diptest = new hartigansDipSignifTest(temp);
		double pi_dip = Diptest.getDip();

		int[] tmpLabel=new int[2];

		int[] indexLabel = new int[n+1];
		indexLabel[0]=0;
		indexLabel[n]=edgeList.length;
		for (int i=1; i< n; i++) {
			indexLabel[i]=-1;
		}

		for (int i = 1; i < n; i++) {
			for (int j = 0; j < edgeList.length-1; j++) {
				if ((edgeList[j][0]==i-1)&&(edgeList[j+1][0]==i)){
					indexLabel[i]=j+1;
					break;
				}
			}
		}

		double pvalue,xl,xu;

		int[] label=new int[n];
		for (int i=0; i<n; i++) {
			label[i]=i;
		}

		int[] index=new int[2];
		int[] label1=new int[2];
		int[] DM = new int[d];

		temp = new double[label.length];
		double[] temp1=new double[2];
		double[][] DIP_VALUE = new double[2][d];
		for (int i=0; i<d; i++) {

			for (int j=0; j<label.length; j++) {
				temp[j]=data[label[j]][i];
			}

			myQuickSort.quickSort(temp, 0, label.length-1);
			Diptest = new hartigansDipSignifTest(temp);

			DIP_VALUE[0][i]=Diptest.getDip();
			DIP_VALUE[1][i]=i;
		}

		myQuickSort.quickSort(DIP_VALUE, 0, d-1);

		double[][] DIPVALUE = new double[2][numUnimodalDim+1];
		double[][] data_e = new double[n][numUnimodalDim+1];
		for (int i=0; i<numUnimodalDim; i++) {
			for (int j=0; j<n; j++) {
				data_e[j][i] = data[j][(int)DIP_VALUE[1][d-1-i]];
			}
			DIPVALUE[0][i] = DIP_VALUE[0][d-1-i];
			DIPVALUE[1][i] = i;
            DM[(int)DIP_VALUE[1][d-1-i]] = 1;
		}
		
		DIPVALUE[0][numUnimodalDim] = pi_dip;
		DIPVALUE[1][numUnimodalDim] = numUnimodalDim;
		
		for (int j=0; j<n; j++) {
			data_e[j][numUnimodalDim] = e[j];
		}

		myQuickSort.quickSort(DIPVALUE, 0, numUnimodalDim);

		for (int dim=0; dim<numUnimodalDim+1; dim++) {

			double xquery=data_e[query][(int)DIPVALUE[1][numUnimodalDim-dim]];

			temp=new double[label.length];
			for (int j=0; j<label.length; j++) {
				temp[j]=data_e[label[j]][(int)DIPVALUE[1][numUnimodalDim-dim]];
			}

			double[] proX = new double[label.length];
			for (int j=0; j<label.length; j++)
				proX[j]=temp[j];

			myQuickSort.quickSort(temp, 0, label.length-1);
			Diptest = new hartigansDipSignifTest(temp);
			pvalue=Diptest.getpValue();
			xl=Diptest.getXL();
			xu=Diptest.getXU();

			int tmp,t,t1;
			while(pvalue<=0.05) {

				if (xquery<xl) {
					tmp=0;
					for (int i=0; i<label.length; i++) {
						if (proX[i]<xl)
							tmp+=1;
					}
					index = new int[tmp];
					temp1=new double[tmp];
					t1=0;
					for (int i=0; i<label.length; i++) {
						if (temp[i]<xl) {
							temp1[t1]=temp[i];
							t1++;
						}
					}
					temp=new double[tmp];
					for (int i=0; i<tmp; i++) {
						temp[i]=temp1[i];
					}

					t=0;
					for (int i=0; i<label.length; i++) {
						if (proX[i]<xl) {
							index[t]=i;
							t++;
						}
					}
					tmpLabel=new int[index.length];
					for (int i=0; i<index.length; i++) {
						tmpLabel[i]=label[index[i]];
					}

					label=new int[index.length];
					for (int i=0; i<index.length; i++) {
						label[i]=tmpLabel[i];
					}	
				}
				else if (xquery>xu){
					tmp=0;
					for (int i=0; i<label.length; i++) {
						if (proX[i]>xu)
							tmp+=1;
					}
					index = new int[tmp];
					temp1=new double[tmp];
					t1=0;
					for (int i=0; i<label.length; i++) {
						if (temp[i]>xu) {
							temp1[t1]=temp[i];
							t1++;
						}
					}
					temp=new double[tmp];
					for (int i=0; i<tmp; i++) {
						temp[i]=temp1[i];
					}

					t=0;
					for (int i=0; i<label.length; i++) {
						if (proX[i]>xu) {
							index[t]=i;
							t++;
						}
					}
					tmpLabel=new int[index.length];
					for (int i=0; i<index.length; i++) {
						tmpLabel[i]=label[index[i]];
					}
					label=new int[index.length];
					for (int i=0; i<index.length; i++) {
						label[i]=tmpLabel[i];
					}

				}
				else if ((xquery>=xl)&&(xquery<=xu)){
					tmp=0;
					for (int i=0; i<label.length; i++) {
						if ((proX[i]>=xl)&&(proX[i]<=xu))
							tmp+=1;
					}
					index = new int[tmp];
					temp1=new double[tmp];
					t1=0;
					for (int i=0; i<label.length; i++) {
						if ((temp[i]>=xl)&&(temp[i]<=xu)) {
							temp1[t1]=temp[i];
							t1++;
						}
					}
					temp=new double[tmp];
					for (int i=0; i<tmp; i++) {
						temp[i]=temp1[i];
					}

					t=0;
					for (int i=0; i<label.length; i++) {
						if ((proX[i]>=xl)&&(proX[i]<=xu)){
							index[t]=i;
							t++;
						}
					}
					tmpLabel=new int[index.length];
					for (int i=0; i<index.length; i++) {
						tmpLabel[i]=label[index[i]];
					}
					label=new int[index.length];
					for (int i=0; i<index.length; i++) {
						label[i]=tmpLabel[i];
					}	
				}


				if (label.length<=5)
					break;

				proX = new double[label.length];
				for (int j=0; j<label.length; j++)
					proX[j]=data_e[label[j]][(int)DIPVALUE[1][numUnimodalDim-dim]];

				Diptest = new hartigansDipSignifTest(temp);
				pvalue=Diptest.getpValue();
				xl=Diptest.getXL();
				xu=Diptest.getXU();
			}
		}

		this.label=label;
		this.indexLabel=indexLabel;
		this.DM=DM;
		this.e=e;
	}
	public int[] getLabel() {
		return label;
	}

	public int[] getIndex() {
		return indexLabel;
	}

	public int[] getDM() {
		return DM;
	}
	
	public double[] getEig() {
		return e;
	}

}
