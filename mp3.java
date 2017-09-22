/*--------------------------------------------------------------------------------------*/
/*  mp3.java  -  sorts and edits songs in a playlists and allows user to handle their   */
/*               songs and information through a menu                                   */
/*--------------------------------------------------------------------------------------*/
/*  Author: Enoch Wong                                                                  */
/*  Date: May 4, 2015                                                                   */
/*--------------------------------------------------------------------------------------*/
/*  Input: playlist, songs, information                                                 */
/*  Output: sorted songs, backup files                                                  */
/*--------------------------------------------------------------------------------------*/

import java.io.*;
import java.util.*;
import java.text.*;

public class mp3
{
    //open and view method
    static void openAndView (String[] songs, String[] artist, String[] genre, int[] numberOfSongs) throws IOException
    {
	BufferedReader stdin = new BufferedReader (new InputStreamReader (System.in));

	//declare variables
	String random;
	int screenMax = 12;

	System.out.println ();
	System.out.println ("Your list of songs: ");

	//for loop to display all the songs, artists, and genres (stored in arrays)
	for (int x = 0 ; x < numberOfSongs [0] ; x++)
	{
	    //user friendliness
	    if (x % screenMax == 0 && x != 0)
	    {
		System.out.print ("Please press enter to continue: ");
		random = stdin.readLine ();
		System.out.println ();
	    }

	    else
	    {
		System.out.println ();
		System.out.println (songs [x] + " by " + artist [x] + " as " + genre [x]);
	    }
	}

	System.out.println ();
	System.out.println ("That is the end of your playlist.");
	System.out.print ("Please press enter to continue: ");
	random = stdin.readLine ();
    }


    //add method
    static void add (String[] songs, String[] artist, String[] genre, int[] numberOfSongs, String name, int MAX) throws IOException
    {
	//open file to append
	BufferedWriter add = new BufferedWriter (new FileWriter (name + ".txt", true));
	BufferedReader stdin = new BufferedReader (new InputStreamReader (System.in));

	//declare variables
	int counter = 0;
	String answer = ("N");

	//do loop to repeat adding proccess (if desired)
	do
	{
	    //make sure another song can be added
	    if (numberOfSongs [0] > MAX)
	    {
		System.out.println ("Sorry, you are out of space");
	    }

	    //find out what to add
	    else
	    {
		System.out.print ("What song would you like add?: ");
		String newSong = stdin.readLine ();
		add.write (newSong);
		songs [numberOfSongs [0]] = newSong;

		add.newLine ();
		System.out.print ("Who is the song by?: ");
		String newArtist = stdin.readLine ();
		add.write (newArtist);
		artist [numberOfSongs [0]] = newArtist;

		add.newLine ();
		System.out.print ("What is the genre?: ");
		String newGenre = stdin.readLine ();
		add.write (newGenre);
		add.newLine ();
		genre [numberOfSongs [0]] = newGenre;

		//give user option to add another
		System.out.println ();
		System.out.print ("Would you like to add another a new song? (Y/N): ");
		answer = stdin.readLine ();

		numberOfSongs [0]++;
	    }
	}
	while (answer.equals ("y") || answer.equals ("Y"));
	//close appended file
	add.close ();
    }


