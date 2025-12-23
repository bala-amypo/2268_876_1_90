public interface TicketCategoryRepository {
    Optional<TicketCategory> findByCategoryName(String name);
    Optional<TicketCategory> findById(Long id);
    TicketCategory save(TicketCategory category);
}
