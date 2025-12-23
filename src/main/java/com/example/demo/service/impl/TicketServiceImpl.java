@Service
public class TicketCategoryServiceImpl implements TicketCategoryService {

    private final TicketCategoryRepository repo;

    public TicketCategoryServiceImpl(TicketCategoryRepository repo) {
        this.repo = repo;
    }

    public TicketCategory createCategory(TicketCategory category) {
        if (repo.findByCategoryName(category.getCategoryName()).isPresent())
            throw new RuntimeException("category exists");
        return repo.save(category);
    }

    public TicketCategory getCategory(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
    }
}