    //search method
    static void search (String[] songs, String[] artist, String[] genre, int[] numberOfSongs) throws IOException
    {
	BufferedReader stdin = new BufferedReader (new InputStreamReader (System.in));

	//declare variables
	String search;
	int category, counter = 0;
	boolean found;

	do
	{
	    //in case song is not found
	    found = false;
	    counter = 0;

	    //what to search by
	    System.out.println ();
	    System.out.print ("1) Search by Song \n2) Search by Artist \n3) Search by Genre \n0) Exit \nWhat would you like to do?: ");
	    category = Integer.parseInt (stdin.readLine ());

	    switch (category)
	    {
		case 1:
		    //what to search for
		    System.out.print ("What would you like to search for?: ");
		    search = stdin.readLine ();
		    System.out.println ();

		    //for loop to search through the array
		    for (int x = 0 ; x < numberOfSongs [0] ; x++)
		    {
			if (songs [x].equals (search))
			{
			    System.out.println (songs [x] + " by " + artist [x] + " as " + genre [x]);

			    found = true;
			}
		    }
		    break;

		case 2:
		    //what to search for
		    System.out.print ("What would you like to search for?: ");
		    search = stdin.readLine ();
		    System.out.println ();

		    //for loop to search through the array
		    for (int x = 0 ; x < numberOfSongs [0] ; x++)
		    {
			if (artist [x].equals (search))
			{
			    System.out.println (songs [x] + " by " + artist [x] + " as " + genre [x]);

			    found = true;
			}
		    }
		    break;

		case 3:
		    //what to search for
		    System.out.print ("What would you like to search for?: ");
		    search = stdin.readLine ();
		    System.out.println ();

		    //for loop to search through the array
		    for (int x = 0 ; x < numberOfSongs [0] ; x++)
		    {
			if (genre [x].equals (search))
			{
			    System.out.println (songs [x] + " by " + artist [x] + " as " + genre [x]);

			    found = true;
			}
		    }
		    break;

		case 0:
		    //counter to end the loop
		    counter = 1;
		    break;

		default:
		    //in case the user cannot follow instructions properly
		    System.out.println ("What you have entered is incorrect.");
		    break;
	    }

	    //tell user if item has not been found
	    if (!found)
	    {
		System.out.println ("What you have searched for has not been found. Please try again.");
	    }
	}
	while (counter == 0);
    }


