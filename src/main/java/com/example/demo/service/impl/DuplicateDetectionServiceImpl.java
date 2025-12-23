@Service
public class DuplicateDetectionServiceImpl implements DuplicateDetectionService {

    private final TicketRepository ticketRepo;
    private final DuplicateRuleRepository ruleRepo;
    private final DuplicateDetectionLogRepository logRepo;

    public DuplicateDetectionServiceImpl(
            TicketRepository t,
            DuplicateRuleRepository r,
            DuplicateDetectionLogRepository l) {
        this.ticketRepo = t;
        this.ruleRepo = r;
        this.logRepo = l;
    }

    public List<DuplicateDetectionLog> detectDuplicates(Long ticketId) {
        Ticket base = ticketRepo.findById(ticketId).orElseThrow();
        List<DuplicateDetectionLog> logs = new ArrayList<>();

        for (DuplicateRule rule : ruleRepo.findAll()) {
            for (Ticket other : ticketRepo.findAll()) {
                if (other.getId().equals(base.getId())) continue;

                double score = 0.0;
                if ("EXACT_MATCH".equals(rule.getMatchType())) {
                    score = base.getDescription()
                            .equalsIgnoreCase(other.getDescription()) ? 1.0 : 0.0;
                } else if ("SIMILARITY".equals(rule.getMatchType())) {
                    score = TextSimilarityUtil.similarity(
                            base.getDescription(), other.getDescription());
                }

                if (score >= rule.getThreshold()) {
                    DuplicateDetectionLog log = new DuplicateDetectionLog();
                    log.setTicket(base);
                    log.setDuplicateTicket(other);
                    log.setMatchScore(score);
                    logs.add(logRepo.save(log));
                }
            }
        }
        return logs;
    }

    public List<DuplicateDetectionLog> getLogsForTicket(Long ticketId) {
        return logRepo.findAll().stream()
                .filter(l -> l.getTicket().getId().equals(ticketId))
                .toList();
    }
}
