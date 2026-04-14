package spp.data.repository.implementation;


import spp.data.repository.ReportDAO;
import spp.domain.dto.ReportDTO;
import spp.data.exception.DataAccessException;
import spp.data.connection.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import spp.data.exception.ConfigurationException;


/**
 * Implementation for managing intern activity reports.
 * Supports submitting, modifying, and retrieving reports.
 */
public class ReportDAOImplementation implements ReportDAO {

    /**
     * Submits a new partial or final report.
     *
     * @param report DTO with report details.
     * @return true if insertion is successful.
     * @throws DataAccessException If foreign keys (intern, period) are invalid.
     */
    @Override
    public boolean save(ReportDTO report) throws DataAccessException {
        String query = "INSERT INTO reporte (tipo, fecha_entrega, nrc, periodo_abarca, horas_cubiertas, descripcion, id_practicante, id_periodo_escolar) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, report.getType());
            preparedStatement.setTimestamp(2, report.getDeliveryDate());
            preparedStatement.setString(3, report.getNrc());
            preparedStatement.setString(4, report.getCoveredPeriod());
            preparedStatement.setInt(5, report.getCoveredHours());
            preparedStatement.setString(6, report.getDescription());
            preparedStatement.setInt(7, report.getInternId());
            preparedStatement.setInt(8, report.getSchoolPeriodId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException | ConfigurationException e) {
            throw new DataAccessException("Error saving the report.", e);
        }
    }

    /**
     * Updates an existing report (e.g., correcting covered hours or description).
     *
     * @param report DTO with updated information and original ID.
     * @return true if updated successfully.
     * @throws DataAccessException If SQL syntax is incorrect.
     */
    @Override
    public boolean update(ReportDTO report) throws DataAccessException {
        String query = "UPDATE reporte SET tipo = ?, fecha_entrega = ?, nrc = ?, periodo_abarca = ?, horas_cubiertas = ?, descripcion = ?, id_practicante = ?, id_periodo_escolar = ? WHERE id_reporte = ?";
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, report.getType());
            preparedStatement.setTimestamp(2, report.getDeliveryDate());
            preparedStatement.setString(3, report.getNrc());
            preparedStatement.setString(4, report.getCoveredPeriod());
            preparedStatement.setInt(5, report.getCoveredHours());
            preparedStatement.setString(6, report.getDescription());
            preparedStatement.setInt(7, report.getInternId());
            preparedStatement.setInt(8, report.getSchoolPeriodId());
            preparedStatement.setInt(9, report.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException | ConfigurationException e) {
            throw new DataAccessException("Error updating the report.", e);
        }
    }

    /**
     * Removes a report from the system if uploaded by mistake.
     *
     * @param reportId The internal ID of the report.
     * @return true if deleted.
     * @throws DataAccessException If SQL execution fails.
     */
    @Override
    public boolean delete(int reportId) throws DataAccessException {
        String query = "DELETE FROM reporte WHERE id_reporte = ?";
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, reportId);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException | ConfigurationException e) {
            throw new DataAccessException("Error deleting the report.", e);
        }
    }

    /**
     * Retrieves a specific report's full details by its ID.
     *
     * @param reportId The internal ID of the report.
     * @return ReportDTO object or null if not found.
     * @throws DataAccessException If database connection fails.
     */
    @Override
    public ReportDTO getById(int reportId) throws DataAccessException {
        String query = "SELECT * FROM reporte WHERE id_reporte = ?";
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, reportId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    ReportDTO report = new ReportDTO();
                    report.setId(resultSet.getInt("id_reporte"));
                    report.setType(resultSet.getString("tipo"));
                    report.setDeliveryDate(resultSet.getTimestamp("fecha_entrega"));
                    report.setNrc(resultSet.getString("nrc"));
                    report.setCoveredPeriod(resultSet.getString("periodo_abarca"));
                    report.setCoveredHours(resultSet.getInt("horas_cubiertas"));
                    report.setDescription(resultSet.getString("descripcion"));
                    report.setInternId(resultSet.getInt("id_practicante"));
                    report.setSchoolPeriodId(resultSet.getInt("id_periodo_escolar"));
                    return report;
                }
            }
        } catch (SQLException | ConfigurationException e) {
            throw new DataAccessException("Error retrieving the report.", e);
        }
        return null;
    }

    /**
     * Retrieves all reports submitted by a specific intern.
     *
     * @param internId The internal ID of the intern.
     * @return List of ReportDTOs.
     * @throws DataAccessException If query fails.
     */
    @Override
    public List<ReportDTO> getByInternId(int internId) throws DataAccessException {
        List<ReportDTO> reports = new ArrayList<>();
        String query = "SELECT * FROM reporte WHERE id_practicante = ?";
        try (Connection connection = ConnectionPool.getInstanceConectionPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, internId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    ReportDTO report = new ReportDTO();
                    report.setId(resultSet.getInt("id_reporte"));
                    report.setType(resultSet.getString("tipo"));
                    report.setDeliveryDate(resultSet.getTimestamp("fecha_entrega"));
                    report.setNrc(resultSet.getString("nrc"));
                    report.setCoveredPeriod(resultSet.getString("periodo_abarca"));
                    report.setCoveredHours(resultSet.getInt("horas_cubiertas"));
                    report.setDescription(resultSet.getString("descripcion"));
                    report.setInternId(resultSet.getInt("id_practicante"));
                    report.setSchoolPeriodId(resultSet.getInt("id_periodo_escolar"));
                    reports.add(report);
                }
            }
        } catch (SQLException | ConfigurationException e) {
            throw new DataAccessException("Error retrieving reports by intern.", e);
        }
        return reports;
    }
}