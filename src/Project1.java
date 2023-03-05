import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Project1 {

	
	
	
	public static void main(String[] args) throws FileNotFoundException, IOException   {

		
		
		   
		String input= args[0];	
		String output = args[1];
		
		FactoryImpl a= new FactoryImpl();

			File Obj = new File(input); 
	        Scanner Reader = new Scanner(Obj); 
        	FileWriter myWriter = new FileWriter(output, false);
	        while (Reader.hasNextLine()) { 
	        	String data = Reader.nextLine(); 
	            String [] mylist = data.split(" ");      
	            
	            if(mylist[0].equals("AF")) {
	            	int i=Integer.parseInt(mylist[1]);
	            	int j=Integer.parseInt(mylist[2]);
	            	Product product= new Product(i, j);
	            	a.addFirst(product);	   
	            }
	            
	            else if(mylist[0].equals("AL")) {
	            	int i=Integer.parseInt(mylist[1]);
	            	int j=Integer.parseInt(mylist[2]);	 
	            	a.addLast(new Product(i, j));	            	
	            }
	            
	            else if(mylist[0].equals("A")) {
	            	try {

	            	int i=Integer.parseInt(mylist[1]);
	                int j=Integer.parseInt(mylist[2]);
	                int k=Integer.parseInt(mylist[3]); 
	                a.add(i,new Product(j, k));
	            	}
	            	catch (Exception e) {
	            		e.getMessage();
	            		myWriter.write(e.getMessage());
		                myWriter.write("\n");
					}
	            }
	            
	            else if(mylist[0].equals("RF")) {                  
	                try {
	                	Product sProduct = a.removeFirst();	                	
	                	String string= sProduct.toString();
	                	myWriter.write(string);
	                	myWriter.write("\n");                     
	                }
	                
	                 catch (Exception e) {                	 
		                 myWriter.write(e.getMessage());
		                 myWriter.write("\n");		                 
	                  } 	                
	            }
	            
	            else if(mylist[0].equals("RP")) {                   
	                try {
	                	int i=Integer.parseInt(mylist[1]);
	                	Product sProduct = a.removeProduct(i);             		                	
	                	

	                	myWriter.write(sProduct.toString());	
	                	myWriter.write("\n");

	                	}
	                	               
	                 catch (Exception e) {	                	 
	                	 myWriter.write("Product not found.");
		                 myWriter.write("\n");
	                 }
	                  } 	 
	            
	            else if(mylist[0].equals("RL")) {                   
	                try {
	                	Product sProduct = a.removeLast();	                	
	                	String string= sProduct.toString();
	                	myWriter.write(string);
	                	myWriter.write("\n");	                     
	                }
	                
	                 catch (Exception e) {           	 
		                 myWriter.write(e.getMessage());
		                 myWriter.write("\n");		                 
	                  } 	                
	            }
	            
	            else if(mylist[0].equals("RI")) {                   
	            	int i=Integer.parseInt(mylist[1]);
	            	try {
	                	Product sProduct = a.removeIndex(i);         	
	                	String string= sProduct.toString();
	                	myWriter.write(string);
	                	myWriter.write("\n");                     
	                }
	                
	                 catch (Exception e) {	                	 
		                 myWriter.write(e.getMessage());
		                 myWriter.write("\n");                 
	                  } 	                
	            }
	            
	            else if(mylist[0].equals("F")) {                  
	            	int i=Integer.parseInt(mylist[1]);
	            	try {
	                	Product sProduct = a.find(i);	                	
	                	String string= sProduct.toString();
	                	myWriter.write(string);
	                	myWriter.write("\n");	                     
	                }
	                
	                 catch (Exception e) {	                	 
		                 myWriter.write(e.getMessage());
		                 myWriter.write("\n");		                 
	                  } 	                
	            }
	            
	            else if(mylist[0].equals("G")) {                  
	            	int i=Integer.parseInt(mylist[1]);
	            	try {
	                	if(a.get(i) ==null) {
	                		myWriter.write("Index out of bounds.");
	                		myWriter.write("\n");
	                	}
	                	else {
	            		Product sProduct = a.get(i);                	
	                	String string= sProduct.toString();
	                	myWriter.write(string);
	                	myWriter.write("\n");                     
	                }
	            	}
	                
	                 catch (Exception e) {              	 
		                 myWriter.write(e.getMessage());
		                 myWriter.write("\n");		                 
	                  } 	                
	            }
	            
	            else if(mylist[0].equals("U")) {                  
	            	int i=Integer.parseInt(mylist[1]);
	            	int j=Integer.parseInt(mylist[2]);
	            	try {
	                	Product sProduct = a.update(i,j);	                	
	                	String string= sProduct.toString();
	                	myWriter.write(string);
	                	myWriter.write("\n");          
	                }
	                
	                 catch (Exception e) {              	 
		                 myWriter.write(e.getMessage());
		                 myWriter.write("\n");	                 
	                  } 	                
	            }
	    
	            else if(mylist[0].equals("FD")) {                  
	            	try {
	                	int numberOfDuplicates = a.filterDuplicates();         	
	                	myWriter.write(Integer.toString(numberOfDuplicates));
	                	myWriter.write("\n");                  
	                }
	                
	                 catch (Exception e) {       	 
		                 myWriter.write(e.getMessage());
		                 myWriter.write("\n");		                 
	                  } 	                
	            }
	            
	            else if(mylist[0].equals("R")) {                   
	            	try {
	            		a.reverse();
	            		String s= new String();
	            		Holder newHolder2= a.getFirst().getNextHolder();
	            		for(int i=0;i<a.getSize();i++) {
	            			s= s+ newHolder2.getProduct();
	            			newHolder2=newHolder2.getNextHolder();		
	            		}
	            		s="{"+s.replaceAll("\\)\\(", "\\),\\(")+"}";
	                	myWriter.write(s);
	                	myWriter.write("\n");
                }
	                
	                 catch (Exception e) {
		                 myWriter.write(e.getMessage());
		                 myWriter.write("\n");
	                  } 	                
	            }
	                    
	            else if(mylist[0].equals("P")) {                  
	            	try {
	            		String s= new String();
	            		Holder newHolder2= a.getFirst().getNextHolder();
	            		for(int i=0;i<a.getSize();i++) {
	            			s= s+ newHolder2.getProduct();
	            			newHolder2=newHolder2.getNextHolder();		
	            		}
	            		s="{"+s.replaceAll("\\)\\(", "\\),\\(")+"}";
	                	myWriter.write(s);
	                	myWriter.write("\n");	                     
	                }
	                
	                 catch (Exception e) {
		                 myWriter.write(e.getMessage());
		                 myWriter.write("\n");
	                  } 	                
	            }	            
	            }
	        Reader.close();  
	        myWriter.close();

	}      
	} 
	        
			
			
		


		

