package barchartracerarraylist;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BarChartRacerArrayList
{
    // STEP 1: Choose which data source you would like to use.       
    //public static final String FILE_NAME = "baby-names.txt";        // Most popular baby names in the US, 1880-2018.  Source: US Social Security
    public static final String FILE_NAME = "brands.txt";            // Most valuable brands in the world, 2000-2018.  Source: Interbrand
    //public static final String FILE_NAME = "cities.txt";            // Most populous cities in the world, 1500-2018.  Source: John Burn-Murdoch
    //public static final String FILE_NAME = "cities-usa.txt";        // Most populous cities in the US, 1790-2018.  Source: US Census Bureau
    //public static final String FILE_NAME = "countries.txt";         // Most populous countries in the world, 1950-2100.  Source: United Nations
    //public static final String FILE_NAME = "football.txt";          // The best football clubs in Europe, 1960-2019.  Source clubelo.com
    //public static final String FILE_NAME = "game-of-thrones.txt";   // Chracters in Game of Thrones by screen time, S01E01-S08E06.  Source Preetish
    //public static final String FILE_NAME = "movies.txt";            // Highest-grossing movies in the US, 1982-2019.  Box Office Mojo
    //public static final String FILE_NAME = "patents.txt";           // Patents granted by country, 1980-2018.  WIPO
    //public static final String FILE_NAME = "trademarks.txt";        // Trademarks granted by country, 1980-2018.  WIPO
        
    // STEP 2: Choose how many bars you want to display.
    public static final int NUMBER_OF_BARS = 12;

    // STEP 3: Choose how many milliseconds you would like to wait in between charts.
    public static final int MILLISECONDS = 10;

    // STEP 4: Choose the window size that works for your screen.
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;
    
    // STEP 5: Run the program!
        
    public static void main(String[] args) throws InterruptedException
    {
        // Load the data into an ArrayList of BarChart objects.
        // Each BarChart object represents one slide in the animation.
        ArrayList<BarChart> barCharts = loadData(FILE_NAME);
        StdDraw.setCanvasSize(WIDTH, HEIGHT);
        StdDraw.enableDoubleBuffering();
        
        // Loop through the BarChart objects.  Draw each one and then pause
        // before drawing the next one.
        for (BarChart barChart: barCharts)
        {
            StdDraw.clear();
            barChart.draw();
            StdDraw.show();
            StdDraw.pause(MILLISECONDS);
        }
    }
    
    public static ArrayList<BarChart> loadData(String fileName)
    {
        ArrayList<BarChart> barCharts = new ArrayList<>();
        
        try (Scanner input = new Scanner(new File(fileName)))
        {
            String title = input.nextLine();
            String xAxisLabel = input.nextLine();
            String source = input.nextLine();
            input.nextLine();
            
            int numberOfRecords;
            String date = "";
            String city;
            String country;
            int value;
            String category;
            int counter;
            String line;
            String[] items;
            BarChart barChart;
            Bar bar;
            Bar[] bars;
            
            while(input.hasNextInt())
            {
                numberOfRecords = input.nextInt();
                input.nextLine();
                barChart = new BarChart(title, xAxisLabel, source);
                bars = new Bar[numberOfRecords];
                
                for (counter = 0; counter < numberOfRecords; counter++)
                {
                    line = input.nextLine();
                    items = line.split(",");
                    
                    date = items[0];
                    city = items[1];
                    country = items[2];
                    value = Integer.parseInt(items[3]);
                    category = items[4];
                    
                    bar = new Bar(city + ", " + country, value, category);
                    bars[counter] = bar;
                }
                
                Arrays.sort(bars);
                
                for (counter = 0; counter < NUMBER_OF_BARS; counter++)
                {
                    barChart.add(bars[counter]);
                }
                
                barChart.setCaption("" + date);
                barCharts.add(barChart);
                input.nextLine();
            }
        }
        catch (FileNotFoundException exception)
        {
            System.out.println(exception.getMessage());
        }
        
        return barCharts;
    }
}
