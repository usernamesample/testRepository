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

/**
 *
 * @author 1
 */
public class NNA {

    /**
     * @param args the command line arguments
     */
    

    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here

        
        Algorythm algo = new Algorythm();
        //algo.getTrainingSetFromFile("D:\\projects\\machinelearning\\inp1.txt");
        //algo.getTestSetFromFile("D:\\projects\\machinelearning\\inp2.txt");
        algo.getEverythingFromFile("D:\\projects\\machinelearning\\iris.txt");
        //algo.showTrainingSet();
        //algo.showTestSet();
        algo.makeItHappen();
        algo.showTestSet();
        algo.showMistakes();
    }
    

    

    
}
