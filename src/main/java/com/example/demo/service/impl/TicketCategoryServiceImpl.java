@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepo;
    private final UserRepository userRepo;
    private final TicketCategoryRepository categoryRepo;

    public TicketServiceImpl(TicketRepository t, UserRepository u, TicketCategoryRepository c) {
        this.ticketRepo = t;
        this.userRepo = u;
        this.categoryRepo = c;
    }

    public Ticket createTicket(Long userId, Long categoryId, Ticket ticket) {
        if (ticket.getDescription().length() < 10)
            throw new RuntimeException("description");

        ticket.setUser(userRepo.findById(userId).orElseThrow());
        ticket.setCategory(categoryRepo.findById(categoryId).orElseThrow());
        return ticketRepo.save(ticket);
    }

    public Ticket getTicket(Long id) {
        return ticketRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("ticket not found"));
    }

    public List<Ticket> getAllTickets() {
        return ticketRepo.findAll();
    }

    public List<Ticket> getTicketsByUser(Long userId) {
        return ticketRepo.findAll().stream()
                .filter(t -> t.getUser().getId().equals(userId))
                .toList();
    }
}
