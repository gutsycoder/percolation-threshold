import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class Percolation {
	private final byte[] status; 
	private final int N;  
	private int count; 
	private WeightedQuickUnionUF uf;
	private final int virtual_top;
private   byte p;
	public Percolation(int n){
		if (n <= 0) throw new IllegalArgumentException("IllegalArgument");
		N = n;
		count = 0;
		uf = new WeightedQuickUnionUF(N*N + 1);
		status = new byte[N*N+1];
		virtual_top = N*N;
p=0;
	}
	
	public void open(int row, int col){
		checkRange(row, col);
		if(status[rcTo1D(row,col)]==0)
		{
			status[rcTo1D(row,col)]=1;
			concat(row,col);
			count++;
		}
	}
	
	private void concat(int row,int col){
int pos=rcTo1D(row,col);
byte bottom=0;
		if(row != 1 && status[pos-N]!=0){
if(p==0&&status[uf.find(pos-N)]==2)
bottom=2;
			union(pos, pos-N);
		}else if (row == 1){
			union(pos, virtual_top);
		}
		if(row != N && status[pos+N]!=0){
if(p==0&&status[uf.find(pos+N)]==2)
bottom=2;
			union(pos, pos+N);
		}else if(row == N){ 
	bottom=2;
		}
		if(col != 1 && status[pos-1]!=0){
if(p==0&&status[uf.find(pos-1)]==2)
bottom=2;
			union(pos,pos-1);
		}
		if(col != N && status[pos+1]!=0){
if(p==0&&status[uf.find(pos+1)]==2)
bottom=2;
			union(pos, pos+1);
		}
if(bottom!=0)
status[uf.find(pos)]=bottom;
if(p==0&&uf.find(pos)==uf.find(virtual_top)&&status[uf.find(pos)]==2)
 p=1;
	}
	
	public boolean isOpen(int row, int col){  
		checkRange(row, col);
		return (status[rcTo1D(row,col)]==1||status[rcTo1D(row,col)]==2);
	}
	private  int rcTo1D(int i, int j) {
        
        return j + (i-1) * N -1;
    }
    public boolean isFull(int row, int col){  
    	checkRange(row, col);
    	return uf.find(rcTo1D(row,col))==uf.find(virtual_top);
    }
    
    public int numberOfOpenSites(){
		return count;
    }
    
	public boolean percolates(){
		return p==1;
	}
	private void checkRange(int i, int j){
		if (i <= 0 || j <= 0 || i > N || j > N)
			throw new IllegalArgumentException();
	}
	
	private void union(int p, int q){
		if(!(uf.find(p)==uf.find(q))){
			uf.union(p, q);
		}
	}
}