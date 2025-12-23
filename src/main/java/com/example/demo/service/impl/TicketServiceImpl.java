@Override
public Ticket createTicket(Long userId, Long categoryId, Ticket ticket) {
    User user = new User();
    user.setId(userId);   // lightweight reference, no DB hit

    ticket.setUser(user);
    ticket.setCategoryId(categoryId);
    ticket.setStatus("OPEN");

    return ticketRepository.save(ticket);
}
