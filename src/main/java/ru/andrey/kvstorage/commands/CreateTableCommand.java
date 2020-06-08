package ru.andrey.kvstorage.commands;

import ru.andrey.kvstorage.console.DatabaseCommand;
import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.ExecutionEnvironment;
import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

import java.util.Optional;

public class CreateTableCommand implements DatabaseCommand {

    private final ExecutionEnvironment env;
    private final String databaseName;
    private final String tableName;

    public CreateTableCommand(ExecutionEnvironment env, String databaseName, String tableName) {
        this.env = env;
        this.databaseName = databaseName;
        this.tableName = tableName;
    }

    @Override
    public DatabaseCommandResult execute() throws DatabaseException {
        Optional<Database> database = env.getDatabase(databaseName);
        if (database.isEmpty()) {
            return DatabaseCommandResult.error(String.format("Database \"%s\" doesn't exist", databaseName));
        }

        try {
            database.get().createTableIfNotExists(tableName);
        } catch (DatabaseException ex) {
            return DatabaseCommandResult.error(ex.getMessage());
        }

        return DatabaseCommandResult.success(String.format("Table \"%s\" was created successfully", tableName));
    }
}
