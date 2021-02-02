/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.os1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zezo
 */


public class Terminal {
    
   public static String location="C:\\one"; 
   String root="C:\\";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

  
        public void cd(String position){
         boolean file=false;   
          for(int i=2;i<position.length();i++){    
           if(position.charAt(i)=='.'){file=true;}
        } 
                        
   if(file){System.out.println("The directory name is invalid.");}
            
   else if(position.equals("..")){

        for(int i= location.length()-1; i>=0;i--){ 
           if(location.equals(root)){break;}
              char x= location.charAt(i);
              location= location.substring(0, i);        
               if(x =='\\'){ break;}
         }      
 
     }
        
    else if(position.equals("\\")){
        location=root;
        }
        
       else{
        boolean in=true,dirkt=false;
         File test;   
         if(position.charAt(0)=='E'||position.charAt(0)=='C'||position.charAt(0)=='F'||position.charAt(0)=='D'){
          test=new File(position);
          dirkt=true;
        }
        
        else{
         test=new File(location+"\\"+position);
         }
         
          if(!test.exists()){
          System.out.println("A subdirectory or file "+position+" not Exists");
          in=false;
         }

   if(in){
       if(dirkt){location=position;}
       
      else if(location.equals(root)){
       location=location+position;
       }
       
       else{
       location=location+'\\'+position;
       
       }
   
   }  
     }       
        
      }

     public ArrayList<String> cat(String[] paths) throws IOException{
         ArrayList<String> copy=new ArrayList<>();
     
         for(int i=0;i<paths.length-1;i++){
         
            boolean folder=true;   
          for(int j=0;j<paths[i].length();j++){
        
        if(paths[i].charAt(j)=='.'){folder=false;}
        }
     
         if(folder){
                System.out.println("cat: "+paths[i]+": Is a directory");
                   continue;
               }
                  File test;   
         if(paths[i].charAt(0)=='E'||paths[i].charAt(0)=='C'||paths[i].charAt(0)=='F'||paths[i].charAt(0)=='D'){
          test=new File(paths[i]);
        }
        
        else{
         test=new File(location+"\\"+paths[i]);
         }
         
          if(!test.exists()){
          System.out.println("A subdirectory or file "+paths[i]+" not Exists");
          continue;
         }

         
         FileReader read =new FileReader(test);
         
         Scanner input = null ;
             input = new Scanner(read);
  
     
         while(input.hasNextLine()){
           String data=input.nextLine();
            copy.add(data);
         
         }
         
     }
     return  copy;

     }
     
     
     
