/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nna;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author 1
 */
public class Algorythm {
    ArrayList<MyObject> trainingSet;
    ArrayList<MyObject> testSet;
    HashMap<String, Double> classes;
    ArrayList<String> classNames;
    int k = 5;
    double q = 0.8;
    int val = 75;
    String whatSplit = ",";
    
    public Algorythm(){
        
    }
    
    public void getTrainingSetFromFile(String filePath) throws FileNotFoundException, IOException{
        
        int amountOfTestInstances = 0;
        ArrayList<String> tempSet = new ArrayList();        
        File file = new File(filePath);
        @SuppressWarnings("resource")
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String tempString = null;
        
        while ((tempString = bufferedReader.readLine()) != null) {
            tempSet.add(tempString);
            amountOfTestInstances++;
        }
        
        trainingSet = new ArrayList();        
        
        for (int j = 0; j < amountOfTestInstances; j++) {
            String[] array = tempSet.get(j).split(whatSplit);
            MyObject myObj = new MyObject(array);
            trainingSet.add(myObj);
        }
    }
    
    public void getEverythingFromFile(String filePath) throws FileNotFoundException, IOException{
        int amountOfTestInstances = 0;
        ArrayList<String> tempSet = new ArrayList();        
        File file = new File(filePath);
        @SuppressWarnings("resource")
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String tempString = null;

        while ((tempString = bufferedReader.readLine()) != null) {
            tempSet.add(tempString);
            amountOfTestInstances++;
        }
        
        Random rnd = new Random();
        for (int i = tempSet.size() - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            String a = tempSet.get(index);
            tempSet.set(index,tempSet.get(i));
            tempSet.set(i,a);
        }
        trainingSet = new ArrayList();  
        testSet = new ArrayList();  
        for (int j = 0; j < val; j++) {
            String[] array = tempSet.get(j).split(whatSplit);
            MyObject myObj = new MyObject(array);
            trainingSet.add(myObj);
        }
        
        for (int j = val; j < amountOfTestInstances; j++) {
            String[] array = tempSet.get(j).split(whatSplit);
            MyObject myObj = new MyObject(array);
            testSet.add(myObj);
        }
    }
    
    public void getTestSetFromFile(String filePath) throws FileNotFoundException, IOException{
        
        int amountOfTestInstances = 0;
        ArrayList<String> tempSet = new ArrayList();        
        File file = new File(filePath);
        @SuppressWarnings("resource")
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String tempString = null;

        while ((tempString = bufferedReader.readLine()) != null) {
            tempSet.add(tempString);
            amountOfTestInstances++;
        }

        testSet = new ArrayList();        

        for (int j = 0; j < amountOfTestInstances; j++) {
            String[] array = tempSet.get(j).split(whatSplit);
            MyObject myObj = new MyObject(array);
            testSet.add(myObj);
        }
    }
    
    public void showTrainingSet(){
        System.out.println("TRAINING SET:");
        for (int i = 0; i < trainingSet.size(); i++) {
            trainingSet.get(i).show();
        }
    }
    
    public void showTestSet(){
        System.out.println("TEST SET:");
        for (int i = 0; i < testSet.size(); i++) {
            testSet.get(i).show();
        }
    }
    
    public ArrayList<MyObject> makeFriendlyNeighbours(MyObject o){
        ArrayList<MyObject> tempArray = new ArrayList<MyObject>();
        
        for (int i = 0; i < trainingSet.size(); i++) {           
            MyObject tempObj = new MyObject(trainingSet.get(i));
            tempObj.distance = tempObj.distanceMyObject(o);
            tempArray.add(tempObj);
        }
        
        
        
        for(int i = 0; i < tempArray.size() - 1; i++)
            for(int j = 0; j < tempArray.size() - i - 1; j++)
                if(tempArray.get(j).distance > tempArray.get(j+1).distance)
                {
                    MyObject tempObj1 = new MyObject(tempArray.get(j));
                    MyObject tempObj2 = new MyObject(tempArray.get(j+1));
                    tempArray.set(j, tempObj2);
                    tempArray.set(j+1, tempObj1);
                    String tempString;
                    
                }
        return tempArray;
    }
    
    public void makeClasses(){
        classes = new HashMap<String, Double>(50);
        classNames = new ArrayList();
        //classes = new ArrayList();
        for (int i = 0; i < trainingSet.size(); i++) {
            //if (!(classes.contains(trainingSet[i][amountOfArguments]))){
            //    classes.add(trainingSet[i][amountOfArguments]);
                //System.out.println(trainingSet[i][amountOfArguments]);
            //}
            if (!(classes.containsKey(trainingSet.get(i).realClass))){
                classes.put(trainingSet.get(i).realClass, 0.0);
                classNames.add(trainingSet.get(i).realClass);
                //System.out.println(trainingSet[i][amountOfArguments]);
            }
        }
    }
    
    public double omegaFunction(int i, MyObject o ){
        double result = Math.pow(q, i);
        
        return result;
    }
    
    public void makeItHappen(){
        this.makeClasses();
        for (int i = 0; i < testSet.size(); i++) {
            this.makeClasses();
            ArrayList<MyObject> friendlyNeighbours = new ArrayList();
            friendlyNeighbours = makeFriendlyNeighbours(testSet.get(i));
            
            /*
            for (int n = 0; n < friendlyNeighbours.size(); n++) {
                System.out.print(friendlyNeighbours.get(n).realClass);
                System.out.println(friendlyNeighbours.get(n).distance);
            }
            */
            /*
            for (int n = 0; n < classNames.size(); n++) {
                System.out.print(" "+classNames.get(n));
                System.out.println(" "+classes.get(classNames.get(n)));
            }
            */

            for (int j = 0; j < k; j++) {
                
                classes.put(friendlyNeighbours.get(j).realClass,classes.get(friendlyNeighbours.get(j).realClass)
                      +1*omegaFunction(j,testSet.get(i)) );
                //System.out.println(friendlyNeighbours[j][0]);
                
            }
            
            double max = 0;
            String winClass = "";
            
            /*
            for (int n = 0; n < classNames.size(); n++) {
                System.out.print(" "+classNames.get(n));
                System.out.println(" "+classes.get(classNames.get(n)));
            }
            */
            
            for (int m = 0; m < classNames.size(); m++){
                if (classes.get(classNames.get(m)) > max)
                {
                    max = classes.get(classNames.get(m));
                    winClass = classNames.get(m);
                    //System.out.print(winClass+" "+classes.get(classNames.get(m)));
                    //System.out.println(" "+classNames.size());
                    
                }
            }
            
            
            //System.out.println(winClass);
            
            MyObject tempTestObj = testSet.get(i);
            tempTestObj.resultClass = winClass;
            testSet.set(i, tempTestObj);

        }
    }
    
    public void showMistakes(){
        int errorCount = 0;
        int errorPercent = 0;
        for (int i = 0; i < testSet.size(); i++){
            String tempRealClass = testSet.get(i).realClass;
            String tempResultClass = testSet.get(i).resultClass;;
            if (!(tempRealClass.equals(tempResultClass))){
                errorCount++;
            }
        }
        
        errorPercent = (errorCount *  100 / testSet.size()) ;
        
        System.out.println("--------------------------" + String.valueOf(k));
        System.out.println("k = " + String.valueOf(k));
        System.out.println("q = " + String.valueOf(q));
        System.out.println("Training set: " + String.valueOf(trainingSet.size()));
        System.out.println("Test set: " + String.valueOf(testSet.size()));
        System.out.println("Number of mistakes: " + String.valueOf(errorCount)+" ("+String.valueOf(errorPercent)+"%)");        
    }
           
}
