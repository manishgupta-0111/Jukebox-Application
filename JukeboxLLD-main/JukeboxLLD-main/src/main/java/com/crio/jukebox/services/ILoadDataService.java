package com.crio.jukebox.services;

import java.io.IOException;
import com.opencsv.exceptions.CsvValidationException;

public interface ILoadDataService {
    public void loadData(String path) throws CsvValidationException, IOException;
}
