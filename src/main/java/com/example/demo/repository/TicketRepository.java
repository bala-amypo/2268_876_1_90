public interface TicketRepository {
    Optional<Ticket> findById(Long id);
    List<Ticket> findAll();
    Ticket save(Ticket ticket);
}
