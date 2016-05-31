/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nna;

/**
 *
 * @author 1
 */
public class MyObject {
    String realClass;
    String resultClass = null;
    double[] attributes;
    int numOfAtr;
    double distance = 0;
    
    public MyObject(String[] str){
        numOfAtr = str.length;
        attributes = new double[numOfAtr];
        for (int i = 0; i < numOfAtr-1; i++) {
            attributes[i] = Double.parseDouble(str[i]);
        }
        realClass = str[numOfAtr-1];
    }
    
    public MyObject(MyObject o){
        realClass = o.realClass;
        resultClass = o.resultClass;
        numOfAtr = o.numOfAtr;
        distance = o.distance;
        attributes = new double[numOfAtr];
        
        for (int i = 0; i < numOfAtr-1; i++) {            
            attributes[i] = o.attributes[i];           
        }
    
    }
    
    public double distanceMyObject(MyObject o){
        double result = 0;
        for (int i = 0; i < numOfAtr; i++) {            
            result = result + Math.pow(this.attributes[i]-o.attributes[i],2);           
        }
        //System.out.println(String.valueOf(result));
        return result;
    }
    
    public void show(){
        for (int i = 0; i < numOfAtr-1; i++) {            
            System.out.print(attributes[i]+" ");          
        }
        System.out.print(realClass+" ");
        if (resultClass != null)
            System.out.print(resultClass);
        System.out.println();
    }
    

    
}