    //sort method
    static void sort (String[] songs, String[] artist, String[] genre, int[] numberOfSongs, String name) throws IOException
    {
	//declare variables
	String backup, random;
	int exit = 0, screenMax = 12;

	//open file for writing
	BufferedWriter sort = new BufferedWriter (new FileWriter (name + ".txt"));
	BufferedReader stdin = new BufferedReader (new InputStreamReader (System.in));

	do
	{
	    //what to sort by
	    System.out.println ();
	    System.out.print ("1) Sort by Song \n2) Sort by Artist \n3) Sort by Genre \n0) Exit \nWhat would you like sort by?: ");
	    int sortBy = Integer.parseInt (stdin.readLine ());

	    switch (sortBy)
	    {
		case 1:
		    //sort files by song names
		    for (int x = 0 ; x < numberOfSongs [0] - 1 ; x++)
		    {
			for (int y = 0 ; y < numberOfSongs [0] - 1 - x ; y++)
			{
			    if ((songs [y].compareTo (songs [y + 1])) > 0)
			    {
				backup = songs [y];
				songs [y] = songs [y + 1];
				songs [y + 1] = backup;

				backup = artist [y];
				artist [y] = artist [y + 1];
				artist [y + 1] = backup;

				backup = genre [y];
				genre [y] = genre [y + 1];
				genre [y + 1] = backup;
			    }

			    //if song names are the same...
			    else if ((songs [y].compareTo (songs [y + 1])) == 0)
			    {
				//...sort by artist
				if ((artist [y].compareTo (artist [y + 1])) > 0)
				{
				    backup = songs [y];
				    songs [y] = songs [y + 1];
				    songs [y + 1] = backup;

				    backup = artist [y];
				    artist [y] = artist [y + 1];
				    artist [y + 1] = backup;

				    backup = genre [y];
				    genre [y] = genre [y + 1];
				    genre [y + 1] = backup;
				}
			    }
			}
		    }

		    //write (sorted) songs into file
		    for (int i = 0 ; i < numberOfSongs [0] ; i++)
		    {
			sort.write (songs [i]);
			sort.newLine ();

			sort.write (artist [i]);
			sort.newLine ();

			sort.write (genre [i]);
			sort.newLine ();

			//user friendliness
			if (i % screenMax == 0 && i != 0)
			{
			    System.out.print ("Please press enter to continue: ");
			    random = stdin.readLine ();
			    System.out.println ();
			}

			//display songs for user
			else
			{
			    System.out.println ();
			    System.out.println (songs [i] + " by " + artist [i] + " as " + genre [i]);
			}
		    }

		    System.out.println ();
		    System.out.println ("Your playlist has been sorted.");
		    break;

		case 2:
		    //sort songs by artist
		    for (int x = 0 ; x < numberOfSongs [0] - 1 ; x++)
		    {
			for (int y = 0 ; y < numberOfSongs [0] - 1 - x ; y++)
			{
			    if ((artist [y].compareTo (artist [y + 1])) > 0)
			    {
				backup = songs [y];
				songs [y] = songs [y + 1];
				songs [y + 1] = backup;

				backup = artist [y];
				artist [y] = artist [y + 1];
				artist [y + 1] = backup;

				backup = genre [y];
				genre [y] = genre [y + 1];
				genre [y + 1] = backup;
			    }

			    //if artists are the same...
			    else if ((artist [y].compareTo (artist [y + 1])) == 0)
			    {
				//...sort by song name
				if ((songs [y].compareTo (songs [y + 1])) > 0)
				{
				    backup = songs [y];
				    songs [y] = songs [y + 1];
				    songs [y + 1] = backup;

				    backup = artist [y];
				    artist [y] = artist [y + 1];
				    artist [y + 1] = backup;

				    backup = genre [y];
				    genre [y] = genre [y + 1];
				    genre [y + 1] = backup;
				}
			    }
			}
		    }

		    //write (sorted) songs into file
		    for (int i = 0 ; i < numberOfSongs [0] ; i++)
		    {
			sort.write (songs [i]);
			sort.newLine ();

			sort.write (artist [i]);
			sort.newLine ();

			sort.write (genre [i]);
			sort.newLine ();

			//user friendliness
			if (i % screenMax == 0 && i != 0)
			{
			    System.out.print ("Please press enter to continue: ");
			    random = stdin.readLine ();
			    System.out.println ();
			}

			//display songs for user
			else
			{
			    System.out.println ();
			    System.out.println (songs [i] + " by " + artist [i] + " as " + genre [i]);
			}
		    }

		    System.out.println ();
		    System.out.println ("Your playlist has been sorted.");
		    break;

		case 3:
		    //sort songs by genre
		    for (int x = 0 ; x < numberOfSongs [0] - 1 ; x++)
		    {
			for (int y = 0 ; y < numberOfSongs [0] - 1 - x ; y++)
			{
			    if ((genre [y].compareTo (genre [y + 1])) > 0)
			    {
				backup = songs [y];
				songs [y] = songs [y + 1];
				songs [y + 1] = backup;

				backup = artist [y];
				artist [y] = artist [y + 1];
				artist [y + 1] = backup;

				backup = genre [y];
				genre [y] = genre [y + 1];
				genre [y + 1] = backup;
			    }

			    //if genres are the same...
			    else if ((genre [y].compareTo (genre [y + 1])) == 0)
			    {
				//...sort by song name
				if ((songs [y].compareTo (songs [y + 1])) > 0)
				{
				    backup = songs [y];
				    songs [y] = songs [y + 1];
				    songs [y + 1] = backup;

				    backup = artist [y];
				    artist [y] = artist [y + 1];
				    artist [y + 1] = backup;

				    backup = genre [y];
				    genre [y] = genre [y + 1];
				    genre [y + 1] = backup;
				}

				//if song names are the same...
				else if ((songs [y].compareTo (songs [y + 1])) == 0)
				{
				    //...sort by artist
				    if ((artist [y].compareTo (artist [y + 1])) > 0)
				    {
					backup = songs [y];
					songs [y] = songs [y + 1];
					songs [y + 1] = backup;

					backup = artist [y];
					artist [y] = artist [y + 1];
					artist [y + 1] = backup;

					backup = genre [y];
					genre [y] = genre [y + 1];
					genre [y + 1] = backup;
				    }
				}
			    }
			}
		    }

		    //write (sorted) songs into file
		    for (int i = 0 ; i < numberOfSongs [0] ; i++)
		    {
			sort.write (songs [i]);
			sort.newLine ();

			sort.write (artist [i]);
			sort.newLine ();

			sort.write (genre [i]);
			sort.newLine ();

			//user friendliness
			if (i % screenMax == 0 && i != 0)
			{
			    System.out.print ("Please press enter to continue: ");
			    random = stdin.readLine ();
			    System.out.println ();
			}

			//display songs for user
			else
			{
			    System.out.println ();
			    System.out.println (songs [i] + " by " + artist [i] + " as " + genre [i]);
			}
		    }

		    System.out.println ();
		    System.out.println ("Your playlist has been sorted.");
		    break;

		case 0:
		    exit = 1;
		    break;

		default:
		    //in case the user cannot follow instructions properly
		    System.out.println ("What you have entered is incorrect.");
		    break;
	    }
	}
	while (exit == 0);
	//save and close file
	sort.close ();
    }


