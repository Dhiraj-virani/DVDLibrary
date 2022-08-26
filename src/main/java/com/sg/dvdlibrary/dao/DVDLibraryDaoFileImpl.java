/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVDFile;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Dhiraj
 */
public class DVDLibraryDaoFileImpl implements DVDLibraryDao {

    private Map<String, DVDFile> dvdFiles = new HashMap<>();
    public static final String DVD_FILE = "dvdlibrary.txt";
    public static final String DELIMITER = "::";

    @Override
    public DVDFile addDVDFile(String title, DVDFile dvdFile)
    throws DVDLibraryDaoException{
        loadLibrary();
        DVDFile prevFile = dvdFiles.put(title, dvdFile);
        saveLibrary();
        return prevFile;
    }

    @Override
    public DVDFile searchDVDSongByTitle(String title)
    throws DVDLibraryDaoException {
        return dvdFiles.get(title);
    }

    @Override
    public DVDFile songInfoByTitle(String title) 
    throws DVDLibraryDaoException {
        loadLibrary();
        return dvdFiles.get(title);
    }

    @Override
    public List<DVDFile> getAllTheDVDCollection()
    throws DVDLibraryDaoException {
        loadLibrary();
        return new ArrayList<DVDFile>(dvdFiles.values());
    }

    @Override
    public DVDFile removeDVDFile(String title) 
    throws DVDLibraryDaoException{
        loadLibrary();
        DVDFile removedSong = dvdFiles.remove(title);
        saveLibrary();
        return removedSong;
    }

    private DVDFile unmarshallDvdFile(String dvdFile) {
        //dvdFile is expecting a line read from the file
        // we split our data using delimeter ::

        String[] dvdSongs = dvdFile.split(DELIMITER);

        //We get the firt data as a key which is the title of the song
        String title = dvdSongs[0];

        DVDFile listFromFile = new DVDFile(title);

        // we will get the values using the index values of object and assign
        //it to the correct values
        listFromFile.setReleaseDate(dvdSongs[1]);
        listFromFile.setRating(Float.parseFloat(dvdSongs[2]));
        listFromFile.setDirectorName(dvdSongs[3]);
        listFromFile.setStudioName(dvdSongs[4]);
        listFromFile.setUserReview(dvdSongs[5]);

        return listFromFile;

    }

    private void loadLibrary() throws DVDLibraryDaoException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(DVD_FILE)));
        } catch (FileNotFoundException e) {
            throw new DVDLibraryDaoException(
                    "-_- Could not load roster data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentSong holds the most recent student unmarshalled
        DVDFile currentSong;
        // Go through DVD_FILE line by line, decoding each line into a 
        // Song object by calling the unmarshallSong method.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into a Songs
            currentSong = unmarshallDvdFile(currentLine);

            // We are going to use the title as the map key for our DVD file object.
            // Put currentSong into the map using title as the key
            dvdFiles.put(currentSong.getTitle(), currentSong);
        }
        // close scanner
        scanner.close();
    }

    private String marshallDvdFile(DVDFile file) {
        // We need to turn a Dvd file object into a line of text for our file.
        // For example, we need an in memory object to end up like this:
        // Sorry::02/20/2019::3.5::Justin::Rockstar::top hit

        // we need to use our DELIMITER as a kind of spacer. 
        // Start with the title , since that's supposed to be first.
        String fileAsText = file.getTitle() + DELIMITER;

        // add the rest of the properties in the correct order:
        // release date
        fileAsText += file.getReleaseDate() + DELIMITER;

        // rating
        fileAsText += file.getRating() + DELIMITER;

        // Director name.
        fileAsText += file.getDirectorName() + DELIMITER;

        // studio name
        fileAsText += file.getStudioName() + DELIMITER;

        //UserReview
        fileAsText += file.getUserReview();

        // We have now turned a Dvd File to text! Return it!
        return fileAsText;
    }

    private void saveLibrary() throws DVDLibraryDaoException {
        
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(DVD_FILE));
        } catch (IOException e) {
            throw new DVDLibraryDaoException(
                    "Could not save student data.", e);
        }

        String fileAsText;
        List<DVDFile> songList = this.getAllTheDVDCollection();
        for (DVDFile currentSong : songList) {
            // turn a Student into a String
            fileAsText = marshallDvdFile(currentSong);
            // write the Student object to the file
            out.println(fileAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }
}