        public ArrayList<String> ls(String args){
            
        char x='\\';
        File newFile;
        ArrayList<String> copy= new ArrayList<>();
        
           boolean file=false;   
          for(int j=0;j<args.length();j++){
        
        if(args.charAt(j)=='.'){file=true;}
        }
     
         if(file){
                System.out.println("ls: "+args+": Is a file");
               }
         else{
        if(args.charAt(0)=='E'||args.charAt(0)=='C'||args.charAt(0)=='F'||args.charAt(0)=='D'){
          newFile=new File(args);
        }
        
        else{
        if(args.equals("")){x=' ';} 
        
         newFile=new File(location+x+args);
        }
        
        if(!newFile.exists()){
          System.out.println("A subdirectory or file "+newFile+" not exsist");
         }
         
         else{
        File[] files = newFile.listFiles();    
        for (int i = 0; i < files.length; i++) { 
       copy.add(files[i].getName()); 
           
         }
         }
         }        
        return  copy;
 } 


        
  public void mkdir(String[] names ){
      
      boolean in=false;
      
      for(int i=0;i<names.length-1;i++){
          
           boolean file=false;   
          for(int j=0;j<names[i].length();j++){
        
        if(names[i].charAt(j)=='.'){file=true;}
        }
     
         if(file){
              System.out.println("mkdir: "+names[i]+": Is a file");
                   continue;
               }
        
         File newfFile;   
         if(names[i].charAt(0)=='E'||names[i].charAt(0)=='C'||names[i].charAt(0)=='F'||names[i].charAt(0)=='D'){
          newfFile=new File(names[i]);
        }
        
        else{
         newfFile=new File(location+"\\"+names[i]);
         }
         
         int ex=0;
         
         for(int j=names[i].length()-1;j>=0;j--){
         if(names[i].charAt(j)=='\\'){
             ex=1;
         names[i]=names[i].substring(0, j);
         break;
         }
         }
         
         if(ex==1){
         File test= new File(names[i]);
          if(!test.exists()){
          System.out.println("A subdirectory or file "+names[i]+" not Exists");
          in=true;
         }
         }
         
       if(in){continue;}
 
  
      newfFile.mkdir();
      
    }  

           
  
  }       
       
public void mv(String sourcePath, String destinationPath) throws IOException{
        boolean sIsFolder=true,notFound=false;
        boolean dIsFolder=true;
        FileWriter destination = null ;
        
        ArrayList<String>copy=new ArrayList<>();
        for(int i=0;i<sourcePath.length();i++){
           if(sourcePath.charAt(i)=='.'){sIsFolder=false;}
        }
        
        for(int i=0;i<destinationPath.length();i++){
        if(destinationPath.charAt(i)=='.'){dIsFolder=false;}
        }
        
       if(sIsFolder){System.out.println("cannot move "+sourcePath+" Is a directory ");}

  else{
            File test;
            if(sourcePath.charAt(0)=='E'||sourcePath.charAt(0)=='C'||sourcePath.charAt(0)=='F'||sourcePath.charAt(0)=='D'){
              test=new File(sourcePath);
         }
          
          else{test=new File(location+"\\"+sourcePath);}
  
    if(!test.exists()){
            System.out.println("A subdirectory or file "+sourcePath+" not Exists");
       }
   else{
             FileReader source=new FileReader(test); 

            if(dIsFolder){
                File test1=new File(location+"\\"+destinationPath);
                
               if(!test1.exists()){
                System.out.println("A subdirectory or file "+destinationPath+" not Exists");
                notFound=true;
               }
               
                else{destination=new FileWriter(location+"\\"+destinationPath+"\\"+sourcePath);}
               }
            
            else{destination=new FileWriter(location+"\\"+destinationPath);}
   
            if(dIsFolder==false||(dIsFolder==true&&notFound==false)){
             Scanner input = new Scanner(source);
      
             while(input.hasNextLine()){
              String data=input.nextLine();
              copy.add(data);
             
             }
             
            for(int i=0;i<copy.size();i++){
           destination.write(copy.get(i)+"\n");
         }
             
             destination.close();
             source.close();
             this.rm(sourcePath);
              }
         }
       }         
    }
    
        public void pwd(){
        System.out.println(location);
        } 

        
 public void rm(String sourcePath){
     
 boolean file=true;
     for(int i=0;i<sourcePath.length();i++){
     if(sourcePath.charAt(i)=='.'){
     file=false;
     break;
     }
     }
     
     if(file){
     
         System.out.println("cannot remove "+sourcePath+"  Is a directory");
     }
     
     else{
        File source;    
      if(sourcePath.charAt(0)=='E'||sourcePath.charAt(0)=='C'||sourcePath.charAt(0)=='F'||sourcePath.charAt(0)=='D'){
           source=new File(sourcePath);
        }
   
   else{
        source=new File(location+"\\"+sourcePath);
   }
   
       if(!source.exists()){
           System.out.println("A subdirectory or file "+sourcePath+" not exsist");
       }
       
       else{
       source.delete();
       }
     }
 }
 
