package com.ibm.jgit;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import com.esotericsoftware.yamlbeans.YamlReader;
public class MainClass {
	static List<Identity> IdentityList;
	static HashSet<String> Licenseset;
	static String RootPath;
	/**
	 * Recursively finds all files with name Identity.yml and parses  
	 * yaml into objects which are held in a list to allow for later
	 * enhancements.
	 * Failure to parse any one of the Identity file aborts the job
	 * with exit(1).
	 * 
	 * @author Mike Carr 	 
	 * @param  args[1]  Absolute path of root directory
	 */
    public static void main(String[] args) {
    	if(args.length != 1){
    		System.out.println("Usage: Identity <Directory Path>");
    		System.exit(1);
    	}
    	RootPath = args[0];
    	File f = new File(RootPath);
    	if(!f.isDirectory()){
    		System.out.println("Path is not a valid directory");
    		System.exit(1);
    	}
    	IdentityList = new ArrayList<Identity>();
    	Licenseset = new HashSet<String>();
    	System.out.println("Identity.yml files processed");
    	System.out.println("--------------------------------------------------------------------------------------------------");
    	getIdentities(RootPath);
    	System.out.println("--------------------------------------------------------------------------------------------------");
    	System.out.println("");
    	for(Identity ident: IdentityList )
    		display(ident);
    	System.out.println("\nUnique list of licenses");
    	System.out.println("-----------------------");
    	for(String s: Licenseset)
    		System.out.println(s);
		}

    private static void getIdentities(String FilePath){
    	for (File file : new File(FilePath).listFiles()) {
    		if (file.isFile()) {
    			if(file.getName().toLowerCase().equals("identity.yml")){
    				try {
    					parseYaml(file.getAbsolutePath());
					} catch (Exception e) {
						System.out.format("Failed processing file %s",FilePath);
						e.printStackTrace();
						System.exit(1);
					}
    			}
    		}
    		else if (file.isDirectory()) {
    			if(!file.getName().startsWith("."))
    			getIdentities(file.getAbsolutePath());
    		}
    	}
    }
	private static int parseYaml(String fname) throws Exception{
	try {
	YamlReader reader = new YamlReader(new FileReader(fname));
	while(true){
	Identity mf = reader.read(Identity.class);
	if(mf == null)
		break;
	IdentityList.add(mf);
	Licenseset.add(mf.License);
	System.out.format("%s\n",fname);
	}
	} catch (Exception ex) {
		throw(ex);
	} finally {
		
        }
	return IdentityList.size();
	}
	private static void display(Identity c){
		String spaces = "       ";
		System.out.format("\n\nPackage Name: %s\n", c.PackageName);
		System.out.format("      Origin: %s\n", c.OriginUrl);
		System.out.format("     License: %s\n", c.License);
		System.out.format("   Copyright: %s\n", c.Copyright);
		System.out.format("    Licensor: %s\n", c.Licensor);
		System.out.format("%sDependencies\n",spaces);
		System.out.format("%s-------------------------\n",spaces);
		if(c.Dependencies != null){
			for(String s : c.Dependencies){
				System.out.format("%s%s%s\n",spaces,spaces,s);
			}
		} else
			System.out.format("%s%s%s\n",spaces,spaces,"None");
		System.out.format("%s-------------------------\n",spaces);
		System.out.println();
	}

}