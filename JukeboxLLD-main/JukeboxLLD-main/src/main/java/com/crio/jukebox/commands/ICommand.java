package com.crio.jukebox.commands;

import java.io.IOException;
import java.util.List;
import com.opencsv.exceptions.CsvValidationException;

public interface ICommand {
    void execute(List<String> tokens) throws CsvValidationException, IOException;
}
