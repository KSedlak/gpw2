package pl.spring.demo.dataLoad.fileReader;



import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class CsvReader {

	private final String DEFAULT_PATH="src/main/resources/data/dane.csv";
    private List <String> records;
    private Path currentPath;


    public CsvReader(){
    	currentPath=Paths.get(DEFAULT_PATH);
    }

    public CsvReader(Path pathToFile){
    	currentPath=pathToFile;
    }


    public List<String> readRecords() {

    	BufferedReader breader=null;
    	try{
    	       breader = Files.newBufferedReader(currentPath);
    	    }catch(IOException exception){
    	        		System.out.println("Error occurred while trying to read the file :"+currentPath.toAbsolutePath().toString());
    	       		System.exit(0);
    	     	}

    	records=breader.lines()
    	  	                  .collect(Collectors.toList());

    	return records;

    }

	public List<String> getRecords() {
		return records;
	}





    }