    //backup method
    static void backup (String[] songs, String[] artist, String[] genre, int[] numberOfSongs, String[] names, int count) throws IOException
    {
	//declare variables
	boolean existing;
	String nameBackup;
	int userResponse;

	BufferedReader stdin = new BufferedReader (new InputStreamReader (System.in));

	do
	{
	    //declare variables
	    userResponse = 0;
	    //in case username is already taken
	    existing = false;

	    //name the new file
	    System.out.println ();
	    System.out.print ("What would you name the file to backup?: ");
	    nameBackup = stdin.readLine ();

	    //check for existing username
	    for (int x = 0 ; x < count ; x++)
	    {
		if (names [x].equals (nameBackup))
		{
		    existing = true;
		    System.out.println ("The username already exists.");
		    x = count;
		    System.out.println ();
		}
	    }

	    if (!existing)
	    {
		//create the new file
		BufferedWriter backup = new BufferedWriter (new FileWriter (nameBackup + ".txt"));

		//transport all the songs
		for (int x = 0 ; x < numberOfSongs [0] ; x++)
		{
		    backup.write (songs [x]);
		    backup.newLine ();

		    backup.write (artist [x]);
		    backup.newLine ();

		    backup.write (genre [x]);
		    backup.newLine ();
		}

		//save and close file
		backup.close ();

		//add the new playlist into database
		//open database file for appending
		BufferedWriter database = new BufferedWriter (new FileWriter ("names.txt", true));
		database.write (nameBackup);
		database.newLine ();
		//save and close file
		database.close ();

		System.out.println ("A new file has been created under the name " + nameBackup + ".");
	    }

	    //give user option to try again
	    else
	    {
		System.out.print ("Would you like to (0)Exit or (1) Try Again?: ");
		userResponse = Integer.parseInt (stdin.readLine ());
	    }
	}
	while (userResponse == (1));
    }


    //delete method
    static void delete (String[] songs, String[] artist, String[] genre, int[] numberOfSongs, String name) throws IOException
    {
	BufferedReader stdin = new BufferedReader (new InputStreamReader (System.in));

	//declare variables
	String deleteSong, loop;
	boolean found = false;

	do
	{
	    //in case not found
	    found = false;

	    //ask user for song to delete
	    System.out.println ();
	    System.out.print ("What song would you like to delete?: ");
	    deleteSong = stdin.readLine ();

	    //search for the song
	    for (int x = 0 ; x < numberOfSongs [0] ; x++)
	    {
		if (songs [x].equals (deleteSong))
		{
		    found = true;
		    //verify the deletion
		    System.out.print ("Do you want to delete '" + songs [x] + " by " + artist [x] + "? (Y/N): ");
		    String response = stdin.readLine ();

		    if (response.equals ("Y") || response.equals ("y"))
		    {
			//delete song
			for (int y = x ; y < numberOfSongs [0] ; y++)
			{
			    songs [y] = songs [y + 1];
			    artist [y] = artist [y + 1];
			    genre [y] = genre [y + 1];
			}
			System.out.println ("The song has been deleted.");
			//subtract total number of songs
			numberOfSongs [0]--;
		    }
		}
	    }

	    //notify user if the song to be deleted was not found
	    if (!found)
	    {
		System.out.println ("The song you want to delete was not found.");
	    }

	    //write songs (without the deleted one) into file
	    else
	    {
		//open file for writing
		BufferedWriter delete = new BufferedWriter (new FileWriter (name + ".txt"));

		//write
		for (int i = 0 ; i < numberOfSongs [0] ; i++)
		{
		    delete.write (songs [i]);
		    delete.newLine ();

		    delete.write (artist [i]);
		    delete.newLine ();

		    delete.write (genre [i]);
		    delete.newLine ();
		}
		//save and close the file
		delete.close ();
	    }

	    //give user option to delete another song
	    System.out.print ("Delete another song? (Y/N): ");
	    loop = stdin.readLine ();
	}
	while (loop.equals ("Y") || loop.equals ("y"));
    }