  public void rmdir(String sourcePath){
      File source;
       boolean file=false;
     for(int i=0;i<sourcePath.length();i++){
     if(sourcePath.charAt(i)=='.'){
     file=true;
     break;
     }
     }
     
     if(file){
     
         System.out.println("cannot remove "+sourcePath+"  Is a file");
     }
     else{
   if(sourcePath.charAt(0)=='E'||sourcePath.charAt(0)=='C'||sourcePath.charAt(0)=='F'||sourcePath.charAt(0)=='D'){
           source=new File(sourcePath);
        }
   
   else{
        source=new File(location+"\\"+sourcePath);
   }
   
       if(!source.exists()){
           System.out.println("A subdirectory or file "+sourcePath+" not exsist");
       }
       
       else{
           if(ls(sourcePath).size()==0){
                    source.delete();
              
           }
           else{
   System.out.println("The directory is not empty.");
           }
       }
     }
  }
  
  public void date(){
  
  Date date=java.util.Calendar.getInstance().getTime();  
  System.out.println(date);  
  
  }
  
   public void help(){
       
       System.out.println(
"args : List all command arguments\n" +
"date : Current date/time\n" +
"exit : Stop\n"+
"mv   : move file or directory to anther dirctory\n"+
">    : takes the standard output of the command on the left and redirects it to the file on the right.\n"  +
">>   : akes the standard output of the command on the left and appends (adds) it to the file on the right.\n"+
"cd   : takes a directory name as an argument, and switches into that directory.\n"+
"cp   : copies files or directories.\n"+
"ls   : lists all files and directories in the working directory.\n"+
"mkdir: takes in a directory name as an argument, and then creates a new directory in the current working directory.\n"+
"pwd  : prints the name of the working directory.\n"+
"rm   : deletes files.\n"+
"rmdir: deletes directory.\n"+
"cat  : display lines in files.\n"+
"pipe : can make to commands in same time.\n"+
"clear: clear screen.\n"       
       );   
   }
   
   
   
   
        public void cp(String sourcePath, String destinationPath ) throws IOException{
   
        boolean s=true;
        boolean d=true;
       for(int i=0;i<sourcePath.length();i++){
        if(sourcePath.charAt(i)=='.'){ s=false;}
    
       }
       for(int i=0;i<destinationPath.length();i++){
         if(destinationPath.charAt(i)=='.'){ d=false;}
 
       }
   
 
  if(s){System.out.println("cannot copy "+sourcePath+"  Is a directory");}
    
     
  else{
        File source;  
        File destination;
      
      if(sourcePath.charAt(0)=='E'||sourcePath.charAt(0)=='C'||sourcePath.charAt(0)=='F'||sourcePath.charAt(0)=='D'){
           source=new File(sourcePath);
        }
         else{ source=new File(location+"\\"+sourcePath);}
 
     
       destination=new File(location+"\\"+destinationPath);
   
          if(!source.exists()){
           System.out.println("A subdirectory or file "+sourcePath+" not exsist");
          }
    
//folder and exist
    
          else if(source.exists()&&destination.exists()&&d){

           File newfile=new File(location+"\\"+destinationPath+"\\"+sourcePath);

          FileReader readsource=new FileReader(source);
          BufferedReader br=new BufferedReader(readsource); 
          FileWriter write=new FileWriter(newfile);

          String x ;
        while((x=br.readLine())!=null) {           
         write.write(x);
         write.write("\n");
        } 
       write.close();
          }

  //file and exist or not
          else if(source.exists()&&d==false){

           FileReader readsource=new FileReader(source);
           BufferedReader br=new BufferedReader(readsource); 
           FileWriter write=new FileWriter(destination);
      
            String x;
         while((x=br.readLine())!=null) {         
          write.write(x);
          write.write("\n");
         }
          write.close();
  
          }
    //folder and not exist
         if(source.exists()&&!destination.exists()&&d){
        System.out.println("A subdirectory  "+destinationPath+" not exsist");
            }
  }}

   
     public ArrayList<String> more(String[] paths) throws IOException{
         ArrayList<String> copy=new ArrayList<>();
      for(int i=0;i<paths.length-1;i++){
           boolean folder=true;   
          for(int j=0;j<paths[i].length();j++){
        
        if(paths[i].charAt(j)=='.'){folder=false;}
        }
     
         if(folder){
                System.out.println("more: "+paths[i]+": Is a directory");
                   continue;
               }
                  File test;   
         if(paths[i].charAt(0)=='E'||paths[i].charAt(0)=='C'||paths[i].charAt(0)=='F'||paths[i].charAt(0)=='D'){
          test=new File(paths[i]);
        }
        
        else{
         test=new File(location+"\\"+paths[i]);
         }
         
          if(!test.exists()){
          System.out.println("A subdirectory or file "+paths[i]+" not Exists");
          continue;
         }
        
         
         Scanner input = null ;
         try {
             input = new Scanner(test);
         } catch (FileNotFoundException ex) {
             Logger.getLogger(Terminal.class.getName()).log(Level.SEVERE, null, ex);
         }
     
         while(input.hasNextLine()){
           String data=input.nextLine();
            copy.add(data);
         
         }
     }

      
     return  copy;
     }
   
