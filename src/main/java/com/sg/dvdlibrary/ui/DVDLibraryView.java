/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.ui;

import com.sg.dvdlibrary.dto.DVDFile;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Dhiraj
 */
public class DVDLibraryView {

    private UserIO io;

    public DVDLibraryView(UserIO io) {
        this.io = io;
    }

    // Printing the option on the Console to select
    public int printOptionAndGetUserOutput() {
        io.print("Welcome to the DVD Library!");
        io.print("1. Add A New DVD File");
        io.print("2. List all the DVD files titles in the collection");
        io.print("3. Search DVD file Title exist in the collection.");
        io.print("4. Edit Information of A DVD File");
        io.print("5. Get all the Information of Specific DVD Song by title");
        io.print("6. Get Full Collection Information");
        io.print("7. Remove DVD file");
        io.print("8. Like to add, edit or remove multiple file from collection");
        io.print("9. Exit");

        return io.readInt("Please select from the above choices.", 1, 9);
    }

    public DVDFile getDvdFileInfo() {
        String title = io.readString("Please enter title of the DVD");
        String releaseDate = io.readString("Please enter Release Date(mm/dd/yyyy)");
        float rating = io.readFloat("Please enter the rating of the file", 1, 5);
        String directorName = io.readString("Please enter Director Full Name");
        String studioName = io.readString("Please enter Studio Name");
        String userReview = io.readString("Please enter review for the song");

        DVDFile currentSong = new DVDFile(title);
        currentSong.setReleaseDate(releaseDate);
        currentSong.setRating(rating);
        currentSong.setDirectorName(directorName);
        currentSong.setStudioName(studioName);
        currentSong.setUserReview(userReview);

        return currentSong;
    }

    public void displayCollectionList(List<DVDFile> collectionList) {
        for (DVDFile currentFile : collectionList) {
            String dvdInfo = String.format("#%s :: %s :: %f :: %s :: %s :: %s",
                    currentFile.getTitle(),
                    currentFile.getReleaseDate(),
                    currentFile.getRating(),
                    currentFile.getDirectorName(),
                    currentFile.getStudioName(),
                    currentFile.getUserReview());

            io.print(dvdInfo);
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayAllTitlesInCollection(List<DVDFile> titles) {
        io.print("The Song titles in the DVD collection are as follows: ");
        for (DVDFile currentFile : titles) {
            String currentTitles = String.format("%s", currentFile.getTitle());
            io.print(currentTitles + " ");
        }
        io.readString("Please hit enter to continue.");

    }

    public void getSongInfo(DVDFile song) {
        if (song != null) {
            io.print(song.getTitle() + " released on " + song.getReleaseDate()
                    + " by " + song.getDirectorName() + " from studio "
                    + song.getStudioName());
            io.print("");
        } else {
            io.print("No such song in the collection.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void searchTitle(DVDFile song) {
        if (song != null) {
            io.print("Song exist in the collection");
        } else {
            io.print("No such song in the collection.");
        }
        io.readString("Please hit enter to continue.");

    }

    public void displayRemoveResult(DVDFile songRecord) {
        if (songRecord != null) {
            io.print("Song successfully removed.");
        } else {
            io.print("No such song in the collection.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void editSearchSong(DVDFile song) {
        if (song != null) {
            io.print("You are gonna update this song.");
            io.print(song.getTitle() + " released on " + song.getReleaseDate()
                    + " by " + song.getDirectorName() + " from studio "
                    + song.getStudioName());

        } else {
            io.print("No such song in the collection.");
        }
    }

    public DVDFile editAdd() {
        String title = io.readString("Please re enter title of the DVD");
        String releaseDate = io.readString("Please re enter Release Date(mm/dd/yyyy)");
        float rating = io.readFloat("Please re enter the rating of the file");
        String directorName = io.readString("Please re enter Director Full Name");
        String studioName = io.readString("Please re enter Studio Name");
        String userReview = io.readString("Please re enter review for the song");

        DVDFile currentSong = new DVDFile(title);
        currentSong.setReleaseDate(releaseDate);
        currentSong.setRating(rating);
        currentSong.setDirectorName(directorName);
        currentSong.setStudioName(studioName);
        currentSong.setUserReview(userReview);

        return currentSong;
    }
    
    public int multipleOptionSelction(){
       io.print("What would you like to do?");
        io.print("1. Add Multiple New DVD File");
        io.print("2. Edit Mutiple DVD File");
        io.print("3. Remove Multiple DVD File");
        
       return io.readInt("Please select from the above choices.", 1, 3);
    }

    public void displayAddSongBanner() {
        io.print("=== Add Song ===");
    }

    public void displayCreateSuccessBanner() {
        io.readString(
                "DVD file successfully added.  Please hit enter to continue");
    }

    public void displayDisplayAllBanner() {
        io.print("=== Display Full Collection ===");
    }

    public void displayDisplayDvdBanner() {
        io.print("=== Display DVD Song ===");
    }

    public String getTitleChoice() {
        return io.readString("Please enter the DVD Title.");
    }

    public void displayRemoveSongBanner() {
        io.print("=== Remove song ===");
    }

    public void displaySongTitles() {
        io.print("=== All songs in the Collection ===");
    }

    public void displayExitBanner() {
        io.print("Good Bye Thank you for checking library!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

}
