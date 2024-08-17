package PRJ321x_ASM3_datptFX38455.funix.edu.vn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RestController
public class DatabaseController {


    @Autowired
    private DataSource dataSource;

    @GetMapping("/test-db-connection")
    @ResponseBody
    public String testDbConnection() {
        try {
            Connection connection = dataSource.getConnection();
            return "Database connection successful!";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Database connection failed!";
        }
    }


}
