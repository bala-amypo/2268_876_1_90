public interface DuplicateDetectionLogRepository {
    DuplicateDetectionLog save(DuplicateDetectionLog log);
    List<DuplicateDetectionLog> findAll();
}
