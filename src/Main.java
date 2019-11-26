import Restructure.FolderRestructer;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		FolderRestructer od = new FolderRestructer("Desired_File");
		long time=System.currentTimeMillis();
		od.RestructureDirectory();
		System.out.format("<MAIN THREAD STARTED> STARTs at : %sms",time);
		System.out.format("<MAIN THREAD ENDED> Ended in : %sms\n",(System.currentTimeMillis()-time));

		System.out.println("<Main THREAD> Done ^.^");
		
		
		//Tests done by calculating The LastThreadRunning(ms) - TimeTheFirstMainThreadRuns(ms)
		//Experiment done on 68 FILES (images,docs,Videos,audio....) ===> 3.97GB
		//copying Large file by 100MB and byte[100MB] ==> 4.07min "LOOL"
		//copying Large file by 100MB and byte[1MB] ==>3.41min
		//copying Large file by 250MB and byte[1MB] ==>2.71min
		//copying Large file by 250MB and byte[100MB] ==>2.97min
		//copying Large file by 250MB and byte[1KB] ==>3.98min 'with io exception!!!???'
		//copying Large file by 250MB and byte[10MB] ==>min 'with io exception!!!???'
	}

}
