package com.tst.tstdb.controllers;

import com.ibatis.common.jdbc.ScriptRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.jdbc.JdbcTestUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


@Component
public class TestBD {

    @Value ("${initFile}")
    String initFile;
    ScriptRunner scriptRunner;
    JdbcTemplate jdbcTemplate;
    DataSource dataSource;

    @Autowired
    public TestBD(JdbcTemplate jdbcTemplate, DataSource dataSource) throws SQLException, IOException {
        this.jdbcTemplate = jdbcTemplate;
        this.dataSource = dataSource;

        Connection connection = dataSource.getConnection();
        scriptRunner = new ScriptRunner(connection, false, false);
//        scriptRunner.runScript(new FileReader("src/main/resources/schemaDB.sql"));
//        checkDefaultData("users");
//        scriptRunner.runScript(new FileReader(initFile));
//        checkDefaultData("questions");
  }

    @PostConstruct
    public void addData() {
        try {
            if (JdbcTestUtils.countRowsInTable(jdbcTemplate, "questions") < 1) {
            scriptRunner.runScript(new FileReader(initFile));}
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void checkDefaultData(String tableName) throws IOException, SQLException {
        if (JdbcTestUtils.countRowsInTable(jdbcTemplate, tableName) < 1) {
            scriptRunner.runScript(new FileReader("src/main/resources/" + tableName + ".sql"));
        }

    }
}