    //edit method
    static void edit (String[] songs, String[] artist, String[] genre, int[] numberOfSongs, String name) throws IOException
    {
	BufferedReader stdin = new BufferedReader (new InputStreamReader (System.in));

	//declare variables
	boolean found = false;
	String editName, editArtist, editGenre, loop;

	do
	{
	    //in case song is not found
	    found = false;

	    //search for the song to edit
	    System.out.println ();
	    System.out.print ("What song would you like to edit?: ");
	    String editSong = stdin.readLine ();

	    //search for song to edit
	    for (int x = 0 ; x < numberOfSongs [0] ; x++)
	    {
		if (songs [x].equals (editSong))
		{
		    found = true;
		    //verify the edit
		    System.out.print ("Do you want to edit '" + songs [x] + " by " + artist [x] + "? (Y/N): ");
		    String response = stdin.readLine ();

		    //ask for new name, artist, and genre
		    if (response.equals ("Y") || response.equals ("y"))
		    {
			System.out.print ("What would you like to change the name to?: ");
			editName = stdin.readLine ();
			songs [x] = editName;

			System.out.print ("What would you like to change the artist to?: ");
			editArtist = stdin.readLine ();
			artist [x] = editArtist;

			System.out.print ("What would you like to change the genre to?: ");
			editGenre = stdin.readLine ();
			genre [x] = editGenre;
		    }
		    System.out.println ("The song has been edited.");
		}
	    }

	    //notify user that the song to be edited was not found
	    if (!found)
	    {
		System.out.println ("The song you want to edit was not found.");
	    }

	    //write songs (including edited one) into file
	    else
	    {
		//open file for writing
		BufferedWriter edit = new BufferedWriter (new FileWriter (name + ".txt"));

		//write
		for (int i = 0 ; i < numberOfSongs [0] ; i++)
		{
		    edit.write (songs [i]);
		    edit.newLine ();

		    edit.write (artist [i]);
		    edit.newLine ();

		    edit.write (genre [i]);
		    edit.newLine ();
		}
		//save and close file
		edit.close ();
	    }

	    //give user option to edit another song
	    System.out.print ("Edit another song? (Y/N): ");
	    loop = stdin.readLine ();
	}
	while (loop.equals ("Y") || loop.equals ("y"));
    }


    //properties method
    static void properties (int[] numberOfSongs)
    {
	System.out.println ("You have " + numberOfSongs [0] + " songs in your playlist.");
	System.out.println ("You have " + (150 - numberOfSongs [0]) + " spaces remaining.");
    }


