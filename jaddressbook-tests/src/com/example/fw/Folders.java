package com.example.fw;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Nikita Nazarov
 * Date: 30.12.12
 */
public class Folders {

    private List<String> storedFolders = null;

    public Folders (List<String> folders) {
        this.storedFolders = new ArrayList<String> (folders);
    }

    public Folders withAdded(String folder) {
        Folders newList = new Folders(storedFolders);
        newList.storedFolders.add(folder);
        return newList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Folders folders = (Folders) o;

        if (storedFolders != null ? !storedFolders.equals(folders.storedFolders) : folders.storedFolders != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return storedFolders != null ? storedFolders.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Folders " + storedFolders;
    }
}
