                                      /*********************************************************
                                       ***  Project Name : CS 200 Programming Assignment 1   ***
                                       ***  Author       : Seerat Sandha                     ***
                                       ***  Date         : 09/24/2023                        ***
                                       *********************************************************/

 
import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;
public class CNNSimulator
{
    public static void main (String args[])
    {
        
        Scanner scnr = new Scanner(System.in);
        System.out.println("Please enter the Dimension size of the array   (Between 2 and 8)");// prompting for array size
        int mainSize = scnr.nextInt();
        System.out.println("Please enter the seed value ");// prompting for seed value fpor randomNo.generator
        int seed = scnr.nextInt();
        seed = seed%16;
        System.out.println("please enter the size of the sliding window.");// prompting for the desired window sliding size. 
        int windowSize = scnr.nextInt();
        // Initialize flags for input validation
        boolean checkSize = true;
        boolean checkSeed = true;
        boolean checkWindowSize = true;
        while(checkSize==true) // while loop to check whether the enter size of the array is within the range (2-8)
        {
          if(mainSize>=2 && mainSize<=8)
          {
           
           checkSize=false;
          }
          else
          {
            System.out.println("<Invalid Input> YOUR ENTERED VALUE IS NOT IN RANGE");// again prompting for value in *range*
            System.out.println("<Again> Enter the dimension size of the array (Between 2 and 8)");
            mainSize = scnr.nextInt();
            checkSize = true;
          }
        }
        while(checkWindowSize==true) //while loop to check whether the enter seed value is within the range (0-15)
        {
          if(windowSize >0 && windowSize<mainSize)
          {
           checkWindowSize=false;
          }
          
          else
          {
            System.out.println("<Invalid Input> YOUR ENTERED VALUE IS NOT IN RANGE");// again prompting for value in *range*
            System.out.println("<Again> Enter the size of the sliding window (* smaller than the dimension size but,not Negative)");
            windowSize  = scnr.nextInt();              //again prompting if entered value is not within *range*
            checkWindowSize= true;
          }
        }
        
         // store the main array
        int[][] mainArray = printingMainArray(mainSize, seed, windowSize);
        //Calling to display the sum, min, max, and average of the sliding windows, using same arguments. 
        calculatingSum(mainArray, seed, mainSize, windowSize);  
        calculatingMinValue(mainArray, seed, mainSize, windowSize);
        calculatingMaxValue(mainArray, seed, mainSize, windowSize);
        
}
// Method to generate and print the main array
public static int[][] printingMainArray(int mainSize, int seed, int windowSize)
{
    int[][] mainRandArray = new int[mainSize][mainSize];  // declaring Main Array
    Random rand = new Random(seed);
    System.out.println();
    System.out.println("Main array:"+"\n");
    for (int i = 0; i < mainSize; i++)  // using two for loops to fill the 2D array.
    {
        for (int j = 0; j < mainSize; j++)
        {
            mainRandArray[i][j] = rand.nextInt(16);  // Adjusted the random range
            if (mainRandArray[i][j] <= 9) // for formatting (one digit to two digits)
            {
                System.out.print("0" + mainRandArray[i][j] + " ");
            }else
            {
                System.out.print(mainRandArray[i][j] + " ");
            }
        }
        System.out.println();
    }
    return mainRandArray;  // Return the generated array  
}
// Method to calculate and print the sum of sliding windows
public static void calculatingSum(int mainArray[][], int seed, int mainSize, int windowSize)
{
    int [][] sumArray = new int [mainSize][mainSize];  //declaring sumArray to store the sum of sliding window elements. 
    System.out.println();
    System.out.println("Sum array: "+"\n");
    boolean checkFirstDigit = false;
    boolean checkSecondDigit = false;
    boolean checkThirdDigit= false;
    for(int i =0; i < mainSize-windowSize+1; i++) /* iterates over rows of the mainArray,  
                                                 using for loops to just adding the elements from the desired sliding window
                                                 where using the formula: n-k+1. 
                                             */
    {
        for(int j=0;j< mainSize-windowSize+1;j++)//iterates over columns of the mainArray
        {
            int slidingSum = 0;            // storing the value of sum after completing each sliding. 

          for (int a = 0;  a< windowSize; ++a) // again these two nested loops iterates over the rows and columms,respectively
            {
                for (int  b = 0; b < windowSize; ++b)  
                {
                    slidingSum += mainArray[i + a][j+ b];/* within the sliding window, the value at mainArray[i + a][j + b] 
                     is added to slidingSum.Continues until all elements add (within the sliding window) */
                }
            }
          sumArray[i][j] = slidingSum; // now the sum of the elements is stores at where we start in the sliding window. 
        
   }
}
boolean checkThreeDigitNo = false; // to check whether the array contain any three digit number for proper formatting 

// Check if the array contains a three-digit number
for (int i = 0; i < mainSize-windowSize+1; i++) 
{
    for (int j = 0; j < mainSize-windowSize+1; j++) 
     {
        if (sumArray[i][j] >= 100)
        {                         // if there is any three didgit number then the variable checkThree digit number will be true
            checkThreeDigitNo = true; 
            break;
        }
    }
    if (checkThreeDigitNo) //if not then break
    {
        break;
    }
}

// Now, format and print the array 
for (int i = 0; i < mainSize -windowSize+1; i++) {
    for (int j = 0; j < mainSize-windowSize+1; j++) 
    {
        int number = sumArray[i][j];
        
        if (checkThreeDigitNo) // if array conatian three digit number then the following fomattting system will be used 
        {  // its ti
            if (number < 10)
            // if no smaller than 10(* means one didgit number), then printing  "00" in front of the number to as three didgit number
            {
                System.out.print("00" + number + " ");
            } else if (number < 100) // for two digit no. priting one zero on the front. 
            {
                System.out.print("0" + number + " ");
            } else    // else number is already three didgit then no need to print any zero on the front 
            { System.out.print(number + " ");
            }
        }
        else
        {
            // If there are no three-digit numbers, print as it is
            System.out.print(number + " ");
        }
    }
    System.out.println();
}
calculatingAverage(sumArray, mainSize, windowSize);  /*calling the calculatingAverage inside the calculatingSum, and 
                                                         passing the sumArray as an argument for further processing.*/
                                                        
}
// Method to calculate and print the average of sliding windows
public static void calculatingAverage(int sumArray[][], int mainSize, int windowSize) {
  int[][] averageArray = new int[mainSize - windowSize + 1][mainSize - windowSize + 1];
  /* Here, we use the formula (n - k + 1) to determine the size/dimensions of the averageArray.'n' represents the original 
     size/dimension of the array, and 'k' represents the window size.This array will store the average values calculated 
     based on the sliding window.*/
  System.out.println();
  System.out.println("Avg array: "+"\n");
  for (int i = 0; i < mainSize - windowSize + 1; i++) /* here we using the sumArray and just dividing it by the no. of elements in 
                                                    the sliding window */
  {
       for (int j = 0; j < mainSize - windowSize + 1; j++) {
            int slidingAvg = sumArray[i][j] / (windowSize * windowSize); /*(no.of elements = in the sliding window, which is
                                                             determined by windowSize * windowSize) */
            
            if (slidingAvg <= 9)   // for formatting the printing array 
            {
               System.out.print("0" + slidingAvg + " ");
            } 
            else 
            {
               System.out.print(slidingAvg + " ");
            }
        } System.out.println();
    }
}
// Method to calculate and print the Minimum value  of sliding windows
public static void calculatingMinValue(int mainArray[][], int seed, int mainSize, int windowSize)
{
    int [][] minArray = new int [mainSize][mainSize]; 
    System.out.println();
    System.out.println("Min array:"+"\n");
    int initialMin = Integer.MAX_VALUE; // Initialize to a large value possible
    for (int i = 0; i < mainSize - windowSize + 1; i++) 
    {
      for (int j = 0; j < mainSize - windowSize + 1; j++) 
      { int slidingMin = Integer.MAX_VALUE; // Initialize to a large value for each window

        for (int a = 0; a < windowSize; ++a) 
        {
          for (int b = 0; b < windowSize; ++b)
          {
             int currentElement = mainArray[i + a][j + b]; 
             slidingMin = Math.min(slidingMin, currentElement);/*updating the slidingMin value to the minimum no. in the sliding 
                                                                  window*/
          }
        }
        
        initialMin = Math.min(initialMin, slidingMin);// updating the intialMin(max value)to the possible minimumm value.
        minArray[i][j]= slidingMin;  // minimum value storing in an array  
        if(slidingMin<=9)  // for formatting the printing array 
        {
          System.out.print("0" + slidingMin+" ");
        }
        else
        {
           System.out.print(slidingMin+" ");
        }
        }
      System.out.println();
    }
        
}
// Method to calculate and print the maximum value of sliding windows
public static void calculatingMaxValue (int mainArray[][], int seed, int mainSize, int windowSize){
    int [][] maxArray = new int [mainSize][mainSize]; 
    System.out.println();
    System.out.println("Max array:"+"\n");
    int initialMax = Integer.MIN_VALUE; // Intialize to minimum  value possible 
    for (int i = 0; i < mainSize - windowSize + 1; i++) 
{
    for (int j = 0; j < mainSize - windowSize + 1; j++) 
    {
        int slidingMax = Integer.MIN_VALUE; // Initialize to a small value for each window

        for (int a = 0; a < windowSize; ++a) 
        {
            for (int b = 0; b < windowSize; ++b)
            {
                int currentElement = mainArray[i + a][j + b];
                slidingMax = Math.max(slidingMax, currentElement); 
                                                          /*updating the slidingMax value to the maximum no. in the sliding                                                  window*/
            }
        }

                                                                  // Update the overall minimum value to maximum value 
        initialMax = Math.max(initialMax, slidingMax);
        maxArray[i][j] = slidingMax; // Storing in an array
        if(slidingMax<=9)
        {                                            // for formatting the printing array 
          System.out.print("0" + slidingMax+" ");
        }
        else
        {
          System.out.print(slidingMax+" ");
        }
        }
        System.out.println();
}
}
}