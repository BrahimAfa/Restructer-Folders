import Restructure.FolderRestructer;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		FolderRestructer od = new FolderRestructer("C:\\Users\\brahi\\Downloads\\Opera Download");
		long time=System.currentTimeMillis();
		od.RestructureDirectory();
		System.out.println("<MAIN THREAD ENDED >Ended in : "+(System.currentTimeMillis()-time)+"ms");
		System.out.println("<MAIN THREAD STARTED >START at : "+time+"ms");

		System.out.println("Done");
		
		
		//Tests done by calculating The LastThreadRunning(ms) - TimeTheFirstMainThreadRuns(ms)
		//Experiment done on 68 FILES (images,docs,Videos,audio....) ===> 3.97GB
		//copying Large file by 100MB and byte[100MB] ==> 4.07min
		//copying Large file by 100MB and byte[1MB] ==>3.41min
		//copying Large file by 250MB and byte[1MB] ==>2.71min
		//copying Large file by 250MB and byte[100MB] ==>2.97min
		//copying Large file by 250MB and byte[1KB] ==>3.98min 'with io exception!!!???'
		//copying Large file by 250MB and byte[10MB] ==>min 'with io exception!!!???'
	}

}
