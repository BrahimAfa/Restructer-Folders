package Restructure;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class LargeFile_Thread extends Thread {
		BufferedInputStream bis;
		File File_Distination;
		public LargeFile_Thread(String File_Path,File file) throws FileNotFoundException {
			this.bis = new BufferedInputStream(new FileInputStream(File_Path));
			this.File_Distination = file;
		}

		private synchronized void Copy_Large_File() throws IOException {
			byte[] buffer= new byte[Constants._1MB_IN_BYTES];
			BufferedOutputStream bos=null;
			FileOutputStream fos =null;
			try {
				fos = new FileOutputStream(File_Distination);
				bos = new BufferedOutputStream(fos);
				
				System.out.println("<THREAD::STARTED> ===> "+this.getName()+" Started");
			int Readable_byte;
			long time = System.currentTimeMillis();
			while((Readable_byte=bis.read(buffer))!=-1){
				//System.out.format("\nbyte Readed = %d\n", Readable_byte);
				bos.write(buffer);
				
				//PastFile(ByteBuffer.wrap(buffer),File_Distination);
			}
			System.out.println("<THREAD::ENDED> ===> "+this.getName()+" Ended at : "+(System.currentTimeMillis()-time)+"ms");
			System.out.println("<THREAD::COPIES> ===> "+this.getName()+" || "+File_Distination.getName()+" Copied Succesfully");
			System.out.println("<THREAD::ENDED> ===> "+this.getName()+" Ended at : full time || "+System.currentTimeMillis()+"ms");

			} 
			catch (IOException e) {e.printStackTrace();}
			finally{
				if(fos!=null) fos.close();
				if(bis!=null) bis.close();
				if(bos!=null) bos.close();
			}

		}
		
		@Override
		public void run() {
			try {Copy_Large_File();}
			catch (IOException e) {e.printStackTrace();}
		}


}
