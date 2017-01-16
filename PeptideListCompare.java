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
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dex
 */
public class PeptideListCompare {
    
    public static void main(String []args)
    {
        try {
                 BufferedReader br1 = new BufferedReader(new FileReader(new File("F:/IOB_Acromegaly_heatmap/Acro_Phospho_R1_Peptide list.txt")));
                 BufferedReader br2 = new BufferedReader(new FileReader(new File("F:/IOB_Acromegaly_heatmap/Acro_Phospho_R2_Peptide list.txt")));
                 BufferedReader br3 = new BufferedReader(new FileReader(new File("F:/IOB_Acromegaly_heatmap/Acro_Phospho_R3_Peptide list.txt")));
                 HashSet<String> p1 = new HashSet();
                 HashSet<String> p2 = new HashSet();
                 HashSet<String> p3 = new HashSet();
                 String line1;
                 String line2;
                 String line3;
                 HashSet commons = new HashSet();
               while((line1=br1.readLine()) != null)
            {
                String[] col1 = line1.split("\t");
                p1.add(col1[0]);
                
                
                //System.out.println(col1[0]);
                //break;
            }
            while((line2=br2.readLine()) != null)
            {
                String[] col2 = line2.split("\t");
                p2.add(col2[0]);
                
                
               // System.out.println(col2[0]);
                //break;
            }
            while((line3=br3.readLine()) != null)
            {
           String[] col3 = line3.split("\t");
                p3.add(col3[0]);
           
                //System.out.println(col3[0]);
                //break;
            }
            
                  for(String pep1 : p1 )
            {
              for(String pep2 : p2)
              {
                 for(String pep3 : p3)
                 {
                    if((pep3.equals(pep1)) && (pep3.equals(pep2)))
                    {
                        commons.add(pep3);
                    }
                 }
              }
            }
            
            System.out.println("Common Peptides : " + commons.size());
                 
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PeptideListCompare.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PeptideListCompare.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
