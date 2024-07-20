package com.crio.jukebox.commands;

import java.io.IOException;
import java.util.List;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.services.IUserService;
import com.opencsv.exceptions.CsvValidationException;

public class CreateUserCommand implements ICommand{
    private IUserService userService;

    public CreateUserCommand(IUserService userService){
        this.userService = userService;
    }

    @Override
    public void execute(List<String> tokens) throws CsvValidationException, IOException {
        if(tokens.get(0).equals("CREATE-USER")){
            User user = userService.create(tokens.get(1));
            System.out.println(user.getId() + " " + user.getName());
        }
    }
}
