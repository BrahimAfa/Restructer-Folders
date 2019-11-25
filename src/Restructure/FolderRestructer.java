package Restructure;

import java.io.File;
import java.io.FileNotFoundException;
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
			System.out.format("\n%s => %s\n",Extension[1],Extension[0]);
		});;
	}
	
	
	
	/*==========>Private Functions<==============*/
	
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
}
