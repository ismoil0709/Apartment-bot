package uz.pdp.telegram_bot.apartmentBot.bot.util;

import org.telegram.telegrambots.meta.api.objects.Update;

import java.sql.*;

/*
this class has nothing to do with the bot
 */
public class DbConnectForTest {
    public static Statement connect() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/Telegram-bot";
        String username = "postgres";
        String password = "ismoil_0709";
        Connection connection = DriverManager.getConnection(url, username, password);
        return connection.createStatement();
    }

    public static void putForTest(Update update) {
        if (check(update)) {
            String query = "INSERT INTO users (id, name) VALUES ('"
                    + update.getMessage().getFrom().getId() + "','" + update.getMessage().getFrom().getFirstName() + "')";
            try {
                Statement connect = connect();
                boolean execute = connect.execute(query);
                if (!execute) System.out.println("Added");
                else System.out.println("Failed");
                connect.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static boolean check(Update update) {
        String query = "select id from users";
        try {
            Statement connect = connect();
            ResultSet resultSet = connect.executeQuery(query);
            while (resultSet.next()) {
                if (resultSet.getString("id") != null) {
                    return false;
                }
            }
            connect.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
