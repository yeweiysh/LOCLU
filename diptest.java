package graphClustering;

public class diptest {
	int[] triple;
	double dip;
	double xl;
	double xu;

	public diptest(double[] x, int debug, int min_is_0) throws Exception{
		int n=x.length;
		int mnj, mnmnj, mjk, mjmjk, ig, ih, iv, ix,  i, j, k, low, high;
		double dip_l, dip_u, dipnew;
		double du_best=0;
		double dl_best=0;
		double xl;
		double xu;
		int[] triple = new int[3];
		int[] triple_dl=new int[3]; 
		int[] triple_dl_best=new int[3];
		int[] triple_du=new int[3];
		int[] triple_du_best=new int[3];
		int best1=0,best2=0,i1;

		low=0;
		high=n-1;
		double dip;
		dip = (min_is_0==1) ? 0: 1;

		if (n < 2 || x[n - 1] == x[0]){      
			dip /= (2*n);
			this.dip=dip;
			this.triple=triple;
			this.xl=x[0];
			this.xu=x[n-1];
			return;
		}

		double[] diff1 = new double[n-1];
		for (i=1;i<n;i++)
			diff1[i-1]=x[i]-x[i-1];

		double[] diff2 = new double[n-2];
		int[] xsign = new int[n-2];

		for (i=1;i<n-1;i++){
			diff2[i-1]=diff1[i]-diff1[i-1];
			if (diff2[i-1]<0)
				xsign[i-1]=1;
			else
				xsign[i-1]=-1;
		}
		int cnt=0;
		for (i=0;i<n-2;i++){
			if (xsign[i]>0)
				cnt++;
		}
		boolean isunimodal=false;
		if (cnt==0 || cnt==n-2)
			isunimodal=true;
		if (isunimodal){
			dip=0;
			this.dip=dip;
			this.triple=triple;
			this.xl=x[0];
			this.xu=x[n-1];
			return;
		}

		if (0<cnt && cnt<n-2){
			isunimodal=true;
			int[] posi = new int[cnt];
			int[] negi = new int[n-2-cnt];
			cnt=0;
			for (i=0;i<n-2;i++){
				if (xsign[i]>0){
					posi[cnt]=i;
					cnt++;
				}
			}
			cnt=0;
			for (i=0;i<n-2;i++){
				if (xsign[i]<0){
					negi[cnt]=i;
					cnt++;
				}
			}

			int min=negi[0];
			for (i=1;i<negi.length;i++){
				if (negi[i]<min)
					min=negi[i];
			}

			for (i=0;i<posi.length;i++){
				if(posi[i]>=min){
					isunimodal=false;
					break;
				}
			}

			if (isunimodal){
				dip=0;
				this.dip=dip;
				this.triple=triple;
				this.xl=x[0];
				this.xu=x[n-1];
				return;
			}
		}

		xl=x[low];
		xu=x[high];

		/* Establish the indices   mn[1..n]  over which combination is necessary
		   for the convex MINORANT (GCM) fit.
		 */
		int[] mn=new int[n];
		mn[0]=0;
		for (j=1;j<=n-1;j++){
			mn[j]=j-1;
			while(true) {
				mnj = mn[j];
				mnmnj = mn[mnj];
				if (mnj == 0 || ( x[j]  - x[mnj]) * (mnj - mnmnj) < (x[mnj] - x[mnmnj]) * (j - mnj)) 
					break;
				mn[j] = mnmnj;
			}
		}

		/* Establish the indices   mj[1..n]  over which combination is necessary
		   for the concave MAJORANT (LCM) fit.
		 */
		int[] mj=new int[n];
		mj[n - 1] = n-1;
		for (k = n - 2; k >= 0; k--) {
			mj[k] = k + 1;
			while(true) {
				mjk = mj[k];
				mjmjk = mj[mjk];
				if (mjk == (n - 1) || ( x[k]  - x[mjk]) * (mjk - mjmjk) <(x[mjk] - x[mjmjk]) * (k - mjk)) 
					break;
				mj[k] = mjmjk;
			}
		}

		int[] gcm=new int[n];
		int[] lcm=new int[n];
		int l_gcm, l_lcm;
		boolean loopstart=true;


		while(loopstart){
			loopstart=false;

			/* Collect the change points for the GCM from HIGH to LOW. */
			gcm[0] = high;
			for(i = 0; gcm[i] > low; i++)
				gcm[i+1] = mn[gcm[i]];
			ig = i+1; // l_gcm == relevant_length(GCM)
			l_gcm = i+1;
			ix = ig-1; //  ix, ig  are counters for the convex minorant.

			//System.out.println(ix);

			/* Collect the change points for the LCM from LOW to HIGH. */
			lcm[0] = low;
			for(i = 0; lcm[i] < high; i++)
				lcm[i+1] = mj[lcm[i]];
			ih = i+1; // l_lcm == relevant_length(LCM)
			l_lcm = i+1;
			iv = 1; //  iv, ih  are counters for the concave majorant.


			/*  Find the largest distance greater than 'DIP' between the GCM and
			 *  the LCM from LOW to HIGH. */

			// FIXME: <Rconfig.h>  should provide LDOUBLE or something like it
			double d = 0;//

			if (l_gcm != 2 || l_lcm != 2) {
				//				int cntt=0;

				do { /* gcm[ix] != lcm[iv]  (after first loop) */
					//					cntt++;
					//					System.out.println(cntt);
					double dx;
					int gcmix = gcm[ix];
					int lcmiv = lcm[iv];
					if (gcmix > lcmiv) {
						/* If the next point of either the GCM or LCM is from the LCM,
						 * calculate the distance here. */
						int gcmi1 = gcm[ix + 1];
						dx = (lcmiv - gcmi1 + 1) -
								((double) x[lcmiv] - x[gcmi1]) * (gcmix - gcmi1)/(x[gcmix] - x[gcmi1]);
						++iv;
						if (dx >= d) {
							d = dx;
							ig = ix + 1;
							ih = iv - 1;

						}
					}
					else {
						/* If the next point of either the GCM or LCM is from the GCM,
						 * calculate the distance here. */
						int lcmiv1 = lcm[iv - 1];
						/* Fix by Yong Lu {symmetric to above!}; original Fortran: only ")" misplaced! :*/
						dx = ((double)x[gcmix] - x[lcmiv1]) * (lcmiv - lcmiv1) /
								(x[lcmiv] - x[lcmiv1])- (gcmix - lcmiv1 - 1);
						--ix;
						if (dx >= d) {
							d = dx;
							ig = ix + 1;
							ih = iv;

						}
					}
					if (ix < 0)   ix = 0;
					if (iv > l_lcm)   iv = l_lcm;

				} while (gcm[ix] != lcm[iv]);

			}
			else { /* l_gcm or l_lcm == 2 */
				d = (min_is_0==1) ? 0: 1;

			}


			if (d < dip) {
				dip /= (2*n);
				this.dip=dip;
				this.triple=triple;
				this.xl=x[low];
				this.xu=x[high];
				return;
			}


			/*     Calculate the DIPs for the current LOW and HIGH. */

			int j_best, j_l = -1, j_u = -1;

			/* The DIP for the convex minorant. */
			dip_l = 0;
			best1=0;

			for (j = ig; j < l_gcm; ++j) {
				double max_t = 1;
				int j_ = -1, jb = gcm[j + 1], je = gcm[j];
				if (je - jb > 1 && x[je] != x[jb]) {
					double C = (je - jb) / (x[je] - x[jb]);
					for (int jj = jb; jj <= je; ++jj) {
						double t = (jj - jb + 1) - (x[jj] - x[jb]) * C;
						if (max_t < t) {
							max_t = t; j_ = jj; best1=jj;
						}
					}
				}
				if (dip_l < max_t) {
					dip_l = max_t; j_l = j_;
					triple_dl[0]=jb;
					triple_dl[1]=best1;
					triple_dl[2]=je;
				}
			}

			if (dl_best<dip_l) {
				dl_best=dip_l;
				for (i1=0;i1<3;i1++) {
					triple_dl_best[0]=triple_dl[0];
					triple_dl_best[1]=triple_dl[1];
					triple_dl_best[2]=triple_dl[2];
				}
			}

			/* The DIP for the concave majorant. */
			dip_u = 0;
			for (j = ih; j < l_lcm; ++j) {
				double max_t = 1;
				int j_ = -1, jb = lcm[j], je = lcm[j + 1];
				if (je - jb > 1 && x[je] != x[jb]) {
					double C = (je - jb) / (x[je] - x[jb]);
					for (int jj = jb; jj <= je; ++jj) {
						double t = (x[jj] - x[jb]) * C - (jj - jb - 1);
						if (max_t < t) {
							max_t = t; j_ = jj; best2=jj;
						}
					}
				}
				if (dip_u < max_t) {
					dip_u = max_t; j_u = j_;
					triple_du[0]=jb;
					triple_du[1]=best2;
					triple_du[2]=je;
				}
			}

			if (du_best<dip_u) {
				du_best=dip_u;
				for (i1=0;i1<3;i1++) {
					triple_du_best[0]=triple_du[0];
					triple_du_best[1]=triple_du[1];
					triple_du_best[2]=triple_du[2];
				}
			}
			if (dl_best>du_best) {
				for (i1=0;i1<3;i1++) {
					triple[0]=triple_dl_best[0];
					triple[1]=triple_dl_best[1];
					triple[2]=triple_dl_best[2];
				}
			}
			else {
				for (i1=0;i1<3;i1++) {
					triple[0]=triple_du_best[0];
					triple[1]=triple_du_best[1];
					triple[2]=triple_du_best[2];
				}
			}

			/* Determine the current maximum. */
			if(dip_u > dip_l) {
				dipnew = dip_u; j_best = j_u;
			} else {
				dipnew = dip_l; j_best = j_l;
			}

			if (dip < dipnew)
				dip = dipnew;

			/*--- The following if-clause is NECESSARY  (may loop infinitely otherwise)!
		          --- Martin Maechler, Statistics, ETH Zurich, July 30 1994 ---------- */
			if (low == gcm[ig] && high == lcm[ih]) {
				if(debug==1)
					System.out.println("No improvement in  low = %d  nor  high = %d --> END\n"+low + high);
			} else {
				low  = gcm[ig];
				high = lcm[ih];    
				loopstart=true; /* Recycle */
			}
		}
		/*---------------------------------------------------------------------------*/

		/* do this in the caller :
		 *   *xl = x[low];  *xu = x[high];
		 * rather return the (low, high) indices -- automagically via lo_hi[]  */
		xl=x[low];
		xu=x[high];
		dip /= (2*n);

		this.dip=dip;
		this.triple=triple;
		this.xl=xl;
		this.xu=xu;

	}
	
	public int[] getTriple() {
		return triple;
	}
	
	public double getDipvalue() {
		return dip;
	}
	
	public double getXL() {
		return xl;
	}
	
	public double getXU() {
		return xu;
	}
}
