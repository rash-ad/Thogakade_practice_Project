package repository.custom;

import model.Item;

import java.sql.SQLException;
import java.util.List;

public interface ItemRepository {

    boolean create(Item item) throws SQLException;

    boolean update(Item item);

    boolean deleteById(String s);

    Item getById(String code) throws SQLException;

    List<Item> getAll() throws SQLException;

    List<String> getItemCodes() throws SQLException;
}