   public void operator1(ArrayList<String> lines ,String destnation) throws IOException{
            FileWriter des;
                boolean folder=true;   
          for(int j=0;j<destnation.length();j++){
        
        if(destnation.charAt(j)=='.'){folder=false;}
        }
     
         if(folder){
                System.out.println("more: "+destnation+": Is a directory");
             
               }
         else{
          if(destnation.charAt(0)=='E'||destnation.charAt(0)=='C'||destnation.charAt(0)=='F'||destnation.charAt(0)=='D'){
             des=new FileWriter(destnation);
        }
          else{des=new FileWriter(location+"\\"+destnation);}
           
            
             for(int i=0;i<lines.size();i++){
             des.write(lines.get(i)+"\n");
             }
          des.close();
   
         }
   }
   
      public void operator2(ArrayList<String> lines ,String destnation) throws IOException{
            FileWriter des;
               boolean folder=true;   
          for(int j=0;j<destnation.length();j++){
        
        if(destnation.charAt(j)=='.'){folder=false;}
        }
     
         if(folder){
                System.out.println("more: "+destnation+": Is a directory");
             
               }
         else{
          if(destnation.charAt(0)=='E'||destnation.charAt(0)=='C'||destnation.charAt(0)=='F'||destnation.charAt(0)=='D'){
             des=new FileWriter(destnation);
        }
          else{des=new FileWriter(location+"\\"+destnation,true);}
      
             for(int i=0;i<lines.size();i++){
              
            des.write(lines.get(i)+"\n");

             }
          des.close();}
   
   }
      
    public void clear() {
        for (int i = 0; i < 1000; i++) {
         System.out.println("\b");
        }
    }
    
    
     

      
    public  void args(String args){
     
          
                if("cd".equals(args)){System.out.println("Number of args is 1: Source Path");}
                else if("cp".equals(args))System.out.println("Number of args is 2: Source Path, Destination Path");
                else if("mv".equals(args))System.out.println("Number of args is 2: Source Path, Destination Path");
                else if("rm".equals(args))System.out.println("Number of args is 1: The Directory to be removed");  
                else if("mkdir".equals(args))System.out.println("Number of args is 1: The new Directory Path"); 
                else if("ls".equals(args))System.out.println("Number of args is 1: Directory ");  
                else if("rmdir".equals(args))System.out.println("Number of args is 1: The Directory to be removed"); 
                else if("cat".equals(args))System.out.println("Many Number of args: The File Directories"); 
                else if("args".equals(args))System.out.println("Number of args is 1: Command Instruction");  
                else if("more".equals(args)){System.out.println("Many Number of args: Source Path ");}
                else if("date".equals(args)||"help".equals(args)||"clear".equals(args)||"pwd".equals(args)){ System.out.println("No Arguments for this command");}
                
                
            }



}




