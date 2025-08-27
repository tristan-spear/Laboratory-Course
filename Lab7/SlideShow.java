//--------------------------------------------------------------------
//
//  Laboratory 7, In-lab Exercise 3                   SlideShow.jshl
//
//  (Shell) Slide show program
//
//  The student is to complete all missing or incomplete method
//     implementations for each class
//
//--------------------------------------------------------------------

// Displays a slide show.

import java.io.*;


class Slide
{
    // constants
    static final int SLIDE_HEIGHT = 10;   // Slide dimensions
    static final int SLIDE_WIDTH  = 36;

    // Data members
    private String [] image =            // Slide image
            new String [SLIDE_HEIGHT];
    private long pause;                  // Seconds to pause after
                                         //  displaying slide

    public boolean read ( BufferedReader bufFinReader )
        // Read a slide from inFile. Returns false at EOF.
    {
        try {
            pause = Integer.parseInt(bufFinReader.readLine()) * 500;
        } catch (Exception e) { return false; }

        for(int i = 0; i < SLIDE_HEIGHT; ++i)
            try {
                image[i] = bufFinReader.readLine();
            } catch (Exception e) { return false; }

        return true;
    }

    public void display ( )
        // Display a slide and pause or sleep.
    {
        for(int i = 0; i < SLIDE_HEIGHT; ++i)
            System.out.println(image[i]);

        try {
            Thread.sleep(pause);
        } catch (Exception e) {}
    }
} // class Slide

class SlideShow
{
    static final int NUM_LINES = 50;    // Number of lines on the screen

    public static void main ( String[ ] args ) throws IOException,
                                                      InterruptedException
    {
        SList slideShow = new SList( );     // Slide show
        Slide currSlide;                    // Slide
        String filename;                    // Name of slide show file
        FileInputStream slideFile = null;   // The slide input file
        boolean succeeded = false;          // Succeeded in opening file

        //-----------------------------------------------------------------
        // Initialize reader - To read filename from keyboard
        BufferedReader reader =
                       new BufferedReader(new InputStreamReader(System.in));

        // Open the specified slide file.
        while ( !succeeded )
        {
            System.out.print("\nEnter the name of the slide show file : ");
            filename = reader.readLine( );
            try
            {
                slideFile = new FileInputStream(filename);
                succeeded = true;           // Assume file is found
            }
            catch (FileNotFoundException e)
            {
                System.out.print("File Not Found: " + filename);
                succeeded = false;          // File was not found
            }
        }

        // Buffer the file input stream
        BufferedReader bufSlideReader =
                     new BufferedReader(new InputStreamReader(slideFile));

        // Read in the slides one-by-one & insert in list.


        currSlide = new Slide();

        int i = 0;
        while(currSlide.read(bufSlideReader))
        {
            //currSlide.display();
            slideShow.insert(currSlide);
            currSlide = new Slide();
            //System.out.println(i++);
        }

        // Close the file.
        bufSlideReader.close();

        // Display the slide show slide-by-slide.
        slideShow.gotoBeginning();

        if(!slideShow.isEmpty()) {
            do {
                for(int s = 0; s < 25; ++s)
                    System.out.println("\n");

                ((Slide)slideShow.getCursor()).display();

            } while (slideShow.gotoNext());
        }
    } // main
} // class SlideShow
