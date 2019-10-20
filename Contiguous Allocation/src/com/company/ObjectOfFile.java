package com.company;

public class ObjectOfFile {
    String fileName = "";
    int memorySpace = 0;
    int startIndex = 0;

    public ObjectOfFile(String fileName, int memorySpace, int startIndex)
    {
        this.fileName = fileName;
        this.memorySpace = memorySpace;
        this.startIndex = startIndex;
    }
}
