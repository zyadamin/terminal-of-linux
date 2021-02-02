/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.os1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// ls | cat zz.txt
/**
 *
 * @author zezo
 */
public class parser {
    
    String[] args;
    String command;
    Terminal tem = new Terminal();

    public String[] getArgs() {
        return args;
    }

    public String getCommand() {
        return command;
    }

    
     public boolean parse(String input) throws IOException{
         boolean notMore=false;
          int operator=0;
         String newArgs = null;
        ArrayList newCopy = null;
        
       for(int i=0;i<input.length();i++){
       if(input.charAt(i)=='|'){
           parse(input.substring(i+2,input.length()));
           input=input.substring(0,i);

       }
       
       
       }
        
         for(int i=0;i<input.length();i++){
             
         if(input.charAt(i)=='>'&&input.charAt(i+1)=='>'){
         operator=2;
         newArgs=input.substring(i+3,input.length());
         input=input.substring(0, i);
         }
         else if(input.charAt(i)=='>'){
         operator=1;
       newArgs=input.substring(i+2,input.length());
         input=input.substring(0, i);
         }
         
         }

 
      
         
        String  args[]= input.split(" "); 
        command=args[0];
  
        for (int i=0;i<args.length-1;i++){ 
	      args[i]=args[i+1];
	}
        
        args[args.length-1]=" ";
        
         this.args=args;
         
        
     
     if(command.equals("cd")){
  
         tem.cd(args[0]);   
     }
     
     else if(command.equals("cat")){
         
       newCopy=tem.cat(args);
     }
         
         
    else if(command.equals("ls")){
     
    newCopy=tem.ls(args[0]);
     
     }
     
    else if(command.equals("mkdir")){
    
    tem.mkdir(args);
    }
     
     
    else if(command.equals("mv")){
    
    tem.mv(args[0],args[1]);
    }
     
     
     else if(command.equals("pwd")){
    tem.pwd();
    }
     
    else if(command.equals("rm")){
    tem.rm(args[0]);
    }
     
    else if(command.equals("rmdir")){
    tem.rmdir(args[0]);
    }
     
   else if(command.equals("date")){
    tem.date();
    }
     
    else if(command.equals("help")){
    tem.help();
    }
     
    else if(command.equals("cp")){
       tem.cp(args[0],args[1]);
         }
     
     else if(command.equals("more")){
      
        notMore=true;
        newCopy=tem.more(args);          
         }
    
     
      else if(command.equals("args")){
 
      tem.args(args[0]);
      }
     
    else if(command.equals("clear")){
 
      tem.clear();
      }

    else{System.out.println("bad command");}
     
     if(newCopy!=null){
    
        if(operator==0 && notMore==false){
      
     for(int i=0;i<newCopy.size();i++){
     System.out.println(newCopy.get(i));
     }
         
     }
        
     else if(operator==1){ tem.operator1(newCopy, newArgs);}
     else if(operator==2){tem.operator2(newCopy, newArgs);}
     else if(operator==0 && notMore && newCopy.size()>0){
     
          Scanner more= new Scanner(System.in);
          int i, j=0;
          char a;
     do{
         for( i=j;i<=5+j;i++){
      if(i==newCopy.size()){break;}
     System.out.println(newCopy.get(i));
        
         }
          if(i==newCopy.size()){break;}
         j=i;
     System.out.println("press a to print more ");
      a=more.next().charAt(0);
     }while(i<newCopy.size()-1);
     
     }
        
        
     }
      
     return true;
     }
}


