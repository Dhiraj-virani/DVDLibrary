/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.controller;

import com.sg.dvdlibrary.dao.DVDLibraryDao;
import com.sg.dvdlibrary.dao.DVDLibraryDaoException;
import com.sg.dvdlibrary.dao.DVDLibraryDaoFileImpl;
import com.sg.dvdlibrary.dto.DVDFile;
import com.sg.dvdlibrary.ui.DVDLibraryView;
import com.sg.dvdlibrary.ui.UserIO;
import com.sg.dvdlibrary.ui.UserIOConsoleImpl;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Dhiraj
 */
public class DVDLibraryController {
    
    private DVDLibraryView  view;
    private DVDLibraryDao dao;
    
    public DVDLibraryController(DVDLibraryDao dao, DVDLibraryView view){
        this.dao = dao;
        this.view = view;
    }
    private UserIO io = new UserIOConsoleImpl();
    
    public void run() throws DVDLibraryDaoException{
        boolean loopThrough = true;
        int menuSelection = 0;
        try {
        while(loopThrough){
                       
            menuSelection = getUserMenuSelection();
                    
            
            switch(menuSelection){
                
                case 1:
                    addDvdSong();
                    break;
                case 2:
                    listTitles();
                    break;
                case 3:
                    searchSongTitle();
                    break;
                case 4:
                    editSong();
                    break;
                case 5:
                    viewSongInfoByTitle();
                    break;
                case 6:
                    listFullCollection();
                    break;
                case 7:
                    removeDvdSong();
                    break;
                case 8:
                    multipleEntry();
                    break;
                case 9:
                    loopThrough = false;
                    break;
                default:
                    unknownCommand();
            } 
        }
        exitMessage();
        } catch(DVDLibraryDaoException e) {
             view.displayErrorMessage(e.getMessage());

        }
    }

    private int getUserMenuSelection() {
        return view.printOptionAndGetUserOutput();
    }
    
    private void addDvdSong()throws DVDLibraryDaoException {
    view.displayAddSongBanner();
    DVDFile newDvdFile = view.getDvdFileInfo();
    dao.addDVDFile(newDvdFile.getTitle(), newDvdFile);
    view.displayCreateSuccessBanner();
    } 
    
    private void listFullCollection() throws DVDLibraryDaoException{
        view.displayDisplayAllBanner();
        List<DVDFile> dvdList = dao.getAllTheDVDCollection();
        view.displayCollectionList(dvdList);
    }
    
    private void listTitles()throws DVDLibraryDaoException{
        List<DVDFile> dvdList = dao.getAllTheDVDCollection();
        view.displayAllTitlesInCollection(dvdList);
    }
    
    private void viewSongInfoByTitle()throws DVDLibraryDaoException {
    view.displayDisplayDvdBanner();
    String title = view.getTitleChoice();
    DVDFile dvdSong = dao.songInfoByTitle(title);
    view.getSongInfo(dvdSong);   
    }
    
    private void searchSongTitle()throws DVDLibraryDaoException{
        String title = view.getTitleChoice();
        DVDFile dvdSong = dao.searchDVDSongByTitle(title);
        view.searchTitle(dvdSong);
    }
    
    private void removeDvdSong() throws DVDLibraryDaoException{
    view.displayRemoveSongBanner();
    String title = view.getTitleChoice();
    DVDFile removedSong = dao.removeDVDFile(title);
    view.displayRemoveResult(removedSong);
    }
    
    private void editSong()throws DVDLibraryDaoException{
        String title = view.getTitleChoice();
        DVDFile dvdSong = dao.searchDVDSongByTitle(title);
        view.editSearchSong(dvdSong);
        if(dvdSong != null){
            DVDFile newDvdFile = view.editAdd();
            String newTitle = newDvdFile.getTitle();
            if(title == newTitle){
            dao.addDVDFile(newTitle, newDvdFile);
            } else{
            DVDFile removedSong = dao.removeDVDFile(title);
            dao.addDVDFile(newTitle, newDvdFile);    
            }
        }
    }
    
    private void multipleEntry() throws DVDLibraryDaoException{
       boolean keepGoing = true;
        int multipleAns = view.multipleOptionSelction();
       while(keepGoing){
           if(multipleAns == 1){
              addDvdSong(); 
              int ans = io.readInt("If you like to add more files press 1 and for exit press 0?");
              if(ans == 0){
                  keepGoing = false;
                  break;
              }
           }else if(multipleAns == 2){
               editSong();
               int ans = io.readInt("If you like to edit more files press 1 and for exit press 0?");
                if(ans == 0){
                  keepGoing = false;
                  break;
              }
           }else if(multipleAns == 3){
               removeDvdSong();
               int ans = io.readInt("If you like to remove more files press 1 and for exit press 0?");
                if(ans == 0){
                  keepGoing = false;
                  break;
              }
               
           }else{
               io.print("Invalid number");
           }
           
           
       }
    }
    
    private void unknownCommand() {
    view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
    view.displayExitBanner();
    }
}
