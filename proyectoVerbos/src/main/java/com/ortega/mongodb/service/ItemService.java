package com.ortega.mongodb.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ortega.mongodb.model.GroceryItem;

@Service
public class ItemService {

    private List<GroceryItem> groceryItems;

    boolean started=false;
    public void setGroceryItems(){
        if (this.started==false){
            this.groceryItems = new ArrayList();
            groceryItems.add(new GroceryItem("Whole", "Whole Wheat Biscuit", 5, "snacks"));
            groceryItems.add(new GroceryItem("Dried", "Dried Whole Red Chilli", 2, "spices"));
            groceryItems.add(new GroceryItem("Pearl", "Healthy Pearl Millet", 1, "millets"));
            groceryItems.add(new GroceryItem("Cheese", "Bonny Cheese Crackers Plain", 6, "snacks"));
            this.started=true;
        }
    }

    public String getAll(){
        setGroceryItems();
        return "---ITEMS EXISTENTES---\n"+groceryItems.toString();
    }

    public String insert(GroceryItem groceryItem){
        setGroceryItems();
        groceryItems.add(new GroceryItem(groceryItem.getId(), groceryItem.getName(), groceryItem.getQuantity(), groceryItem.getCategory()));
        return "---ITEM INSERTADO---\n"+groceryItem.toString();
    }

    public String update(GroceryItem groceryItem){
        setGroceryItems();
        Iterator<GroceryItem> iterator = groceryItems.iterator();
        while (iterator.hasNext()) {
            GroceryItem item = iterator.next();
            if (item.getId().equals(groceryItem.getId())) {
                item.setName(groceryItem.getName());
                item.setQuantity(groceryItem.getQuantity());
                item.setCategory(groceryItem.getCategory());
                return "---ITEM ACTUALIZADO---\n"+item.toString();
            }
        }
        return "---ITEM NO ENCONTRADO---";
    }

    public String delete(String id) {
        setGroceryItems();
        Iterator<GroceryItem> iterator = groceryItems.iterator();
        while (iterator.hasNext()) {
            GroceryItem item = iterator.next();
            if (item.getId().equals(id)) {
                iterator.remove();
                return "---ITEM ELIMINADO---";
            }
        }
        return "---ITEM NO ENCONTRADO---";
    }

    public String updateData(String id, GroceryItem groceryItem){
        setGroceryItems();
        Iterator<GroceryItem> iterator = groceryItems.iterator();
        while(iterator.hasNext()) {
            GroceryItem item = iterator.next();
            if (item.getId().equals(id)) {
                if (groceryItem.getName() != null) {
                    item.setName(groceryItem.getName());
                }
                if (groceryItem.getCategory() != null) {
                    item.setCategory(groceryItem.getCategory());
                }
                return "---ITEM ACTUALIZADO---\n"+item.toString();
            }
        }
        return "---ITEM NO ENCONTRADO---";
    }

    public String optionsUpdate(){
        return "OPTIONS: It insert a new grosery item, if the grosery item doesn't exist, it will create automatically";
    }

}