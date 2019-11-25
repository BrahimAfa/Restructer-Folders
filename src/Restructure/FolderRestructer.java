package Restructure;

import java.io.File;
import java.io.FileNotFoundException;

public class FolderRestructer {
	private String _DirectoryPath;
	private File _File ;
	
	public FolderRestructer (String DirectoryPath) throws Exception {
		this._DirectoryPath = DirectoryPath;
		_File = new File(_DirectoryPath);
		//throws Exception Of the The Given Path in Not a Directory
		Check_Is_Directory_And_Valid_Path();
		
	}
	 
	private void Check_Is_Directory_And_Valid_Path() throws Exception  {
		if(!_File.exists()) {
			throw new FileNotFoundException("File Not Found!!! Check the if The Path is Valid");
			
		}
		else if(!_File.isDirectory()) {
			throw new Exception("The Given Path in Not a Directory");
		}
	}
}
