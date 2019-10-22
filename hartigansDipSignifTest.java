package graphClustering;

public class hartigansDipSignifTest {
	int[] triple;
	double dip,pvalue,xl,xu;

	public hartigansDipSignifTest(double[] x) throws Exception{
		int debug=0, min_is_0=1, n=x.length,nboot=1000,i,j;
		int[] triple = new int[3];
		double xl,xu;
		double dip,pvalue;

		diptest dipStatistics=new diptest(x,debug,min_is_0);
		dip=dipStatistics.getDipvalue();
		triple=dipStatistics.getTriple();
		xl=dipStatistics.getXL();
		xu=dipStatistics.getXU();

		double[] bootDip = new double[nboot];
		double[] unifpdfboot = new double[n];


		for (i=0;i<nboot;i++){
			//Random generator = new Random();

			for (j=0;j<n;j++){
				//unifpdfboot[j]=generator.nextDouble();
				unifpdfboot[j]=Math.random();
			}
			myQuickSort.quickSort(unifpdfboot, 0, n-1);
			dipStatistics=new diptest(unifpdfboot,debug,min_is_0);
			bootDip[i]=dipStatistics.getDipvalue();
		}

		myQuickSort.quickSort(bootDip, 0, nboot-1);
		int cnt=0;
		for (i=0;i<nboot;i++){
			if (dip<bootDip[i])
				cnt=cnt+1;
		}
		pvalue=1.0*cnt/nboot;
		
//		if (x.length<2)
//			pvalue=1;



		this.triple=triple;
		this.dip=dip;
		this.pvalue=pvalue;
		this.xl=xl;
		this.xu=xu;
	}

	public int[] getTriple() {
		return triple;
	}

	public double getDip() {
		return dip;
	}

	public double getpValue() {
		return pvalue;
	}
	
	public double getXL() {
		return xl;
	}
	
	public double getXU() {
		return xu;
	}

}
