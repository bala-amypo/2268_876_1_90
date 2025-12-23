public interface TicketService {
    Ticket createTicket(Long userId, Long categoryId, Ticket ticket);
    Ticket getTicket(Long id);
    List<Ticket> getAllTickets();
    List<Ticket> getTicketsByUser(Long userId);
}
