package com.ikubinfo.konferenca.filesOperations;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.primefaces.model.file.UploadedFile;

import com.ikubinfo.konferenca.utils.UtilMessages;

public class UploadUtil{

	private static Logger log = Logger.getLogger(UploadUtil.class);
	
	public static void deleteFile(String fileToDelete) {
		try{ 
			String initialPath = "/Users/Arber/git/projektKonferenca/WebContent/resources";
			
			SearchFile fileSearch = new SearchFile();
			String resultPath = fileSearch.getFullFileDirectory(new File(initialPath), fileToDelete);
			log.info("File full path !: " + resultPath);
			Files.deleteIfExists(Paths.get(resultPath));
			log.info("File deleted successfully!");
	        UtilMessages.addMessageSuccess("Sukses!", "File u fshi me sukses!");
			
        }
        catch(Exception e){ 
        	log.error("Error during file deletion: ",e);
	        UtilMessages.addMessageError(null, "Error, file nuk u fshi!"); 
        } 
  
	}
	
	
	public static void saveFile(UploadedFile uploadedFile, String newFilename, String path) {
	    try {
	    	
	    	if (!newFilename.isEmpty()) {
			
		    	if (path==null || !path.contains("resources")) {
		    		path = "C:/Users/Arber/git/projektKonferenca/WebContent/resources/uploads";
				}else if(path.contains("/resources/")){
					path = "C:/Users/Arber/git/projektKonferenca/WebContent" + path;
				}
			    	
			        String savedFileName = path + "/" + newFilename;
			        log.info("Into save uploaded file path: " + savedFileName);
			        File fileToSave = new File(savedFileName);
			        fileToSave.getParentFile().mkdirs();
			        fileToSave.delete();
			        
			        
			        Path folder = Paths.get(savedFileName);
			        Path fileToSavePath = Files.createFile(folder);
			        log.info("Into save uploaded file : the path: " + fileToSavePath.toString());
			        
			        
			        InputStream input = uploadedFile.getInputStream();
			        Files.copy(input, fileToSavePath, StandardCopyOption.REPLACE_EXISTING);
				
			    log.info("File uploaded successfully!");
		        UtilMessages.addMessageSuccess("Sukses! ", "File u ngarkua me sukses!");
	    	}
	    } catch (Exception e) {
	        log.error("Error during upload ",e);
	        
	    }
	}
	
	
	@SuppressWarnings("unused")
	public static String nameGenerator(UploadedFile uploadedFile) {
		try {
			//log.info("New name for file: " + uploadedFile.getFileName());
			if (
					uploadedFile!=null  
					&& uploadedFile.getFileName()!="null"
					&& uploadedFile.getFileName()!= null
					) {
				String ext = FilenameUtils.getExtension(uploadedFile.getFileName());
				String newEmriFile = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(10), ext);
				log.info("New name for file: " + newEmriFile);
				return newEmriFile;
			}else {
				log.error("Error during upload, file name null:");
				UtilMessages.addMessageError(null, "Error, gjate procesit te ngarkimit!");
				return new String();
				}
		} catch (Exception e) {
			log.error("Error during upload, name: ",e);
	        return new String();
		}
	}
}
