package com.codecool.travelhelper.aws.database.repositories.search_city_repositories.jdbc;

import com.codecool.travelhelper.aws.database.repositories.search_city_repositories.WeatherRepository;
import com.codecool.travelhelper.aws.database.tables.search_city_tables.WeatherTable;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

@Component
public class WeatherRepositoryImpl implements WeatherRepository {

    private final DataSource dataSource;

    public WeatherRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void setWeatherDataByCityAndCountryName(WeatherTable weatherTable) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO weather_table (city_name, country_name, description, temperature, feels_like, pressure, humidity, wing_speed)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";
//            "insert into weather_table (city_name, country_name, description, temperature, feels_like, pressure, humidity, wing_speed) " +
//                    "values (:cityName, :countryName, :description, :temperature, :feelsLike, :pressure, :humidity, :wingSpeed)" +
//                    "on conflict (city_name, country_name) do update set " +
//                    "description=EXCLUDED.description, " +
//                    "temperature=EXCLUDED.temperature, " +
//                    "feels_like=EXCLUDED.feels_like, " +
//                    "pressure=EXCLUDED.pressure, " +
//                    "humidity=EXCLUDED.humidity, " +
//                    "wing_speed=EXCLUDED.wing_speed"

            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, weatherTable.getCityName());
            statement.setString(2, weatherTable.getCountryName());
            statement.setString(3, weatherTable.getDescription());
            statement.setInt(4, weatherTable.getTemperature());
            statement.setInt(5, weatherTable.getFeelsLike());
            statement.setInt(6, weatherTable.getPressure());
            statement.setInt(7, weatherTable.getHumidity());
            statement.setFloat(8, weatherTable.getWingSpeed());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
