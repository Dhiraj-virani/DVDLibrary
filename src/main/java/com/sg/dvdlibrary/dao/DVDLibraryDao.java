/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVDFile;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Dhiraj
 */
public interface DVDLibraryDao {

    /* Add the DVD file to the collection and associates with the title as a key
    *in the hashmap if the title is already in the file it will return that
    object else will add it to the file.
     */
    DVDFile addDVDFile(String title, DVDFile dvdFile)throws DVDLibraryDaoException;

    // List all the DVD Titles in the collection
    //List<DVDFile> getAllDVDTitles()throws DVDLibraryDaoException;

    //Search the DVD file in the collection by title
    DVDFile searchDVDSongByTitle(String title)throws DVDLibraryDaoException;

    //get all the information of one DVD song by title
    DVDFile songInfoByTitle(String title)throws DVDLibraryDaoException;
    
    
    //Get all the collection in the title
    List<DVDFile> getAllTheDVDCollection()throws DVDLibraryDaoException;

    //Remove the DVD file from collection
    DVDFile removeDVDFile(String title)throws DVDLibraryDaoException;

}
