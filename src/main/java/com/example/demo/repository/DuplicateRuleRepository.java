public interface DuplicateRuleRepository {
    Optional<DuplicateRule> findByRuleName(String name);
    List<DuplicateRule> findAll();
    DuplicateRule save(DuplicateRule rule);
}
