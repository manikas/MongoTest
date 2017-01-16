/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package acromegalyheatmap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dex
 */
public class AcromegalyHeatmap {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            
            BufferedReader br1 = new BufferedReader(new FileReader(new File("F:/IOB_Acromegaly_heatmap/R1.txt")));
            String line1;
            HashSet<String> peps1 = new HashSet();
            BufferedReader br2 = new BufferedReader(new FileReader(new File("F:/IOB_Acromegaly_heatmap/R2.txt")));
            String line2;
            HashSet<String> peps2 = new HashSet();
            BufferedReader br3 = new BufferedReader(new FileReader(new File("F:/IOB_Acromegaly_heatmap/R3.txt")));
            String line3;
            HashSet<String> peps3 = new HashSet();
            HashSet<String> commonPeps = new HashSet();
            ArrayList list1 = new ArrayList();
            ArrayList list2 = new ArrayList();
            ArrayList list3 = new ArrayList();
            while((line1=br1.readLine()) != null)
            {
                list1.add(line1);
                String[] col1 = line1.split("\t");
                peps1.add(col1[0]);
                //System.out.println(col1[0]);
                //break;
            }
            while((line2=br2.readLine()) != null)
            {
                list2.add(line2);
                String[] col2 = line2.split("\t");
                peps2.add(col2[0]);
                //System.out.println(col2[0]);
                //break;
            }
            while((line3=br3.readLine()) != null)
            {
                list3.add(line3);
                String[] col3 = line3.split("\t");
                peps3.add(col3[0]);
                //System.out.println(col3[0]);
                //break;
            }
            
            System.out.println("R1 : " + peps1.size() + "//  " + "R2 : " + peps2.size() + "// " +   "R3 :  " + peps3.size() );
            
            for(String pep1 : peps1 )
            {
              for(String pep2 : peps2)
              {
                 for(String pep3 : peps3)
                 {
                    if((pep3.equals(pep1)) && (pep3.equals(pep2)))
                    {
                        commonPeps.add(pep3);
                    }
                 }
              }
            }
            
            System.out.println("Common Peptides : " + commonPeps.size());
            
            ArrayList listR1 = new ArrayList();
            for(String com:commonPeps)
            {
              for(int i=0;i<list1.size();i++)
              {
                 if(list1.get(i).toString().contains(com))
                 {
                    listR1.add(list1.get(i));
                 }
              }
            }
            System.out.println("R1 list : " + listR1.size());
            
            
            ArrayList listR2 = new ArrayList();
            for(String com:commonPeps)
            {
              for(int i=0;i<list2.size();i++)
              {
                 if(list2.get(i).toString().contains(com))
                 {
                    listR2.add(list2.get(i));
                 }
              }
            }
            System.out.println("R2 list : " + listR2.size());
            
            
            
            ArrayList listR3 = new ArrayList();
            for(String com:commonPeps)
            {
              for(int i=0;i<list3.size();i++)
              {
                 if(list3.get(i).toString().contains(com))
                 {
                    listR3.add(list3.get(i));
                 }
              }
            }
            System.out.println("R3 list : " + listR3.size());
            
            for(int i=0;i<listR1.size();i++)
            {
               String[] splitz1 = listR1.get(i).toString().split("\t");
               System.out.println("Splitz1 Array : " + splitz1.length);
               
            }
            
            
            
            
            
            
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AcromegalyHeatmap.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AcromegalyHeatmap.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
