package app.domain.repositories;

public interface IDbAdapter<Model> {
    public Model getItemById(int id) throws Exception;

    public Model[] getManyItems(int limit, int offset) throws Exception;

    public void createItem(Model model) throws Exception;

    public void deleteItem(int id) throws Exception;

    public void updateItem(Model model) throws Exception;
}
