package Restructure;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

public class FolderRestructer {
	private String _DirectoryPath;
	private File _File ;
	
	public FolderRestructer (String DirectoryPath) throws Exception {
		this._DirectoryPath = DirectoryPath;
		_File = new File(_DirectoryPath);
		//throws Exception Of "the The Given Path is Not a Directory"
		Check_Is_Directory_And_Valid_Path();
		
	}
	 
	public void RestructureDirectory() {
		Arrays.stream(_File.listFiles()).filter(f->f.isFile()).forEach(x->{		
			String[]  Extension= Name_and_Extension_Seperator(x.getName());
			//System.out.format("\n%s => %s\n",Extension[1],Extension[0]);
			
			CopyFile(GetFileType(Extension[1]),Extension[0] ,Extension[1]);
		});;
	}
	
	
	
	/*==========>Private Functions<==============*/
	
	private boolean CopyAndPast(String FileName,String Constant_Path,String ext)  {
		
		boolean Is_CopiedSucces = false;
		String new_Path = String.format("%s\\%s\\%s.%s",_DirectoryPath,Constant_Path,FileName,ext); 
		String original_Path = String.format("%s\\%s.%s", _DirectoryPath,FileName,ext);
		File _f = new File(new_Path);
		
		if(!_f.getParentFile().exists()) _f.getParentFile().mkdir();
		
		try {
		//Copy operation
			//if this Is Null Means Its Been Copied by Large Function Files
		ByteBuffer file_copied_buffer = CopyFile(original_Path,_f);
		
		System.out.println(file_copied_buffer.capacity());
		//Past operation
		//if file_CopiedBuffer is Null means Thats its a large file and been pasted by an other Function
				if(file_copied_buffer !=null) {
					PastFile(file_copied_buffer, _f);
					System.out.println("File "+FileName+" Copied Succesfully To : "+new_Path);
					
					// new File(original_Path).delete();
					 Is_CopiedSucces = true;
				}
		} 
		catch (IOException e) {e.printStackTrace();}
		catch(Exception e){e.printStackTrace();}
		
		return Is_CopiedSucces;
	}
	private ByteBuffer CopyFile(String File_Path_toCopy,File _f) throws IOException {
		
		 FileInputStream fis;
		 //FileChannel fc;
		 fis= new FileInputStream(File_Path_toCopy);
		 BufferedInputStream bis = new BufferedInputStream(fis);
		 if (bis.available()>Constants._250MB_IN_BYTES) {
			 System.out.println("===> COPIEING A LARGE FILE...");
			 Copy_and_Past_Large_File(bis,_f);
			 fis.close();
			 bis.close();
			 return null;
		 };
		 
		 byte[] buffer = new byte[bis.available()];
		 bis.read(buffer);
		 bis.close();
		 fis.close();

		 return ByteBuffer.wrap(buffer);
	}
	private void PastFile(ByteBuffer File_Copied_Buffer , File _f) throws IOException {
		FileOutputStream fos;
		FileChannel fc;
		 fos= new FileOutputStream(_f);
		 fc = fos.getChannel();
		 fc.write(File_Copied_Buffer);
		 fc.close();
		 fos.close();
	}
	
	private void Copy_and_Past_Large_File(BufferedInputStream bis,File File_Distination) throws IOException {
		byte[] buffer= new byte[Constants._1MB_IN_BYTES];
		BufferedOutputStream bos=null;
		FileOutputStream fos =null;
		try {
			fos = new FileOutputStream(File_Distination);
			bos = new BufferedOutputStream(fos);
			
			int Readable_byte;
			long time = System.currentTimeMillis();
			while((Readable_byte=bis.read(buffer))!=-1){
				//System.out.format("\nbyte Readed = %d\n", Readable_byte);
				bos.write(buffer);
				
				//PastFile(ByteBuffer.wrap(buffer),File_Distination);
			}
		} 
		catch (IOException e) {e.printStackTrace();}
		finally{
			if(fos!=null) fos.close();
			if(bis!=null) bis.close();
			if(bos!=null) bos.close();
		}

}
	private boolean CopyFile(ExtensionTypes ext,String FileName,String Extension) {
		switch (ext) {
		case PDF:
			System.out.println("Copying "+FileName+"...");
			return CopyAndPast(FileName,Constants.PDF_PATH,Extension);
			
		case DOCUMENT:
			System.out.println("Copying "+FileName+"...");
			return CopyAndPast(FileName,Constants.DOCUMENT_PATH,Extension);
			
		case IMAGE:
			System.out.println("Copying "+FileName+"...");
			return CopyAndPast(FileName,Constants.IMAGE_PATH,Extension);
		case PRESENTATION:
			System.out.println("Copying "+FileName+"...");
			return CopyAndPast(FileName,Constants.PRESENTATION_PATH,Extension);
		case ZIP:
			System.out.println("Copying "+FileName+"...");
			return CopyAndPast(FileName,Constants.ZIP_PATH,Extension);
		case VIDEO:
			System.out.println("Copying "+FileName+"...");
			return CopyAndPast(FileName,Constants.VIDEO_PATH,Extension);
		case TORRENT:
			System.out.println("Copying "+FileName+"...");
			return CopyAndPast(FileName,Constants.TORRENT_PATH,Extension);
		case PROGRAMM:
			System.out.println("Copying "+FileName+"...");
			return CopyAndPast(FileName,Constants.PROGRAMME_PATH,Extension);
		default:
			System.out.println("Copying "+FileName+"...");
			return CopyAndPast(FileName,Constants.OTHER_PATH,Extension);
			
		}
	}
	
	private String[] Name_and_Extension_Seperator(String file) {
		
		int Point_Last_Index = file.lastIndexOf(".");
		
		String FileExtension = file.substring(Point_Last_Index+1,file.length());
		String FileName = file.substring(0,Point_Last_Index);

		
		return new String[] {FileName,FileExtension};
	}
	
	private void Check_Is_Directory_And_Valid_Path() throws Exception  {
		if(!_File.exists()) {
			throw new FileNotFoundException("File Not Found!!! Check the if The Path is Valid");
			
		}
		else if(!_File.isDirectory()) {
			throw new Exception("The Given Path is Not a Directory");
		}
	}
	private ExtensionTypes GetFileType(String Ext) {
		  if (Ext.equals("pdf") ) {
			 return ExtensionTypes.PDF;
		 }
		 else if(Ext.equals("jpg") || Ext.equals("png")|| Ext.equals("jpeg") ) {
			 return ExtensionTypes.IMAGE;
		 }
		 else if (Ext.equals("mp4") || Ext.equals("mkv")) {
			 return ExtensionTypes.VIDEO;
		 }
		 else if (Ext.equals("zip") || Ext.equals("rar")) {
			 return ExtensionTypes.ZIP;
		 }
		 else if (Ext.equals("doc") || Ext.equals("docx")|| Ext.equals("txt")) {
			 return ExtensionTypes.DOCUMENT;
		 }
		 else if (Ext.equals("exe") || Ext.equals("msi")) {
			 return ExtensionTypes.PROGRAMM;
		 }
		 else if (Ext.equals("ppt") || Ext.equals("pptx")) {
			 return ExtensionTypes.PRESENTATION;
		 }
		 else if (Ext.equals("torrent")) {
			 return ExtensionTypes.TORRENT;
		 }
		return ExtensionTypes.OTHER;
	}
}
