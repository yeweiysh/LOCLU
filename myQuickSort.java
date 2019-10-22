package graphClustering;

public class myQuickSort {

	public static void quickSort(int[][] a, int p, int r)
	{
		if(p<r)
		{
			int q=partition(a,p,r);
			quickSort(a,p,q);
			quickSort(a,q+1,r);
		}
	}

	private static int partition(int[][] a, int p, int r) {

		int x = a[0][p];
		int i = p-1 ;
		int j = r+1 ;

		while (true) {
			i++;
			while ( i< r && a[0][i] < x)
				i++;
			j--;
			while (j>p && a[0][j] > x)
				j--;

			if (i < j)
				swap(a, i, j);
			else
				return j;
		}
	}

	private static void swap(int[][] a, int i, int j) {

		int row=a.length;
		int temp;
		for (int i1=0;i1<row;i1++){
			temp = a[i1][i];
			a[i1][i] = a[i1][j];
			a[i1][j] = temp;
		}
	}
	

	public static void quickSort(double[][] a, int p, int r)
	{
		if(p<r)
		{
			int q=partition(a,p,r);
			quickSort(a,p,q);
			quickSort(a,q+1,r);
		}
	}

	private static int partition(double[][] a, int p, int r) {

		double x = a[0][p];
		int i = p-1 ;
		int j = r+1 ;

		while (true) {
			i++;
			while ( i< r && a[0][i] < x)
				i++;
			j--;
			while (j>p && a[0][j] > x)
				j--;

			if (i < j)
				swap(a, i, j);
			else
				return j;
		}
	}

	private static void swap(double[][] a, int i, int j) {

		int row=a.length;
		double temp;
		for (int i1=0;i1<row;i1++){
			temp = a[i1][i];
			a[i1][i] = a[i1][j];
			a[i1][j] = temp;
		}
	}

	public static void quickSort(double[] a, int p, int r)
	{
		if(p<r)
		{
			int q=partition(a,p,r);
			quickSort(a,p,q);
			quickSort(a,q+1,r);
		}
	}

	private static int partition(double[] a, int p, int r) {

		double x = a[p];
		int i = p-1 ;
		int j = r+1 ;

		while (true) {
			i++;
			while ( i< r && a[i] < x)
				i++;
			j--;
			while (j>p && a[j] > x)
				j--;

			if (i < j)
				swap(a, i, j);
			else
				return j;
		}
	}

	private static void swap(double[] a, int i, int j) {

		double temp;
		temp = a[i];
		a[i] = a[j];
		a[j] = temp;

	}
	

		public static void quickSort(int[] a, int p, int r)
		{
			if(p<r)
			{
				int q=partition(a,p,r);
				quickSort(a,p,q);
				quickSort(a,q+1,r);
			}
		}

		private static int partition(int[] a, int p, int r) {

			double x = a[p];
			int i = p-1 ;
			int j = r+1 ;

			while (true) {
				i++;
				while ( i< r && a[i] < x)
					i++;
				j--;
				while (j>p && a[j] > x)
					j--;

				if (i < j)
					swap(a, i, j);
				else
					return j;
			}
		}

		private static void swap(int[] a, int i, int j) {
			int temp;
			temp = a[i];
			a[i] = a[j];
			a[j] = temp;

		}

}
