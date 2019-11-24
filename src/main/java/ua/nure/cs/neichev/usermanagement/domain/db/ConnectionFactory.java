package ua.nure.cs.neichev.usermanagement.domain.db;

import java.sql.Connection;

public interface ConnectionFactory {
	Connection createConnection() throws DatabaseException;
}
