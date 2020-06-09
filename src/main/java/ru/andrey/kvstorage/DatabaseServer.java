package ru.andrey.kvstorage;

import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.DatabaseCommands;
import ru.andrey.kvstorage.console.ExecutionEnvironment;

import java.util.Arrays;

public class DatabaseServer {

    private final ExecutionEnvironment env;

    public DatabaseServer(ExecutionEnvironment env) {
        this.env = env;
    }

    public static void main(String[] args) {

    }

    DatabaseCommandResult executeNextCommand(String commandText) {
        if (commandText == null) {
            return DatabaseCommandResult.error("Command should not be null");
        }

        String[] commandTextArray = commandText.split(" ");

        try {
            return DatabaseCommands
                    .valueOf(commandTextArray[0])
                    .getCommand(env, Arrays.copyOfRange(commandTextArray, 1, commandTextArray.length))
                    .execute();
        } catch (Exception e) {
            return DatabaseCommandResult.error(e.getMessage());
        }
    }
}
