package com.ikubinfo.konferenca.filesOperations;

import java.io.File;

import org.apache.log4j.Logger;

public class SearchFile {

	private static Logger log = Logger.getLogger(SearchFile.class);
	
	String fullPath;
	
	public String getFullFileDirectory(File pathi, String fileToFind) {
			
		if (pathi.isDirectory()) {
				for (File temp : pathi.listFiles()) {
				    if (temp.isDirectory()) {
				    	fullPath = getFullFileDirectory(temp, fileToFind);
				    } else {
						if (fileToFind.equals(temp.getName())) {
							fullPath = temp.getAbsoluteFile().toString();
					    	log.info("directory for file!" + fullPath);
							break;
					    }
				    }
			    }
		}else {
			if (fileToFind.equals(pathi.getName())) {
				fullPath = pathi.getAbsoluteFile().toString();
		    	log.info("directory for file!" + fullPath);
			}
		}
		return fullPath;
	}
}




