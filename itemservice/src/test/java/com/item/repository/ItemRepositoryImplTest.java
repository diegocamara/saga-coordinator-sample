package com.item.repository;

import com.item.entity.Item;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class ItemRepositoryImplTest {

    @Inject
    private Repository<Item, String> repository;

    @Inject
    private EntityManager entityManager;

    @Test
    void shouldPersistOneItem() {

        Item item = new Item();
        item.setId(UUID.randomUUID().toString());
        item.setDescription("Item Description");

        repository.save(item);

        Item persistedItem = entityManager.find(Item.class, item.getId());

        assertNotNull(persistedItem);

    }

    @Test
    void shouldUpdateOneItem() {

        Item item = new Item();
        item.setId(UUID.randomUUID().toString());
        item.setDescription("Item Description");

        repository.saveOrUpdate(item);

        String description = "Super Description";

        Item item2 = new Item();
        item2.setId(item.getId());
        item2.setDescription(description);

        repository.saveOrUpdate(item2);

        Item resultItem = entityManager.find(Item.class, item.getId());

        assertEquals(description, resultItem.getDescription());

    }

    @Test
    void shouldRemoveOneItem() {

        Item item = new Item();
        item.setId(UUID.randomUUID().toString());
        item.setDescription("Item Description");

        repository.saveOrUpdate(item);

        entityManager.flush();
        entityManager.clear();

        repository.delete(item);

        entityManager.flush();

        Item resultItem = entityManager.find(Item.class, item.getId());

        assertNull(resultItem);

    }

    @Test
    void shouldReturnOneItem() {

        Item item1 = new Item();
        item1.setId(UUID.randomUUID().toString());
        item1.setDescription("Item Description 1");

        Item item2 = new Item();
        item2.setId(UUID.randomUUID().toString());
        item2.setDescription("Item Description 2");

        entityManager.persist(item1);
        entityManager.persist(item2);

        entityManager.flush();
        entityManager.clear();

        Item resultItem = repository.findById(item1.getId());

        assertNotNull(resultItem);

    }

}