    //MAIN MENU
    public static void main (String str[]) throws IOException
    {
	BufferedReader stdin = new BufferedReader (new InputStreamReader (System.in));
	DecimalFormat df = new DecimalFormat ("#.##");

	//declare variables and arrays
	final int MAX = 150;
	String name, line = null;
	String line1 = null;
	boolean found = false;
	int method, arraycount = 0, count = 0, exitMenu = 0;
	int[] numberOfSongs = new int [1];
	String[] names = new String [MAX];
	String[] songs = new String [MAX];
	String[] artist = new String [MAX];
	String[] genre = new String [MAX];

	//loop the menu until the user stops it
	do
	{
	    //reset the count
	    numberOfSongs [0] = 0;

	    //resets arrays (after user is switched)
	    for (int x = 0 ; x < MAX ; x++)
	    {
		songs [x] = null;
		artist [x] = null;
		genre [x] = null;
	    }

	    //manners are important
	    System.out.print ("Hello. What is your name?: ");
	    name = stdin.readLine ();

	    //open database reader for reading
	    BufferedReader reader = new BufferedReader (new FileReader ("names.txt"));

	    //read the names of all existing users
	    while ((line = reader.readLine ()) != null)
	    {
		names [count] = line;
		count++;
	    }

	    //save and close file
	    reader.close ();

	    //search to see if the user has an existing playlist...
	    for (int x = 0 ; x < count ; x++)
	    {
		if ((names [x].equals (name)))
		{
		    found = true;
		}
	    }

	    //...if the user has none, create a new one
	    if (!found)
	    {
		System.out.println ("Sorry " + name + ", there is no database under your name. One is being made as you read this.");

		//add name of new user to database
		BufferedWriter writer = new BufferedWriter (new FileWriter ("names.txt", true));
		writer.write (name);
		writer.newLine ();
		writer.close ();

		//create new playlist
		BufferedWriter newDatabase = new BufferedWriter (new FileWriter (name + ".txt"));
	    }

	    //...if the user has one, welcome them back because manners are important
	    else
	    {
		System.out.println ("Welcome back, " + name + "!");
	    }

	    //open playlist to read
	    BufferedReader playlist = new BufferedReader (new FileReader (name + ".txt"));

	    //read songs, artists, and genres
	    while ((line1 = playlist.readLine ()) != null)
	    {
		//notify user if their playlist is full
		if (numberOfSongs [0] > MAX)
		{
		    System.out.println ("Your playlist is full.");
		}

		songs [numberOfSongs [0]] = line1;
		artist [numberOfSongs [0]] = playlist.readLine ();
		genre [numberOfSongs [0]] = playlist.readLine ();
		//calculate the total amount of songs
		numberOfSongs [0]++;
	    }

	    //close file after reading
	    playlist.close ();

	    //loop until the user is finished with a playlist
	    do
	    {
		//declare counter for loops
		exitMenu = 0;

		//give the user their options
		System.out.println ();
		System.out.print ("************************************\n1) Open and View the collection \n2) Add to the collection \n3) Search by: category, titles, artists \n4) Sort by: category, titles, artists \n5) Create a backup of the collection \n6) Delete a song from the collection \n7) Edit \n8) Properties \n9) Switch users  \n0) Exit \n************************************\nWhat would you like to do today?: ");
		method = Integer.parseInt (stdin.readLine ());

		//open respective methods to run
		switch (method)
		{
		    case 1:
			openAndView (songs, artist, genre, numberOfSongs);
			break;
		    case 2:
			add (songs, artist, genre, numberOfSongs, name, MAX);
			break;
		    case 3:
			search (songs, artist, genre, numberOfSongs);
			break;
		    case 4:
			sort (songs, artist, genre, numberOfSongs, name);
			break;
		    case 5:
			backup (songs, artist, genre, numberOfSongs, names, count);
			break;
		    case 6:
			delete (songs, artist, genre, numberOfSongs, name);
			break;
		    case 7:
			edit (songs, artist, genre, numberOfSongs, name);
			break;
		    case 8:
			properties (numberOfSongs);
			break;
		    case 9:
			System.out.println ();
			exitMenu = 1;
			break;
		    case 0:
			exitMenu = 2;
			System.out.println ("Goodbye.");
			break;
		    default:
			System.out.println ("What you have entered is invalid. Please try again.");
			break;
		}
	    }
	    //allows user to switch playlists (back to first 'do' loop)
	    while (exitMenu == 0);
	}
	//closes program
	while (exitMenu == 1);
    }
}



