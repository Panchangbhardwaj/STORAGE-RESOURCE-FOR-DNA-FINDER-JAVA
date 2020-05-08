
/**
 * Write a description of demo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */



import java.util.Arrays;
import edu.duke.*;
public class part4 {
     static int numofgene = 0;
     static int end ;
     //caculate end 
      public int endfinder(int end1,int end2,int end3)
      {
          int[] a = new int[3];
          a[0] = end1;
          a[1] = end2;
          a[2] = end3;
          Arrays.sort(a);
            if(a[2] == -1)
           { return -1;
            }
            else if(a[1] == -1)
            {
                return a[2]; 
            }
            else if(a[0] == -1)
            {   return a[1];
            
            }
            else
            {   return a[0];
            }
     }
    
     //give end
     public int endc(String dna,int start)
     {
        int end1 = dna.indexOf("TAA",start+3);
        int end2 = dna.indexOf("TGA",start+3);
        int end3 = dna.indexOf("TAG",start+3);
        int result = endfinder(end1,end2,end3);
        return result;
    }
    
    
     public void processgene(StorageResource srp)
    {
        StorageResource src = srp;
        int largerthennine = 0;
        int largerthencg = 0;
        int maxlength = 0;
            for(String s : srp.data())
                  {   
                      if(s.length() > 60)
                        {  System.out.println((largerthennine + 1) + "GENE HAVING LENGTH LONGER THEN 60 IS = " + s);
                          largerthennine ++;
                        }
                          
                      if(cgratio(s) > 0.35)
                      {
                        System.out.println((largerthencg + 1) + "GENE HAVING CGRATIO HIGHER THEN 0.35 IS = "+ s);
                        largerthencg ++;
                        }
                        
                      if(maxlength < s.length())
                      {     maxlength = s.length();
                        }
                      System.out.println("\n");  
                     }
                     System.out.println("NO OF STRINGS LARGER THEN 60 CHARACTER ARE  = " + largerthennine);
                     System.out.println("NO OF STRINGS HAVING CG RATIO LARGER THEN 0.35 ARE = "+ largerthencg);
                     System.out.println("LONEST STRING LENGTH IS = " + maxlength);
    }
    
     //count ctg in a dna string
    public int ctgcount(String dna)
    {   int end = 0;
        int ctgnum = 0;
        while(true)
        {
            if((dna.indexOf("CTG",end)) != -1)
            {
             
             ctgnum ++;
             end = dna.indexOf("CTG",end) + 3;
            
            }
            else
          { 
            break ;
           }
        
        }
        return ctgnum;
    }
    
    
    //calculate c and g ratio from a given dna strand
    public float cgratio(String dna)
    {
        int cindna = 0;
        int cindex =0;
        int gindna = 0;
        
        float ratio = 0;
        int length = dna.length();
        while(true)
        {
            if(dna.indexOf("C",cindex) != -1)
            {
                cindna ++;
                cindex = dna.indexOf("C",cindex) + 1;
            }
            else
            {
            break;
            }
        
        }
        cindex = 0;
        while(true)
        {
            if(dna.indexOf("G",cindex) != -1)
            {
                gindna ++;
                cindex = dna.indexOf("G",cindex) + 1;
            }
            else
            {
            break;
            }
        
        }
        ratio = length/(cindna+gindna);
        return ratio;
   }
    
   
   public String findgene(String dna ,int searchfrom)
    {
        int start = dna.indexOf("ATG",searchfrom);
        if(start == -1)
        {
            return null;
        }
        
        end  = endc(dna,start);
        while(end != -1)
        {  
            int length = end - start ;
            if(length%3 == 0)
            {
                System.out.println(dna.substring(start,end + 3));
                System.out.println("\n");
                System.out.println(length + "\n");
                return dna.substring(start,end + 3);
            }
            else 
            {
                end = endc(dna,end+1); 
            }
            
        }
        return null;
    }
   
    
    public StorageResource  findg(String dna)
    {
        int searchfrom = 0;
        int flag = -1;
        dna = dna.toUpperCase();
        StorageResource se = new StorageResource();
        
        
        do
        {   
            if(findgene(dna,searchfrom) == null)
            {
                flag = 0;
            }
            else {
                  
                  String currentgene = (findgene(dna,searchfrom));
                  se.add(currentgene);
                  numofgene++;
                  searchfrom = end + 3;
        }
        }while(flag != 0);
        return se;
    }
   
   public void test()
    {
        
        StorageResource soe = new StorageResource();
        
        FileResource fe = new FileResource("brca1line.fa");
        //String dna = fe.asString();
        String dna = "AUIOIEROINJIATGJKLIKJTAAHJEYUITAAKLOIUPATGYYIWWETGA";
        
        soe = findg(dna);
        processgene(soe);
        //processgene(soe);
        System.out.println("NO OF GENE ARE = "+numofgene);
    }
}
