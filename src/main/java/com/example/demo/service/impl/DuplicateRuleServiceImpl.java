@Service
public class DuplicateRuleServiceImpl implements DuplicateRuleService {

    private final DuplicateRuleRepository repo;

    public DuplicateRuleServiceImpl(DuplicateRuleRepository repo) {
        this.repo = repo;
    }

    public DuplicateRule createRule(DuplicateRule rule) {
        if (repo.findByRuleName(rule.getRuleName()).isPresent())
            throw new RuntimeException("exists");
        return repo.save(rule);
    }
}
