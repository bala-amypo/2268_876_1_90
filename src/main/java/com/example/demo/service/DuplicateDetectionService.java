public interface DuplicateDetectionService {
    List<DuplicateDetectionLog> detectDuplicates(Long ticketId);
    List<DuplicateDetectionLog> getLogsForTicket(Long ticketId);
}
