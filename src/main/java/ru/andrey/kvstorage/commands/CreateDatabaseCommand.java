package ru.andrey.kvstorage.commands;

import ru.andrey.kvstorage.console.DatabaseCommand;
import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.ExecutionEnvironment;
import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.SampleDatabase;

public class CreateDatabaseCommand implements DatabaseCommand {

    private ExecutionEnvironment env;
    private String databaseName;

    public CreateDatabaseCommand(ExecutionEnvironment env, String databaseName) {
        this.env = env;
        this.databaseName = databaseName;
    }

    @Override
    public DatabaseCommandResult execute() throws DatabaseException {
        if (env.getDatabase(databaseName).isPresent()) {
            return DatabaseCommandResult.error(String.format("Database \"%s\" is already exists", databaseName));
        }

        env.addDatabase(new SampleDatabase(databaseName));

        return DatabaseCommandResult.success(String.format("Database \"%s\" successfully created", databaseName));
    }
}
