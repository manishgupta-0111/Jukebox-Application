package com.crio.jukebox.commands;


import java.io.IOException;
import java.util.List;
import com.crio.jukebox.services.ILoadDataService;
import com.opencsv.exceptions.CsvValidationException;

public class LoadDataCommand implements ICommand{
    private ILoadDataService loadDataService;
 
    public LoadDataCommand(ILoadDataService loadDataService){
        this.loadDataService = loadDataService;
    }

    @Override
    public void execute(List<String> tokens) throws CsvValidationException, IOException {
            if(tokens.get(0).equals("LOAD-DATA")){
               loadDataService.loadData(tokens.get(1));
               System.out.println("Songs Loaded successfully");
        }
    }
    
}
