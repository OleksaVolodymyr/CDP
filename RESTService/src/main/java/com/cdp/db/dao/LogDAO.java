package com.cdp.db.dao;

import com.cdp.db.connection.ConnectionFactory;
import com.cdp.model.TestLog;
import com.cdp.util.Transformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LogDAO {
    private static final Logger LOG = LoggerFactory.getLogger(LogDAO.class);
    private static final String INSERT_QUERY = "INSERT INTO log (level,threadName,date,className,lineNumber," +
            "methodName,message) Values (?,?,?,?,?,?,?);";
    private static final String SELECT_QUERY = "SELECT * FROM log";

    private LogDAO() {
    }

    public static void insertLogs(TestLog log) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_QUERY)) {
            statement.setString(1, log.getLevel());
            statement.setString(2, log.getThreadName());
            statement.setString(3, log.getDate());
            statement.setString(4, log.getClassName());
            statement.setInt(5, log.getLineNumber());
            statement.setString(6, log.getMethodName());
            statement.setString(7, log.getMessage());
            int countUpdates = statement.executeUpdate();
            LOG.info("Insert {} records in log table", countUpdates);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        }
    }

    public static List<TestLog> getLogs() {
        List<TestLog> logs = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet set = statement.executeQuery(SELECT_QUERY)) {
            while (set.next()) {
                logs.add(Transformer.toInstance(set, TestLog.class));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        }
        return logs;
    }

}
