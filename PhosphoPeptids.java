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
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dex
 */
public class PhosphoPeptids {
    public static void main(String args[])
    {
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("E:/IOB_Acromegaly_heatmap/Consolidated/PhosphoPeptideToCheck.txt")));
            String line;
            ArrayList<String> peptides = new ArrayList();
            ArrayList<Float> X126 = new ArrayList();
            ArrayList<Float> X127_N = new ArrayList();
            ArrayList<Float> X127_C = new ArrayList();
            ArrayList<Float> X128_N = new ArrayList();
            ArrayList<Float> X128_C = new ArrayList();
            ArrayList<Float> X129_N = new ArrayList();
            ArrayList<Float> X129_C = new ArrayList();
            ArrayList<Float> X130_N = new ArrayList();
            ArrayList<Float> X130_C = new ArrayList();
            ArrayList<Float> X131 = new ArrayList();
            HashMap<String,ArrayList> mapPeps = new HashMap();
            
              while((line=br.readLine()) != null)
            {
                String[] col = line.split("\t");
                String phospho = col[1].toString();
                StringBuilder sb = new StringBuilder();
                if(phospho.contains(";"))
                {
                   String[] temp = phospho.split(";");
                   
                   for(int i=0;i<temp.length;i++)
                   {
                      sb.append(temp[i].replaceAll(": [0-9][0-9].[0-9][0-9]",""));
                   }
                }
                else
                {
                  sb.append(phospho.replaceAll(": [0-9]*.[0-9]*", ""));
                }
               // System.out.println(col[0].toUpperCase());
                peptides.add(col[0].toUpperCase() + " " + sb.toString().replaceAll(": 100", ""));
                X126.add(Float.parseFloat(col[2]));
                X127_N.add(Float.parseFloat(col[3]));
                X127_C.add(Float.parseFloat(col[4]));
                X128_N.add(Float.parseFloat(col[5]));
                X128_C.add(Float.parseFloat(col[6]));
                X129_N.add(Float.parseFloat(col[7]));
                X129_C.add(Float.parseFloat(col[8]));
                X130_N.add(Float.parseFloat(col[9]));
                X130_C.add(Float.parseFloat(col[10]));
                X131.add(Float.parseFloat(col[11]));
            }
              //System.out.println(peptides.size());
              //System.out.println(X126.size());
              //System.out.println(X127_N.size());
              //System.out.println(X127_C.size());
              //System.out.println(X128_N.size());
              //System.out.println(X128_C.size());
              //System.out.println(X129_N.size());
              //System.out.println(X129_C.size());
              //System.out.println(X130_N.size());
              //System.out.println(X130_C.size());
              //System.out.println(X131.size());
              
              for(int i=0;i<peptides.size();i++)
              {
                  String currentPep = (String)peptides.get(i);
                  ArrayList indexes = mapPeps.get(currentPep);
                  if(indexes==null)
                  {
                    indexes = new ArrayList();
                    mapPeps.put(currentPep, indexes);
                  }
                  indexes.add(i);
              }
              System.out.println(mapPeps.size());
              
              Iterator iter = mapPeps.entrySet().iterator();
              while(iter.hasNext())
              {
                Map.Entry pair = (Map.Entry)iter.next();
                //System.out.println(pair.getKey());
                //System.out.println(pair.getValue().toString());
                ArrayList<Integer> indexList = (ArrayList)pair.getValue();
                //String[] all = pair.getKey().toString().split(" ");
               // String pep = all[0];
                Float median126 =  getMedian(indexList,X126);
                Float median127_N =  getMedian(indexList,X127_N);
                Float median127_C =  getMedian(indexList,X127_C);
                Float median128_N =  getMedian(indexList,X128_N);
                Float median128_C =  getMedian(indexList,X128_C);
                Float median129_N =  getMedian(indexList,X129_N);
                Float median129_C =  getMedian(indexList,X129_C);
                Float median130_N =  getMedian(indexList,X130_N);
                Float median130_C =  getMedian(indexList,X130_C);
                Float median131 = getMedian(indexList,X131);
               
                Float[] array = {median126,median127_N,median127_C,median128_N,median128_C,median129_N,median129_C,median130_N,median130_C,median131};
                Float max = getMaxValue(array);
               // System.out.println(pair.getKey().toString() + "\t" + median126/max + "\t" + median127_N/max + "\t" + median127_C/max + "\t" + median128_N/max + "\t" + median128_C/max + "\t" + median129_N/max + "\t" + median129_C/max + "\t" + median130_N/max + "\t" + median130_C/max + "\t" + median131/max  );
                System.out.println(pair.getKey().toString() + "\t" + median126 + "\t" + median127_N + "\t" + median127_C + "\t" + median128_N + "\t" + median128_C + "\t" + median129_N + "\t" + median129_C + "\t" + median130_N + "\t" + median130_C + "\t" + median131  );
                //System.out.println(pair.getKey().toString().replaceAll("[(Phospho)]", "").trim() + "\t" + Math.round(median126)/max + "\t" +Math.round(median127_N) + "\t" +Math.round(median127_C) + "\t" + Math.round(median128_N) + "\t" + Math.round(median128_C) + "\t" + Math.round(median129_N) + "\t" + Math.round(median129_C) + "\t"+ Math.round(median130_N) + "\t" + Math.round(median130_C) + "\t" + Math.round(median131));
              }
              
              
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PhosphoPeptids.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PhosphoPeptids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static float getMedian(ArrayList<Integer> indexes,ArrayList<Float> X)
    {
        ArrayList<Float> floatList = new ArrayList();
        float median =0;
        for(int i=0;i<indexes.size();i++)
        {
           Integer index = indexes.get(i);
           
           floatList.add(X.get(index));
           //System.out.println(peptides.get(index) + "   " + X.get(index));
        }
        float[] floatArray = new float[floatList.size()];
        int i = 0;
         for (Float f : floatList) {
                floatArray[i++] = (f != null ? f : Float.NaN);
        }
        Arrays.sort(floatArray);
       
        if (floatArray.length % 2 == 0)
                median = ((float)floatArray[floatArray.length/2] + (float)floatArray[floatArray.length/2 - 1])/2;
        else
                median = (float) floatArray[floatArray.length/2];
        return median;
    }
    
     public static Float getMaxValue(Float[] array){  
      Float maxValue = array[0];  
      for(int i=1;i < array.length;i++){  
      if(array[i] > maxValue){  
      maxValue = array[i];  

         }  
     }  
             return maxValue;  
}  


    
}
