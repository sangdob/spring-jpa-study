package jpa.shop.service;

import jpa.shop.domain.item.Item;
import jpa.shop.repository.ItemRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Getter
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Transactional
    public Item updateItem(Long itemId, Item itemParam) {
        Item findItem = itemRepository.findByItem(itemId);
        findItem.setPrice(itemParam.getPrice());
        findItem.setName(itemParam.getName());
        findItem.setStockQuantity(itemParam.getStockQuantity());
        return findItem;
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findByItem(Long id) {
        return itemRepository.findByItem(id);
    }
}
