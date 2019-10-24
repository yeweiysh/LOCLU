package graphClustering;

import java.io.*;
import java.util.*;
import java.lang.Math.*;

public class PI {

	double[] vt;

	public PI (double[][] W, int n, int[] index){
		int len=W.length;
		Random fRandom = new Random();
		double[] v0 = new double[n];
		for (int i=0; i<n; i++){
			v0[i] = fRandom.nextGaussian();
		}

		double conv=0.001;
		int maxit=1000;
		double[] vt = new double[n];
		double[] tmpvt = new double[n];
		double[] vtp = new double[n];
		double[] dt = new double[n];
		double[] dtp = new double[n];
		double[] a = new double[n];
		double max=0,temp;
		for (int i=0; i<n; i++){
			vt[i] = v0[i];
			dt[i] = 1;
			dtp[i] = 0;
			a[i]=Math.abs(dt[i]-dtp[i]);
			if (a[i]>max){
				max=a[i];
			}
		}

		int cnt=0;
		int col,i,j;
		while((max>conv)&&(cnt<maxit)){
//			if (cnt%10==0)
//				System.out.println(cnt);
			
			for (i=0; i<n; i++){
				vtp[i] = vt[i];
				dtp[i] = dt[i];
			}

			for (i=0; i<n; i++){
				temp=0;
				for (j=index[i]; j<index[i+1];j++){
					col=(int)W[j][1];
					temp += W[j][2]*vt[col];
				}
				tmpvt[i]=temp;
			}

			for (i=0; i<n; i++)
				vt[i]=tmpvt[i];

			temp=0;
			for (i=0; i<n; i++){
				temp += vt[i];
			}
			for (i=0; i<n; i++){
				vt[i] /= temp;
			}

			for (i=0; i<n; i++){
				dt[i] = Math.abs(vt[i]-vtp[i]);
			}

			cnt++;
			max=0;
			for (i=0; i<n; i++){
				a[i]=Math.abs(dt[i]-dtp[i]);
				if (a[i]>max){
					max=a[i];
				}
			}
		}
		
		this.vt=vt;
	}
	
	public double[] getPseudoEig() {
		return vt;
	}

}
